package com.jgbravo.core.presentation.managers

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.jgbravo.core.presentation.managers.ThemeManager.ThemeState
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
        when (currentTheme) {
            ThemeState.LIGHT -> currentTheme = ThemeState.DARK
            ThemeState.DARK -> currentTheme = ThemeState.LIGHT
            else -> Unit
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
            else -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            } else {
                AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            }
        }
    }
}