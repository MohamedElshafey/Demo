package egipa.digitalegypt.com.egipa.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import egipa.digitalegypt.com.egipa.R
import egipa.digitalegypt.com.egipa.ui.theme.LocalCustomColors

@Composable
fun SavedScreen() {
    val colors = LocalCustomColors.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.savedPlacesCellColor)
    ) {
        Text(stringResource(R.string.saved_title))

    }
}