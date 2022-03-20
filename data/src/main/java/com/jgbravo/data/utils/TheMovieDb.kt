package com.jgbravo.data.utils

object TheMovieDb {

    const val BASE_URL = "https://api.themoviedb.org"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val SPANISH_LANGUAGE = "es-ES"

    object Endpoints {
        const val v3_DISCOVER_MOVIE = "3/discover/movie"
    }

    object Parameters {
        const val API_KEY = "api_key"
        const val LANGUAGE = "language"
        const val PAGE = "page"
    }
}