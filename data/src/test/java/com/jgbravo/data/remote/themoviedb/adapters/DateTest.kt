package com.jgbravo.data.remote.themoviedb.adapters

import com.google.common.truth.Truth
import com.jgbravo.core.extensions.toDate
import com.jgbravo.core.extensions.year
import com.jgbravo.data.remote.themoviedb.adapters.SimpleDateAdapter.Companion.DATE_PATTERN
import org.junit.Test

class DateTest {

    companion object {
        private const val EXPECTED_YEAR = 2021
    }

    private val fakeDate = "2021-12-15"

    @Test
    fun `map Date to String by SimpleDateAdapter`() {
        val dateAdapter = SimpleDateAdapter()
        val dateFromApi = dateAdapter.fromJson(fakeDate)
        val year = dateFromApi?.year()
        Truth.assertThat(year).isEqualTo(EXPECTED_YEAR)
    }

    @Test
    fun `map Date to String by extension function`() {
        val date = fakeDate.toDate(DATE_PATTERN)
        val year = date?.year()
        Truth.assertThat(year).isEqualTo(EXPECTED_YEAR)
    }

}