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
  private final ReactApplicationContext reactContext;
  private final int START_PAYMENT_REQUEST_CODE = 1001;
  private Callback callback;

  public RNKhenshinModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.reactContext.addActivityEventListener(this);
  }

  @Override
  public String getName() {
    return "RNKhenshin";
  }

  @ReactMethod
  public void startPaymentById(String paymentId, Callback callback) {
    this.callback = callback;
    Intent intent = Khenshin.getInstance().getStartTaskIntent();
    intent.putExtra(KhenshinConstants.EXTRA_PAYMENT_ID, paymentId);
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
      String status = data.getStringExtra(KhenshinConstants.EXTRA_FAILURE_REASON);
      String exitStatus = resultCode == RESULT_OK
              ? "CONCILIATING"
              : status;
      String result = resultCode == RESULT_OK
              ? "{\"paymentId\": \"" + data.getStringExtra(KhenshinConstants.EXTRA_PAYMENT_ID) + "\" }"
              : "{\"message\": \"" + data.getStringExtra(KhenshinConstants.EXTRA_RESULT_MESSAGE) + "\"}";

      Log.d(TAG, exitStatus);
      Log.d(TAG, result);
      this.callback.invoke(exitStatus, result);
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
