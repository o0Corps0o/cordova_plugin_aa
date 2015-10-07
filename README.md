Cordova/Phonegap connector plugin for Enhance

## Integration with your Cordova app for Android

1. Add the Enhance plugin to your Cordova/Phonegap app project, for instance with the following command line:

   phonegap local plugin add {PATH/TO/FOLDER...}/cordova_plugin

2. Create an instance of Enhance, during your game initialization. For instance in index.js:

   // Declared outside of app
   var enhance;

   Then:

   onDeviceReady: function() {
      ...

      // Create instance of Enhance
      enhance = new Enhance ();

      ...
   },

3. You can now show interstitial ads with one line of code.

   enhance.showInterstitialAd ();

4. Show rewarded ads

   You can show rewarded ads as well. These ads take a placement relevant to your game, such as "GrantCoins" or "Booster", and a trigger, either Trigger.TRIGGER_USER_ACTION (the ad is triggered by the user
   tapping a View Video For Coins button, for instance) or Trigger.TRIGGER_EVENT (the ad got triggered by a level completion, for instance). Check with FGL about what placements your game should use.

   enhance.isRewardedAdReady (function(result) {
      console.log ('Rewarded ad ready:' + result);
      if (result == true) {
         enhance.showRewardedAd ("GrantCoins", "TRIGGER_USER_ACTION", function(result) {
            console.log ('Rewarded ad granted');
         });
      }
   });

## Conclusion

That's it! Enhance will take care of the rest. Happy monetization!

