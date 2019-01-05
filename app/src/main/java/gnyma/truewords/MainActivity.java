package gnyma.truewords;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import gnyma.truewords.View.CustomViewPager;
import gnyma.truewords.View.MainFragmentPager;

public class MainActivity extends AppCompatActivity {

    private CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);

        TabLayout mainTabLayout = (TabLayout) findViewById(R.id.mainTabs);
        MainFragmentPager mainFragmentPager = new MainFragmentPager(getSupportFragmentManager());

        mViewPager = (CustomViewPager) findViewById(R.id.vPager);
        mViewPager.setAdapter(mainFragmentPager);
        mViewPager.beginFakeDrag();
        mainTabLayout.setupWithViewPager(mViewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("MovieDB");

        mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
