package gnyma.truewords;

public class SplashPresenter implements MVPInterface.SplashPresenterOps,
        MVPInterface.SplasgRequiredPresenterOps {

    MVPInterface.SplashViewOps mView;
    MVPInterface.SplashModelOps mModel;

    SplashPresenter(MVPInterface.SplashViewOps view) {
        mView = view;
        mModel = new SplashModel(this);
    }

    @Override
    public void checkForData() {
        mModel.fetchDataIfNeeded();
    }

    @Override
    public void fetchComplete(String data) {
        if (!Utils.performCheckForValidity(data)) {
            mView.fetchFailed();
        } else {
            Utils.storeLocally(data, "data.json");
            mView.fetchSucceed();
        }
    }

}
