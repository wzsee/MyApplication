package wz.com.myapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class ContentFragment extends Fragment {
    private String mArgument;
    public static final String ARGUMENT = "argument";
    public static final String RESPONSE = "response";
    public static final String EVALUATE_DIALOG = "evaluate_dialog";
    public static final int REQUEST_EVALUATE = 0x110;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(null != bundle){
            mArgument = bundle.getString(ARGUMENT);
        }
    }

    /**
     * 静态方法创建ContentFragment,传入需要的参数，设置给arguments。
     * 完成Fragement和Activity间的解耦
     * @param argument
     * @return
     */
    public static ContentFragment newInstance(String argument){
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Random random = new Random();
        TextView tv = new TextView(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(params);
        tv.setText(mArgument);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.argb(random.nextInt(100),random.nextInt(255),random.nextInt(255),random.nextInt(255)));

        //单击
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("点击了我","ContentFragment");
                EvaluateDialog dialog = new EvaluateDialog();
                dialog.setTargetFragment(ContentFragment.this,REQUEST_EVALUATE);
                dialog.show(getFragmentManager(),EVALUATE_DIALOG);
            }
        });
        return tv;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_EVALUATE){
            String evaluate = data.getStringExtra(EvaluateDialog.RESPONSE_EVALUATE);
            Toast.makeText(getActivity(),evaluate,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra(RESPONSE,evaluate);
            getActivity().setResult(Activity.RESULT_OK, intent);
        }

    }
}
