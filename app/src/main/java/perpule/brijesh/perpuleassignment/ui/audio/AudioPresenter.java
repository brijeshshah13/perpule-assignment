package perpule.brijesh.perpuleassignment.ui.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import perpule.brijesh.perpuleassignment.R;
import perpule.brijesh.perpuleassignment.services.AudioDownloadService;
import perpule.brijesh.perpuleassignment.ui.main.MainPresenter;
import perpule.brijesh.perpuleassignment.utils.Utils;

public class AudioPresenter implements AudioPresenterInteractor {

    private String TAG = "AudioPresenter";
    private String mCurrentAudioURL = "";

    private Context mContext;
    private AudioView mAudioView;
    private MediaPlayer mMediaPlayer;

    public AudioPresenter(Context mContext, AudioView mAudioView) {
        this.mContext = mContext;
        this.mAudioView = mAudioView;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                String audioUrl = bundle.getString(AudioDownloadService.AUDIO_URL);
                if(audioUrl.equals(mCurrentAudioURL)) {
                    loadAudio();
                    readyNextAudio(MainPresenter.position);
                }
            }
        }
    };

    private void playAudio(String url) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    private void stopAudio() {
        try {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    private void readyNextAudio(int position) {
        try {
            String nextAudio = MainPresenter.mAudios.get(position+1).getAudio();
            if(Utils.isInternetConnected(mContext)) {
                Intent intent = new Intent(mContext, AudioDownloadService.class);
                intent.putExtra(AudioDownloadService.AUDIO_URL, nextAudio);
                intent.putExtra(AudioDownloadService.DOWNLOAD_MODE, 1);
                mContext.startService(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    @Override
    public void onViewAttached() {
        mContext.registerReceiver(receiver, new IntentFilter(
                AudioDownloadService.NOTIFICATION));
    }

    @Override
    public void onViewDetached() {
        mContext.unregisterReceiver(receiver);
        stopAudio();
    }

    @Override
    public void loadAudio() {
        stopAudio();

        if(MainPresenter.position >= MainPresenter.mAudios.size())
        {
            mAudioView.displayToast(mContext.getString(R.string.is_last_track));
            MainPresenter.position = 0;
        }

        mCurrentAudioURL = MainPresenter.mAudios.get(MainPresenter.position).getAudio();
        String desc = MainPresenter.mAudios.get(MainPresenter.position).getDesc();

        mAudioView.onAudioPlay(desc);

        if(!Utils.isInternetConnected(mContext)) {
            mAudioView.onError(mContext.getString(R.string.offline));
            return;
        }

        Intent intent = new Intent(mContext, AudioDownloadService.class);
        intent.putExtra(AudioDownloadService.AUDIO_URL, mCurrentAudioURL);
        intent.putExtra(AudioDownloadService.DOWNLOAD_MODE, 0);
        mContext.startService(intent);
        playAudio(mCurrentAudioURL);
        readyNextAudio(MainPresenter.position);
    }
}
