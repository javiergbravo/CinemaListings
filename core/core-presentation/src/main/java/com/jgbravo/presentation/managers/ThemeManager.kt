package com.jgbravo.presentation.managers

interface ThemeManager {

    enum class ThemeState {
        LIGHT, DARK,
    }

    fun toggleTheme()

    fun setTheme(newTheme: ThemeState)

    fun updateToCurrentTheme()
}