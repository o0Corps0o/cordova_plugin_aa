package com.fgl.enhance.cordova;

import java.util.Arrays;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.fgl.enhance.connector.FGLConnector;
import com.fgl.enhance.connector.FGLConnector.Trigger;

// Cordova plugin class

public class EnhanceCordova extends CordovaPlugin {
   // Tag for logging
   public static final String TAG = "FGLEnhanceCordova";
   
   // Cordova connector version
   private static final String CORDOVA_CONNECTOR_VERSION = "1.1.0";
      
   // Initialize connector
   
   @Override
   public void initialize (CordovaInterface cordova, CordovaWebView webView) {
      super.initialize (cordova, webView);
      Log.d (TAG, "initialize Cordova connector version " + CORDOVA_CONNECTOR_VERSION);
      FGLConnector.initialize (cordova.getActivity ());
   }
   
   // Execute Adsorb command
   
   @Override
   public boolean execute (String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
      if (action.equals ("isEnhanced")) {
         try {
            // Check if the game is injected with Enhance
            callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, FGLConnector.isEnhanced()));
         } catch (Exception e) {
            Log.e (TAG, "exception in isEnhanced: " + e.toString());
            e.printStackTrace ();

            callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, false));
         }

         return true;
      }
      else if (action.equals ("showInterstitialAd")) {
         try {
            // Show interstitial ad
            FGLConnector.showInterstitialAd();
            callbackContext.success();
            return true;
         } catch (Exception e) {
            Log.e (TAG, "exception in showInterstitialAd: " + e.toString());
            e.printStackTrace ();
            callbackContext.success();
         }
      }
      else if (action.equals ("isRewardedAdReady")) {
         try {
            // Check if a rewarded ad is ready to show
            callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, FGLConnector.isRewardedAdReady()));
         } catch (Exception e) {
            Log.e (TAG, "exception in isRewardedAdReady: " + e.toString());
            e.printStackTrace ();

            callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, false));
         }

         return true;
      }
      else if (action.equals ("showRewardedAd")) {
         try {
				String strPlacement = args.getString (0);
				String strTriggerType = args.getString (1);
				Trigger enumTriggerType;
				
				if (strTriggerType.equals("TRIGGER_EVENT"))
					enumTriggerType = Trigger.TRIGGER_EVENT;
				else
					enumTriggerType = Trigger.TRIGGER_USER_ACTION;

            // Show rewarded ad
            FGLConnector.showRewardedAd(strPlacement, enumTriggerType, new Runnable () {
					@Override
					public void run () {
						// Reward granted
						Log.i (TAG, "onRewardGranted");
						callbackContext.sendPluginResult (new PluginResult (PluginResult.Status.OK, true));
					}
				});

            return true;
         } catch (Exception e) {
            Log.e (TAG, "exception in showRewardedAd: " + e.toString());
            e.printStackTrace ();
            callbackContext.success();
         }
      }
      else {
         Log.e (TAG, "Unknown action requested: \"" + action + "\", args: " + args);
      }
      
      return false;
   }
}
