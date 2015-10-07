var cordova = require('cordova'),
    argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

// Constructor

var Enhance = function() {
    this.serviceName = "Enhance";
    this._start ();
};

Enhance.prototype._start = function() {
}

/**
 * Check if Enhance SDK is injected into the host app
 *
 * @param resultCallback function that receives the result: true if injected, false if not
 */
Enhance.prototype.isEnhanced = function (resultCallback) {
   exec(resultCallback, null, this.serviceName, 'isEnhanced', []);
};

/**
 * Show interstitial ad
 */
Enhance.prototype.showInterstitialAd = function () {
    exec(null, null, this.serviceName, 'showInterstitialAd', []);
};

/**
 * Check if a rewarded ad is ready to show
 *
 * @param resultCallback function that receives the result: true if ready, false if not
 */
Enhance.prototype.isRewardedAdReady = function (resultCallback) {
   exec(resultCallback, null, this.serviceName, 'isRewardedAdReady', []);
};

/**
 * Load success rewarded ad
 *
 * @param placement placement name for this ad
 * @param triggerType what triggered the ad: TRIGGER_EVENT for an event (level completed, game over..), TRIGGER_USER_ACTION for a button tap
 * @param resultCallback function that is called when the reward is granted
 */
Enhance.prototype.showRewardedAd = function (strPlacement, strTriggerType, resultCallback) {
    exec(resultCallback, null, this.serviceName, 'showRewardedAd', [strPlacement, strTriggerType]);
};

module.exports = Enhance;
