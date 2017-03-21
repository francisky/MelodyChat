package www.melody.chat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * Created by ZhengSheng on 2017/3/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    // 数据库名
    private static final String DB_NAME = "saccelerator.db";
    // 数据库版本
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建表
        sqLiteDatabase.execSQL(ServerDataSource.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
