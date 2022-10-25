package com.jgbravo.actiasystemsmobile.fakeDomain.fakeModels

import com.jgbravo.domain.models.MovieDomainModel
import java.util.*

object FakeDomainMovies {

    val movie1 = MovieDomainModel(
        id = 1,
        title = "Movie1",
        poster = "fakeUrl",
        releaseDate = Date(2022, 10, 19),
        rate = 7.2,
        votes = 425
    )

    val movie2 = MovieDomainModel(
        id = 2,
        title = "Movie2",
        poster = "fakeUrl",
        releaseDate = Date(2020, 10, 19),
        rate = 7.2,
        votes = 425
    )

    val movie3 = MovieDomainModel(
        id = 3,
        title = "Movie1",
        poster = "fakeUrl",
        releaseDate = Date(2019, 10, 19),
        rate = 7.2,
        votes = 425
    )

    val movie4 = MovieDomainModel(
        id = 4,
        title = "Movie4",
        poster = "fakeUrl",
        releaseDate = Date(2011, 10, 19),
        rate = 7.2,
        votes = 425
    )

    val movie5 = MovieDomainModel(
        id = 5,
        title = "Movie5",
        poster = "fakeUrl",
        releaseDate = Date(2021, 10, 19),
        rate = 7.2,
        votes = 425
    )

    val movie6 = MovieDomainModel(
        id = 6,
        title = "Movie6",
        poster = "fakeUrl",
        releaseDate = Date(2022, 10, 19),
        rate = 7.2,
        votes = 425
    )
}