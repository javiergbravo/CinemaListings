package com.jgbravo.actiasystemsmobile.features.movieDetails.models.mappers

import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.commons.models.mappers.UiMapper
import com.jgbravo.commons.extensions.year
import com.jgbravo.domain.models.MovieDetailsDomainModel

class MovieDetailsUiMapper : UiMapper<MovieDetailsDomainModel, MovieDetails>() {

    override fun mapToUi(domainModel: MovieDetailsDomainModel, moreInfo: HashMap<String, Any>?): MovieDetails {
        return MovieDetails(
            id = domainModel.id,
            title = domainModel.title,
            director = domainModel.director,
            banner = domainModel.banner,
            poster = domainModel.poster,
            releaseYear = domainModel.releaseDate.year(),
            duration = mapDuration(domainModel.duration),
            description = domainModel.description,
            score = domainModel.score / 2,
        )
    }

    private fun mapDuration(duration: Int): String {
        val hours = duration / 60
        val minutes = duration % 60
        return "${hours}h ${minutes}min"
    }
}