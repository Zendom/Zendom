package ua.co.zen;

import java.io.IOException;
import java.net.URL;
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
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Logic {

	// Declare variables
	TextView textview;
	NodeList nodelist;
	ProgressDialog pDialog;
	// Insert image URL
	String URL = "http://192.168.1.33";
	private Timer mTimer;
	private MyTimerTask mMyTimerTask;
	String name;
	Integer param;
	Button Button1;
	Button Button2;
	
	public Logic(){
		super();
		mTimer = new Timer();
		mMyTimerTask = new MyTimerTask();
		mTimer.schedule(mMyTimerTask, 1000, 3000);
	}
	
	public void startParser () {

		// Locate a TextView in your activity_main.xml layout
	//	textview = (TextView) findViewById(R.id.text);
		// Execute DownloadXML AsyncTask
		mTimer = new Timer();
		mMyTimerTask = new MyTimerTask();
		mTimer.schedule(mMyTimerTask, 1000, 3000);
		/* Button1 = (Button) findViewById(R.id.button1);
		// Ñëóøàåì ãîëîñ ïî êëèêó íà êíîïêó. Âîçâðàùàåì ðåçóëüòàò â ìàññèâ ñòðîê
		Button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// new Thread(new Runnable() {
				// public void run() {
				// Button1.post(new Runnable() {
				// public void run() {
				addurl = "?LED3=1";
				new sendGet().execute(URL + addurl);
				// }
				// });
				// }
				// }).start();
			}
		});
		Button2 = (Button) findViewById(R.id.button2);
		// Ñëóøàåì ãîëîñ ïî êëèêó íà êíîïêó. Âîçâðàùàåì ðåçóëüòàò â ìàññèâ ñòðîê
		Button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// new Thread(new Runnable() {
				// public void run() {
				// Button2.post(new Runnable() {
				// public void run() {
				addurl = "?LED4=1";
				new sendGet().execute(URL + addurl);
				// }
				// });
				// }
				// }).start();
			}
		}); */
	}
	
	public void sentParam (String addurl) {
		new sendGet().execute(URL + addurl);
		
	}

	// DownloadXML AsyncTask
	private class DownloadXML extends AsyncTask<String, Void, Void> {
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
			// if (nodelist.getLength() > 0) {
			for (int temp = 0; temp < nodelist.getLength(); temp++) {
				Node nNode = nodelist.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Set the texts into TextViews from item nodes
					// Get the title
					textview.setText("Analog : " + getNode("analog", eElement)
							+ "\n" + "\n");
					// Get the description
					textview.setText(textview.getText() + "Switch : "
							+ getNode("switch", eElement) + "\n" + "\n");
					// Get the link
					textview.setText(textview.getText() + "LED : "
							+ getNode("LED", eElement) + "\n" + "\n");
				}
			}
			// }
			// Close progressbar
			// pDialog.dismiss();
		}
	}

	class sendGet extends AsyncTask<String, Void, Void> {
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

	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
					new DownloadXML().execute(URL);	
		}
	}

}
