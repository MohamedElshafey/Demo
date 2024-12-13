package egipa.digitalegypt.com.egipa.data.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject constructor() {

    private val _currentLanguage = MutableStateFlow(determineCurrentLanguage())
    val currentLanguage: StateFlow<Language> = _currentLanguage

    fun updateLanguage(newLanguage: Language) {
        if (_currentLanguage.value != newLanguage) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguage.locale))
            _currentLanguage.value = newLanguage
        }
    }

    private fun determineCurrentLanguage(): Language {
        val appLocales = AppCompatDelegate.getApplicationLocales()
        val languageTags = appLocales.toLanguageTags()

        return when {
            languageTags.isNotEmpty() -> mapLocaleToLanguage(languageTags)
            else -> mapLocaleToLanguage(getDeviceLocale().language)
        }
    }

    private fun mapLocaleToLanguage(locale: String): Language {
        return when (locale.lowercase()) {
            Language.ARABIC.locale -> Language.ARABIC
            else -> Language.ENGLISH
        }
    }

    private fun getDeviceLocale(): Locale {
        return LocaleListCompat.getAdjustedDefault()[0] ?: Locale.getDefault()
    }
}
