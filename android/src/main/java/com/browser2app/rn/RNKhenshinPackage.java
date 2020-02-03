package com.browser2app.rn;

import android.app.Application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.browser2app.khenshin.KhenshinApplication;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import com.browser2app.khenshin.Khenshin;
import com.browser2app.khenshin.KhenshinInterface;


public class RNKhenshinPackage implements ReactPackage, KhenshinApplication {
    private Khenshin khenshin;
    private Application app;
    RNKhenshinModule khenshinModule;

    public RNKhenshinPackage(Application app) {
        this.app = app;
        Khenshin.KhenshinBuilder builder = new Khenshin.KhenshinBuilder();
        builder.setAllowCredentialsSaving(true);
        builder.setAPIUrl("https://khipu.com/app/enc/");
        builder.setAutomatonTimeout(60);
        builder.setAutoSubmitIfComplete(true);
        builder.setClearCookiesBeforeStart(true);
        builder.setDecimalSeparator('.');
        builder.setGroupingSeparator(',');
        builder.setSkipExitPage(false);
        builder.setApplication(this.app);
        this.khenshin = builder.build();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        khenshinModule = new RNKhenshinModule(reactContext, this.khenshin);
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

    @Override
    public KhenshinInterface getKhenshin() {
        return this.khenshin;
    }
}
