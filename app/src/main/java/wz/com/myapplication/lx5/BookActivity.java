package wz.com.myapplication.lx5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class BookActivity extends FragmentActivity {

    private static final int  BOOK_CODE = 2;

    @BindView(R.id.bt_novel)
    Button btNovel;
    @BindView(R.id.et_book)
    EditText etBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        btNovel.setText(intent.getStringExtra("bookName"));
    }

    @OnClick(R.id.bt_novel)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_novel:
                Intent intent = new Intent();
                intent.putExtra("bookName",etBook.getText().toString());
                setResult(BOOK_CODE, intent);
                finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
