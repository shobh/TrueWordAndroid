package gnyma.truewords;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity implements MVPInterface.SplashViewOps {

    private MVPInterface.SplashPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new SplashPresenter(this);
        mPresenter.checkForData();
    }

    @Override
    public void fetchSucceed() {
        launchMainActivity();
        finish();
    }

    @Override
    public void fetchFailed() {
        Toast.makeText(this, "Error connecting to server", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
