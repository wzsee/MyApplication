package wz.com.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import wz.com.myapplication.R;
import wz.com.myapplication.fragment.ContentFragment;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class ContentActivity extends SingleFragmentActivity {

    private ContentFragment mContentFragment;

    @Override
    protected Fragment createFragment() {
        String title = getIntent().getStringExtra(ContentFragment.ARGUMENT);
        mContentFragment = ContentFragment.newInstance(title);
        return mContentFragment;
    }
}
