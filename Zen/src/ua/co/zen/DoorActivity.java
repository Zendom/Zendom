package ua.co.zen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class DoorActivity extends Activity {
	
	private ImageView enterDoor, windsleepDoor, windkabDoor, kladowDoor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_door);
		
		enterDoor = (ImageView) findViewById(R.id.enterDoor);
		windsleepDoor = (ImageView) findViewById(R.id.windsleepDoor);
		windkabDoor = (ImageView) findViewById(R.id.windkabDoor);
		kladowDoor = (ImageView) findViewById(R.id.kladowDoor);
		
		
		Intent intent = getIntent();
		String main_door = intent.getStringExtra("main_door");
		String bed_wind = intent.getStringExtra("bed_wind");
		String kab_wind = intent.getStringExtra("kab_wind");
		String safe_wind = intent.getStringExtra("safe_wind");
		
		
		if (main_door.equals("off")){
			enterDoor.setImageResource(R.drawable.zen_door_green_1);
		} else {
			enterDoor.setImageResource(R.drawable.zen_door_red_5);
		}
		
		if (bed_wind.equals("off")){
			windsleepDoor.setImageResource(R.drawable.zen_door_green_2);
		} else {
			windsleepDoor.setImageResource(R.drawable.zen_door_red_6);
		}
		
		if (kab_wind.equals("off")){
			windkabDoor.setImageResource(R.drawable.zen_door_green_3);
		} else {
			windkabDoor.setImageResource(R.drawable.zen_door_red_7);
		}
		
		if (safe_wind.equals("off")){
			kladowDoor.setImageResource(R.drawable.zen_door_green_4);
		} else {
			kladowDoor.setImageResource(R.drawable.zen_door_red_8);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.door, menu);
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
