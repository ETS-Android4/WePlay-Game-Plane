-repackageclasses
-optimizationpasses 10
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,EnclosingMethod
-dontpreverify
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepclasseswithmembernames class * {native <methods>;}
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}
-keep class * extends java.lang.Enum { *; }
-keep interface * extends java.lang.Enum { *; }
-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }
-dontwarn android.support.**
##############################  bean  ################################
-keepclassmembernames class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}
##############################  WePlay  ################################
-keep class com.weplay.WePlay{public static <methods>;}
-keep public interface com.weplay.WePlay$*{*;}
-keep class com.weplay.WePlayServer{public <methods>;}
-keep class com.weplay.message.**{*;}
## Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod