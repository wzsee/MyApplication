package wz.com.myapplication.lx3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        mBtn = (Button) view.findViewById(R.id.fragment_two_btn);
        mBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.e("FragmentTwo","Click");
        FragmentThree fragmentThree  = new FragmentThree();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(this);
        transaction.add(R.id.id_content,fragmentThree, "THREE");
//        transaction.replace(R.id.id_content,fragmentThree,"THREE");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
