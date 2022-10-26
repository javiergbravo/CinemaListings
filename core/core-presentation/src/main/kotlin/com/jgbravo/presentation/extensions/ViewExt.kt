package com.jgbravo.presentation.extensions

import android.content.Context
import android.view.View
import android.view.ViewParent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun <T : View> T.visibilityBy(condition: () -> Boolean) {
    this.visibility = when {
        condition() -> View.VISIBLE
        else -> View.GONE
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun AppBarLayout.setUpExpandable(
    isToolbarExpanded: Boolean = true,
    onCollapseToolbar: () -> Unit,
    onExpandToolbar: () -> Unit,
) {
    var scrollRange = -1
    var isExpanded = isToolbarExpanded
    this.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (scrollRange == -1) {
            scrollRange = appBarLayout.totalScrollRange
        }
        if (scrollRange + verticalOffset <= 0 && isExpanded) {
            isExpanded = false
            onCollapseToolbar()
        } else if (scrollRange + verticalOffset > 0 && !isExpanded) {
            isExpanded = true
            onExpandToolbar()
        }
    })
}

fun TextInputEditText.validateField(errorMsg: String, validator: (text: String) -> Boolean) {
    this.getInputLayout()?.let { inputLayout ->
        if (validator(this.text.toString())) {
            inputLayout.isErrorEnabled = true
            inputLayout.error = errorMsg
        } else {
            inputLayout.resetTextInputLayout()
        }
    }
}

fun TextInputEditText.getInputLayout(viewParent: ViewParent? = null, maxDepth: Int = 3): TextInputLayout? {
    val view = viewParent ?: this.parent
    return when {
        view is TextInputLayout -> view
        maxDepth > 0 -> this.getInputLayout(view.parent, maxDepth - 1)
        else -> null
    }
}

fun TextInputLayout.resetTextInputLayout(resetText: Boolean = false) {
    this.isErrorEnabled = false
    this.error = null
    if (resetText) {
        this.editText?.setText("")
    }
}

fun TextInputEditText.showError(errorMsg: String) {
    this.getInputLayout()?.let { inputLayout ->
        inputLayout.isErrorEnabled = true
        inputLayout.error = errorMsg
    }
}

fun TextInputEditText.cleanError() {
    this.getInputLayout()?.let { inputLayout ->
        inputLayout.isErrorEnabled = false
        inputLayout.error = ""
    }
}