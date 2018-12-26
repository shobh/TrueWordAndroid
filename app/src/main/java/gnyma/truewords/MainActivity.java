package gnyma.truewords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gnyma.truewords.View.VerticalViewPager;
import gnyma.truewords.View.VerticlePagerAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
        initSwipePager();
    }

    private void initSwipePager(){
        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.vPager);
        verticalViewPager.setAdapter(new VerticlePagerAdapter(this));
    }

    @Override
    public void onClick(View v) {
    }
}
