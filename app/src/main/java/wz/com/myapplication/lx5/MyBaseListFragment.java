package wz.com.myapplication.lx5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public abstract class MyBaseListFragment<T> extends Fragment{

    protected List<T> mData;
    protected BaseQuickAdapter<T,BaseViewHolder> mAdapter;

    @BindView(R.id.recycleView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.ptr_layout)
    protected PtrFrameLayout mPtrFrameLayout;

    protected int mPageNum;
    protected int mPageSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
      View view =  inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(getActivity(),view);
        return view;
    }

    /**
     * 初始化数据
     */
    protected void initData(){}

    /**
     * 初始化view
     */
    protected void initView(){}

    /**
     * 下拉刷新
     */
    protected void refresh(){
        mPageNum = 1;
        // 该插件的下拉事件
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                initData();
                // 结束刷新头
                mPtrFrameLayout.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });

    }

    /**
     * 获取布局id
     * @return
     */
    protected int getLayoutId(){
         return R.layout.fragment_base_list;
     }
}
