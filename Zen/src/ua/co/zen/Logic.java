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

import ua.co.zen.MainActivity.DownloadXML;
import ua.co.zen.MainActivity.MyTimerTask;
import ua.co.zen.MainActivity.sendGet;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Logic {
	
	public static String URL = "http://192.168.1.33";
	protected static final int RESULT_SPEECH = 1;
	private static final String IP = "192.168.1.33";
	InetAddress inet;
	MainActivity ma;
	public NodeList nodelist;
	
	public void main(){
		 ma  = new MainActivity();
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
			try {
				for (int temp = 0; temp < nodelist.getLength(); temp++) {
					Node nNode = nodelist.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						// Set the texts into TextViews from item nodes
						// Get the title					
						ma.current =  getNode("current", eElement);
						ma.bed_temp =  getNode("bed_temp", eElement);
						ma.kitch_temp =  getNode("kitch_temp", eElement);
						ma.toil_temp =  getNode("toil_temp", eElement);
						ma.street_temp =  getNode("street_temp", eElement);
						ma.toil_water =  getNode("toil_water", eElement);
						ma.bath_water =  getNode("bath_water", eElement);
						ma.wash_water =  getNode("wash_water", eElement);
						ma.kitch_water =  getNode("kitch_water", eElement);
						ma.main_door =  getNode("main_door", eElement);
						ma.bed_wind =  getNode("bed_wind", eElement);
						ma.kab_wind =  getNode("kab_wind", eElement);
						ma.safe_wind =  getNode("safe_wind", eElement);
						ma.toil_light =  getNode("toil_light", eElement);
						ma.hall_light =  getNode("hall_light", eElement);
						ma.bed_light =  getNode("bed_light", eElement);
						ma.kitch_light =  getNode("kitch_light", eElement);
					}
				}
			 } catch (NullPointerException e) {
				 Toast t = Toast.makeText(ma.getApplicationContext(),
                         "Ошибка связи с мозгом. Невозможно получить состояния",
                         Toast.LENGTH_SHORT);
                 t.show();
			 }
			//}
			// Close progressbar
			// pDialog.dismiss();
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
			ma.runOnUiThread(new Runnable() {
				public void run() {
					new DownloadXML().execute(URL);
				}
			});
		}
	}

}
