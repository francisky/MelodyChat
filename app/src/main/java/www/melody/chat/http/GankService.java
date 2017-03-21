package www.melody.chat.http;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 接口服务
 * Created by ZhengSheng on 2017/3/20.
 */

public interface GankService {

//    http://gank.io/api/data/拓展资源/10/1
    @GET("data/拓展资源/10/{page}")
    Observable<ApiResponse<List<GankBean>>> getData(@Path("page") int page);

}