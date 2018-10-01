package wz.com.myapplication.lx4;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/8/24 0024.
 */

public interface GankApi {

    @GET("api/data/Android/10/1")
    Call<GankBean> getAndroidInfo();
}
