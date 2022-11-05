package com.jgbravo.cinemalistings.features.billboard.models.mappers

import com.jgbravo.cinemalistings.features.billboard.models.SummaryMovie
import com.jgbravo.commons.extensions.year
import com.jgbravo.commons.models.mappers.UiMapper
import com.jgbravo.domain.models.MovieDomainModel

class SummaryMovieUiMapper : UiMapper<MovieDomainModel, SummaryMovie>() {

    override fun mapToUi(domainModel: MovieDomainModel, moreInfo: HashMap<String, Any>?): SummaryMovie {
        return SummaryMovie(
            id = domainModel.id,
            title = domainModel.title,
            poster = domainModel.poster,
            releaseYear = domainModel.releaseDate.year()
        )
    }
}