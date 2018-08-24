package perpule.brijesh.perpuleassignment.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import perpule.brijesh.perpuleassignment.network.APIClient;
import perpule.brijesh.perpuleassignment.network.IAudios;

public class AudioDownloadService extends IntentService {

    private String audioUrl = "";
    private int flag = 0;
    private String TAG = "AudioDownloadService";
    public static final String AUDIO_URL = "audioUrl";
    public static final String DOWNLOAD_MODE = "downloadMode";
    public static final String NOTIFICATION = "perpule.brijesh.perpuleassignment.services.downloadReceiver";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public AudioDownloadService() {
        super("AudioDownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        audioUrl = intent.getStringExtra(AUDIO_URL);
        flag = intent.getIntExtra(DOWNLOAD_MODE, 1);
        getObservable(audioUrl).subscribeWith(getObserver());
    }

    private Observable<ResponseBody> getObservable(String audioUrl) {
        return APIClient.getRetrofitClient().create(IAudios.class)
                .downloadAudio(audioUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ResponseBody> getObserver() {
        return new DisposableObserver<ResponseBody>() {

            @Override
            public void onNext(@NonNull ResponseBody responseBody) {
                publishResults(audioUrl, flag);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }

    private void publishResults(String audioUrl, int flag) {
        if(flag == 0) {
            Intent intent = new Intent(NOTIFICATION);
            intent.putExtra(AUDIO_URL, audioUrl);
            intent.putExtra(DOWNLOAD_MODE, flag);
            sendBroadcast(intent);
        }
    }
}
