package wz.com.myapplication.lx4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 分割线
 * Created by Administrator on 2018/8/23 0023.
 */

// TODO 看不懂
public class ItemDecoration extends RecyclerView.ItemDecoration{

    private Context mContext;
    private Drawable mDivider;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS  = new int[]{
            android.R.attr.listDivider
    };

    public ItemDecoration(Context context,int orientation){
        this.mContext = context;
        final TypedArray ta =context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
    }

    //设置屏幕的方向
    public void setOrientation(int orientation){
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");        }
        mOrientation = orientation;
    }

    //画横线
    public void drawHorizontallLine(Canvas c,RecyclerView parent,RecyclerView.State state){
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingLeft();
        final int childCount = parent.getChildCount();
        for (int i = 0 ; i < childCount; i++){
            final View child = parent.getChildAt(i);
            //获取得到child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            Log.e("left",String.valueOf(left));
            mDivider.draw(c);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontallLine(c,parent,state);
    }

    //由于Divider也有长宽高，每一个item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    }
}
