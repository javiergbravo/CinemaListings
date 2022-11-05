package com.jgbravo.cinemalistings.features.billboard.filters

import com.google.common.truth.Truth
import com.jgbravo.cinemalistings.features.billboard.models.MovieFilterModel
import org.junit.Before
import org.junit.Test

class MovieFiltersTest {

    private val filter = MovieFilterModel()

    @Before
    fun cleanFilters() {
        filter.cleanYearsFilters()
    }

    @Test
    fun `yearFrom before 1000`() {
        val inputYearFrom = "900"
        filter.setYearFrom(inputYearFrom)
        Truth.assertThat(filter.yearFrom).isNull()
    }

    @Test
    fun `yearFrom not number`() {
        val inputYearFrom = "#qw12"
        filter.setYearFrom(inputYearFrom)
        Truth.assertThat(filter.yearFrom).isNull()
    }

    @Test
    fun `yearTo before 1000`() {
        val inputYearTo = "900"
        filter.setYearTo(inputYearTo)
        Truth.assertThat(filter.yearTo).isNull()
    }

    @Test
    fun `yearTo not number`() {
        val inputYearTo = "#qw12"
        filter.setYearTo(inputYearTo)
        Truth.assertThat(filter.yearTo).isNull()
    }


    @Test
    fun `yearFrom before yearTo`() {
        val inputYearFrom = "1995"
        filter.setYearFrom(inputYearFrom)

        val inputYearTo = "2000"
        filter.setYearTo(inputYearTo)

        val validInputs = filter.hasValidYearFilters(inputYearTo)
        Truth.assertThat(validInputs).isTrue()
    }

    @Test
    fun `yearFrom after yearTo`() {
        val inputYearFrom = "2000"
        filter.setYearFrom(inputYearFrom)

        val inputYearTo = "1995"
        filter.setYearTo(inputYearTo)

        val validInputs = filter.hasValidYearFilters(inputYearTo)
        Truth.assertThat(validInputs).isFalse()
    }
}