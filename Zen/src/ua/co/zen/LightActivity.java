package ua.co.zen;

import java.util.Timer;
import java.util.TimerTask;

import ua.co.zen.MainActivity.DownloadXML;
import ua.co.zen.MainActivity.MyTimerTask;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LightActivity extends Activity {

	private ImageButton toiletLight, hallLight, bedLight, kitchLight;
	private Button infoButton;
	public String toil_light="", hall_light="", bed_light="", kitch_light="";
	String URL = "http://192.168.1.33";
	private Timer mTimer;
	private MyTimerTask mMyTimerTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);

		infoButton = (Button) findViewById(R.id.infoButton);
		Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
		infoButton.setTypeface(tp);

		toiletLight = (ImageButton) findViewById(R.id.toiletLight);
		

		hallLight = (ImageButton) findViewById(R.id.hallLight);
		

		bedLight = (ImageButton) findViewById(R.id.bedroomLight);
	

		kitchLight = (ImageButton) findViewById(R.id.kitchenLight);
		/*
		 	Intent intent = getIntent();
		 	toil_light = intent.getStringExtra("toil_light");
		 	hall_light = intent.getStringExtra("hall_light");
		 	bed_light = intent.getStringExtra("bed_light");
		 	toil_light = intent.getStringExtra("kitch_light");
		 */	
		
		if (MainActivity.toil_light.equals("on")) {
			toiletLight.setImageResource(R.drawable.zen_light_1);
		} else {
			toiletLight.setImageResource(R.drawable.zen_light_black_5);
		}

		if (MainActivity.hall_light.equals("on")) {
			hallLight.setImageResource(R.drawable.zen_light_2);
		} else {
			hallLight.setImageResource(R.drawable.zen_light_black_6);
		}

		if (MainActivity.bed_light.equals("on")) {
			bedLight.setImageResource(R.drawable.zen_light_3);
		} else {
			bedLight.setImageResource(R.drawable.zen_light_black_7);
		}

		if (MainActivity.kitch_light.equals("on")) {
			kitchLight.setImageResource(R.drawable.zen_light_4);
		} else {
			kitchLight.setImageResource(R.drawable.zen_light_black_8);
		}
		
		 mTimer = new Timer();
		 mMyTimerTask = new MyTimerTask();
		 mTimer.schedule(mMyTimerTask, 100, 2000);
		

		// �������� � ������ �����
		toiletLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (MainActivity.toil_light.equals("on")) { // ���� ����� ���� ��������
					toiletLight.setImageResource(R.drawable.zen_light_black_5); // ������
																				// ��������
					String addurl = "?toil_light=0"; // ��������� �����
					new MainActivity.sendGet().execute(URL + addurl); // ����������
																		// ������
					infoButton.setText("���� � ������� ��������");
					//toil_light = "off"; // ������ ������
				} else { // ����������� �������� �������� ���� ����� ����
							// ���������
					toiletLight.setImageResource(R.drawable.zen_light_1);
					String addurl = "?toil_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("���� � ������� �������");
					//toil_light = "on";
				}
			}
		});

		// �������� � ������ �����
		hallLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (MainActivity.hall_light.equals("on")) { // ���� ����� ���� ��������
					hallLight.setImageResource(R.drawable.zen_light_black_6); // ������
																				// ��������
					String addurl = "?hall_light=0"; // ��������� �����
					new MainActivity.sendGet().execute(URL + addurl); // ����������
																		// ������
					infoButton.setText("���� � �������� ��������");
					//hall_light = "off"; // ������ ������
				} else { // ����������� �������� �������� ���� ����� ����
							// ���������
					hallLight.setImageResource(R.drawable.zen_light_2);
					String addurl = "?hall_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("���� � �������� �������");
					//hall_light = "on";
				}
			}
		});

		// �������� � ������ �����
		bedLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (MainActivity.bed_light.equals("on")) { // ���� ����� ���� ��������
					bedLight.setImageResource(R.drawable.zen_light_black_7); // ������
																				// ��������
					String addurl = "?bed_light=0"; // ��������� �����
					new MainActivity.sendGet().execute(URL + addurl); // ����������
																		// ������
					infoButton.setText("���� � ������� ��������");
					//bed_light = "off"; // ������ ������
				} else { // ����������� �������� �������� ���� ����� ����
							// ���������
					bedLight.setImageResource(R.drawable.zen_light_3);
					String addurl = "?bed_light=1";
					new MainActivity.sendGet().execute(URL + addurl);
					infoButton.setText("���� � ������� �������");
					//bed_light = "on";
				}
			}
		});
		
		// �������� � ������ �����
				kitchLight.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (MainActivity.kitch_light.equals("on")) { // ���� ����� ���� ��������
							kitchLight.setImageResource(R.drawable.zen_light_black_8); // ������
																						// ��������
							String addurl = "?kitch_light=0"; // ��������� �����
							new MainActivity.sendGet().execute(URL + addurl); // ����������
																				// ������
							infoButton.setText("���� �� ����� ��������");
							//kitch_light = "off"; // ������ ������
						} else { // ����������� �������� �������� ���� ����� ����
									// ���������
							kitchLight.setImageResource(R.drawable.zen_light_4);
							String addurl = "?kitch_light=1";
							new MainActivity.sendGet().execute(URL + addurl);
							infoButton.setText("���� �� ����� �������");
							//kitch_light = "on";
						}
					}
				});


	}
	
	public class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				public void run() {
					//new RefreshScreen().execute();
					//LightActivity.this.recreate();
					
					if (MainActivity.toil_light.equals("on")) {
						toiletLight.setImageResource(R.drawable.zen_light_1);
					} else {
						toiletLight.setImageResource(R.drawable.zen_light_black_5);
					}

					if (MainActivity.hall_light.equals("on")) {
						hallLight.setImageResource(R.drawable.zen_light_2);
					} else {
						hallLight.setImageResource(R.drawable.zen_light_black_6);
					}

					if (MainActivity.bed_light.equals("on")) {
						bedLight.setImageResource(R.drawable.zen_light_3);
					} else {
						bedLight.setImageResource(R.drawable.zen_light_black_7);
					}

					if (MainActivity.kitch_light.equals("on")) {
						kitchLight.setImageResource(R.drawable.zen_light_4);
					} else {
						kitchLight.setImageResource(R.drawable.zen_light_black_8);
					}
					
				}
			});
		}
	}
	
	class RefreshScreen extends AsyncTask<Void, Void, Void> {

	    @Override
	    protected void onPreExecute() {
	      super.onPreExecute();
	     
	    }

	    @Override
	    protected Void doInBackground(Void... params) {
	    	toil_light = MainActivity.toil_light;// ����� ���������� �� MainActivity
	    	hall_light = MainActivity.hall_light;// ����� ���������� �� MainActivity
	    	bed_light = MainActivity.bed_light;// ����� ���������� �� MainActivity
	    	kitch_light = MainActivity.kitch_light;// ����� ���������� ��
			// MainActivity
	      return null;
	    }

	    @Override
	    protected void onPostExecute(Void result) {
	      super.onPostExecute(result);

			if (toil_light.equals("on")) {
				toiletLight.setImageResource(R.drawable.zen_light_1);
			} else {
				toiletLight.setImageResource(R.drawable.zen_light_black_5);
			}

			if (hall_light.equals("on")) {
				hallLight.setImageResource(R.drawable.zen_light_2);
			} else {
				hallLight.setImageResource(R.drawable.zen_light_black_6);
			}

			if (bed_light.equals("on")) {
				bedLight.setImageResource(R.drawable.zen_light_3);
			} else {
				bedLight.setImageResource(R.drawable.zen_light_black_7);
			}

			if (kitch_light.equals("on")) {
				kitchLight.setImageResource(R.drawable.zen_light_4);
			} else {
				kitchLight.setImageResource(R.drawable.zen_light_black_8);
			}
	    }
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
