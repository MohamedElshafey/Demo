package egipa.digitalegypt.com.egipa.ui.more

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import egipa.digitalegypt.com.egipa.data.language.Language
import egipa.digitalegypt.com.egipa.data.language.LanguageManager
import egipa.digitalegypt.com.egipa.ui.theme.ThemeManager
import egipa.digitalegypt.com.egipa.ui.theme.ThemeMode
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val languageManager: LanguageManager,
    private val themeManager: ThemeManager,
) : ViewModel() {

    val themeState = themeManager.themePreference
    val currentLanguage = languageManager.currentLanguage

    fun updateTheme(themeMode: ThemeMode) {
        themeManager.updateThemePreference(themeMode)
    }

    fun updateLanguage(language: Language) {
        languageManager.updateLanguage(language)
    }
}