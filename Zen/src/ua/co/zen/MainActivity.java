package ua.co.zen;

import java.io.IOException;
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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static String URL = "http://192.168.1.33";
	protected static final int RESULT_SPEECH = 1;
	private ImageButton headerButton; // Красная кнопка
	private Button infoButton; // Белая кнопка - строка информации
	private ImageButton voiceButton; // Кнопка управления голосом
	private ImageButton tempButton; // Кнопка экрана температур
	private ImageButton waterButton; // Кнопка экрана протечек
	private ImageButton electButton; // Кнопка экрана электричества
	private ImageButton multiButton; // Кнопка экрана мультимедия
	private ImageButton cameraButton; // Кнопка экрана камеры
	private ImageButton doorButton; // Кнопка экрана дверей и окон
	private ImageButton lightButton; // Кнопка экрана дверей и окон
	private ArrayList<String> textVoice; // Массив распознаных Google'ом фраз
	private String bedroomTemp = "15°", kitchenTemp = "25°",
			toiletTemp = "35°", streetTemp = "-25°";

	private Timer mTimer;
	private MyTimerTask mMyTimerTask;
	public NodeList nodelist;
	public static String LED;

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
				infoButton.setText(LED);
			}
		});

		voiceButton = (ImageButton) findViewById(R.id.voiceButton);
		// Слушаем голос по клику на кнопку. Возвращаем результат в массив строк
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
							"Это говно не поддерживает распознование голоса",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		tempButton = (ImageButton) findViewById(R.id.tempButton);
		tempButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						TemperatureActivity.class);
				intent.putExtra("bedroomTemp", bedroomTemp);
				intent.putExtra("kitchenTemp", kitchenTemp);
				intent.putExtra("toiletTemp", toiletTemp);
				intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка в передаче температур", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		waterButton = (ImageButton) findViewById(R.id.waterButton);
		waterButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						WaterActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с WaterActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		electButton = (ImageButton) findViewById(R.id.electricButton);
		electButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						ElectricActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с ElectricActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		multiButton = (ImageButton) findViewById(R.id.multiButton);
		multiButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						MultimediaActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с WaterActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		cameraButton = (ImageButton) findViewById(R.id.cameraButton);
		cameraButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						CameraActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с CameraActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		doorButton = (ImageButton) findViewById(R.id.doorButton);
		doorButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						DoorActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с CameraActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

		lightButton = (ImageButton) findViewById(R.id.lightButton);
		lightButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						LightActivity.class);
				// intent.putExtra("bedroomTemp", bedroomTemp);
				// intent.putExtra("kitchenTemp", kitchenTemp);
				// intent.putExtra("toiletTemp", toiletTemp);
				// intent.putExtra("streetTemp", streetTemp);

				try {
					startActivity(intent);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Ошибка с LightActivity", Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
			
		
		 mTimer = new Timer();
		 mMyTimerTask = new MyTimerTask();
		 mTimer.schedule(mMyTimerTask, 10, 1000);


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

	// Метод для распознования голоса
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {
				textVoice = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				// Пишем то, что распознали в текст кнопки
				infoButton.setText(textVoice.get(0));
			}
			break;
		}
		}
	}

	public String getLED() {
		return LED;

	}

	// DownloadXML AsyncTask
	public class DownloadXML extends AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressbar
			// pDialog = new ProgressDialog(MainActivity.this);
			// Set progressbar title
			// pDialog.setTitle("Android Simple XML Parsing using DOM Tutorial");
			// Set progressbar message
			// pDialog.setMessage("Loading...");
			// pDialog.setIndeterminate(false);
			// Show progressbar
			// pDialog.show();
		}

		@Override
		protected Void doInBackground(String... Url) {
			try {
				URL url = new URL(Url[0]);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				// Download the XML file
				Document doc = db.parse(new InputSource(url.openStream()));
				doc.getDocumentElement().normalize();
				// Locate the Tag Name
				nodelist = doc.getElementsByTagName("inputs");
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void args) {
			//if (nodelist.getLength() > 0) {
				for (int temp = 0; temp < nodelist.getLength(); temp++) {
					Node nNode = nodelist.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						// Set the texts into TextViews from item nodes
						// Get the title
						infoButton.setText( "LED : "
								+ getNode("LED", eElement) + "\n" + "\n");
						LED =  getNode("LED", eElement);
					}
				}
			//}
			// Close progressbar
			// pDialog.dismiss();
		}
	}

	public static class sendGet extends AsyncTask<String, Void, Void> {
		private Exception exception;

		protected Void doInBackground(String... urls) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet(urls[0]);
				// replace with your url
				HttpResponse response;
				try {
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
				this.exception = e;
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
					new DownloadXML().execute(URL);
				}
			});
		}
	}

}
