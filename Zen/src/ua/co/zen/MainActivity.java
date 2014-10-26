package ua.co.zen;




import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
	 private ImageButton headerButton;
	 private Button infoButton;
	 private ImageButton voiceButton;
	 private ArrayList<String> textVoice; //������ ����������� Google'�� ����
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        infoButton = (Button) findViewById(R.id.infoButton);
        
        
       
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
