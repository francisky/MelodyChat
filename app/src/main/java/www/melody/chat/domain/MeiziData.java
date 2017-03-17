package www.melody.chat.domain;

import java.util.List;

/**
 * Created by zhengsheng on 16/7/10.
 */
public class MeiziData extends BaseData {
    public List<Meizi> results;

    @Override
    public String toString() {
        return "MeiziData{" +
                "results=" + results +
                '}';
    }
}
