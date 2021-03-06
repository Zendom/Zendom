package ua.co.zen;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import ua.co.zen.MainService.DownloadXML;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public String URL;
	protected static final int RESULT_SPEECH = 1;
	//private static final String IP = "192.168.1.33";
	private ImageButton headerButton; // ������� ������
	private Button infoButton; // ����� ������ - ������ ����������
	private ImageButton voiceButton; // ������ ���������� �������
	private ImageButton tempButton; // ������ ������ ����������
	private ImageButton waterButton; // ������ ������ ��������
	private ImageButton electButton; // ������ ������ �������������
	private ImageButton multiButton; // ������ ������ �����������
	private ImageButton cameraButton; // ������ ������ ������
	private ImageButton doorButton; // ������ ������ ������ � ����
	private ImageButton lightButton; // ������ ������ ������ � ����
	private ArrayList<String> textVoice; // ������ ����������� Google'�� ����
	
	InetAddress inet;

	private Timer mTimer;
	private MyTimerTask mMyTimerTask;
	public NodeList nodelist;
	public static String LED = "����� ���", current = "0",
			bed_temp = "null", kitch_temp = "null", toil_temp = "null",
			street_temp = "null";
	public static String toil_water = "null", bath_water = "null",
			wash_water = "null", kitch_water = "null", main_door = "null",
			bed_wind = "null", kab_wind = "null", safe_wind = "null";
	public static String toil_light = "null", hall_light = "null",
			bed_light = "null", kitch_light = "null";

	final String LOG_TAG = "myLogs";

	boolean bound = false;
	ServiceConnection sConn;
	Intent intent;
	MainService myService;
	TextView tvInterval;
	long interval;
	boolean service_flag = true;
	
	DownloadXML downxml = new DownloadXML();
	
	SharedPreferences sp;
	Boolean on;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		headerButton = (ImageButton) findViewById(R.id.headerButton);
		voiceButton = (ImageButton) findViewById(R.id.voiceButton);
		tempButton = (ImageButton) findViewById(R.id.tempButton);
		waterButton = (ImageButton) findViewById(R.id.waterButton);
		electButton = (ImageButton) findViewById(R.id.electricButton);
		multiButton = (ImageButton) findViewById(R.id.multiButton);
		cameraButton = (ImageButton) findViewById(R.id.cameraButton);
		doorButton = (ImageButton) findViewById(R.id.doorButton);
		lightButton = (ImageButton) findViewById(R.id.lightButton);

		infoButton = (Button) findViewById(R.id.infoButton);
		Typeface tp = Typeface.createFromAsset(getAssets(), "fonts/ptsans.ttf");
		infoButton.setTypeface(tp);

		 sp = PreferenceManager.getDefaultSharedPreferences(this);
		 
		 on = sp.getBoolean("on", false);
		 URL = sp.getString("ip", "");
		   
		   
		/* ��� ������ �������, ���� �� �����
		headerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				
                if (service_flag) {
                	startService(intent);
                	service_flag = false;
                } else {
    				stopService(intent);
    				myService.onDestroy();
    				service_flag = true;
                }  

				

			}
		});
		*/

		// ������� ����� �� ����� �� ������. ���������� ��������� � ������ �����
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

		tempButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						TemperatureActivity.class);
				intent.putExtra("bedroomTemp", bed_temp);
				intent.putExtra("kitchenTemp", kitch_temp);
				intent.putExtra("toiletTemp", toil_temp);
				intent.putExtra("streetTemp", street_temp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � �������� ����������", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		waterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						WaterActivity.class);
				intent.putExtra("toil_water", toil_water);
				intent.putExtra("bath_water", bath_water);
				intent.putExtra("wash_water", wash_water);
				intent.putExtra("kitch_water", kitch_water);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � �������� ��������� ��������",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		electButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						ElectricActivity.class);
				intent.putExtra("current", current);
				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � ElectricActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		multiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						MultimediaActivity.class);
				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � WaterActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		cameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						CameraActivity.class);
				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � CameraActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		doorButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						DoorActivity.class);
				intent.putExtra("main_door", main_door);
				intent.putExtra("bed_wind", bed_wind);
				intent.putExtra("kab_wind", kab_wind);
				intent.putExtra("safe_wind", safe_wind);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � DoorActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		lightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						LightActivity.class);
				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ � LightActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		if (on) {
				mTimer = new Timer();
				mMyTimerTask = new MyTimerTask();
				mTimer.schedule(mMyTimerTask, 10, 2000);
		} 
		/*
        //��������� ����� ��� ������ �������
		intent = new Intent(this, MainService.class);
		sConn = new ServiceConnection() {

			public void onServiceConnected(ComponentName name, IBinder binder) {
				Log.d(LOG_TAG, "MainActivity onServiceConnected");
				myService = ((MainService.MyBinder) binder).getService();
				bound = true;
			}

			public void onServiceDisconnected(ComponentName name) {
				Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
				bound = false;
			}	
		};*/
	}
	
	
	/* ��� �������, ���� �� ����� 
	//��� ������� Service
	@Override
	protected void onStart() {
		super.onStart();
		bindService(intent, sConn, 0);
	}
	
	//��� ��������� Service
	@Override
	protected void onStop() {
		super.onStop();
		if (!bound)
			return;
		unbindService(sConn);
		bound = false;
	}
	 	*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    MenuItem mi = menu.add(0, 1, 0, "���������");
	    mi.setIntent(new Intent(this, PrefActivity.class));
	    return super.onCreateOptionsMenu(menu);
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

	// ����� ��� ������������� ������
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {
				textVoice = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// ����� ��, ��� ���������� � ����� ������
				infoButton.setText(textVoice.get(0));
				voice�ontrol(textVoice.get(0));
			}
			break;
		}
		}
	}

	protected void voice�ontrol(String reqtext) {
		if (reqtext.equalsIgnoreCase("�������� ���� � �������")) {
			String addurl = "?bed_light=1"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("��������� ���� � �������")) {
			String addurl = "?bed_light=0"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("�������� ���� � �������")) {
			String addurl = "?toil_light=1"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("��������� ���� � �������")) {
			String addurl = "?toil_light=0"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("�������� ���� � ��������")) {
			String addurl = "?hall_light=1"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("��������� ���� � ��������")) {
			String addurl = "?hall_light=0"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("�������� ���� �� �����")) {
			String addurl = "?kitch_light=1"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}
		if (reqtext.equalsIgnoreCase("��������� ���� �� �����")) {
			String addurl = "?kitch_light=0"; // ��������� �����
			new sendGet().execute(URL + addurl);
		}

	}

	// DownloadXML
		public class DownloadXML {
			protected Void loadXML() {
				try {
					URL url = new URL(URL);
					DocumentBuilderFactory dbf = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					// Download the XML file
					Document doc = db.parse(new InputSource(url.openStream()));
					doc.getDocumentElement().normalize();
					// Locate the Tag Name
					NodeList nodelist = doc.getElementsByTagName("inputs");
					getTag(nodelist);
				} catch (Exception e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return null;
			}

			protected void getTag(NodeList nodelist) {
				// if (nodelist.getLength() > 0) {
				try {
					for (int temp = 0; temp < nodelist.getLength(); temp++) {
						Node nNode = nodelist.item(temp);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) nNode;
							// Set the texts into TextViews from item nodes
							// Get the title
							current = getNode("current", eElement);
							bed_temp = getNode("bed_temp", eElement);
							kitch_temp = getNode("kitch_temp", eElement);
							toil_temp = getNode("toil_temp", eElement);
							street_temp = getNode("street_temp", eElement);
							toil_water = getNode("toil_water", eElement);
							bath_water = getNode("bath_water", eElement);
							wash_water = getNode("wash_water", eElement);
							kitch_water = getNode("kitch_water", eElement);
							main_door = getNode("main_door", eElement);
							bed_wind = getNode("bed_wind", eElement);
							kab_wind = getNode("kab_wind", eElement);
							safe_wind = getNode("safe_wind", eElement);
							toil_light = getNode("toil_light", eElement);
							hall_light = getNode("hall_light", eElement);
							bed_light = getNode("bed_light", eElement);
							kitch_light = getNode("kitch_light", eElement);
						}
					}
				} catch (NullPointerException e) {
					Toast t = Toast.makeText(getApplicationContext(),
							"������ ����� � ������. ���������� �������� ���������",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		}

	public static class sendGet extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String... urls) {
			try {

				// replace with your url
				HttpResponse response;
				try {
					HttpClient client = new DefaultHttpClient();
					HttpGet request = new HttpGet(urls[0]);
					response = client.execute(request);
					Log.d("Response of GET request", response.toString());
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				return null;
			}
			return null;
		}

		protected void onPostExecute() {
			// TODO: check this.exception
			// TODO: do something with the feed
		}
	}

	// getNode function
	private static String getNode(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		return nValue.getNodeValue();
	}

	public class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				public void run() {
					Thread myThread = new Thread(new Runnable() {
						public void run() {
							downxml.loadXML();
						}
					});
					myThread.start();
				}
			});
		}
	}

}
