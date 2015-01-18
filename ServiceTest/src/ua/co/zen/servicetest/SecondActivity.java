package ua.co.zen.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class SecondActivity extends Activity {
	  ServiceConnection sConn;
	  MainService myService;
	  boolean bound = false;
	  final String LOG_TAG = "myLogs";
	  Intent intent;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_second);
	    intent = new Intent(this, SecondActivity.class);
	    
	    sConn = new ServiceConnection() {

	        public void onServiceConnected(ComponentName name, IBinder binder) {
	          Log.d(LOG_TAG, "SecondActivity onServiceConnected");
	          myService = ((MainService.MyBinder) binder).getService(); 
	          bound = true;
	        }

	        public void onServiceDisconnected(ComponentName name) {
	          Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
	          bound = false;
	        }
	      }; 
	      
	    
	      
	      
	      Log.d(LOG_TAG, "Second activity say: "+ myService.setHallLiString());
	      
	}
	
	  @Override
      protected void onStart() {
        super.onStart();
        bindService(intent, sConn, 0);
      }
      
      @Override
      protected void onStop() {
        super.onStop();
        if (!bound) return;
        unbindService(sConn);
        bound = false;
      }
	
}
