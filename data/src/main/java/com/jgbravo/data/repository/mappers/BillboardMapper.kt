package com.jgbravo.data.repository.mappers

import com.jgbravo.core.extensions.getOrThrow
import com.jgbravo.core.extensions.mapList
import com.jgbravo.core.models.mappers.ApiMapper
import com.jgbravo.data.remote.themoviedb.models.BillboardDTO
import com.jgbravo.data.remote.themoviedb.models.MovieDTO
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.SummaryMovieDataModel

class BillboardMapper : ApiMapper<BillboardDTO, BillboardDataModel>() {

    override fun mapToData(dtoModel: BillboardDTO, moreInfo: HashMap<String, Any>?): BillboardDataModel {
        return BillboardDataModel(
            movieList = dtoModel.results.mapList(SummaryMovieMapper()),
            page = dtoModel.page,
            totalPages = dtoModel.totalPages
        )
    }
}

class SummaryMovieMapper : ApiMapper<MovieDTO, SummaryMovieDataModel>() {

    override fun mapToData(dtoModel: MovieDTO, moreInfo: HashMap<String, Any>?): SummaryMovieDataModel {
        return SummaryMovieDataModel(
            id = dtoModel::id.getOrThrow(),
            title = dtoModel::title.getOrThrow(),
            poster = dtoModel::posterPath.getOrThrow(),
            releaseDate = dtoModel::releaseDate.getOrThrow(),
            rate = dtoModel::voteAverage.getOrThrow(),
            votes = dtoModel::voteCount.getOrThrow(),
        )
    }
}