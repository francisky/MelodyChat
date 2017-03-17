package www.melody.chat;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by zhengsheng on 16/7/9.
 */
public class MelodyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Init Stetho
        Stetho.initializeWithDefaults(this);
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());

        // Init Bugly
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        //...在这里设置strategy的属性，在bugly初始化时传入
        strategy.setAppChannel("myChannel");  //设置渠道
        strategy.setAppVersion("1.0.1");      //App的版本
        strategy.setAppPackageName("www.melody.chat");  //App的包名
        // Bugly会在启动10s后联网同步数据。若您有特别需求，可以修改这个时间。
        strategy.setAppReportDelay(20000);   //改为20s
        /* 为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。
           第三个参数为SDK调试模式开关，调试模式的行为特性如下：
           输出详细的Bugly SDK的Log；
           每一条Crash都会被立即上报；
           自定义日志将会在Logcat中输出。
           建议在测试阶段建议设置成true，发布时设置为false。*/
        CrashReport.initCrashReport(getApplicationContext(), "041c807d13", true);
        /* 自定义标签，用于标明App的某个“场景”。
           在发生Crash时会显示该Crash所在的“场景”，以最后设置的标签为准，标签id需大于0。
           例：当用户进入界面A时，打上9527的标签*/
        CrashReport.setUserSceneTag(this, 9527); // 上报后的Crash会显示该标签
    }
}
