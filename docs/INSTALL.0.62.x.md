# Install instructions for react-native 0.62.x

## iOS

> iOS implementation is still in progress

[//]: <> (1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`)
[//]: <> (2. Go to `node_modules` ➜ `react-native-khenshin` and add `RNKhipu.xcodeproj`)
[//]: <> (3. In XCode, in the project navigator, select your project. Add `libRNKhipu.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`)
[//]: <> (4. Run your project `Cmd+R`)

## Android

1. Append the following lines to `android/settings.gradle`:

```
include ':react-native-khenshin'
project(':react-native-khenshin').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-khenshin/android')
```

2. Add the following lines inside the dependencies block in `android/app/build.gradle`:

```
implementation 'com.browser2app:khenshin:5.4.2'
implementation project(':react-native-khenshin')
```

3. Add the following lines to tour `android/build.gradle`:

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

4. Add The following modifications in your `MainApplication.java`

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

5. Add the following code inside the `getPackages()` method
  
```
@Override
protected List<ReactPackage> getPackages() {
    //...
    khipuPackage = new RNKhenshinPackage(mainApp);
    packages.add(khipuPackage);
    return packages;
}
```

6. Add or edit `react-native.config.js`

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
