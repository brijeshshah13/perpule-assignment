package perpule.brijesh.perpuleassignment.ui.welcome;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.subscribers.TestSubscriber;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import perpule.brijesh.perpuleassignment.models.AudioResponse;
import perpule.brijesh.perpuleassignment.network.APIClient;
import perpule.brijesh.perpuleassignment.network.ServerData;

public class WelcomePresenterTest {

    private AudioResponse mAudioResponse;
    private MockWebServer mMockWebServer;
    private TestSubscriber<AudioResponse> mTestSubscriber;

    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        mTestSubscriber = new TestSubscriber<>();
    }

    @Test
    public void apiResponse() {
        mMockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(mAudioResponse)));
        ServerData serverData = new ServerData(APIClient.getRetrofitClient());
        serverData.getAudios().subscribe(mTestSubscriber);
        mTestSubscriber.assertNoErrors();
        mTestSubscriber.assertComplete();
    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer = null;
        mTestSubscriber = null;
    }
}