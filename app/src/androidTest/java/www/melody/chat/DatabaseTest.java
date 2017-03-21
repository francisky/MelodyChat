package www.melody.chat;

import www.melody.chat.database.DBHelper;
import www.melody.chat.database.Server;
import www.melody.chat.database.ServerDataSource;

/**
 * 数据库操作的测试类
 * Created by ZhengSheng on 2017/3/20.
 */

public class DatabaseTest extends ApplicationTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // before
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // after
    }

    public void testInsert() {
        System.out.println("DatabaseTest.testInsert");
        ServerDataSource serverDataSource = new ServerDataSource(new DBHelper(getContext()));
        long insert = serverDataSource.insert(new Server("2kj234", "11.23.23.11", "www.bai.com"));
        System.out.println("insert result :" + insert);
    }

}