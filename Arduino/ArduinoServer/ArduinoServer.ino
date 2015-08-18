
#include <SPI.h>
#include <Ethernet.h>
#include <SD.h>
#include <DS1302.h>
#include <stdio.h>

// size of buffer used to capture HTTP requests
#define REQ_BUF_SZ   60

const int port_h1 = 2; //снимаем состояние датчик холла
const int port_h2 = 3; //питание датчика холла
uint8_t state = 0; //состояние датчика

char buf[50]; //буфер времени

// MAC address from Ethernet shield sticker under board
byte mac[] = {
  0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED
};
IPAddress ip(192, 168, 1, 33); // IP address, may need to change depending on network
EthernetServer server(80);  // create a server at port 80
char HTTP_req[REQ_BUF_SZ] = {
  0
}; // buffered HTTP request stored as null terminated string
char req_index = 0;              // index into HTTP_req buffer
boolean LED_state[4] = {
  0
}; // stores the states of the LEDs

//Real time clock
namespace {
  const int kCePin   = 5;  // Chip Enable
  const int kIoPin   = 6;  // Input/Output
  const int kSclkPin = 7;  // Serial Clock
// Create a DS1302 object.
DS1302 rtc(kCePin, kIoPin, kSclkPin);

void printTime() {
  // Get the current time and date from the chip.
  Time t = rtc.time();
  // Format the time and date and insert into the temporary buffer.
  
  snprintf(buf, sizeof(buf), "%04d-%02d-%02d %02d:%02d:%02d", t.yr, t.mon, t.date,
           t.hr, t.min, t.sec);
 }
}  // namespace of real time clock

void setup()
{
  // disable Ethernet chip
  pinMode(10, OUTPUT);
  digitalWrite(10, HIGH);

  // Light
  pinMode(14, OUTPUT);
  pinMode(15, OUTPUT);
  pinMode(16, OUTPUT);
  pinMode(17, OUTPUT);
  
  //Holle sensor door
  pinMode(port_h1, INPUT);
  pinMode(port_h2, OUTPUT);
  
  //Real time Clock
  rtc.writeProtect(false);
  rtc.halt(false);

  Serial.begin(9600);       // for debugging

  Ethernet.begin(mac, ip);  // initialize Ethernet device
  server.begin();           // start to listen for clients
}

void loop()
{
  EthernetClient client = server.available();  // try to get client

  if (client) {  // got client?
    boolean currentLineIsBlank = true;
    while (client.connected()) {
      if (client.available()) {   // client data available to read
        char c = client.read(); // read 1 byte (character) from client
        // limit the size of the stored received HTTP request
        // buffer first part of HTTP request in HTTP_req array (string)
        // leave last element in array as 0 to null terminate string (REQ_BUF_SZ - 1)
        if (req_index < (REQ_BUF_SZ - 1)) {
          HTTP_req[req_index] = c;          // save HTTP request character
          req_index++;
        }
        // last line of client request is blank and ends with \n
        // respond to client only after last line received
        if (c == '\n' && currentLineIsBlank) {
          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
          // remainder of header follows below, depending on if
          // web page or XML page is requested
          // Ajax request - send XML file

          // send rest of HTTP header
          client.println("Content-Type: text/xml");
          client.println("Connection: keep-alive");
          client.println();
          //место собственных функций
          SetLights();
          //GetHolleSensor();
          
          // send XML file containing input states
          XML_response(client);
          // display received HTTP request on serial port
          Serial.print(HTTP_req);
          // reset buffer index and all buffer elements to 0
          req_index = 0;
          StrClear(HTTP_req, REQ_BUF_SZ);
          break;
        }
        // every line of text received from the client ends with \r\n
        if (c == '\n') {
          // last character on line of received text
          // starting new line with next character read
          currentLineIsBlank = true;
        }
        else if (c != '\r') {
          // a text character was received from client
          currentLineIsBlank = false;
        }
      } // end if (client.available())
    } // end while (client.connected())
    delay(1);      // give the web browser time to receive the data
    client.stop(); // close the connection
  } // end if (client)
}

void GetHolleSensor (void) {
  digitalWrite (port_h2, HIGH); //включаем питание
  state = digitalRead(port_h1); //опрашиваем датчик
  digitalWrite (port_h2, LOW);//сбрасываем значение датчика в 0
  if (state == 1) {
    printTime();
  }
}

