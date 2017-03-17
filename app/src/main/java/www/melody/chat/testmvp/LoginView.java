package www.melody.chat.testmvp;

/**
 * Created by zhengsheng on 16/7/10.
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
