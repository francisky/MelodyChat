package www.melody.chat.database;

import java.io.Serializable;

/**
 * 数据库中server表对应的JavaBean
 * 这里是作为测试
 * Created by ZhengSheng on 2017/3/18.
 */

public class Server implements Serializable {

    private int _id;
    private String serverId;
    private String ip;
    private String domain;

    public Server() {

    }

    public Server(String serverId, String ip, String domain) {
        this.serverId = serverId;
        this.ip = ip;
        this.domain = domain;
    }

    public Server(int _id, String serverId, String ip, String domain) {
        this._id = _id;
        this.serverId = serverId;
        this.ip = ip;
        this.domain = domain;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return "Server{" +
                "_id=" + _id +
                ", serverId=" + serverId +
                ", ip='" + ip + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
