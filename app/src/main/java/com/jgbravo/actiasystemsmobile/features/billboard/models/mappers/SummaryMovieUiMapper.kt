package com.jgbravo.actiasystemsmobile.features.billboard.models.mappers

import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovieUiModel
import com.jgbravo.core.models.mappers.UiMapper
import com.jgbravo.domain.models.MovieDomainModel

class SummaryMovieUiMapper : UiMapper<MovieDomainModel, SummaryMovieUiModel>() {

    override fun mapToUi(domainModel: MovieDomainModel, moreInfo: HashMap<String, Any>?): SummaryMovieUiModel {
        return SummaryMovieUiModel(
            id = domainModel.id,
            title = domainModel.title,
            poster = domainModel.poster,
            releaseYear = domainModel.releaseDate.year
        )
    }
}