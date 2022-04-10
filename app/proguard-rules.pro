-keepclasseswithmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class * extends androidx.fragment.app.Fragment

-keepattributes LineNumberTable, SourceFile, RuntimeVisibleAnnotations, Signature

-keep class com.hiczp.bilibili.api.passport.model.LoginResponse$** {
    <fields>;
}

-keepclassmembers class * extends com.duzhaokun123.bilibilihd2.ui.main.DynamicAdapter$BaseDynamicHolder {
    public <init>(android.content.Context);
}

-keepnames class io.github.duzhaokun123.androidapptemplate.bases.BaseActivity {
    private androidx.databinding.ViewDataBinding baseBinding;
}

-keepclassmembers class * {
    @io.github.duzhaokun123.lazyjson.annotation.LazyjsonFrom <methods>;
}
