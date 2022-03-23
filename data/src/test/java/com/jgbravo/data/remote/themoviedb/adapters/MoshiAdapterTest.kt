package com.jgbravo.data.remote.themoviedb.adapters

import com.google.common.truth.Truth
import com.jgbravo.commons.extensions.toDate
import com.jgbravo.data.remote.themoviedb.adapters.SimpleDateAdapter.Companion.DATE_PATTERN
import com.jgbravo.data.remote.themoviedb.models.MovieDTO
import com.jgbravo.data.utils.FileTestUtils
import com.squareup.moshi.Moshi
import org.junit.Before
import org.junit.Test

class MoshiAdapterTest {

    private val moshi = Moshi.Builder().add(SimpleDateAdapter()).build()

    private lateinit var json: String

    @Before
    fun readJson() {
        json = FileTestUtils.kotlinReadFileWithNewLineFromResources("MovieInDTO.json")
    }

    @Test
    fun `parse movie`() {
        val actual = moshi.adapter(MovieDTO::class.java).fromJson(json)

        val expected = MovieDTO(
            adult = false,
            backdropPath = "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
            genreIds = listOf(28, 12, 878),
            id = 634649,
            originalLanguage = "en",
            originalTitle = "Spider-Man: No Way Home",
            overview = "Peter Parker es desenmascarado y por tanto no es capaz de separar su vida normal de los enormes riesgos que conlleva ser un súper héroe. Cuando pide ayuda a Doctor Strange, los riesgos pasan a ser aún más peligrosos, obligándole a descubrir lo que realmente significa ser Spider-Man.",
            popularity = 9675.798,
            posterPath = "/osYbtvqjMUhEXgkuFJOsRYVpq6N.jpg",
            releaseDate = "2021-12-15".toDate(DATE_PATTERN),
            title = "Spider-Man: No Way Home",
            video = false,
            voteAverage = 8.2,
            voteCount = 9831,
        )

        Truth.assertThat(actual).isEqualTo(expected)
    }
}