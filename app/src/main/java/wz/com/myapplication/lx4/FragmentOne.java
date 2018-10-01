package wz.com.myapplication.lx4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class FragmentOne extends Fragment implements View.OnClickListener{
    private Button mBtn;
    private Button mBtnRecyleView;
    private Button mBtnRequest;
    private TextView mTvResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_one, container, false);
        mBtn= (Button) view.findViewById(R.id.fragment_one_btn);
        mBtnRecyleView = (Button) view.findViewById(R.id.btn_recyleview);
        mBtnRequest = (Button) view.findViewById(R.id.btn_request);
        mTvResult = (TextView)  view.findViewById(R.id.tv_result);
        mBtn.setOnClickListener(this);
        mBtnRecyleView.setOnClickListener(this);
        mBtnRequest.setOnClickListener(this);
        return view;
    }

    /**
     * 交由activity处理，如果他希望处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn_recyleview:
                FragmentRecyleView fragmentRecyleView = new FragmentRecyleView();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.id_content,fragmentRecyleView);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.fragment_one_btn:
                if(getActivity() instanceof FOneBtnClickListener){
                    ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
                }
                break;
            case R.id.btn_request:
                getResult();
        }
    }

    public interface FOneBtnClickListener{
        void onFOneBtnClick();
    }
    public void getResult(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GankApi api = retrofit.create(GankApi.class);
        Call<GankBean> call = api.getAndroidInfo();
        call.enqueue(new Callback<GankBean>() {
            @Override
            public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                String result = response.body().getResults().get(0).getDesc();
                Log.e("getResult",result);
                mTvResult.setText(result);
            }

            @Override
            public void onFailure(Call<GankBean> call, Throwable t) {

            }
        });
    }
}
