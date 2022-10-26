package com.jgbravo.remote.utils

import com.jgbravo.remote.utils.TheMovieDbUtils.Path.MOVIE_ID

object TheMovieDbUtils {

    const val BASE_URL = "https://api.themoviedb.org"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    const val V3 = "3"

    object Endpoints {
        const val DISCOVER_MOVIE = "$V3/discover/movie"
        const val MOVIE_DETAILS = "$V3/movie/{$MOVIE_ID}"
    }

    object Parameters {
        const val API_KEY = "api_key"
        const val LANGUAGE = "language"
        const val PAGE = "page"
    }

    object Path {
        const val MOVIE_ID = "id"
    }

    fun buildAbsoluteImageUrl(path: String) = "$IMAGE_BASE_URL$path"
}