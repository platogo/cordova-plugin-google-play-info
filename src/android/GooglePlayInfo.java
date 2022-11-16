package com.platogo.cordova.googleplayinfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;

interface Constants {
  String PKG_NAME = "com.android.vending";
}

public class GooglePlayInfo extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("getVersion".equals(action)) {
      cordova.getThreadPool().execute(() -> {
        Context ctx = cordova.getActivity().getApplicationContext();
        PackageManager pkgManager = ctx.getPackageManager();
        try {
          PackageInfo pkgInfo = pkgManager.getPackageInfo(Constants.PKG_NAME, 0);
          PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, pkgInfo.versionName);
          callbackContext.sendPluginResult(pluginResult);
        } catch (PackageManager.NameNotFoundException e) {
          PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
          callbackContext.sendPluginResult(pluginResult);
        }
      });
      return true;
    }
    return false;
  }
}