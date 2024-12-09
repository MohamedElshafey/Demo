package com.intercept.egipa3.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.intercept.egipa3.ui.categories.CategoriesScreen
import com.intercept.egipa3.ui.explore.ExploreScreen
import com.intercept.egipa3.ui.more.MoreScreen
import com.intercept.egipa3.ui.saved.SavedScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavGraph(navController)
        }
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavScreen.Explore.route) {
        composable(BottomNavScreen.Explore.route) {
            ExploreScreen()
        }
        composable(BottomNavScreen.Saved.route) {
            SavedScreen()
        }
        composable(BottomNavScreen.Categories.route) {
            CategoriesScreen()
        }
        composable(BottomNavScreen.More.route) {
            MoreScreen()
        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        BottomNavScreen.Explore,
        BottomNavScreen.Saved,
        BottomNavScreen.Categories,
        BottomNavScreen.More
    )
    BottomNavigation {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a tab
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
