package perpule.brijesh.perpuleassignment.ui.audio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

import perpule.brijesh.perpuleassignment.R;

public class AudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
