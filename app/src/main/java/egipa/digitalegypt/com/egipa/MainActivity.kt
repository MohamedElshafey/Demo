package egipa.digitalegypt.com.egipa

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import egipa.digitalegypt.com.egipa.ui.main.MainScreen
import egipa.digitalegypt.com.egipa.ui.theme.EGIPATheme
import egipa.digitalegypt.com.egipa.ui.theme.ThemeManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initial content setup
        setContent {
            EGIPATheme(themeManager = themeManager) {
                MainScreen()
            }
        }
    }
}
