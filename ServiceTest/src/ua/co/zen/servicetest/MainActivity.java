package ua.co.zen.servicetest;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

  final String LOG_TAG = "myLogs";

  boolean bound = false;
  ServiceConnection sConn;
  Intent intent;
  MainService myService;
  TextView tvInterval;
  long interval;
  

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvInterval = (TextView) findViewById(R.id.tvInterval);
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
    }; 
    
    Button buttonSec = (Button) findViewById(R.id.buttonSec);
    
    buttonSec.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MainActivity.this,
					SecondActivity.class);

			try {
				startActivity(intent);

			} catch (ActivityNotFoundException a) {
				Toast t = Toast.makeText(getApplicationContext(),
						"Ошибка в передаче температур", Toast.LENGTH_SHORT);
				t.show();
			}
		}
	});

    
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

  public void onClickStart(View v) {
    startService(intent);
    
  }
  
  public void onClickUp(View v) {
    if (!bound) return;
    interval = myService.upInterval(500);
    tvInterval.setText("interval = " + interval);
  }
  
  public void onClickDown(View v) {
    if (!bound) return;
    interval = myService.downInterval(500);
    tvInterval.setText("interval = " + interval);
  }
  public void onClickStop(View v) {
	    stopService(intent);
	    myService.onDestroy();
	  }
  
}