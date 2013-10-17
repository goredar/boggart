package it.goredar.boggart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BoggartActivity extends Activity {
	
	public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	activity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boggart);
        WebView dom_vw = (WebView) findViewById(R.id.domotics_wv);
        dom_vw.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(BoggartActivity.activity);
                String domoticsUser = sharedPref.getString("domotics_user", "");
                String domoticsPassword = sharedPref.getString("domotics_password", "");
                handler.proceed(domoticsUser, domoticsPassword);
            }
        });
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
