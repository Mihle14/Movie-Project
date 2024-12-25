document.getElementById('searchButton').addEventListener('click', function () {
    let movieTitle = document.getElementById('movieInput').value.trim();
    if (movieTitle === '') {
        alert('Please enter a movie title');
        return;
    }
    searchMovie(movieTitle);
});

function searchMovie(title) {
    let apiKey = 'dcc16f75'
    let url = `https://www.omdbapi.com/?t=${title}&apikey=${apiKey}`;
    let movieDetails = document.getElementById('movieDetails');
    movieDetails.innerHTML = '<p>Loading...</p>';

    fetch(url)
        .then(response => response.json())
        .then(data => displayMovieDetails(data))
        .catch(error => {
            movieDetails.innerHTML = '<p>There was an error fetching the movie data. Please try again.</p>';
            console.error('Error:', error);
        });
}

function displayMovieDetails(movie) {
    let movieDetails = document.getElementById('movieDetails');

    if (movie.Response === "True") {
        movieDetails.innerHTML = `
            <h2>${movie.Title} (${movie.Year})</h2>
            <img src="${movie.Poster}" alt="Movie Poster">
            <p><strong>Rating:</strong> ${movie.imdbRating}</p>
            <p><strong>Plot:</strong> ${movie.Plot}</p>
        `;
    } else {
        movieDetails.innerHTML = `<p>Movie not found. Please try another title.</p>`;
    }
}
