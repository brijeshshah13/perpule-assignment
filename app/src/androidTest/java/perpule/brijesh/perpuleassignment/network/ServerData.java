package perpule.brijesh.perpuleassignment.network;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import perpule.brijesh.perpuleassignment.models.AudioResponse;
import retrofit2.Retrofit;

public class ServerData implements IAudiosTest {

    IAudiosTest test;

    public ServerData(Retrofit retrofit) {
        this.test = retrofit.create(IAudiosTest.class);
    }

    @Override
    public Flowable<AudioResponse> getAudios() {
        return test.getAudios();
    }

    @Override
    public Flowable<ResponseBody> downloadAudio(String audioUrl) {
        return test.downloadAudio(audioUrl);
    }
}
