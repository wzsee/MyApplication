package wz.com.myapplication.lx5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/1 0001.
 */

public class FragAdapter extends FragmentPagerAdapter{

    List<Fragment> list;

    public FragAdapter(FragmentManager manager){
        super(manager);
    }


    public FragAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
