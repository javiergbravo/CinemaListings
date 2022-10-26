package com.jgbravo.actiasystemsmobile.fakeDomain.fakeModels

import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.models.MovieDomainModel
import java.util.*

object FakeDomain {

    object Movies {
        val MOVIE_1 = MovieDomainModel(
            id = 1,
            title = "Movie1",
            poster = "fakeUrl",
            releaseDate = Date(2022, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_2 = MovieDomainModel(
            id = 2,
            title = "Movie2",
            poster = "fakeUrl",
            releaseDate = Date(2020, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_3 = MovieDomainModel(
            id = 3,
            title = "Movie1",
            poster = "fakeUrl",
            releaseDate = Date(2019, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_4 = MovieDomainModel(
            id = 4,
            title = "Movie4",
            poster = "fakeUrl",
            releaseDate = Date(2011, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_5 = MovieDomainModel(
            id = 5,
            title = "Movie5",
            poster = "fakeUrl",
            releaseDate = Date(2021, 10, 19),
            rate = 7.2,
            votes = 425
        )

        val MOVIE_6 = MovieDomainModel(
            id = 6,
            title = "Movie6",
            poster = "fakeUrl",
            releaseDate = Date(2022, 10, 19),
            rate = 7.2,
            votes = 425
        )
    }

    object ResourceMoviesList {
        val MOVIES_LIST_1: Resource<List<MovieDomainModel>> = Resource.Success<List<MovieDomainModel>>(
            listOf(
                Movies.MOVIE_1,
                Movies.MOVIE_2,
                Movies.MOVIE_3
            )
        )

        val MOVIES_LIST_2: Resource<List<MovieDomainModel>> = Resource.Success<List<MovieDomainModel>>(
            listOf(
                Movies.MOVIE_4,
                Movies.MOVIE_5,
                Movies.MOVIE_6
            )
        )

        val MOVIES_LIST_ERROR: Resource<List<MovieDomainModel>> = FakeResource.RESOURCE_ERROR
    }


    object MovieDetails {
        val MOVIE_1 = MovieDetailsDomainModel(
            id = 1,
            title = "Movie1",
            director = "",
            banner = "fakeUrlBanner",
            poster = "fakeUrlPoster",
            releaseDate = Date(2022, 10, 19),
            duration = 1,
            description = "fakeDescription1",
            score = 7.2,
            votesAmount = 425
        )
    }

    object ResourceMovieDetails {
        val MOVIE_DETAILS = Resource.Success<MovieDetailsDomainModel>(MovieDetails.MOVIE_1)

        val MOVIE_DETAILS_ERROR: Resource<MovieDetailsDomainModel> = FakeResource.RESOURCE_ERROR
    }
}