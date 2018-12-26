package gnyma.truewords;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS on 12/26/2018.
 */

class SplashModel implements MVPInterface.SplashModelOps {

    String web = "http://truewordserver.herokuapp.com/api/quote";

    private MVPInterface.SplasgRequiredPresenterOps mRequiredPresenter;

    SplashModel(MVPInterface.SplasgRequiredPresenterOps presenter) {
        mRequiredPresenter = presenter;
    }

    @Override
    public void fetchDataIfNeeded() {
        try {
            URL url = new URL(web);
            new MyAsyncTask().execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class MyAsyncTask extends AsyncTask<URL, Void, String> {

        private HttpURLConnection urlConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            String data = parseUrl(params[0]);
            return data;

        /*    if (!Utils.performCheckForValidity(data1)) return false;
            Utils.storeLocally(data1, "data.json");
            return true;
            */
        }

        private String parseUrl(URL url){
            StringBuilder result = new StringBuilder();
            try {
                urlConnection = (HttpURLConnection) url.openConnection(/*proxy*/);
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(20 * 1000);
                urlConnection.setReadTimeout(20 * 1000);

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            mRequiredPresenter.fetchComplete(data);
        }
    }

}
