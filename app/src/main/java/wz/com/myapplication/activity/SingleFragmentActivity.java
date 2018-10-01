package wz.com.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import wz.com.myapplication.R;

/**
 * 抽象出来的Activity
 * Created by Administrator on 2018/8/21 0021.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("SingleFragmentActivity","进来了");
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.id_fragment_container);

        if(fragment == null){
            fragment = createFragment() ;
            fm.beginTransaction().add(R.id.id_fragment_container,fragment).commit();
        }
    }
}
