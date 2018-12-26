package gnyma.truewords;

/**
 * Created by ASUS on 12/26/2018.
 */

public interface MVPInterface {

    interface SplashViewOps {
        void fetchSucceed();
        void fetchFailed();
    }

    interface SplashPresenterOps {
        void checkForData();
    }

    interface SplashModelOps{
        void fetchDataIfNeeded();
    }

    interface SplasgRequiredPresenterOps{
        void fetchComplete(String data);
    }

}
