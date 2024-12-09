package com.intercept.egipa3.data.language

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale
import javax.inject.Inject

class LanguageManager @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private const val PREFS_NAME = "language_prefs"
        private const val KEY_LANGUAGE = "language"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _languagePreference = MutableStateFlow(getSavedLanguage())
    val languagePreference = _languagePreference.asStateFlow()

    private fun getSavedLanguage(): Language {
        val savedValue = sharedPreferences.getString(KEY_LANGUAGE, Language.ENGLISH.name)
        return savedValue?.let {
            Language.valueOf(savedValue)
        } ?: getSystemLanguage()
    }

    private fun getSystemLanguage(): Language {
        return if (isSystemLanguageArabic()) Language.ARABIC else Language.ENGLISH
    }

    private fun isSystemLanguageArabic(): Boolean {
        return Locale.getDefault().language == "ar"
    }

    fun updateLanguage(newLanguage: Language) {
        _languagePreference.value = newLanguage
        saveLanguage(newLanguage)
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguage.locale))
    }

    private fun saveLanguage(language: Language) {
        sharedPreferences.edit()
            .putString(KEY_LANGUAGE, language.name)
            .apply()
    }

}