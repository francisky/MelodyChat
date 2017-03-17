package www.melody.chat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import www.melody.chat.R;
import www.melody.chat.api.GankApi;
import www.melody.chat.bus.TestPersonBus;
import www.melody.chat.common.base.MBaseActivity;
import www.melody.chat.domain.GankData;
import www.melody.chat.domain.TestPerson;
import www.melody.chat.realm.TestRealmActivity;
import www.melody.chat.testmvp.LoginActivity;

public class MainTestActivity extends MBaseActivity {

    private final String TAG = getClass().getName();

    private TestRecyclerAdapter mAdapter;

    @BindView(R.id.img) ImageView img;

    @BindView(R.id.rv) RecyclerView mRecyclerView;

    @OnClick(R.id.btn_eventbus) void eventBus() {
        Toast.makeText(MainTestActivity.this, "init data", Toast.LENGTH_SHORT).show();
        List<TestPerson> persons = new ArrayList<>();
        persons.add(new TestPerson("韦德", "http://img3.imgtn.bdimg.com/it/u=1138714575,3303061519&fm=21&gp=0.jpg"));
        persons.add(new TestPerson("奥尼尔", "http://news.newhua.com/Files/Remoteupfile/2008-7/10/0611303194642997.jpg"));
        persons.add(new TestPerson("科比", "http://s9.knowsky.com/bizhi/l/45001-55000/200952916350214130639.jpg"));
        persons.add(new TestPerson("马龙", "http://img2.imgtn.bdimg.com/it/u=184760028,2439202149&fm=21&gp=0.jpg"));
        persons.add(new TestPerson("皮蓬", "http://s15.sinaimg.cn/orignal/4c46c7918bac2ce986a3e"));
        persons.add(new TestPerson("艾弗森", "http://e.hiphotos.bdimg.com/album/scrop%3D228%3Bq%3D90/sign=87f4c6e115ce36d3a65ac46f4ace08b5/43a7d933c895d143710ade1873f082025aaf0788.jpg"));
        persons.add(new TestPerson("罗宾逊", "http://img1.qq.com/gamezone/pics/15591/15591599.jpg"));
        persons.add(new TestPerson("乔丹", "http://img3.imgtn.bdimg.com/it/u=522522022,1676282891&fm=21&gp=0.jpg"));
        persons.add(new TestPerson("詹姆斯", "http://g.hiphotos.bdimg.com/wisegame/pic/item/fd2f070828381f3047d2bfa9aa014c086f06f0e3.jpg"));
        // EventBus post
        EventBus.getDefault().post(new TestPersonBus(persons));
    }

    @OnClick(R.id.btn_realm) void realm() {
        startActivity(new Intent(this, TestRealmActivity.class));
    }

    @OnClick(R.id.btn_mvp) void mvp() {
        this.finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.btn_bugly) void bugly() {
        // 测试
        // 现在您可以制造一个Crash（建议通过“按键”来触发），来体验Bugly的能力了。在初始化Bugly的之后，调用Bugly测Java Crash接口。
        CrashReport.testJavaCrash();
    }

    @OnClick(R.id.btn_logger) void logger() {
        // test Logger
        String json = "{\"name\" : \"XiaoMing\", \"age\" : 22}";
        String xml = "<person name=\"ZhangSan\"><phone>13312345678</phone><address>德玛西亚</address></person>";
        Logger.init(TAG); // init
        Logger.d("Hello %s", "Logger");
        Logger.e("Logger e");
        Logger.json(json);
        Logger.xml(xml);
    }

    @OnClick(R.id.btn_retrofit_rxjava) void retrofitRxjava() {
        final String BASE_URL = "http://gank.io/api/";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
//        // 重复创建会报错
//        OkHttpClient httpClient = new OkHttpClient();
        // 添加拦截器
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        Retrofit retrofit = builder.client(httpClient).build();
//        Retrofit retrofit = builder.build();
        GankApi gankApi = retrofit.create(GankApi.class);
        Observable<GankData> observable = gankApi.getDailyData(2016, 7, 11);

        Subscriber subscriber = new Subscriber<GankData>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainTestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainTestActivity.this, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GankData entity) {
                Toast.makeText(MainTestActivity.this, entity.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        // register EventBus
        EventBus.getDefault().register(this);
        // test ButterKnife
        ButterKnife.bind(this);

        // test gif Glide
        MainTestActivity.display(this, img, "http://www.cneln.com/editor/uploadfile/201110241319462726.jpg");

        // test RecyclerView
        mAdapter = new TestRecyclerAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //设置布局管理器
        mRecyclerView.setAdapter(mAdapter); //设置adapter
        mRecyclerView.setItemAnimator(new DefaultItemAnimator()); //设置Item增加、移除动画
    }

    @Override
    protected void onDestroy() {
        // unregister EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(TestPersonBus bus) {
        Toast.makeText(MainTestActivity.this, "EventBus onEvent", Toast.LENGTH_SHORT).show();
        mDatas.clear();
        mDatas.addAll(bus.getPersons());
        mAdapter.notifyDataSetChanged();
    }

    public static void display(Context context, ImageView target, String url) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.app_logo)
                .error(R.mipmap.app_logo)
                .into(target);
    }

    //================  Test RecyclerView  ================  begin
    private List<TestPerson> mDatas = new ArrayList<>();

    class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.TestViewHolder> {

        @Override
        public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TestViewHolder holder = new TestViewHolder(LayoutInflater.from(
                    MainTestActivity.this).inflate(R.layout.test_item_recycler, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(TestViewHolder holder, int position) {

            TestPerson person = mDatas.get(position);
            holder.txtName.setText(person.name);
            MainTestActivity.display(MainTestActivity.this, holder.imgIcon, person.icon);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class TestViewHolder extends RecyclerView.ViewHolder {

            TextView txtName;
            ImageView imgIcon;

            public TestViewHolder(View view) {
                super(view);
                txtName = (TextView) view.findViewById(R.id.txt_name);
                imgIcon = (ImageView) view.findViewById(R.id.img_icon);
            }
        }
    }
    //================  Test RecyclerView  ================  end

}
