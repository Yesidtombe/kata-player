package com.dojo.globant.mycustomplayer.common.util

/**
 * This is a generic class to manage API responses with 2 options: Success and Error
 *
 * @param data Data from API using a custom model.
 * @param errorMessage A custom error message.
 */
sealed class ApiResponse<T>(val data: T? = null, val errorMessage: UiText? = null) {
    class Success<T>(data: T?) : ApiResponse<T>(data)
    class Error<T>(
        data: T? = null,
        errorMessage: UiText? = null
    ) : ApiResponse<T>(data, errorMessage)
}