package com.browser2app.rn;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.browser2app.khenshin.Khenshin;
import com.browser2app.khenshin.KhenshinConstants;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import static android.app.Activity.RESULT_OK;


public class RNKhenshinModule extends ReactContextBaseJavaModule implements ActivityEventListener {
  private static final String TAG = RNKhenshinModule.class.getName();
  private Khenshin khenshin;
  private final ReactApplicationContext reactContext;
  private final int START_PAYMENT_REQUEST_CODE = 1001;
  private Callback callback;

  public RNKhenshinModule(ReactApplicationContext reactContext, Khenshin khenshin) {
    super(reactContext);
    this.reactContext = reactContext;
    this.khenshin = khenshin;
    this.reactContext.addActivityEventListener(this);
  }

  @Override
  public String getName() {
    return "RNKhenshin";
  }

  @ReactMethod
  public void startPaymentById(String paymentId, Callback callback) {
    this.callback = callback;
    Intent intent = this.khenshin.getStartTaskIntent();
    intent.putExtra(KhenshinConstants.EXTRA_PAYMENT_ID, paymentId);
    intent.putExtra(KhenshinConstants.EXTRA_FORCE_UPDATE_PAYMENT, true);
    intent.putExtra("EXTRA_EXTERNAL_PAYMENT", false);
    intent.putExtra("EXTRA_IGNORE_RETURN_URL", true);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    getCurrentActivity().startActivityForResult(intent, START_PAYMENT_REQUEST_CODE);
  }


  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d(TAG, "requestCode: " + requestCode);
    Log.d(TAG, "resultCode: " + resultCode);
    Log.d(TAG, "data: " + data);
    if (requestCode == START_PAYMENT_REQUEST_CODE && data != null) {
      String exitStatus = "";
      String result = "";
      if (resultCode == RESULT_OK) {
        exitStatus = "PAYMENT_OK";
        result = "{ \"paymentId\": \"" + data.getStringExtra(KhenshinConstants.EXTRA_PAYMENT_ID) + "\" }";

      } else {
        exitStatus = data.getStringExtra(KhenshinConstants.EXTRA_FAILURE_REASON);
        result = "{}";
      }
      Log.d(TAG, exitStatus);
      Log.d(TAG, result);
      callback.invoke(exitStatus, result);
    }
  }

  @Override
  public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void onNewIntent(Intent intent) {

  }
}
