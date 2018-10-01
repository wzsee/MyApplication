package wz.com.myapplication.lx5;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import wz.com.myapplication.R;


/**
 * Created by Administrator on 2018/9/7 0007.
 */

public class ChatAdapter extends BaseQuickAdapter<ChatBean,BaseViewHolder>{
    public ChatAdapter(@Nullable List<ChatBean> data) {
        super(R.layout.item_chat, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChatBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }
}
