package wz.com.myapplication.lx3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class FragmentOne extends Fragment implements View.OnClickListener{
    private Button mBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);
            mBtn =(Button) view.findViewById(R.id.fragment_one_btn);
            mBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTwo fTwo = new FragmentTwo();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.id_content,fTwo, "TWO");
//         将当前事务添加到了回退栈,所以碎片不会被销毁，但是视图层次依然会被销毁
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
