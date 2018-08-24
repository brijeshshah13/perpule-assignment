package perpule.brijesh.perpuleassignment.models;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class AudioResponse {

    @SerializedName("data")
    private JsonArray mData;

    public JsonArray getData() {
        return mData;
    }

    public void setData(JsonArray mData) {
        this.mData = mData;
    }
}
