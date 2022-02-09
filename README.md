# WePlay Game SDK

see [com.weplay.game.GameActivity](https://github.com/WePlayGaming/WePlay-Game-Plane/blob/master/game-plane/src/main/java/com/weplay/game/GameActivity.java) from [DEMO](https://github.com/WePlayGaming/WePlay-Game-Plane)

https://github.com/WePlayGaming/WePlay-Game-Plane

## 1.Step One init

### 1. Add aar library

![image](aar.png)

### 2. implementation aar

```groovy
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.aar'])
    implementation 'com.google.code.gson:gson:2.8.0'
}
```

### 3. proguard

```groovy
##############################  WePlay  ################################
-keep class com.weplay.WePlay{public static <methods>;}
-keep public interface com.weplay.WePlay$*{*;}
-keep class com.weplay.WePlayServer{public <methods>;}
-keep class com.weplay.message.**{*;}
## Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}
-keep class * extends java.lang.Enum { *; }
-keep interface * extends java.lang.Enum { *; }
```

### 4. set YOUR_APP_KEY

```xml
<application
    android:name=".GameApplication">
    <meta-data
        android:name="WE_PLAY_APP_KEY"
        android:value="${YOUR_APP_KEY}" />
</application>
```

### 5. initialization WePlay SDK

```java
public class GameApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WePlay.init(this);
    }
}
```
## 2.Step Two use

### 1. Auto Login with WePlay

```java
WePlay.setOnAccountCallback(new WePlay.OnAccountCallback() {
    @Override
    public boolean onLogin(WePlayUser wePlayUser) {
        System.out.println(wePlayUser.userId + "<>" + wePlayUser.userName);
        return true;//if use this login info return true
    }

    @Override
    public boolean onLogout() {
        return true;//if logout return true
    }
});
```

### 2. send gamer's score

```java
WePlay.sendScore((long)score)
```

### 3. Great Moment

```java
WePlay.greatMoment()
```

### 4. Advertisement

```java
//show rewardedVideo ad
WePlay.showAd(AdType.TYPE_REWARDED_VIDEO, adId);
//show interstitial ad
WePlay.showAd(AdType.TYPE_INTERSTITIAL, null);
//listen ad callback
WePlay.setOnAdCallback(adId -> {
    //Reward users
});
```

