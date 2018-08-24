package perpule.brijesh.perpuleassignment.ui.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import perpule.brijesh.perpuleassignment.R;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {

    private String TAG = "WelcomeActivity";

    private Button mStartBtn;
    WelcomePresenter mWelcomePresenter;

    private int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Objects.requireNonNull(getSupportActionBar()).hide();

        mStartBtn = findViewById(R.id.btn_start);
        setupMVP();
        mStartBtn.setOnClickListener(v -> {
            flag = 2;
            mWelcomePresenter.launchMain();
        });
        flag = 1;
        checkFlag();
    }

    private void checkFlag() {
        if(flag == 1) {
            mWelcomePresenter.getAudios();
        } else {
            mWelcomePresenter.launchMain();
        }
    }

    private void setupMVP() {
        mWelcomePresenter = new WelcomePresenter(this,this);
    }

    @Override
    public void displayToast(String str) {
        Toast.makeText(WelcomeActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {
        displayToast(errorMessage);
    }
}
