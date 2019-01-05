package gnyma.truewords.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.util.Log;
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

    int resId2[]={R.drawable.a21,R.drawable.a22,
            R.drawable.a23,R.drawable.a26,R.drawable.a29,
            R.drawable.a33,
            R.drawable.a34,R.drawable.a35,R.drawable.a36,R.drawable.a38,R.drawable.a39,
            R.drawable.a40,R.drawable.a42,R.drawable.a43};


    public VerticlePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = Utils.readFromFile();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public String getCurrentQuote(int position) {
        return ((Quotes)mList.get(position)).getQuote();
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
        ImageView card = (ImageView) itemView.findViewById(R.id.image_bg);

        card.setImageResource(getDrawableBg());

        label.setText(((Quotes)mList.get(position)).getQuote());
        author.setText(((Quotes)mList.get(position)).getAuthor());
        int color = getToolbarColor(((BitmapDrawable)card.getDrawable()).getBitmap());

        if (color != 0) {
            label.setTextColor(color);
        }
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private int getDrawableBg() {
        int rndInt = new Random().nextInt(resId2.length);
        return resId2[rndInt];
    }

    public int getToolbarColor(Bitmap bitmap) {
        // Generate the palette and get the vibrant swatch
        // See the createPaletteSync() method
        // from the code snippet above
        Palette p = createPaletteSync(bitmap);
        Palette.Swatch vibrantSwatch = p.getDominantSwatch();

        int textColor = 0;
        // Load default colors

        // Check that the Vibrant swatch is available
        if(vibrantSwatch != null){
            textColor = vibrantSwatch.getBodyTextColor();
            Log.d("shob", "color = " + textColor);
        } else {
            Log.d("shob", "color is null");
        }



        return textColor;
    }

    public Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }

}
