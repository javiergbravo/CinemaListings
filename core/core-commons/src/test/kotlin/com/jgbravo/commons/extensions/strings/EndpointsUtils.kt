package com.jgbravo.commons.extensions.strings

import com.google.common.truth.Truth
import com.jgbravo.commons.extensions.getAllArguments
import com.jgbravo.commons.extensions.getArgumentsWithBraces
import com.jgbravo.commons.extensions.replaceArgumentsByCompletePath
import org.junit.Test

class EndpointsUtils {

    private val endpoint = "endpoint/{id}/{country}/{random}"
    private val endpointWithOutArguments = "endpoint/discover/movie"
    private val endpointWithArgumentsFake = "endpoint/discover/movie/{}"

    private val movieEndpoint = "3/movie/{id}/{kiwi}/{apple}"

    @Test
    fun `find endpoint's arguments`() {
        val arguments = endpoint.getAllArguments()
        Truth.assertThat(arguments).hasSize(3)
        val arguments2 = endpointWithOutArguments.getAllArguments()
        Truth.assertThat(arguments2).hasSize(0)
        val arguments3 = endpointWithArgumentsFake.getAllArguments()
        Truth.assertThat(arguments3).hasSize(0)
    }

    @Test
    fun `find endpoint's arguments with braces`() {
        val arguments = endpoint.getArgumentsWithBraces()
        Truth.assertThat(arguments).hasSize(3)
        val arguments2 = endpointWithOutArguments.getArgumentsWithBraces()
        Truth.assertThat(arguments2).hasSize(0)
        val arguments3 = endpointWithArgumentsFake.getArgumentsWithBraces()
        Truth.assertThat(arguments3).hasSize(0)
    }

    @Test
    fun `replace arguments from url and match`() {
        val endpointWithArguments = "3/movie/1003/es_ES/50"
        val endpointReplacingArguments = movieEndpoint.replaceArgumentsByCompletePath(endpointWithArguments)
        Truth.assertThat(endpointReplacingArguments).isEqualTo(endpointWithArguments)
    }
}