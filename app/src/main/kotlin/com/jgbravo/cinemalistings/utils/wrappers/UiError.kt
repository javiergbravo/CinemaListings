package com.jgbravo.cinemalistings.utils.wrappers

import androidx.annotation.StringRes
import com.jgbravo.cinemalistings.R

sealed class UiError(@StringRes val title: Int, @StringRes val message: Int) {

    data object UnknownError: UiError(
        title = R.string.dialog_error_unknown_title,
        message = R.string.dialog_error_unknown_body
    )

    data object NoInternetError: UiError(
        title = R.string.dialog_error_no_internet_title,
        message = R.string.dialog_error_no_internet_body
    )
}