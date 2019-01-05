package gnyma.truewords.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gnyma.truewords.View.Fragments.ChatFragment;
import gnyma.truewords.View.Fragments.PopularFragment;
import gnyma.truewords.View.Fragments.UserFragment;

public class MainFragmentPager extends FragmentPagerAdapter {
    public MainFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Stoic Quotes";
            case 1:
                return "User Quotes";
            case 2:
                return "Chat with Buddha";
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                PopularFragment popularFragment = new PopularFragment();
                return popularFragment;

            case 1:
                UserFragment userFragment = new UserFragment();
                return userFragment;

            case 2:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
        }
        return null;
    }

}
