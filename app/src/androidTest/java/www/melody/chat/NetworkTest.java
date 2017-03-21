package www.melody.chat;

import www.melody.chat.http.ApiResponse;
import www.melody.chat.http.BaseSubscriber;
import www.melody.chat.http.GankBean;
import www.melody.chat.http.GankService;
import www.melody.chat.http.RetrofitServiceManager;
import www.melody.chat.utils.ToastUtils;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求的测试类
 * Created by ZhengSheng on 2017/3/20.
 */

public class NetworkTest extends ApplicationTest {

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

    public void testHttp() {
        GankService gankService = RetrofitServiceManager.getInstance().create(GankService.class);
        Observable<ApiResponse<List<GankBean>>> observable = gankService.getData(1);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ApiResponse<List<GankBean>>>() {
                    @Override
                    public void onSuccess(ApiResponse<List<GankBean>> listApiResponse) {
                        ToastUtils.showLongToast("response : onSuccess" + listApiResponse.toString());
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        ToastUtils.showLongToast("response : onFailure");
                    }
                });
    }
}
