package com.intercept.egipa3.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    data object Explore : BottomNavScreen("explore", "Explore", Icons.Default.Search)
    data object Saved : BottomNavScreen("saved", "Saved", Icons.Default.FavoriteBorder)
    data object Categories : BottomNavScreen("categories", "Categories", Icons.Default.List)
    data object More : BottomNavScreen("more", "More", Icons.Default.MoreVert)
}
