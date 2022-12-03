package com.jgbravo.commons.extensions

/**
 * Get all arguments (from endpoint) between "{" and "}" characters
 *  Example: endpoint/{firstParameter}/{secondParameter} -> returns [firstParameter, secondParameter]
 * @receiver String Endpoint
 * @return List<String> All arguments found inside list
 */
fun String.getAllArguments(): List<String> {
    val regex = "(?<=\\{)(\\w*?)(?=\\})".toRegex()
    val matches = regex.findAll(this)
    val arguments = matches.map { it.value }.filter { it.isNotBlank() }
    return arguments.toList()
}

/**
 * Get all arguments (from endpoint) between "{" and "}" characters included braces characters
 * Example: endpoint/{firstParameter}/{secondParameter} -> returns [{firstParameter}, {secondParameter}]
 * @receiver String Endpoint
 * @return List<String> All arguments found inside list
 */
fun String.getArgumentsWithBraces(): List<String> {
    val regex = "(\\{\\w*?\\})".toRegex()
    val matches = regex.findAll(this)
    val arguments = matches.map { it.value }.filter { it != "{}" }
    return arguments.toList()
}

/**
 * Replace all arguments from endpoint by another endpoint with arguments evaluated.
 * Return empty string if there are some error
 * @receiver String Endpoint with arguments unevaluated inside bracers characters (ex: baseEndpoint/{id})
 * @param endpointWithArguments String Endpoint with arguments evaluated (ex: baseEndpoint/1003)
 * @return String First endpoint with arguments evaluated by endpoint param
 */
fun String.replaceArgumentsByCompletePath(endpointWithArguments: String): String {
    val baseEndpoint = this.substringBefore("/{")
    if (!endpointWithArguments.contains(baseEndpoint)) return ""
    val dataArguments = endpointWithArguments
        .replace(baseEndpoint, "")
        .split("/")
        .filter { it.isNotBlank() }

    val arguments = this.getArgumentsWithBraces()
    if (dataArguments.size != arguments.size) return ""

    var endpoint = this
    try {
        for (index in arguments.indices) {
            endpoint = endpoint.replace(arguments[index], dataArguments[index])
        }
    } catch (e: Exception) {
        endpoint = ""
    }
    return endpoint
}