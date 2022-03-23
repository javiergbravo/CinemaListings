package com.jgbravo.domain.models.mappers

import com.jgbravo.commons.models.mappers.DomainMapper
import com.jgbravo.data.repository.models.MovieDataModel
import com.jgbravo.domain.models.MovieDomainModel

class MovieDomainMapper : DomainMapper<MovieDataModel, MovieDomainModel>() {

    override fun mapToDomain(dataModel: MovieDataModel, moreInfo: HashMap<String, Any>?): MovieDomainModel {
        return MovieDomainModel(
            id = dataModel.id,
            title = dataModel.title,
            poster = dataModel.poster,
            releaseDate = dataModel.releaseDate,
            rate = dataModel.rate,
            votes = dataModel.votes
        )
    }
}