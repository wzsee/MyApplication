package wz.com.myapplication.lx4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class MainActivity extends FragmentActivity implements FragmentOne.FOneBtnClickListener,FragmentTwo.FTwoBtnClickListener{
    private FragmentOne mFOne;
    private FragmentTwo mFTwo;
    private FragmentThree mFThree;
    private FragmentRecyleView mFRecycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lx3);

        mFOne = new FragmentOne();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mFOne,"ONE");
        transaction.commit();
    }

    @Override
    public void onFOneBtnClick() {
        if(mFTwo == null){
            mFTwo = new FragmentTwo();
            mFTwo.setfTwoBtnClickListener(this);
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.id_content, mFTwo, "TWO");
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public void onFTwoBtnClick() {

        if (mFThree == null)
        {
            mFThree = new FragmentThree();

        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.hide(mFTwo);
        tx.add(R.id.id_content, mFThree, "THREE");
        tx.addToBackStack(null);
        tx.commit();
    }
}
