package wz.com.myapplication.lx4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class FragmentTwo extends Fragment implements View.OnClickListener{

    private Button mBtn;

    private FTwoBtnClickListener fTwoBtnClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);
        mBtn = (Button) view.findViewById(R.id.fragment_two_btn);
        mBtn.setOnClickListener(this);
        return view;
    }

    public interface FTwoBtnClickListener{
        void onFTwoBtnClick();
    }

    public void setfTwoBtnClickListener(FTwoBtnClickListener fTwoBtnClickListener) {
        this.fTwoBtnClickListener = fTwoBtnClickListener;
    }

    @Override
    public void onClick(View view) {
        if(fTwoBtnClickListener != null){
            fTwoBtnClickListener.onFTwoBtnClick();
        }
    }
}
