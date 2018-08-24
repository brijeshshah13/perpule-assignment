package perpule.brijesh.perpuleassignment.ui.audio;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import perpule.brijesh.perpuleassignment.R;
import perpule.brijesh.perpuleassignment.ui.main.MainPresenter;

public class AudioFragment extends Fragment implements AudioView {

    TextView mDescriptionTV;
    Button mContinueBtn;
    Button mRetryBtn;
    AudioPresenter mAudioPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audio, container, false);
        mDescriptionTV = view.findViewById(R.id.tv_description);
        mContinueBtn = view.findViewById(R.id.btn_continue);
        mRetryBtn = view.findViewById(R.id.btn_retry);
        setupMVP();
        setupViews();
        mAudioPresenter.loadAudio();
        return view;
    }

    private void setupMVP() {
        mAudioPresenter = new AudioPresenter(getActivity(), this);
    }

    private void setupViews() {
        mRetryBtn.setOnClickListener(view -> mAudioPresenter.loadAudio());

        mContinueBtn.setOnClickListener(view -> {
            MainPresenter.position += 1;
            mAudioPresenter.loadAudio();
        });
    }

    @Override
    public void displayToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String errorMessage) {
        mRetryBtn.setVisibility(View.VISIBLE);
        displayToast(errorMessage);
    }

    @Override
    public void onAudioPlay(String desc) {
        mDescriptionTV.setText(desc);
        mRetryBtn.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAudioPresenter.onViewAttached();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAudioPresenter.onViewDetached();
    }
}
