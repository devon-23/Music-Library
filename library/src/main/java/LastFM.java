import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;
import com.google.gson.*;
import java.util.*;;

public class LastFM {
    private static final String API_KEY = "092d316884d8385f35ad8b84f5f42ef8";
    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    private OkHttpClient client;
    private Gson gson;

    public LastFM() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<String> getTopArtists() {
        List<String> topArtists = new ArrayList<>();

        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().addQueryParameter("method", "chart.gettopartists").addQueryParameter("api_key", API_KEY).addQueryParameter("format", "json").build();
        Builder request = new Request.Builder();
        
    }
}
