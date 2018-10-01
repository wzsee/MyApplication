package wz.com.myapplication.lx2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class TitleFragment extends Fragment {

    private ImageButton mleftMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载布局
        View view  = inflater.inflate(R.layout.fragment_title,container,false);
        mleftMenu =(ImageButton) view.findViewById(R.id.id_title_left_btn);
        mleftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"我是碎片的imageButton",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
