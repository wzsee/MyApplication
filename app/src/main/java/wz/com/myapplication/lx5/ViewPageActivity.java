package wz.com.myapplication.lx5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wz.com.myapplication.R;

/**
 * Created by Administrator on 2018/9/1 0001.
 */

public class ViewPageActivity extends FragmentActivity implements PopupMenu.OnMenuItemClickListener{


    private static final String TAG = "MainActivity";

    private static final int  BOOK_CODE = 2 ;//返回的结果码
    private static final int RC_CAMERA_AND_RECORD_AUDIO = 10000;

    private ViewPager mViewPager;
    private FragAdapter fragAdapter;
    private List<Fragment> mFragments;
    private String[] mTab ;

    @BindView(R.id.tv_chat)
     TextView mTv_chat;
    @BindView(R.id.tv_address_boot)
     TextView mTvAddressBook;
    @BindView(R.id.tv_find)
     TextView mTvFind;
    @BindView(R.id.magic_indicator)
    MagicIndicator indicator;

    CommonNavigator commonNavigator ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        /**
         * bind必须在setContentView之后，并且必须要求有
         */
        ButterKnife.bind(this);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mFragments = new ArrayList();
        mFragments.add(new FragmentChat());
        mFragments.add(new FragmentAddressBook());
        mFragments.add(new FragmentShop());
        fragAdapter = new FragAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(fragAdapter);
        /**
         * 默认聊天fragment
         */
        mViewPager.setCurrentItem(0);
        mTv_chat.setTextColor(getResources().getColor(R.color.colorPrimary));

        setIndicator();
        setViewPager();
    }

    @OnClick({R.id.tv_chat, R.id.tv_address_boot, R.id.tv_find})
    public void switchFragment(View v){
        setColorBlack();
        switch (v.getId()){
            case R.id.tv_chat :
                setColor(v.getId());
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_address_boot :
                setColor(v.getId());
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_find:
                setColor(v.getId());
//                mViewPager.setCurrentItem(2);
                initPopup();
        }
    }


    private void setColorBlack(){
        mTv_chat.setTextColor(getResources().getColor(R.color.colorBlack));
        mTvAddressBook.setTextColor(getResources().getColor(R.color.colorBlack));
        mTvFind.setTextColor(getResources().getColor(R.color.colorBlack));
    }
    private void setColor(int viewId){
        TextView tv = (TextView) findViewById(viewId);
        tv.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    private void initPopup(){
        /**
         * 绑定的那个view
         */
        PopupMenu popupMenu = new PopupMenu(this,mTvFind);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_find,popupMenu.getMenu());
        /**
         * 必不可少
         */
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_book:
                Intent intent = new Intent(ViewPageActivity.this,BookActivity.class) ;
                intent.putExtra("bookName","java");
                startActivityForResult(intent,2);
                break;
            case R.id.item_shop:
                mViewPager.setCurrentItem(2);
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BOOK_CODE){
            mTvFind.setText(data.getStringExtra("bookName"));
            Toast.makeText(this,"requestCode==2",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"requestCode!=2",Toast.LENGTH_SHORT).show();
        }
    }


    private void setIndicator(){
        mTab = getResources().getStringArray(R.array.magic_nav);
        /**
         * 指示器
         */
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTab == null ? 0 : mTab.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setTextSize(20);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setText(mTab[index]);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#333333"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#e94220"));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }


            @Override
            public IPagerIndicator getIndicator(Context context) {
                TriangularPagerIndicator indicator = new TriangularPagerIndicator(context);
                indicator.setLineColor(Color.parseColor("#e94220"));
                return indicator;
            }
        });
        /**
         * 忘记加是不可以的 耗时30min
         */
        indicator.setNavigator(commonNavigator);
    }
    private void setViewPager(){
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicator.onPageScrollStateChanged(state);
            }
        });
    }


}
