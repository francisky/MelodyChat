package www.melody.chat.main.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.melody.chat.R;
import www.melody.chat.common.base.MBaseActivity;
import www.melody.chat.common.util.UnreadMsgUtils;
import www.melody.chat.main.fragment.ContactsFragment;
import www.melody.chat.main.fragment.MessageFragment;
import www.melody.chat.main.fragment.NewsFragment;
import www.melody.chat.main.fragment.SettingFragment;
import www.melody.chat.widget.MsgView;

/**
 * @author zhengxiuyuan
 * @date 2016/10/14
 * <p>
 * 主页面
 */
public class MainActivity extends MBaseActivity {

    /**
     * 用于展示消息的Fragment
     */
    private MessageFragment messageFragment;

    /**
     * 用于展示联系人的Fragment
     */
    private ContactsFragment contactsFragment;

    /**
     * 用于展示动态的Fragment
     */
    private NewsFragment newsFragment;

    /**
     * 用于展示设置的Fragment
     */
    private SettingFragment settingFragment;

    /**
     * 消息界面布局
     */
    @BindView(R.id.message_layout)
    View messageLayout;

    /**
     * 联系人界面布局
     */
    @BindView(R.id.contacts_layout)
    View contactsLayout;

    /**
     * 动态界面布局
     */
    @BindView(R.id.news_layout)
    View newsLayout;

    /**
     * 设置界面布局
     */
    @BindView(R.id.setting_layout)
    View settingLayout;

    /**
     * 在Tab布局上显示消息图标的控件
     */
    @BindView(R.id.message_image)
    ImageView messageImage;

    /**
     * 在Tab布局上显示联系人图标的控件
     */
    @BindView(R.id.contacts_image)
    ImageView contactsImage;

    /**
     * 在Tab布局上显示动态图标的控件
     */
    @BindView(R.id.news_image)
    ImageView newsImage;

    /**
     * 在Tab布局上显示设置图标的控件
     */
    @BindView(R.id.setting_image)
    ImageView settingImage;

    /**
     * 在Tab布局上显示消息标题的控件
     */
    @BindView(R.id.message_text)
    TextView messageText;

    /**
     * 在Tab布局上显示联系人标题的控件
     */
    @BindView(R.id.contacts_text)
    TextView contactsText;

    /**
     * 在Tab布局上显示动态标题的控件
     */
    @BindView(R.id.news_text)
    TextView newsText;

    /**
     * 在Tab布局上显示设置标题的控件
     */
    @BindView(R.id.setting_text)
    TextView settingText;

    /**
     * 在Tab布局上显示联系人的未读消息徽章控件
     */
    @BindView(R.id.msg_contacts)
    MsgView contactsMsg;

    /**
     * 在Tab布局上显示消息的未读消息徽章控件
     */
    @BindView(R.id.msg_message)
    MsgView messageMsg;

    /**
     * 在Tab布局上显示动态的未读消息徽章控件
     */
    @BindView(R.id.msg_news)
    MsgView newsMsg;

    /**
     * 在Tab布局上显示设置的未读消息徽章控件
     */
    @BindView(R.id.msg_setting)
    MsgView settingMsg;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    /**
     * 保存当前显示的是第几页
     */
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setToolbar();
        initFragments(savedInstanceState);

