package egipa.digitalegypt.com.egipa.data.language

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// A class to manage language settings
object LanguageManager {
    private const val PREF_NAME = "language_prefs"
    private const val KEY_SELECTED_LANGUAGE = "selected_language"
    private var isInitialized = false

    // Default language maps
    private val englishMap = mapOf(
        "welcome_message" to "Welcome",
        "greeting_message" to "Hello",
        "theme_title" to "Theme",
        "saved_title" to "Saved screen"

    )

    private val arabicMap = mapOf(
        "welcome_message" to "أهلا",
        "greeting_message" to "مرحبا",
        "theme_title" to "ثيم",
        "saved_title" to "شاشه المحفوظات"
    )

    private val _currentLanguage = MutableStateFlow<egipa.digitalegypt.com.egipa.data.language.Language?>(
        egipa.digitalegypt.com.egipa.data.language.Language.ENGLISH
    )
    val currentLanguage = egipa.digitalegypt.com.egipa.data.language.LanguageManager._currentLanguage.asStateFlow()

    // Load language from SharedPreferences
    fun initialize(context: Context) {
        val prefs =
            egipa.digitalegypt.com.egipa.data.language.LanguageManager.getSharedPreferences(context)
        egipa.digitalegypt.com.egipa.data.language.LanguageManager._currentLanguage.value = egipa.digitalegypt.com.egipa.data.language.Language.valueOf(
            prefs.getString(egipa.digitalegypt.com.egipa.data.language.LanguageManager.KEY_SELECTED_LANGUAGE, egipa.digitalegypt.com.egipa.data.language.Language.ENGLISH.name)
                ?: egipa.digitalegypt.com.egipa.data.language.Language.ENGLISH.name
        )
        egipa.digitalegypt.com.egipa.data.language.LanguageManager.isInitialized = true
    }

    fun saveLanguage(context: Context, language: egipa.digitalegypt.com.egipa.data.language.Language) {
        val prefs =
            egipa.digitalegypt.com.egipa.data.language.LanguageManager.getSharedPreferences(context)
        prefs.edit().putString(egipa.digitalegypt.com.egipa.data.language.LanguageManager.KEY_SELECTED_LANGUAGE, language.name).apply()
        egipa.digitalegypt.com.egipa.data.language.LanguageManager._currentLanguage.value = language
    }


    fun getString(key: String): String {
        if (!egipa.digitalegypt.com.egipa.data.language.LanguageManager.isInitialized) {
            throw IllegalStateException("LanguageManager must be initialized using LanguageManager.initialize() before fetching strings.")
        }
        return egipa.digitalegypt.com.egipa.data.language.LanguageManager.getCurrentLanguageMap()[key] ?: key // Return key if no translation is found
    }

    private fun getCurrentLanguageMap(): Map<String, String> {
        return when (egipa.digitalegypt.com.egipa.data.language.LanguageManager._currentLanguage.value) {
            egipa.digitalegypt.com.egipa.data.language.Language.ARABIC -> egipa.digitalegypt.com.egipa.data.language.LanguageManager.arabicMap
            else -> egipa.digitalegypt.com.egipa.data.language.LanguageManager.englishMap
        }
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(egipa.digitalegypt.com.egipa.data.language.LanguageManager.PREF_NAME, Context.MODE_PRIVATE)
    }
}