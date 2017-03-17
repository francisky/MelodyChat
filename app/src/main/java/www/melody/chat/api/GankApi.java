package www.melody.chat.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import www.melody.chat.domain.GankData;
import www.melody.chat.domain.MeiziData;

/**
 * Created by zhengsheng on 16/7/10.
 */
public interface GankApi {

    @GET("data/福利/10/{page}")
    Call<MeiziData> getMeiziData(@Path("page") int page);

//    @GET("data/休息视频/" + PanConfig.MEIZI_SIZE + "/{page}")
//    Observable<休息视频Data> get休息视频Data(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

//    @GET("data/{type}/"+PanConfig.GANK_SIZE+"/{page}")
//    Observable<BatteryData> getBatteryData(@Path("type") String type,@Path("page") int page);
}
