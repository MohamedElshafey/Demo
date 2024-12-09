package com.intercept.egipa3

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.lifecycleScope
import com.intercept.egipa3.data.language.LanguageManager
import com.intercept.egipa3.ui.main.MainScreen
import com.intercept.egipa3.ui.theme.EGIPATheme
import com.intercept.egipa3.ui.theme.ThemeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeManager: ThemeManager

    @Inject
    lateinit var languageManager: LanguageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initial content setup
        setContent {
            EGIPATheme(themeManager = themeManager) {
                MainScreen()
            }
        }
    }

//    override fun attachBaseContext(newBase: Context) {
//        val language = Locale.getDefault().language // Default to system language
//        val updatedContext = updateBaseContextLocale(newBase, language)
//        super.attachBaseContext(updatedContext)
//    }
//
//    private fun observeLanguageChanges() {
//        lifecycleScope.launch {
//            languageManager.languagePreference.collectLatest { language ->
//                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.locale))
////                applyLocale(language.locale)
////                restartActivity() // Restart activity safely
//            }
//        }
//    }
//
//    private fun applyLocale(language: String) {
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//
//        val config = Configuration(resources.configuration)
//        config.setLocale(locale)
//        applicationContext.createConfigurationContext(config)
//    }
//
//    private fun restartActivity() {
//        finish() // Safely finish the current activity
//        startActivity(intent) // Restart the activity with the same intent
//    }
//
//    private fun updateBaseContextLocale(context: Context, language: String): Context {
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//
//        val config = Configuration(context.resources.configuration)
//        config.setLocale(locale)
//
//        return context.createConfigurationContext(config)
//    }
}
