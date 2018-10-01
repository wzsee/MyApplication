package wz.com.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.Arrays;
import java.util.List;

import wz.com.myapplication.activity.ContentActivity;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class ListTitleFragment extends ListFragment {

    public static final int REQUEST_DETAIL = 0x110;
    private List<String> mTitles = Arrays.asList("Hello","World","Android");
    //当前点击的位置
    private int mCurrentPos;
    private ArrayAdapter<String> mAdapter ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,mTitles));
        Log.e("ListFragment","进来了");
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCurrentPos = position;
        //跳转
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        //传参数到
        intent.putExtra(ContentFragment.ARGUMENT,mTitles.get(position));
        startActivityForResult(intent,REQUEST_DETAIL);
    }

    //在Activity中得到新的Activity关闭后返回的数据，使用这个，在新的Activity关闭后会向前面的Activity传回数据。
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG","onActivityResult");
        if(requestCode == REQUEST_DETAIL){
            mTitles.set(mCurrentPos,mTitles.get(mCurrentPos)+"---"+data.getStringExtra(ContentFragment.RESPONSE));
            //原列表数据改变
            mAdapter.notifyDataSetChanged();
        }
    }
}
