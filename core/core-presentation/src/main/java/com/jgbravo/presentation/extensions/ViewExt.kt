package com.jgbravo.presentation.extensions

import com.google.android.material.appbar.AppBarLayout

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