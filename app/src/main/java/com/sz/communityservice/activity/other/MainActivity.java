package com.sz.communityservice.activity.other;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.sz.communityservice.R;
import com.sz.communityservice.base.BaseActivity;
import com.sz.communityservice.base.BaseFragment;
import com.sz.communityservice.fragment.ChatFragment;
import com.sz.communityservice.fragment.ForumFragment;
import com.sz.communityservice.fragment.FriendListFragment;
import com.sz.communityservice.fragment.StoreFragment;
import com.sz.communityservice.fragment.UserSettingFragment;
import com.sz.communityservice.view.HomeBottomButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ViewPager vpMain;
    private LinearLayout llTab;
    private HomeBottomButton tab1;
    private HomeBottomButton tab2;
    private HomeBottomButton tab3;
    private HomeBottomButton tab4;
    private HomeBottomButton tab5;
    private List<BaseFragment> fragmentList;
    private List<HomeBottomButton> tablist;
    /**
     * 旧的选中下标，选中新的时要去掉他,最初为-1
     */
    private int oldSelectindex = -1;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        initTitle("");
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        llTab = (LinearLayout) findViewById(R.id.ll_tab);
        tab1 = (HomeBottomButton) findViewById(R.id.tab1);
        tab2 = (HomeBottomButton) findViewById(R.id.tab2);
        tab3 = (HomeBottomButton) findViewById(R.id.tab3);
        tab4 = (HomeBottomButton) findViewById(R.id.tab4);
        tab5 = (HomeBottomButton) findViewById(R.id.tab5);
    }

    @Override
    protected void initListener() {
        for (int i=0;i<tablist.size();i++){
            tablist.get(i).setOnClickListener(this);
        }
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //选中后更改样式
                selectIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpMain.setCurrentItem(2,false);
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new ChatFragment());
        fragmentList.add(new FriendListFragment());
        fragmentList.add(new StoreFragment());
        fragmentList.add(new ForumFragment());
        fragmentList.add(new UserSettingFragment());

        vpMain.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        tablist = new ArrayList<>();
        tablist.add(tab1);
        tablist.add(tab2);
        tablist.add(tab3);
        tablist.add(tab4);
        tablist.add(tab5);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab1:
                vpMain.setCurrentItem(0,false);
                break;
            case R.id.tab2:
                vpMain.setCurrentItem(1,false);
                break;
            case R.id.tab3:
                vpMain.setCurrentItem(2,false);
                break;
            case R.id.tab4:
                vpMain.setCurrentItem(3,false);
                break;
            case R.id.tab5:
                vpMain.setCurrentItem(5,false);
                break;
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void selectIndex(int postion){
        if (postion==oldSelectindex){
            return;
        }
        if (oldSelectindex>=0){
            tablist.get(oldSelectindex).setSelected(false);
        }
        tablist.get(postion).setSelected(true);
        tv_title.setText(tablist.get(postion).getText());

        oldSelectindex = postion;
    }
}
