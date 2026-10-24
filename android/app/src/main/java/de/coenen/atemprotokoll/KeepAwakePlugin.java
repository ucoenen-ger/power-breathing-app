package de.coenen.atemprotokoll;

import android.view.WindowManager;
import android.webkit.WebView;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.WebViewListener;
import com.getcapacitor.annotation.CapacitorPlugin;

/**
 * Android-WebView kennt die Screen Wake Lock API (navigator.wakeLock) nicht.
 * Dieses Plugin stellt sie nach: navigator.wakeLock.request('screen') haelt
 * den Bildschirm an (FLAG_KEEP_SCREEN_ON), release() gibt ihn wieder frei.
 * Die Web-App selbst bleibt unveraendert.
 */
@CapacitorPlugin(name = "KeepAwake")
public class KeepAwakePlugin extends Plugin {

    private static final String POLYFILL =
        "(function(){" +
        "if('wakeLock' in navigator)return;" +
        "var P=window.Capacitor&&Capacitor.Plugins&&Capacitor.Plugins.KeepAwake;if(!P)return;" +
        "navigator.wakeLock={request:function(){return P.set({on:true}).then(function(){" +
        "var ls=[];var s={type:'screen',released:false," +
        "addEventListener:function(t,f){if(t==='release')ls.push(f);}," +
        "removeEventListener:function(t,f){var i=ls.indexOf(f);if(i>=0)ls.splice(i,1);}," +
        "release:function(){if(s.released)return Promise.resolve();s.released=true;" +
        "ls.forEach(function(f){try{f();}catch(e){}});return P.set({on:false});}};" +
        "return s;});}};" +
        "})();";

    @Override
    public void load() {
        bridge.addWebViewListener(
            new WebViewListener() {
                @Override
                public void onPageLoaded(WebView webView) {
                    webView.evaluateJavascript(POLYFILL, null);
                }
            }
        );
    }

    @PluginMethod
    public void set(PluginCall call) {
        final boolean on = Boolean.TRUE.equals(call.getBoolean("on", true));
        getActivity()
            .runOnUiThread(() -> {
                if (on) {
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                } else {
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
                call.resolve();
            });
    }
}
