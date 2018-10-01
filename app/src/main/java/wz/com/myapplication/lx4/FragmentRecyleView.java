package wz.com.myapplication.lx4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class FragmentRecyleView extends Fragment {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fregment_recyleview,container,false);
        list = new ArrayList<>();
       for(int i=0;i<99;i++){
           list.add(String.valueOf(i));
       }
        mRecyclerView =(RecyclerView) view.findViewById(R.id.recycleView);
        // 设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayout.VERTICAL,false));
//        添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(),DividerItemDecoration.VERTICAL));
//        mRecyclerView.addItemDecoration(new ItemDecoration());
//        mRecyclerView.addItemDecoration(new ItemDecoration(this.getActivity(), ItemDecoration.VERTICAL_LIST));
//            初始化适配器
        mAdapter = new MyRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
        Log.e("mAdapter.getItemCount()",String.valueOf(mAdapter.getItemCount()));
        return view;
    }
}
