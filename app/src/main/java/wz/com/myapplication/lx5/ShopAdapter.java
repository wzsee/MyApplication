package wz.com.myapplication.lx5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/9/2 0002.
 */

public class ShopAdapter extends RecyclerView.Adapter{

    private List<String> list ;

    public ShopAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTv;
        ViewHolder(View itemView) {
            super(itemView);
            mTv =(TextView) itemView.findViewById(R.id.id_item_tv);
        }
    }
}
