package com.browser2app.rn;

import android.app.Application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import com.browser2app.khenshin.Khenshin;


public class RNKhenshinPackage implements ReactPackage {
    private Application app;
    RNKhenshinModule khenshinModule;

    public RNKhenshinPackage(Application app) {
        this.app = app;
        Khenshin.KhenshinBuilder builder = new Khenshin.KhenshinBuilder();
        builder.setAllowCredentialsSaving(true);
        builder.setAPIUrl("https://khipu.com/app/enc/");
        builder.setAutomatonTimeout(90);
        builder.setClearCookiesBeforeStart(true);
        builder.setDecimalSeparator('.');
        builder.setGroupingSeparator(',');
        builder.setSkipExitPage(false);
        builder.setApplication(this.app);
        builder.setDebug(false);
        builder.setFontResourceId(R.font.nunitosans);
        builder.setMainButtonStyle(Khenshin.CONTINUE_BUTTON_IN_FORM);
        builder.setHideWebAddressInformationInForm(true);
        builder.build();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        khenshinModule = new RNKhenshinModule(reactContext);
        return Arrays.<NativeModule>asList(khenshinModule);
    }

    // Deprecated from RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

}
