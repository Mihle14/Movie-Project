package moviesearch;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import moviesearch.controllers.MovieController;
import moviesearch.services.MovieService;
import moviesearch.utils.APIClient;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(7000);

        APIClient apiClient = new APIClient();
        MovieService movieService = new MovieService(apiClient);
        MovieController movieController = new MovieController(movieService);

        app.get("/", ctx -> ctx.result("Welcome to the Movie Search App"));
        app.get("/search", movieController.searchMovies);

        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500);
            ctx.result("Internal Server Error: " + e.getMessage());
        });
    }
}
