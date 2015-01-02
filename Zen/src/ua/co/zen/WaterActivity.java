package ua.co.zen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class WaterActivity extends Activity {
	private ImageView toiletWater, bathWater, washWater, kitchWater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_water);

		toiletWater = (ImageView) findViewById(R.id.toiletWater);
		bathWater = (ImageView) findViewById(R.id.bathroomWater);
		washWater = (ImageView) findViewById(R.id.umivWater);
		kitchWater = (ImageView) findViewById(R.id.moikaWater);
		
		Intent intent = getIntent();
		String toil_water = intent.getStringExtra("toil_water");
		String bath_water = intent.getStringExtra("bath_water");
		String wash_water = intent.getStringExtra("wash_water");
		String kitch_water = intent.getStringExtra("kitch_water");
		
		
		if (toil_water.equals("off")){
			toiletWater.setImageResource(R.drawable.zen_water_1);
		} else {
			toiletWater.setImageResource(R.drawable.zen_water_red_5);
		}
		
		if (bath_water.equals("off")){
			bathWater.setImageResource(R.drawable.zen_water_2);
		} else {
			bathWater.setImageResource(R.drawable.zen_water_red_6);
		}
		
		if (wash_water.equals("off")){
			washWater.setImageResource(R.drawable.zen_water_3);
		} else {
			washWater.setImageResource(R.drawable.zen_water_red_7);
		}
		
		if (kitch_water.equals("off")){
			kitchWater.setImageResource(R.drawable.zen_water_4);
		} else {
			kitchWater.setImageResource(R.drawable.zen_water_red_8);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.water, menu);
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
