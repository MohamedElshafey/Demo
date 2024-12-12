package egipa.digitalegypt.com.egipa.ui.more

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import egipa.digitalegypt.com.egipa.data.language.Language
import egipa.digitalegypt.com.egipa.data.language.LanguageManager
import egipa.digitalegypt.com.egipa.ui.theme.ThemeManager
import egipa.digitalegypt.com.egipa.ui.theme.ThemeMode
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeManager: ThemeManager,
) : ViewModel() {

    val themeState = themeManager.themePreference

    fun updateTheme(themeMode: ThemeMode) {
        themeManager.updateThemePreference(themeMode)
    }

    fun updateLanguage(context: Context, language: Language) {
        LanguageManager.saveLanguage(context, language)
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.locale))
    }
}