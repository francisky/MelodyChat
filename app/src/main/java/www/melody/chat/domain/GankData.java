package www.melody.chat.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhengsheng on 16/7/10.
 */
public class GankData extends BaseData{
    public List<String> category;
    public Result results;
    public class Result {
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> 休息视频List;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<Gank> 妹纸List;
        @SerializedName("拓展资源") public List<Gank> 拓展资源List;
        @SerializedName("瞎推荐") public List<Gank> 瞎推荐List;
        @SerializedName("App") public List<Gank> appList;
        @SerializedName("前端") public List<Gank> 前端List;

        @Override
        public String toString() {
            return "Result{" +
                    "androidList=" + androidList +
                    ", 休息视频List=" + 休息视频List +
                    ", iOSList=" + iOSList +
                    ", 妹纸List=" + 妹纸List +
                    ", 拓展资源List=" + 拓展资源List +
                    ", 瞎推荐List=" + 瞎推荐List +
                    ", appList=" + appList +
                    ", 前端List=" + 前端List +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GankData{" +
                "category=" + category +
                ", results=" + results +
                '}';
    }
}
