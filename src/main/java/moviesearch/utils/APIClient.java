package moviesearch.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class APIClient {
    private final OkHttpClient client = new OkHttpClient();

    public JSONObject fetchMovieDetails(String title) {
        String url = "http://www.omdbapi.com/?apikey=dcc16f75&t=" + title;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


