package com.jgbravo.data.repository.mappers

import com.jgbravo.core.extensions.getOrThrow
import com.jgbravo.core.extensions.mapList
import com.jgbravo.core.models.mappers.ApiMapper
import com.jgbravo.data.remote.themoviedb.models.BillboardDTO
import com.jgbravo.data.remote.themoviedb.models.MovieDTO
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDataModel
import com.jgbravo.data.utils.TheMovieDb.IMAGE_BASE_URL

class BillboardApiMapper : ApiMapper<BillboardDTO, BillboardDataModel>() {

    override fun mapToData(dtoModel: BillboardDTO, moreInfo: HashMap<String, Any>?): BillboardDataModel {
        return BillboardDataModel(
            movieList = dtoModel.results.mapList(MovieApiMapper(), true),
            page = dtoModel.page,
            totalPages = dtoModel.totalPages
        )
    }
}

class MovieApiMapper : ApiMapper<MovieDTO, MovieDataModel>() {

    override fun mapToData(dtoModel: MovieDTO, moreInfo: HashMap<String, Any>?): MovieDataModel {
        return MovieDataModel(
            id = dtoModel::id.getOrThrow(),
            title = dtoModel::title.getOrThrow(),
            poster = buildAbsoluteImageUrl(dtoModel::posterPath.getOrThrow()),
            releaseDate = dtoModel::releaseDate.getOrThrow(),
            rate = dtoModel::voteAverage.getOrThrow(),
            votes = dtoModel::voteCount.getOrThrow(),
        )
    }

    private fun buildAbsoluteImageUrl(path: String) = "$IMAGE_BASE_URL$path"
}