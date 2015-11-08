package ua.co.zen;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class PrefActivity extends PreferenceActivity {

	   @Override
	    protected void onCreate(final Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
	    }

	    public static class MyPreferenceFragment extends PreferenceFragment
	    {
	        @Override
	        public void onCreate(final Bundle savedInstanceState)
	        {
	            super.onCreate(savedInstanceState);
	            addPreferencesFromResource(R.xml.pref);
	        }
	    }
}
	

