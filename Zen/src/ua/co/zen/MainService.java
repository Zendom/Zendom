package ua.co.zen;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service {
	final String LOG_TAG = "myLogs";

	  MyBinder binder = new MyBinder();

	  Timer timer;
	  TimerTask tTask;
	  long interval = 10000;
	  String URL = "http://192.168.1.33";
	  
	    public NodeList nodelist;
		public String LED = "Умный дом", current="null", bed_temp="null", kitch_temp="null", toil_temp="null", street_temp="null";
		public String toil_water="null", bath_water="null", wash_water="null", kitch_water="null", 
				                    main_door="null", bed_wind="null", kab_wind="null", safe_wind="null";
		public String toil_light="null", hall_light="null", bed_light="null", kitch_light="null";

	  

	  public void onCreate() {
	    super.onCreate();
	    Log.d(LOG_TAG, "MyService onCreate");
	    timer = new Timer();
	    schedule();
	  }

	  public void onDestroy() {
		    super.onDestroy();
		    Log.d(LOG_TAG, "onDestroy");
		    tTask.cancel();
		    stopSelf();
		  }

	  
	  void schedule() {
	    if (tTask != null) tTask.cancel();
	    if (interval > 0) {
	      tTask = new TimerTask() {
	        public void run() {
	           new DownloadXML().execute(URL);
	            Log.d(LOG_TAG, hall_light);
	        }
	      };
	      timer.schedule(tTask, 3000, interval);
	    }
	  }

	  long upInterval(long gap) {
	    interval = interval + gap;
	    schedule();
	    return interval;
	  }

	  long downInterval(long gap) {
	    interval = interval - gap;
	    if (interval < 0) interval = 0;
	    schedule();
	    return interval;
	  }
	  
	  String setHallLiString(){
		  return hall_light;
	  }

	  public IBinder onBind(Intent arg0) {
	    Log.d(LOG_TAG, "MyService onBind");
	    return binder;
	  }

	  class MyBinder extends Binder {
	    MainService getService() {
	      return MainService.this;
	    }
	  }
	  
	//DownloadXML AsyncTask
		public class DownloadXML extends AsyncTask<String, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
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
							current =  getNode("current", eElement);
							bed_temp =  getNode("bed_temp", eElement);
							kitch_temp =  getNode("kitch_temp", eElement);
							toil_temp =  getNode("toil_temp", eElement);
							street_temp =  getNode("street_temp", eElement);
							toil_water =  getNode("toil_water", eElement);
							bath_water =  getNode("bath_water", eElement);
							wash_water =  getNode("wash_water", eElement);
							kitch_water =  getNode("kitch_water", eElement);
							main_door =  getNode("main_door", eElement);
							bed_wind =  getNode("bed_wind", eElement);
							kab_wind =  getNode("kab_wind", eElement);
							safe_wind =  getNode("safe_wind", eElement);
							toil_light =  getNode("toil_light", eElement);
							hall_light =  getNode("hall_light", eElement);
							bed_light =  getNode("bed_light", eElement);
							kitch_light =  getNode("kitch_light", eElement);
						}
					}
				 } catch (NullPointerException e) {
					 Toast t = Toast.makeText(getApplicationContext(),
	                        "Ошибка связи с мозгом. Невозможно получить состояния",
	                        Toast.LENGTH_SHORT);
	                t.show();
				}
			}
		}
		
		// getNode function
		private static String getNode(String sTag, Element eElement) {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
					.getChildNodes();
			Node nValue = (Node) nlList.item(0);
			return nValue.getNodeValue();
		}
	

}
