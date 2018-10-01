package wz.com.myapplication.lx5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/9/1 0001.
 */

public class FragmentAddressBook extends Fragment{

    @BindView(R.id.tv_time)
    TextView mTvTime;
    Handler mHandler ;
    private Unbinder unbinder;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vp_fragment2,container,false);
        view.findViewById(R.id.bt_vp2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"这是第二个Fragment",Toast.LENGTH_SHORT);
                for (int i=0;i<10;i++){
                    Message message = Message.obtain(mHandler);
                    message.what = 10-i;
                    mHandler.sendMessageDelayed(message,1000*i);
                }
            }
        });
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mTvTime.setText(msg.what+"");
            }
        };
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
