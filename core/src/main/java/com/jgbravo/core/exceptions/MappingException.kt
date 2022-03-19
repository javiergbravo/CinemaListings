package com.jgbravo.core.exceptions

data class MappingException(
    private val property: String,
    private val reason: MappingReason
) : AppException(
    code = AppCodeException.MAPPING_EXCEPTION,
    msg = "Error mapping property '$property' cause $reason."
)

enum class MappingReason(val msg: String) {
    PROPERTY_NOT_FOUND("Model has not this property"),
    PROPERTY_NULL("Property is null or has different name"),
    PROPERTY_EMPTY("Property value is empty"),
    BUILD_OBJECT("Error building object"),
    INVALID_NUMBER("Invalid number")
}