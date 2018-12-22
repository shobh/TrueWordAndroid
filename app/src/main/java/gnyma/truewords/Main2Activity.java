package gnyma.truewords;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener,TaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            URL url = new URL("https://talaikis.com/api/quotes/random");
            new MyAsyncTask(this, this).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskCompleted(String s) {
        ((TextView) findViewById(R.id.text1)).setText(s);
    }

    public class MyAsyncTask extends AsyncTask<URL, Void, String> {

        private HttpURLConnection urlConnection;
        private Context mContext;
        private ProgressDialog mDialog;
        private TaskListener mListener;

        public MyAsyncTask(Context context, TaskListener listener) {
            this.mContext = context;
            mDialog = new ProgressDialog(mContext);
            this.mListener = listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.show();
        }

        @Override
        protected String doInBackground(URL... params) {
            StringBuilder result = new StringBuilder();

            try {
                URL url = params[0];
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.1", 8080));
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mDialog.dismiss();
            try {
                JSONObject obj = new JSONObject(s);
                obj.opt("quote");
                mListener.onTaskCompleted(obj.opt("quote").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
