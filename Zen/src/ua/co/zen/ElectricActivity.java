package ua.co.zen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ElectricActivity extends Activity {

	private TextView powerText;
	private TextView currentText;
	private TextView voltText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_electric);
		
		Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
		
	 	powerText = (TextView) findViewById(R.id.powerText);
        powerText.setTypeface(tp);
        
        currentText = (TextView) findViewById(R.id.currentText);
        currentText.setTypeface(tp);
        
        voltText = (TextView) findViewById(R.id.voltText);
        voltText.setTypeface(tp);
        
        Intent intent = getIntent();
		String current = intent.getStringExtra("current");
		currentText.setText(current+"A");
		
		float cur = Float.parseFloat(current);
		float pow = (226 * cur) / 1000;
        powerText.setText(pow+" êÂò");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.electric, menu);
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
