
# react-native-khipu

## Getting started

`$ npm install react-native-khenshin --save`

### Mostly automatic installation

`$ react-native link react-native-khenshin`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-khenshin` and add `RNKhipu.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNKhipu.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Append the following lines to `android/settings.gradle`:

```
include ':react-native-khenshin'
project(':react-native-khenshin').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-khenshin/android')
```

2. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

```
implementation 'com.browser2app:khenshin:5.3.5'
implementation project(':react-native-khenshin')
```

3. Implements the `KhenshinApplication` interface in `MainApplication.java` (**react-native >= 0.60.0**):

```java
// ...
import com.browser2app.khenshin.KhenshinApplication;
import com.browser2app.khenshin.KhenshinInterface;
import com.browser2app.rn.RNKhenshinPackage;
// ...

// IMPORTANT!!!: Added ", KhipuApplication" to the implements list
public class MainApplication extends Application implements ReactApplication, KhenshinApplication {
  // ...
  // --> Add the following code
  private RNKhipuPackage khenshinPackage;

  public RNKhenshinPackage getKhenshinPackage() {
    if (khenshinPackage == null) {
      khenshinPackage = new RNKhenshinPackage(this);
    }

    return khenshinPackage;
  }

  @Override
  public KhenshinInterface getKhenshin() {
    return getKhenshinPackage().getKhenshin();
  }
  // --> End
  // ...
}
```

4. In `MainApplication.java`, inside the `getPackages()` method, add the bellow code:

```java
@Override
protected List<ReactPackage> getPackages() {
    // ...
    packages.add(getKhenshinPackage());

    return packages;
}
```

6. If you are using **react-native >= 0.60.0** add into `react-native.config.js`:

```javascript
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

## Usage

```javascript
import Khipu from 'react-native-khenshin';

Khipu.startPaymentById('paymentId').then(payment => console.log(payment));
```
