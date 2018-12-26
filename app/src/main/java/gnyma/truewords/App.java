package gnyma.truewords;

import android.app.Application;
import android.content.Context;

/**
 * Created by ASUS on 12/26/2018.
 */

public class App extends Application{

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
