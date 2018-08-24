package perpule.brijesh.perpuleassignment.models;

import com.google.gson.annotations.SerializedName;

public class Audio {

    @SerializedName("itemId")
    private String mItemId;

    @SerializedName("desc")
    private String mDesc;

    @SerializedName("audio")
    private String mAudio;

    public Audio(String mItemId, String mDesc, String mAudio) {
        super();
        this.mItemId = mItemId;
        this.mDesc = mDesc;
        this.mAudio = mAudio;
    }

    public String getItemId() {
        return mItemId;
    }

    public void setItemId(String mItemId) {
        this.mItemId = mItemId;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getAudio() {
        return mAudio;
    }

    public void setAudio(String mAudio) {
        this.mAudio = mAudio;
    }
}
