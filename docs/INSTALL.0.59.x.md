# Install instructions for react-native 0.59.x

#Link the library

    react-native link react-native-khenshin

## iOS

> iOS implementation is still in progress

[//]: <> (1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`)
[//]: <> (2. Go to `node_modules` ➜ `react-native-khenshin` and add `RNKhipu.xcodeproj`)
[//]: <> (3. In XCode, in the project navigator, select your project. Add `libRNKhipu.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`)
[//]: <> (4. Run your project `Cmd+R`)

## Android

### Add the following lines inside the dependencies block in `android/app/build.gradle`:

```
implementation 'com.browser2app:khenshin:5.4.2'
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
    khipuPackage = new RNKhenshinPackage(mainApp);
    return Arrays.<ReactPackage>asList(
      new MainReactPackage(),
      khipuPackage
    );
  }
```

```
