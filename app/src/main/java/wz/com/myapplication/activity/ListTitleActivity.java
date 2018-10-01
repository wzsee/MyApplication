package wz.com.myapplication.activity;

import android.support.v4.app.Fragment;
import android.util.Log;

import wz.com.myapplication.fragment.ListTitleFragment;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class ListTitleActivity extends SingleFragmentActivity {

    private ListTitleFragment mListTitleFragment;

    @Override
    protected Fragment createFragment() {
//        Log.e("ListTitleActivity","进来了");
        mListTitleFragment = new ListTitleFragment();
        return mListTitleFragment;
    }

}
