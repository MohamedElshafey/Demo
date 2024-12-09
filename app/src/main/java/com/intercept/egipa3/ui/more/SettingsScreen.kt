package com.intercept.egipa3.ui.more

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.intercept.egipa3.data.language.Language
import com.intercept.egipa3.ui.theme.ThemeMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val selectedTheme by settingsViewModel.themeState.collectAsState()
    val selectedLanguage by settingsViewModel.languageState.collectAsState()

    var languageExpanded by remember { mutableStateOf(false) }
    var themeExpanded by remember { mutableStateOf(false) }
    var exploreFilterExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Language Option
            SettingsItem(
                title = "Language",
                expanded = languageExpanded,
                onExpandChange = { languageExpanded = !languageExpanded }
            ) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedLanguage == Language.ENGLISH,
                            onClick = { settingsViewModel.updateLanguage(Language.ENGLISH) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("English")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedLanguage == Language.ARABIC,
                            onClick = { settingsViewModel.updateLanguage(Language.ARABIC) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("العربية")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Theme Option
            SettingsItem(title = "Theme", expanded = themeExpanded, onExpandChange = {
                themeExpanded = !themeExpanded
            }) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedTheme == ThemeMode.LIGHT,
                            onClick = { settingsViewModel.updateTheme(ThemeMode.LIGHT) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Light")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedTheme == ThemeMode.DARK,
                            onClick = { settingsViewModel.updateTheme(ThemeMode.DARK) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Dark")
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            // Language Option
            SettingsItem(
                title = "Explore filter",
                expanded = exploreFilterExpanded,
                onExpandChange = { exploreFilterExpanded = !exploreFilterExpanded }
            ) {

            }
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    expanded: Boolean,
    onExpandChange: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onExpandChange)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null
            )
        }
        AnimatedVisibility(expanded) {
            content()
        }
    }
}