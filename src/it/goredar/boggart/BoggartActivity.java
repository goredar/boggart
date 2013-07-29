package it.goredar.boggart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BoggartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boggart);
        WebView dom_vw = (WebView) findViewById(R.id.domotics_wv);
        WebSettings dom_vw_set = dom_vw.getSettings();
        dom_vw_set.setJavaScriptEnabled(true);
        dom_vw_set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String domoticsServer = sharedPref.getString("domotics_server", "");
        dom_vw.loadUrl(domoticsServer);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_MENU ) {
        	startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
