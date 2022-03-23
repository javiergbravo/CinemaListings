package com.jgbravo.actiasystemsmobile.features.billboard.models.mappers

import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovie
import com.jgbravo.commons.models.mappers.UiMapper
import com.jgbravo.core.extensions.year
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