package www.melody.chat.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by zhengsheng on 16/7/10.
 */
public class MBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*用 supportRequestWindowFeature(Window.FEATURE_NO_TITLE) 去掉了默认的导航栏
        （注意，我的BaseActivity是继承了AppCompatActivity的，
        如果是继承Activity就应该调用 requestWindowFeature(Window.FEATURE_NO_TITLE) ）*/
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 打印吐司
     * @param message
     */
    protected void showToast(String message) {
        if (true) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
