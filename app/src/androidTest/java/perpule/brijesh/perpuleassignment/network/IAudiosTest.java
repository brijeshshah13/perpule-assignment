package perpule.brijesh.perpuleassignment.network;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import perpule.brijesh.perpuleassignment.models.AudioResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IAudiosTest {

    @GET("bins/mxcsl")
    Flowable<AudioResponse> getAudios();

    @GET
    Flowable<ResponseBody> downloadAudio(@Url String audioUrl);
}