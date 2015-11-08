package ua.co.zen;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MultimediaActivity extends Activity {

	private ImageButton playMulti;
	private ImageButton soundMulti;
	private ImageButton volupMulti;
	private ImageButton voldownMulti;
	private ImageButton onMulti;
	private ImageButton backMulti;
	private ImageButton forwMulti;
	String URL;
	SharedPreferences sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multimedia);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		URL = sp.getString("ip", "");
		
		  playMulti = (ImageButton) findViewById(R.id.playMulti);
		  playMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?playmulti"; 
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Вкл/Выкл телевизор",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  
		  soundMulti = (ImageButton) findViewById(R.id.soundMulti);
		  soundMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?soundmulti"; 
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Mute TV",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  volupMulti = (ImageButton) findViewById(R.id.volupMulti);
		  volupMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?volupmulti";
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Volume UP",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  voldownMulti = (ImageButton) findViewById(R.id.voldownMulti);
		  voldownMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?voldownmulti";
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Volume DOWN",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  onMulti = (ImageButton) findViewById(R.id.onMulti);
		  onMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?onmulti";
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Выбор источника",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  backMulti = (ImageButton) findViewById(R.id.backMulti);
		  backMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?backmulti";
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Канал назад",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
		  forwMulti = (ImageButton) findViewById(R.id.forwMulti);
		  forwMulti.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String addurl = "?forwmulti";
					new MainActivity.sendGet().execute(URL + addurl); // отправляем запрос
					Toast t = Toast.makeText(getApplicationContext(),
							"Канал вперед",
							Toast.LENGTH_SHORT);
					t.show();
	            }
	        });
		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multimedia, menu);
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
