package wz.com.myapplication.lx2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class FragmentTestActivity extends FragmentActivity implements View.OnClickListener{

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFriend;

    private ContentFragment mWeixin;
    private FriendFragment mFriend;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_test);
        //初始化控件和声明控件
        mTabFriend = (LinearLayout)findViewById(R.id.tab_bottom_friend);
        mTabWeixin = (LinearLayout)findViewById(R.id.tab_bottom_weixin);

        mTabFriend.setOnClickListener(this);
        mTabWeixin.setOnClickListener(this);

        setDefaultFragment();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId()){
            case R.id.tab_bottom_weixin:
                Log.e("onclick","weixin");
                if(mWeixin == null){
                    mWeixin = new ContentFragment();
                }
                //使用当前Fragment的布局替代id_content的控件
                transaction.replace(R.id.id_content,mWeixin);
                break;
            case R.id.tab_bottom_friend:
                Log.e("onclick","friend");
                if(mFriend == null){
                    mFriend = new FriendFragment();
                }
                transaction.replace(R.id.id_content,mFriend);
                break;
        }
        transaction.commit();
    }

    private void setDefaultFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction  transaction = fm.beginTransaction();
        mWeixin = new ContentFragment();
        transaction.replace(R.id.id_content,mWeixin);
        transaction.commit();
    }

}