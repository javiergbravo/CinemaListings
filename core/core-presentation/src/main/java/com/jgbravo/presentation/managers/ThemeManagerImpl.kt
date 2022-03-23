package com.jgbravo.presentation.managers

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import com.jgbravo.presentation.managers.ThemeManager.ThemeState
import javax.inject.Inject

class ThemeManagerImpl @Inject constructor(private val context: Context) : ThemeManager {

    private val defaultTheme: ThemeState
        get() = when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> ThemeState.LIGHT
            Configuration.UI_MODE_NIGHT_NO -> ThemeState.DARK
            else -> ThemeState.LIGHT
        }

    private var currentTheme = defaultTheme

    override fun toggleTheme() {
        currentTheme = when (currentTheme) {
            ThemeState.LIGHT -> ThemeState.DARK
            ThemeState.DARK -> ThemeState.LIGHT
        }
        setTheme(currentTheme)
    }

    override fun setTheme(newTheme: ThemeState) {
        if (currentTheme != newTheme) {
            currentTheme = newTheme
        }
        AppCompatDelegate.setDefaultNightMode(getThemeMode())
    }

    override fun updateToCurrentTheme() {
        setTheme(currentTheme)
    }

    private fun getThemeMode(): Int {
        return when (currentTheme) {
            ThemeState.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            ThemeState.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        }
    }
}