        UnreadMsgUtils.show(newsMsg, 22);
        UnreadMsgUtils.show(messageMsg, 100);
        UnreadMsgUtils.show(contactsMsg, 5);
        contactsMsg.setBackgroundColor(Color.parseColor("#6D8FB0"));
        UnreadMsgUtils.show(settingMsg, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化主页面上的Fragment
     * @param savedInstanceState
     */
    private void initFragments(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            currentPage = savedInstanceState.getInt("neo");
            Log.i("neo", "onCreate " + currentPage);
        }
        fragmentManager = getFragmentManager();
        //在FragmentManager里面根据Tag去找相应的fragment. 用户屏幕发生旋转，重新调用onCreate方法。否则会发生重叠
        messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
        contactsFragment = (ContactsFragment) fragmentManager.findFragmentByTag("contacts");
        newsFragment = (NewsFragment) fragmentManager.findFragmentByTag("news");
        settingFragment = (SettingFragment) fragmentManager.findFragmentByTag("setting");
        // 第一次启动时选中第0个tab
        setTabSelection(currentPage);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_action_accounts);//设置导航栏图标
//        toolbar.setLogo(R.mipmap.app_logo);//设置app logo
        toolbar.setTitle("MelodyChat");//设置主标题
//        toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setTitleTextColor(Color.WHITE);//设置颜色
//        toolbar.setTitleTextAppearance();//设置大小
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToast("NavigationOnClick");
//            }
//        });
        toolbar.inflateMenu(R.menu.main_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_search:
                        showToast("onMenuItemClick search");
                        break;
                    case R.id.action_notification:
                        showToast("onMenuItemClick notification");
                        break;
                    case R.id.action_add:
                        showToast("onMenuItemClick add");
                        break;
                    default:
                        showToast("onMenuItemClick default ");
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    @OnClick({R.id.message_layout, R.id.contacts_layout, R.id.news_layout, R.id.setting_layout})
    public void onTabClick(View v) {
        switch (v.getId()) {
            case R.id.news_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(0);
                currentPage = 0;
                break;
            case R.id.message_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(1);
                currentPage = 1;
                break;
            case R.id.contacts_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(2);
                currentPage = 2;
                break;
            case R.id.setting_layout:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
                currentPage = 3;
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。0表示动态，1表示消息，2表示联系人，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        //Log.i("neo", "消息为空？ "+messageFragment+"--联系人为空？ "+contactsFragment+"-- 动态为空？ "+newsFragment+"-- 设置为空？ "+settingFragment);
        switch (index) {
            case 0:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                newsImage.setImageResource(R.mipmap.tab_home_select);
                newsText.setTextColor(Color.parseColor("#2C97DE"));
                //	newsFragment = (NewsFragment) fragmentManager.findFragmentByTag("news");
                if (newsFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment, "news");
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(newsFragment);
                }
                break;
            case 1:
                // 当点击了消息tab时，改变控件的图片和文字颜色
                messageImage.setImageResource(R.mipmap.tab_speech_select);
                messageText.setTextColor(Color.parseColor("#2C97DE"));
                //messageFragment = (MessageFragment) fragmentManager.findFragmentByTag("message");
                if (messageFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content, messageFragment, "message");
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(messageFragment);
                }

                break;
            case 2:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                contactsImage.setImageResource(R.mipmap.tab_contact_select);
                contactsText.setTextColor(Color.parseColor("#2C97DE"));
                //	contactsFragment = (ContactsFragment) fragmentManager.findFragmentByTag("contacts");
                if (contactsFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    contactsFragment = new ContactsFragment();
                    transaction.add(R.id.content, contactsFragment, "contacts");
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(contactsFragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                settingImage.setImageResource(R.mipmap.tab_more_select);
                settingText.setTextColor(Color.parseColor("#2C97DE"));
                //	settingFragment = (SettingFragment) fragmentManager.findFragmentByTag("setting");
                if (settingFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment, "setting");
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        messageImage.setImageResource(R.mipmap.tab_speech_unselect);
        messageText.setTextColor(Color.parseColor("#82858b"));
        contactsImage.setImageResource(R.mipmap.tab_contact_unselect);
        contactsText.setTextColor(Color.parseColor("#82858b"));
        newsImage.setImageResource(R.mipmap.tab_home_unselect);
        newsText.setTextColor(Color.parseColor("#82858b"));
        settingImage.setImageResource(R.mipmap.tab_more_unselect);
        settingText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("neo", currentPage);
        super.onSaveInstanceState(outState);
        Log.i("neo", "onSaveInstanceState " + currentPage);
    }
}