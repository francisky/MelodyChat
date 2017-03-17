package www.melody.chat.domain;

import java.util.Date;

/**
 * Created by zhengsheng on 16/7/10.
 */
public class Meizi extends Fundation {
    public String who;
    public String url;
    public String type;
    public String desc;
    public boolean used;
    public Date createdAt;
    public Date updatedAt;
    public Date publishedAt;

    @Override
    public String toString() {
        return "Meizi{" +
                "who='" + who + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                ", used=" + used +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
