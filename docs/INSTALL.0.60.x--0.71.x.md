# Install instructions for react-native from 0.60.x to 0.71.x

## iOS

If you are using an m1 or m2 Macos please run this in a terminal using Rosetta

Run pod install

    cd ios
    pod install
    cd ..


## Android

### Append the following lines to `android/settings.gradle`:

```
include ':react-native-khenshin'
project(':react-native-khenshin').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-khenshin/android')
```

### Add the following lines inside the dependencies block in `android/app/build.gradle`:

```
implementation 'com.browser2app:khenshin:+'
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
...
import com.browser2app.rn.RNKhenshinPackage;
...

public MainApplication getMainApplication() {
    return this;
}
...

```

#### Add the following code inside the `getPackages()` method
  
```
@Override
protected List<ReactPackage> getPackages() {
    //...
    packages.add(new RNKhenshinPackage(getMainApplication()));
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
