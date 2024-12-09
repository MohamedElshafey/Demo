package com.intercept.egipa3.ui.theme

import android.content.Context
import android.content.res.Configuration
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class ThemeManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val PREFS_NAME = "theme_prefs"
        private const val KEY_THEME_MODE = "theme_mode"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _themePreference = MutableStateFlow(getSavedThemePreference())
    val themePreference: StateFlow<ThemeMode> = _themePreference

    private fun getSavedThemePreference(): ThemeMode {
        val savedValue = sharedPreferences.getString(KEY_THEME_MODE, ThemeMode.DARK.name)
        return savedValue?.let {
            ThemeMode.valueOf(savedValue)
        } ?: getSystemTheme()
    }


    private fun getSystemTheme(): ThemeMode {
        return if (isSystemInDarkTheme(context)) ThemeMode.DARK else ThemeMode.LIGHT
    }

    private fun isSystemInDarkTheme(context: Context): Boolean {
        val currentMode =
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentMode == Configuration.UI_MODE_NIGHT_YES
    }


    fun updateThemePreference(newTheme: ThemeMode) {
        _themePreference.value = newTheme
        saveThemePreference(newTheme)
    }

    private fun saveThemePreference(themeMode: ThemeMode) {
        sharedPreferences.edit()
            .putString(KEY_THEME_MODE, themeMode.name)
            .apply()
    }
}