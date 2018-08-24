package perpule.brijesh.perpuleassignment.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import perpule.brijesh.perpuleassignment.R;
import perpule.brijesh.perpuleassignment.models.Audio;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView mAudiosRV;
    RecyclerView.Adapter mAdapter;
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAudiosRV = findViewById(R.id.rv_audios);
        setupMVP();
        setupView();
        loadAudios();
    }

    private void loadAudios() {
        mMainPresenter.loadAudios();
    }

    private void setupMVP() {
        mMainPresenter = new MainPresenter(this, this);
    }

    private void setupView(){
        mAudiosRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayToast(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {
        displayToast(errorMessage);
    }

    @Override
    public void displayAudios(List<Audio> audios) {
        mAdapter = new MainAdapter(audios, MainActivity.this);
        mAudiosRV.setAdapter(mAdapter);
    }
}
