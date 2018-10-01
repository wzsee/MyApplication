package wz.com.myapplication.lx4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<String> list;

    public MyRecyclerViewAdapter(List<String> list){
        this.list = list;
    }
    //     负责数据的绑定
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(list.get(position));
    }
    // 负责每个子项的布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_use,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mText;
        ViewHolder(View itemView) {
            super(itemView);
            mText =(TextView) itemView.findViewById(R.id.id_item_tv);
        }
    }
}
