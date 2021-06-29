-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class * extends androidx.fragment.app.Fragment

-keepattributes LineNumberTable, SourceFile

-keep class com.hiczp.bilibili.api.passport.model.LoginResponse$** {
    <fields>;
}