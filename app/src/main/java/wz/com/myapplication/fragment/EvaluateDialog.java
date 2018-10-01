package wz.com.myapplication.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import wz.com.myapplication.fragment.ContentFragment;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class EvaluateDialog extends DialogFragment {

    private String[] mEvaluteVals = new String[] {"GOOD","BAD","NORMAL"};

    public static final String RESPONSE_EVALUATE = "response_evaluate";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Evaluate :").setItems(mEvaluteVals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                setResult(which);
            }
        });

        return builder.create();
    }

    //设置返回值
    public void setResult(int which){
        if(getTargetFragment() ==null)
            return;
        Intent intent = new Intent();
        intent.putExtra(RESPONSE_EVALUATE, mEvaluteVals[which]);
        getTargetFragment().onActivityResult(ContentFragment.REQUEST_EVALUATE,
                Activity.RESULT_OK, intent);
    }
}
