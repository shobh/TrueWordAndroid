package gnyma.truewords.View;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import gnyma.truewords.App;
import gnyma.truewords.Quotes;
import gnyma.truewords.R;
import gnyma.truewords.Utils;


public class VerticlePagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<Quotes> mList;
    int resId[]={R.drawable.a4,R.drawable.a5,
            R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11};

    public VerticlePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = Utils.readFromFile();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.content_main, container, false);

        TextView label = (TextView) itemView.findViewById(R.id.text1);
        TextView author = (TextView) itemView.findViewById(R.id.author_text);
        CardView card = (CardView) itemView.findViewById(R.id.card_view);
        card.setBackground(ContextCompat.getDrawable(App.getContext(),getDrawableBg()));

        label.setText(((Quotes)mList.get(position)).getQuote());
        author.setText(((Quotes)mList.get(position)).getAuthor());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private int getDrawableBg() {
        int rndInt = new Random().nextInt(resId.length);
        return resId[rndInt];
    }
}
