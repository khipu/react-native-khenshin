# Install instructions for react-native from 0.60.x to 0.62.x

## iOS

> iOS implementation is still in progress

Add the following line to yout Podfile

    pod 'RNKhenshin', :path => '../node_modules/react-native-khenshin'


The run pod install

    pod install


## Android

### Append the following lines to `android/settings.gradle`:

```
include ':react-native-khenshin'
project(':react-native-khenshin').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-khenshin/android')
```

### Add the following lines inside the dependencies block in `android/app/build.gradle`:

```
implementation 'com.browser2app:khenshin:5.4.2'
implementation project(':react-native-khenshin')
```

### Add the following lines to tour `android/build.gradle`:

```
allprojects {
    repositories {
      ....
      maven {
        url 'https://dev.khipu.com/nexus/content/repositories/khenshin'
      }
      ...
    }
}
```

### Add The following modifications in your `MainApplication.java`

```
....
import com.browser2app.khenshin.KhenshinApplication;
import com.browser2app.khenshin.KhenshinInterface;
import com.browser2app.rn.RNKhenshinPackage;

// IMPORTANT!!!: Add ", KhenshinApplication" to the implements list
public class MainApplication extends Application implements ReactApplication, KhenshinApplication {
  // --> Add the following code 
  private MainApplication mainApp;
  private RNKhenshinPackage khipuPackage;

  public MainApplication() {
      mainApp = this;
  }

  @Override
  public KhenshinInterface getKhenshin() {
      return khipuPackage.getKhenshin();
  }
  // --> End
  
  ... 
}

```

### Add the following code inside the `getPackages()` method
  
```
@Override
protected List<ReactPackage> getPackages() {
    //...
    khipuPackage = new RNKhenshinPackage(mainApp);
    packages.add(khipuPackage);
    return packages;
}
```

### Add or edit `react-native.config.js`

```
// react-native.config.js
module.exports = {
  dependencies: {
    'react-native-khenshin': {
      platforms: {
        android: null, // disable Android platform, other platforms will still autolink if provided
      },
    },
  },
};

```
