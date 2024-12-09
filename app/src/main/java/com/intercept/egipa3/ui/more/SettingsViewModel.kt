package com.intercept.egipa3.ui.more

import androidx.lifecycle.ViewModel
import com.intercept.egipa3.data.language.Language
import com.intercept.egipa3.data.language.LanguageManager
import com.intercept.egipa3.ui.theme.ThemeManager
import com.intercept.egipa3.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeManager: ThemeManager,
    private val languageManager: LanguageManager
) : ViewModel() {

    val themeState = themeManager.themePreference
    val languageState = languageManager.languagePreference

    fun updateTheme(themeMode: ThemeMode) {
        themeManager.updateThemePreference(themeMode)
    }

    fun updateLanguage(language: Language) {
        languageManager.updateLanguage(language)
    }

}