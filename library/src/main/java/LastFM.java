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

    public String[][] getTopTracks() {
        List<String> trackNames = new ArrayList<>();
        List<String> artistNames = new ArrayList<>();
        List<String> albumImages = new ArrayList<>();

        HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder() .addQueryParameter("method", "user.getTopTracks") .addQueryParameter("user", "devonbarks") .addQueryParameter("api_key", API_KEY) .addQueryParameter("limit", "25") .addQueryParameter("format", "json") .build();
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) { 
            if (response.isSuccessful()) { 
                String responseData = response.body().string(); 
                JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class); 
                JsonObject topTracksObject = jsonObject.getAsJsonObject("toptracks"); 
                JsonArray trackArray = topTracksObject.getAsJsonArray("track"); 
                for (int i = 0; i < trackArray.size(); i++) { 
                    JsonObject trackObject = trackArray.get(i).getAsJsonObject(); 
                    String trackName = trackObject.get("name").getAsString(); 
                    String artistName = trackObject.getAsJsonObject("artist").get("name").getAsString(); 
                    JsonArray imageArray = trackObject.getAsJsonArray("image"); 
                    String albumImage = imageArray.get(imageArray.size() - 1).getAsJsonObject().get("#text").getAsString(); 
                    trackNames.add(trackName); 
                    artistNames.add(artistName); 
                    albumImages.add(albumImage); }
            } else { 
                System.err.println("Request failed: " + response.code()); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        String[][] result = new String[3][];

        result[0] = trackNames.toArray(new String[0]);
        result[1] = artistNames.toArray(new String[0]);
        result[2] = albumImages.toArray(new String[0]);

        return result; 
    } 
    public static void main(String[] args) { LastFM apiClient = new LastFM();
         String[][] topTracks = apiClient.getTopTracks(); 

         for (int i = 0; i <= topTracks[0].length; i++) {
            System.out.println(topTracks[0][i] + " by " + topTracks[1][i]);
         }

         
    }
}
