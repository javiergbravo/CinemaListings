package com.jgbravo.cinemalistings.fakeData.fakeModels

import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDataModel
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import java.util.*

object FakeDataModel {

    object MovieModel {
        val MOVIE_1 = MovieDataModel(
            id = 1,
            title = "Movie1",
            poster = "fakeUrl",
            releaseDate = Date(2022, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_2 = MovieDataModel(
            id = 2,
            title = "Movie2",
            poster = "fakeUrl",
            releaseDate = Date(2020, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_3 = MovieDataModel(
            id = 3,
            title = "Movie1",
            poster = "fakeUrl",
            releaseDate = Date(2019, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_4 = MovieDataModel(
            id = 4,
            title = "Movie4",
            poster = "fakeUrl",
            releaseDate = Date(2011, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_5 = MovieDataModel(
            id = 5,
            title = "Movie5",
            poster = "fakeUrl",
            releaseDate = Date(2021, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_6 = MovieDataModel(
            id = 6,
            title = "Movie6",
            poster = "fakeUrl",
            releaseDate = Date(2022, 10, 19),
            rate = 7.2,
            votes = 425
        )
    }

    object MoviesList {
        val MOVIES_LIST_1 = listOf<MovieDataModel>(MovieModel.MOVIE_1, MovieModel.MOVIE_2, MovieModel.MOVIE_3)

        val MOVIES_LIST_2 = listOf<MovieDataModel>(MovieModel.MOVIE_4, MovieModel.MOVIE_5, MovieModel.MOVIE_6)
    }

    object BillboardModel {
        val BILLBOARD_PAGE_1 = BillboardDataModel(
            movieList = MoviesList.MOVIES_LIST_1,
            page = 1,
            totalPages = 2
        )

        val BILLBOARD_PAGE_2 = BillboardDataModel(
            movieList = MoviesList.MOVIES_LIST_2,
            page = 2,
            totalPages = 2
        )
    }

    object MovieDetails {
        val MOVIE_DETAILS = MovieDetailsDataModel(
            id = 4,
            title = "Movie4",
            poster = "fakeUrl",
            releaseDate = Date(2011, 10, 19),
            director = "fakeDirector",
            banner = "fakeBanner",
            duration = 2,
            description = "fakeDescription",
            score = 7.2,
            votesAmount = 425,
        )
    }
}