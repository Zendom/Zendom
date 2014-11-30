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
	 private ImageButton headerButton; //������� ������
	 private Button infoButton;        //����� ������ - ������ ����������
	 private ImageButton voiceButton;  //������ ���������� �������
	 private ImageButton tempButton;   //������ ������ ����������
	 private ImageButton waterButton;   //������ ������ ��������
	 private ImageButton electButton;   //������ ������ �������������
	 private ImageButton multiButton;   //������ ������ �����������
	 private ImageButton cameraButton;   //������ ������ ������
	 private ImageButton doorButton;   //������ ������ ������ � ����
	 private ImageButton lightButton;   //������ ������ ������ � ����
	 private ArrayList<String> textVoice; //������ ����������� Google'�� ����
	 private String bedroomTemp = "15�", kitchenTemp = "25�", toiletTemp = "35�", streetTemp = "-25�";
	
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
               infoButton.setText("����� ���");
            }
        });
         
         voiceButton = (ImageButton) findViewById(R.id.voiceButton);
         //������� ����� �� ����� �� ������. ���������� ��������� � ������ �����
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
	                            "��� ����� �� ������������ ������������� ������",
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
	                            "������ � �������� ����������",
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
	                            "������ � WaterActivity",
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
	                            "������ � ElectricActivity",
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
	                            "������ � WaterActivity",
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
	                            "������ � CameraActivity",
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
	                            "������ � CameraActivity",
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
	                            "������ � LightActivity",
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
    
    //����� ��� ������������� ������ 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
            textVoice = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //����� ��, ��� ���������� � ����� ������
            infoButton.setText(textVoice.get(0));
            }
            break;
        }
        }
    }
    
}

