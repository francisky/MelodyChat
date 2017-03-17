package www.melody.chat.realm;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zhengsheng on 2016/10/12.
 * <p>
 * 创建的实体对象继承于RealmObject ，Realm 数据实体定义需要继承自 RealmObject类。
 * 这里需要知道的几点：
 * @PrimaryKey 用来标识主键
 * 默认的所有的字段都会被存储
 * 如果某个字段不需要被存储到本地，则需在在这个字段上面加上 @Ignore 注解
 */

public class User extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private int age;
    private RealmList<User> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<User> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<User> friends) {
        this.friends = friends;
    }

}
