package moviesearch.services;

import moviesearch.models.Movie;
import moviesearch.utils.APIClient;
import org.json.JSONObject;

public class MovieService {
    private final APIClient apiClient;

    public MovieService(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    public Movie searchMovies(String title) {
        JSONObject movieJson = apiClient.fetchMovieDetails(title);
        if (movieJson.has("Title")) {
            String movieTitle = movieJson.getString("Title");
            String director = movieJson.optString("Director", "Unknown");
            String rating = movieJson.optString("imdbRating", "N/A");
            return new Movie(movieTitle, director, rating);
        }
        return null;
    }
}
