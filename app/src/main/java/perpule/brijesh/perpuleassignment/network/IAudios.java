package perpule.brijesh.perpuleassignment.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import perpule.brijesh.perpuleassignment.models.AudioResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IAudios {

    @GET("bins/mxcsl")
    Observable<AudioResponse> getAudios();

    @GET
    Observable<ResponseBody> downloadAudio(@Url String audioUrl);
}
