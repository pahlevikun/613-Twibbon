# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/adt-bundle-mac-x86_64-20140702/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#-keep class * {
#    public private *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

# Setting
-optimizationpasses 5
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dontusemixedcaseclassnames
-dontoptimize
-ignorewarnings

-dontwarn org.apache.commons.logging.**
-dontwarn java.**
-dontwarn javax.**
-dontwarn javax.annotation.*

-keepattributes Signature
-keepattributes Exceptions

-keep class com.crashlytics.** { public private *; }
-keep class com.google.firebase.** { public private  *; }
-keep class id.pahlevikun.twibbon613.** { public private *;}
-keep class com.esafirm.imagepicker.** { public private *;}

-printmapping mapping.txt