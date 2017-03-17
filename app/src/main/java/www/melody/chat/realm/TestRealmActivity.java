package www.melody.chat.realm;

import android.os.Bundle;

import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import www.melody.chat.R;
import www.melody.chat.common.base.MBaseActivity;

public class TestRealmActivity extends MBaseActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_realm);
        setTitle("Realm");
        // ButterKnife
        ButterKnife.bind(this);

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        mRealm = Realm.getInstance(realmConfig);
    }

    @OnClick(R.id.btn_select)
    void select() {
        // 查询全部
        testQuery();
        // 查询年龄小于15的User
        testQueryAgeLessThan15();
        // 得到所有人的平均年龄
        testQueryAverageAge();
    }

    @OnClick(R.id.btn_insert)
    void insert() {
        mRealm.beginTransaction();

        for (int i = 0; i < 10; i++) {
            User user = mRealm.createObject(User.class, UUID.randomUUID().toString());
            user.setName("user" + i);
            user.setAge(10 + i);
        }

        showToast("TestRealm --> 10条数据添加成功");
        mRealm.commitTransaction();
    }

    @OnClick(R.id.btn_update)
    void update() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", "user9").findFirst();
                if (user != null) {
                    user.setAge(99);
                    user.setName("二逼青年");
                }
                showToast("更新成功");
            }
        });
    }

    @OnClick(R.id.btn_delete)
    void delete() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).equalTo("name", "user0").findFirst();
                if (user != null)
                    user.deleteFromRealm();
                showToast("删除成功");
            }
        });
    }

    /**
     * 查询全部
     */
    public void testQuery() {
        List<User> users = mRealm.where(User.class).findAll();
        for (User user : users) {
            showToast("查询全部 --> id:" + user.getId() + " name:" + user.getName() + " age:" + user.getAge());
        }
    }

    /**
     * 条件查询，Realm 支持以下查询条件（来源于官网）：
     * between()、greaterThan()、lessThan()、greaterThanOrEqualTo() 和 lessThanOrEqualTo()
     * equalTo() 和 notEqualTo()
     * contains()、beginsWith() 和 endsWith()
     * isNull() 和 isNotNull()
     * isEmpty() 和 isNotEmpty()
     * 以下代码片段查询年龄小于15的User
     */
    public void testQueryAgeLessThan15() {
        List<User> users = mRealm.where(User.class).lessThan("age", 15).findAll();
        for (User user : users) {
            showToast("查询年龄小于15的User --> id:" + user.getId() + " name:" + user.getName() + " age:" + user.getAge());
        }
    }

    /**
     * 聚合查询，支持的聚合操作有sum，min，max，average
     * <p>
     * 以下代码片段得到所有人的平均年龄
     */
    public void testQueryAverageAge() {
        double age = mRealm.where(User.class).findAll().average("age");
        showToast("查询所有人的平均年龄 --> average age:" + age);
    }
}