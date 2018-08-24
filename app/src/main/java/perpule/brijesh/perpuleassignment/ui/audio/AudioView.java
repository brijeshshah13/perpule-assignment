package perpule.brijesh.perpuleassignment.ui.audio;

public interface AudioView {

    void displayToast(String str);
    void onError(String errorMessage);
    void onAudioPlay(String desc);

}
