package com.jgbravo.data.repository.mappers

import com.jgbravo.commons.extensions.getOrThrow
import com.jgbravo.commons.models.mappers.ApiMapper
import com.jgbravo.data.remote.themoviedb.models.MovieDetailsDTO
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import com.jgbravo.data.utils.TheMovieDbUtils.buildAbsoluteImageUrl

class MovieDetailsApiMapper : ApiMapper<MovieDetailsDTO, MovieDetailsDataModel>() {

    override fun mapToData(dtoModel: MovieDetailsDTO, moreInfo: HashMap<String, Any>?): MovieDetailsDataModel {
        return MovieDetailsDataModel(
            id = dtoModel::id.getOrThrow(),
            title = dtoModel::title.getOrThrow(),
            director = "N/A", // Director property does not exist.
            banner = buildAbsoluteImageUrl(dtoModel::backdropPath.getOrThrow()),
            poster = dtoModel.posterPath?.let { buildAbsoluteImageUrl(it) },
            releaseDate = dtoModel::releaseDate.getOrThrow(),
            duration = dtoModel::runtime.getOrThrow(),
            description = dtoModel::overview.getOrThrow(),
            score = dtoModel.voteAverage ?: 0.0,
            votesAmount = dtoModel.voteCount,
        )
    }
}