// checks if received HTTP request is switching on/off LEDs
// also saves the state of the LEDs
void SetLights (void) {
  // LED 1 (pin 6)
  if (StrContains(HTTP_req, "toil_light=1")) {
    LED_state[0] = 1;  // save LED state
    digitalWrite(14, HIGH);
  }
  else if (StrContains(HTTP_req, "toil_light=0")) {
    LED_state[0] = 0;  // save LED state
    digitalWrite(14, LOW);
  }
  // LED 2 (pin 7)
  if (StrContains(HTTP_req, "hall_light=1")) {
    LED_state[1] = 1;  // save LED state
    digitalWrite(15, HIGH);
  }
  else if (StrContains(HTTP_req, "hall_light=0")) {
    LED_state[1] = 0;  // save LED state
    digitalWrite(15, LOW);
  }
  // LED 3 (pin 8)
  if (StrContains(HTTP_req, "bed_light=1")) {
    LED_state[2] = 1;  // save LED state
    digitalWrite(16, HIGH);
  }
  else if (StrContains(HTTP_req, "bed_light=0")) {
    LED_state[2] = 0;  // save LED state
    digitalWrite(16, LOW);
  }
  // LED 4 (pin 9)
  if (StrContains(HTTP_req, "kitch_light=1")) {
    LED_state[3] = 1;  // save LED state
    digitalWrite(17, HIGH);
  }
  else if (StrContains(HTTP_req, "kitch_light=0")) {
    LED_state[3] = 0;  // save LED state
    digitalWrite(17, LOW);
  }
}

// send the XML file with analog values, switch status
//  and LED status
void XML_response(EthernetClient cl)
{
  int analog_val;            // stores value read from analog inputs

  cl.print("<?xml version = \"1.0\" ?>");
  cl.print("<inputs>");
  // read analog inputs
  //cl.print("<analog>");
  //cl.print(analog_val);
  //cl.println("</analog>");
  //значение силы тока
  cl.print("<current>");
  cl.print("15");
  cl.println("</current>");
  //температура
  cl.print("<bed_temp>");
  cl.print("25");
  cl.println("</bed_temp>");
  cl.print("<kitch_temp>");
  cl.print("22");
  cl.println("</kitch_temp>");
  cl.print("<toil_temp>");
  cl.print("26");
  cl.println("</toil_temp>");
  cl.print("<street_temp>");
  cl.print("-10");
  cl.println("</street_temp>");
  //протечки
  cl.print("<toil_water>");
  cl.print("off");
  cl.println("</toil_water>");
  cl.print("<bath_water>");
  cl.print("off");
  cl.println("</bath_water>");
  cl.print("<wash_water>");
  cl.print("off");
  cl.println("</wash_water>");
  cl.print("<kitch_water>");
  cl.print("off");
  cl.println("</kitch_water>");
  //Двери
  cl.print("<main_door>");
  cl.print(buf); // читаем время из буфера 
  cl.println("</main_door>");
  cl.print("<bed_wind>");
  cl.print("off");
  cl.println("</bed_wind>");
  cl.print("<kab_wind>");
  cl.print("off");
  cl.println("</kab_wind>");
  cl.print("<safe_wind>");
  cl.print("off");
  cl.println("</safe_wind>");
  // Свет
  cl.print("<toil_light>");
  if (LED_state[0]) {
    cl.print("on");
  }
  else {
    cl.print("off");
  }
  cl.println("</toil_light>");

  cl.print("<hall_light>");
  if (LED_state[1]) {
    cl.print("on");
  }
  else {
    cl.print("off");
  }
  cl.println("</hall_light>");

  cl.print("<bed_light>");
  if (LED_state[2]) {
    cl.print("on");
  }
  else {
    cl.print("off");
  }
  cl.println("</bed_light>");

  cl.print("<kitch_light>");
  if (LED_state[3]) {
    cl.print("on");
  }
  else {
    cl.print("off");
  }
  cl.println("</kitch_light>");
  cl.print("</inputs>");
}

// sets every element of str to 0 (clears array)
void StrClear(char *str, char length)
{
  for (int i = 0; i < length; i++) {
    str[i] = 0;
  }
}

// searches for the string sfind in the string str
// returns 1 if string found
// returns 0 if string not found
char StrContains(char *str, char *sfind) {
  char found = 0;
  char index = 0;
  char len;
  len = strlen(str);
  if (strlen(sfind) > len) {
    return 0;
  }
  while (index < len) {
    if (str[index] == sfind[found]) {
      found++;
      if (strlen(sfind) == found) {
        return 1;
      }
    } else {
      found = 0;
    }
    index++;
  }
  return 0;
}

