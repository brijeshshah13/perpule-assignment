package perpule.brijesh.perpuleassignment.ui.welcome;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import perpule.brijesh.perpuleassignment.R;
import perpule.brijesh.perpuleassignment.models.AudioResponse;
import perpule.brijesh.perpuleassignment.network.APIClient;
import perpule.brijesh.perpuleassignment.network.IAudios;
import perpule.brijesh.perpuleassignment.services.AudioDownloadService;
import perpule.brijesh.perpuleassignment.ui.main.MainActivity;
import perpule.brijesh.perpuleassignment.utils.SharedPreferencesManager;
import perpule.brijesh.perpuleassignment.utils.Utils;

public class WelcomePresenter implements WelcomePresenterInteractor {

    private String TAG = "WelcomePresenter";

    private Context mContext;
    private WelcomeView mWelcomeView;
    private SharedPreferencesManager mSharedPreferencesManager;

    public WelcomePresenter(Context mContext, WelcomeView mWelcomeView) {
        this.mContext = mContext;
        this.mWelcomeView = mWelcomeView;
        mSharedPreferencesManager = new SharedPreferencesManager(mContext);
    }

    @Override
    public void launchMain() {
        if(mSharedPreferencesManager.getAudioResponse() != null) {
            mContext.startActivity(new Intent(mContext, MainActivity.class));
        }
        else {
            getAudios();
        }
    }

    @Override
    public void getAudios() {
        if(mSharedPreferencesManager.getAudioResponse()!=null) {
            return;
        }
        if(!Utils.isInternetConnected(mContext)) {
            mWelcomeView.onError(mContext.getString(R.string.offline));
            return;
        }
        getObservable().subscribeWith(getObserver());
    }

    private Observable<AudioResponse> getObservable() {
        return APIClient.getRetrofitClient().create(IAudios.class)
                .getAudios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<AudioResponse> getObserver() {
        return new DisposableObserver<AudioResponse>() {

            @Override
            public void onNext(@NonNull AudioResponse audioResponse) {
                Log.d(TAG, "data : " + audioResponse.getData());
                mSharedPreferencesManager.setAudioResponse(audioResponse.getData());
                readyFirstAudio();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error : " + e);
                e.printStackTrace();
                mWelcomeView.onError("Could not fetch the data!");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }

    private void readyFirstAudio() {
        String firstAudio = mSharedPreferencesManager.getAudioResponse().get(0).getAsJsonObject().get("audio").getAsString();
        if(Utils.isInternetConnected(mContext))
        {
            Intent intent = new Intent(mContext, AudioDownloadService.class);
            intent.putExtra(AudioDownloadService.AUDIO_URL, firstAudio);
            intent.putExtra(AudioDownloadService.DOWNLOAD_MODE, 1);
            mContext.startService(intent);
        }
    }
}