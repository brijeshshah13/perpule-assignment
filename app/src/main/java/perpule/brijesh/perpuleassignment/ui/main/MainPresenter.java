package perpule.brijesh.perpuleassignment.ui.main;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import perpule.brijesh.perpuleassignment.models.Audio;
import perpule.brijesh.perpuleassignment.utils.SharedPreferencesManager;

public class MainPresenter implements MainPresenterInteractor {

    private String TAG = "MainPresenter";

    private Context mContext;
    private MainView mMainView;
    private SharedPreferencesManager mSharedPreferencesManager;

    public static List<Audio> mAudios;
    public static int position = 0;

    public MainPresenter(Context mContext, MainView mMainView) {
        this.mContext = mContext;
        this.mMainView = mMainView;
        mSharedPreferencesManager = new SharedPreferencesManager(mContext);
    }

    @Override
    public void loadAudios() {

        JsonArray audioArray = mSharedPreferencesManager.getAudioResponse();

        if(audioArray != null && audioArray.size() > 0) {
            mAudios = new ArrayList<>();
            for(JsonElement element : audioArray) {
                JsonObject object = (JsonObject) element;
                try {
                    mAudios.add(new Audio(object.get("itemId").getAsString(), object.get("desc").getAsString(),
                            object.get("audio").getAsString()));
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            mMainView.displayAudios(mAudios);
        }
    }
}
