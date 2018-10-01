package wz.com.myapplication.lx5;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wz.com.myapplication.R;

import static com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_RIGHT;

/**
 * Created by Administrator on 2018/9/1 0001.
 */

public class FragmentChat extends Fragment implements  EasyPermissions.PermissionCallbacks{

    @BindView(R.id.test_recycler_view_frame)
    PtrClassicFrameLayout mPtrFrameLayout;
    @BindView(R.id.test_recycler_view)
    RecyclerView mRecycleView;
    @BindView(R.id.bt_vp1)
     Button mButton;

    ChatAdapter mAdapter ;
    List<ChatBean> mChatData  = new ArrayList<>();
    View mFootView ;

    private static final String TAG = "MainActivity";

    private static final int  BOOK_CODE = 2 ;//返回的结果码
    private static final int RC_CAMERA_AND_RECORD_AUDIO = 10000;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vp_fragment1,container,false);
        ButterKnife.bind(this,view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFootView = LayoutInflater.from(getContext()).inflate(R.layout.foot_chat,null);
        mAdapter = new ChatAdapter(mChatData);
        ptr();
        initData();
        mAdapter.notifyDataSetChanged();
        mRecycleView.setAdapter(mAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),R.string.fragment_chat,Toast.LENGTH_SHORT).show();
                requestPermissions();
            }
        });
        return view;
    }

    private void initData(){
        for(int i = 0 ; i<10 ;i++){
            mChatData.add(new ChatBean(""+Math.random()));
        }
        quickAdapter();
        mPtrFrameLayout.refreshComplete();
    }

    //下拉控件设置
    private void ptr(){
        //设置下拉监听
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mChatData.clear();
                reflesh();
                mPtrFrameLayout.refreshComplete();
                mAdapter.setEnableLoadMore(true);
            }
        });
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(true);
            }
        },1000);
    }

    private void reflesh(){
        mChatData.clear();
        for(int i = 0 ; i<10 ;i++){
            mChatData.add(new ChatBean(""+Math.random()));
        }
//        如果上拉结束后，下拉刷新需要再次开启上拉监听，需要使用setNewData方法填充数据。
        mAdapter.setNewData(mChatData);
    }

    private void quickAdapter(){
        // 设置加载动画
        mAdapter.openLoadAnimation(SLIDEIN_RIGHT  );
        mAdapter.setFooterView(mFootView);
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mChatData.size()>30){
                            mAdapter.loadMoreEnd();
                        }else{
                            for(int i = 0 ; i<10 ;i++){
                                mChatData.add(new ChatBean(""+Math.random()));
                            }
                            mAdapter.loadMoreComplete();
                        }

                    }
                },1000);
            }
        },mRecycleView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, getActivity());
    }

    /**
     * 去申请权限
     */
    @AfterPermissionGranted(RC_CAMERA_AND_RECORD_AUDIO)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

        //判断有没有权限
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            // 如果有权限了, 就做你该做的事情
            // doing something
            openCamera();
        } else {
            // 如果没有权限, 就去申请权限
            // this: 上下文
            // Dialog显示的正文
            // RC_CAMERA_AND_RECORD_AUDIO 请求码, 用于回调的时候判断是哪次申请
            // perms 就是你要申请的权限
            EasyPermissions.requestPermissions(getActivity(), "写上你需要用权限的理由, 是给用户看的", RC_CAMERA_AND_RECORD_AUDIO, perms);
        }
    }

    /**
     * 权限申请成功的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms       申请成功的权限集合
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i(TAG, "onPermissionsGranted: ");
        if (requestCode != RC_CAMERA_AND_RECORD_AUDIO) {
            return;
        }
        for (int i = 0; i < perms.size(); i++) {
            if (perms.get(i).equals(Manifest.permission.CAMERA)) {
                Log.i(TAG, "onPermissionsGranted: " + "相机权限成功");
                openCamera();

            } else if (perms.get(i).equals(Manifest.permission.RECORD_AUDIO)) {
                Log.i(TAG, "onPermissionsGranted: " + "录制音频权限成功");
            }
        }
    }

    /**
     * 权限申请拒绝的回调
     *
     * @param requestCode 申请权限时的请求码
     * @param perms       申请拒绝的权限集合
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i(TAG, "onPermissionsDenied: ");
        if (requestCode != RC_CAMERA_AND_RECORD_AUDIO) {
            return;
        }

        for (int i = 0; i < perms.size(); i++) {
            if (perms.get(i).equals(Manifest.permission.CAMERA)) {
                Log.i(TAG, "onPermissionsDenied: " + "相机权限拒绝");
            } else if (perms.get(i).equals(Manifest.permission.RECORD_AUDIO)) {
                Log.i(TAG, "onPermissionsDenied: " + "录制音频权限拒绝");
            }
        }

    }


    private void openCamera() {
        Toast.makeText(getContext(), "打开相机 ~~~", Toast.LENGTH_LONG).show();
    }
}
