package perpule.brijesh.perpuleassignment.ui.main;

import java.util.List;

import perpule.brijesh.perpuleassignment.models.Audio;

public interface MainView {

    void displayToast(String str);
    void onError(String errorMessage);
    void displayAudios(List<Audio> audios);
}
