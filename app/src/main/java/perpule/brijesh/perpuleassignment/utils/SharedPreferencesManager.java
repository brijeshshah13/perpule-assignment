package perpule.brijesh.perpuleassignment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

public class SharedPreferencesManager {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public SharedPreferencesManager(Context context)
    {
        this.mContext = context;
        mSharedPreferences = context.getApplicationContext().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void setAudioResponse(JsonArray data)
    {
        try {
            mEditor.putString("data", data.toString());
            mEditor.commit();
        }
        catch(Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    public JsonArray getAudioResponse()
    {
        JsonArray data;
        try {
            data = new JsonParser().parse(Objects.requireNonNull(mSharedPreferences.getString("data", null))).getAsJsonArray();
        }
        catch(Exception e) {
            data = null;
        }
        return data;
    }
}
