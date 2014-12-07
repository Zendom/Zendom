package ua.co.zen.xmlparcer;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
import android.widget.TextView;

public class MainActivity extends Activity {
	// Declare variables
	TextView textview;
	NodeList nodelist;
	ProgressDialog pDialog;
	// Insert image URL
	String URL = "http://192.168.1.33";
	private Timer mTimer;
	private MyTimerTask mMyTimerTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from activity_main.xml
		setContentView(R.layout.activity_main);
		// Locate a TextView in your activity_main.xml layout
		textview = (TextView) findViewById(R.id.text);
		// Execute DownloadXML AsyncTask
		mTimer = new Timer();
		mMyTimerTask = new MyTimerTask();
		mTimer.schedule(mMyTimerTask, 1000, 3000);
	}

	// DownloadXML AsyncTask
	private class DownloadXML extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressbar
			//pDialog = new ProgressDialog(MainActivity.this);
			// Set progressbar title
			//pDialog.setTitle("Android Simple XML Parsing using DOM Tutorial");
			// Set progressbar message
			//pDialog.setMessage("Loading...");
			//pDialog.setIndeterminate(false);
			// Show progressbar
			//pDialog.show();
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

			for (int temp = 0; temp < nodelist.getLength(); temp++) {
				Node nNode = nodelist.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Set the texts into TextViews from item nodes
					// Get the title
					textview.setText("Analog : "
							+ getNode("analog", eElement) + "\n" + "\n");
					// Get the description
					textview.setText(textview.getText() + "Switch : "
							+ getNode("switch", eElement) + "\n" + "\n");
					// Get the link
					textview.setText(textview.getText() + "LED : "
							+ getNode("LED", eElement) + "\n" + "\n");
				}
			}
			// Close progressbar
			//pDialog.dismiss();
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
			runOnUiThread(new Runnable() {
				public void run() {
					new DownloadXML().execute(URL);
				}
			});
		}
	}
	
}