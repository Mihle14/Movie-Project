package webtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("For the movie search page")
public class MovieSearchPageTests {

    private static final String API_KEY = "dcc16f75";
    private static final String BASE_URL = "https://www.omdbapi.com/";

    @Test
    @DisplayName("when no movie is found")
    void noMovieFound() throws IOException {
        String query = "NonexistentMovie123";
        String jsonResponse = fetchMovies(query);
        assertFalse(jsonResponse.contains("\"Search\": null"),"Expected no movies to be found.");
    }

    @Test
    @DisplayName("when a movie is found")
    public void movieFound() throws IOException {
        String query = "Inception";
        String jsonResponse = fetchMovies(query);

        assertTrue(jsonResponse.contains("\"Title\":\"Inception\""), "Expected movie 'Inception' to be found.");
    }

    @Test
    @DisplayName("when entering an empty search query")
    public void emptySearchQuery() throws IOException {
        String query = "";
        String jsonResponse = fetchMovies(query);
        assertTrue(jsonResponse.contains("Error"), "Expected an error for empty search query.");
    }

    private String fetchMovies(String query) throws IOException {
        String urlString = BASE_URL + "?s=" + query + "&apikey=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (Scanner scanner = new Scanner(conn.getInputStream())) {
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
        }

        return response.toString();
    }
}
