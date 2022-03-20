package com.jgbravo.domain.models.mappers

import com.jgbravo.core.models.mappers.DomainMapper
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import com.jgbravo.domain.models.MovieDetailsDomainModel

class MovieDetailsDomainMapper : DomainMapper<MovieDetailsDataModel, MovieDetailsDomainModel>() {

    override fun mapToDomain(
        dataModel: MovieDetailsDataModel,
        moreInfo: HashMap<String, Any>?
    ): MovieDetailsDomainModel {
        return MovieDetailsDomainModel(
            id = dataModel.id,
            title = dataModel.title,
            director = dataModel.director,
            banner = dataModel.banner,
            poster = dataModel.poster,
            releaseDate = dataModel.releaseDate,
            duration = dataModel.duration,
            description = dataModel.description,
            score = dataModel.score,
            votesAmount = dataModel.votesAmount,
        )
    }
}