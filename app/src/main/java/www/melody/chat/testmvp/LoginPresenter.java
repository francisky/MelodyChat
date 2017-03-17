package www.melody.chat.testmvp;

/**
 * Created by zhengsheng on 16/7/10.
 */
public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}

