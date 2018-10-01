package wz.com.myapplication.lx3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class FragmentThree extends Fragment implements View.OnClickListener{

    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_three,container,false);
        mBtn = (Button) view.findViewById(R.id.fragment_three_btn);
        mBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"我是碎片3",Toast.LENGTH_SHORT).show();
    }
}
