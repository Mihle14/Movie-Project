package moviesearch.controllers;

import io.javalin.http.Handler;
import moviesearch.models.Movie;
import moviesearch.services.MovieService;

public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public Handler searchMovies = ctx -> {
        String query = ctx.queryParam("title");
        if (query == null || query.isEmpty()) {
            ctx.status(400).result("Title query parameter is required");
            return;
        }
        Movie movie = movieService.searchMovies(query);
        if (movie == null) {
            ctx.status(404).result("Movie not found");
        } else {
            ctx.json(movie);
        }
    };
}
