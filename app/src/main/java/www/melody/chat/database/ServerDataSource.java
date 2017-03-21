package www.melody.chat.database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库中server表的管理类
 * Created by ZhengSheng on 2017/3/18.
 */

public class ServerDataSource extends BaseDataSource<Server> {

    /**
     * 表名
     */
    private static final String TABLE_NAME = "server";

    /**
     * 表中字段名称
     */
    private interface Colum {
        String _ID = "_id";
        String SERVER_ID = "server_id";
        String IP = "ip";
        String DOMAIN = "domain";
    }

    /**
     * 创建表的sql语句
     * 在DBHelper中使用并创建表
     */
    public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME
            + "(" + Colum._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Colum.SERVER_ID + " VARCHAR(60) UNIQUE NOT NULL, "
            + Colum.IP + " VARCHAR(50), "
            + Colum.DOMAIN + " VARCHAR(50));";

    public ServerDataSource(DBHelper dbHelper) {
        super(dbHelper);
    }

    /**
     * 新增一条记录
     * @param server
     */
    @Override
    public long insert(Server server) {
        return insert(server.getServerId(), server.getIp(), server.getDomain());
    }

    public synchronized long insert(String serverId, String ip, String domain) {
        ContentValues values = new ContentValues();
        values.put(Colum.SERVER_ID, serverId);
        values.put(Colum.IP, ip);
        values.put(Colum.DOMAIN, domain);
        long result = mDb.insert(TABLE_NAME, null, values);
        return result;
    }

    /**
     * 更新某一条记录
     * @param server
     */
    @Override
    public int update(Server server) {
        return update(server.getServerId(), server.getIp(), server.getDomain());
    }

    public synchronized int update(String serverId, String ip, String domain) {
        ContentValues values = new ContentValues();
        values.put(Colum.IP, ip);
        values.put(Colum.DOMAIN, domain);
        int result = mDb.update(TABLE_NAME, values, "where " + Colum.SERVER_ID + "=?", new String[]{serverId});
        return result;
    }

    /**
     * 插入或更新记录
     * 先查询该记录是否存在
     * 不存在执行插入操作，存在执行更新操作
     * @param server
     * @return
     */
    @Override
    public long insertOrUpdate(Server server) {
        return insertOrUpdate(server.getServerId(), server.getIp(), server.getDomain());
    }

    public long insertOrUpdate(String serverId, String ip, String domain) {
        // 先判断该数据是否存在，已经存在就更新数据，不存在才插入该数据
        if (isExist(serverId)) {
            return update(serverId, ip, domain);
        } else {
            return insert(serverId, ip, domain);
        }
    }

    /**
     * 批量插入记录
     * 加入事务处理
     * @param serverList
     * @return
     */
    @Override
    public boolean insertList(List<Server> serverList) {
        boolean result = false;
        // 批量插入开启事务能提高效率
        mDb.beginTransaction();
        try {
            for (Server server : serverList) {
                insertOrUpdate(server);
            }
            mDb.setTransactionSuccessful();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDb.endTransaction();
        }

        return result;
    }


    /**
     * 根据ID查询某条记录
     *
     * @param findId
     * @return
     */
    @Override
    public Server find(String findId) {
        Server server = null;
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + Colum.SERVER_ID + "=?", new String[]{findId});
        if (cursor.moveToFirst()) {
            int _id = cursor.getInt(cursor.getColumnIndex(Colum._ID));
            String ip = cursor.getString(cursor.getColumnIndex(Colum.IP));
            String domain = cursor.getString(cursor.getColumnIndex(Colum.DOMAIN));
            server = new Server(_id, findId, ip, domain);
        }

        cursor.close();
        return server;
    }

    @Override
    public List<Server> findAll() {
        List<Server> result = new ArrayList<>();
        Cursor cursor = mDb.query(TABLE_NAME, new String[]{Colum._ID, Colum.SERVER_ID, Colum.IP, Colum.DOMAIN}, null, null, null, null, null);
        while (cursor.moveToFirst()) {
            int _id = cursor.getInt(cursor.getColumnIndex(Colum._ID));
            String serverId = cursor.getString(cursor.getColumnIndex(Colum.SERVER_ID));
            String ip = cursor.getString(cursor.getColumnIndex(Colum.IP));
            String domain = cursor.getString(cursor.getColumnIndex(Colum.DOMAIN));
            result.add(new Server(_id, serverId, ip, domain));
        }

        cursor.close();
        return result;
    }

    /**
     * 删除某一条记录
     *
     * @param deletId
     */
    @Override
    public synchronized int delete(String deletId) {
        int result = mDb.delete(TABLE_NAME, Colum.SERVER_ID + "=?", new String[]{deletId});
        return result;
    }

    /**
     * 清空表中的所有记录
     */
    @Override
    public void clear() {
        mDb.delete("delete from " + TABLE_NAME, null, null);
    }


    /**
     * 根据serverId判断对应的记录是否存在
     *
     * @return
     */
    @Override
    public boolean isExist(String searchId) {
        Cursor cursor = mDb.rawQuery("select " + Colum._ID + " from " + TABLE_NAME + " where " + Colum.SERVER_ID + "=?", new String[]{searchId});
        boolean result = cursor.moveToFirst();
        cursor.close();
        return result;
    }

    /**
     * 得到记录数
     *
     * @return
     */
    @Override
    public long getCount() {
        long result = 0;
        Cursor cursor = mDb.rawQuery("select count(" + Colum._ID + ") from " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            result = cursor.getLong(0);
        }
        cursor.close();
        return result;
    }

}