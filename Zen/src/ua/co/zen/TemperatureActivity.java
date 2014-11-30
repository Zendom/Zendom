package ua.co.zen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class TemperatureActivity extends Activity {
	private TextView bedroomTemp;
	private TextView kitchenTemp;
	private TextView toiletTemp;
	private TextView streetTemp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature);
		Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
		
		 	bedroomTemp = (TextView) findViewById(R.id.bedroomTemp);
	        bedroomTemp.setTypeface(tp);
	        
	        kitchenTemp = (TextView) findViewById(R.id.kitchenTemp);
	        kitchenTemp.setTypeface(tp);
	        
	        toiletTemp = (TextView) findViewById(R.id.toiletTemp);
	        toiletTemp.setTypeface(tp);
	        
	        streetTemp = (TextView) findViewById(R.id.streetTemp);
	        streetTemp.setTypeface(tp);
	        
	        Intent intent = getIntent();
			String bedroomTempValue = intent.getStringExtra("bedroomTemp");
			String kitchenTempValue = intent.getStringExtra("kitchenTemp");
			String toiletTempValue = intent.getStringExtra("toiletTemp");
			String streetTempValue = intent.getStringExtra("streetTemp");
			
			bedroomTemp.setText(bedroomTempValue);
			kitchenTemp.setText(kitchenTempValue);
			toiletTemp.setText(toiletTempValue);
			streetTemp.setText(streetTempValue);
			
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.temperature, menu);
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
