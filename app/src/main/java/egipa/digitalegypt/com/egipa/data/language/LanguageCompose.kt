package egipa.digitalegypt.com.egipa.data.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

// Define the CompositionLocal
val LocalLanguageManager = staticCompositionLocalOf<egipa.digitalegypt.com.egipa.data.language.LanguageManager> {
    error("LanguageManager not provided")
}

// Provide the CompositionLocal at the root level
@Composable
fun LanguageProvider(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val languageManager = remember {
        egipa.digitalegypt.com.egipa.data.language.LanguageManager.apply { initialize(context) }
    }

    CompositionLocalProvider(LocalLanguageManager provides languageManager) {
        content()
    }
}
