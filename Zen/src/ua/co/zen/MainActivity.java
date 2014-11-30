package ua.co.zen;




import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {
     
	protected static final int RESULT_SPEECH = 1; 
	 private ImageButton headerButton; //Красная кнопка
	 private Button infoButton;        //Белая кнопка - строка информации
	 private ImageButton voiceButton;  //Кнопка управления голосом
	 private ImageButton tempButton;   //Кнопка экрана температур
	 private ImageButton waterButton;   //Кнопка экрана протечек
	 private ImageButton electButton;   //Кнопка экрана электричества
	 private ImageButton multiButton;   //Кнопка экрана мультимедия
	 private ImageButton cameraButton;   //Кнопка экрана камеры
	 private ImageButton doorButton;   //Кнопка экрана дверей и окон
	 private ImageButton lightButton;   //Кнопка экрана дверей и окон
	 private ArrayList<String> textVoice; //Массив распознаных Google'ом фраз
	 private String bedroomTemp = "15°", kitchenTemp = "25°", toiletTemp = "35°", streetTemp = "-25°";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
        infoButton = (Button) findViewById(R.id.infoButton);
        Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
        infoButton.setTypeface(tp);

        
        headerButton = (ImageButton) findViewById(R.id.headerButton);
        headerButton.setOnClickListener(new View.OnClickListener() {
	    	 
            @Override
            public void onClick(View v) {
               infoButton.setText("Умный дом");
            }
        });
         
         voiceButton = (ImageButton) findViewById(R.id.voiceButton);
         //Слушаем голос по клику на кнопку. Возвращаем результат в массив строк
	     voiceButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(
	                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	 
	                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ru-RU");
	 
	                try {
	                    startActivityForResult(intent, RESULT_SPEECH);
	                    infoButton.setText(" ");
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Это говно не поддерживает распознование голоса",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      tempButton = (ImageButton) findViewById(R.id.tempButton);
	      tempButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, TemperatureActivity.class);
	                intent.putExtra("bedroomTemp", bedroomTemp);
	                intent.putExtra("kitchenTemp", kitchenTemp);
	                intent.putExtra("toiletTemp", toiletTemp);
	                intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка в передаче температур",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      waterButton = (ImageButton) findViewById(R.id.waterButton);
	      waterButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, WaterActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с WaterActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      electButton = (ImageButton) findViewById(R.id.electricButton);
	      electButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, ElectricActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с ElectricActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      multiButton = (ImageButton) findViewById(R.id.multiButton);
	      multiButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, MultimediaActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с WaterActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      cameraButton = (ImageButton) findViewById(R.id.cameraButton);
	      cameraButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с CameraActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      doorButton = (ImageButton) findViewById(R.id.doorButton);
	      doorButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, DoorActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с CameraActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	      
	      lightButton = (ImageButton) findViewById(R.id.lightButton);
	      lightButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(MainActivity.this, LightActivity.class);
	                // intent.putExtra("bedroomTemp", bedroomTemp);
	                // intent.putExtra("kitchenTemp", kitchenTemp);
	                // intent.putExtra("toiletTemp", toiletTemp);
	                // intent.putExtra("streetTemp", streetTemp);
	                
	                try {
	                    startActivity(intent);
	                    
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Ошибка с LightActivity",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    //Метод для распознования голоса 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
            textVoice = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //Пишем то, что распознали в текст кнопки
            infoButton.setText(textVoice.get(0));
            }
            break;
        }
        }
    }
    
}

