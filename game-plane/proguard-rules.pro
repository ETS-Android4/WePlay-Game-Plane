-repackageclasses
##-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 10
#-dontusemixedcaseclassnames # 是否使用大小写混合 混淆时不会产生形形色色的类名
#-verbose # 混淆时记录日志
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,EnclosingMethod

#-dontskipnonpubliclibraryclasses# 不混淆第三方引用的库
#-dontskipnonpubliclibraryclassmembers
#指定不对处理后的类文件进行预校验。默认情况下如果类文件的目标平台是 JavaMicro Edition 或 Java 6 或更高时会进行预校验。目标平台是 Android时没必要开启，关闭可减少处理时间。
-dontpreverify
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepclasseswithmembernames class * {native <methods>;} # 保持 native 方法不被混淆
-keepclassmembers enum * {                  # 保持枚举 enum 类不被混淆
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