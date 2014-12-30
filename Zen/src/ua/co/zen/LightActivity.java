package ua.co.zen;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LightActivity extends Activity {

	private ImageButton toiletLight;
	private Button infoButton;
	String LED;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		
		infoButton = (Button) findViewById(R.id.infoButton);
	
		toiletLight = (ImageButton) findViewById(R.id.toiletLight);
		LED = MainActivity.LED;
		
		if (LED.equals("checked")){
		toiletLight.setImageResource(R.drawable.zen_light_1);
		} else {
			toiletLight.setImageResource(R.drawable.zen_light_black_5);
		}
		
		
		toiletLight.setOnClickListener(new View.OnClickListener() {	 
			@Override
            public void onClick(View v) {
            if (LED.equals("checked")){
            	toiletLight.setImageResource(R.drawable.zen_light_black_5);
               String addurl = "?LED1=0";
               new MainActivity.sendGet().execute("http://192.168.1.33" + addurl);
                infoButton.setText("Свет в спальне выключен");  
            } else {
            	toiletLight.setImageResource(R.drawable.zen_light_1);
            	String addurl = "?LED1=1";
            	new MainActivity.sendGet().execute("http://192.168.1.33" + addurl);
               infoButton.setText("Свет в спальне включен");
            }
            }
        });
         
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.light, menu);
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
}
