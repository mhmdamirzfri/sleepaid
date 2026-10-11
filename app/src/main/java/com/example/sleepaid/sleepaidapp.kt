package com.example.sleepaid

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Routes {
    const val HOME = "home"
    const val SLEEP_TOOLS = "sleep_tools"
    const val SLEEP_CYCLE = "sleep_cycle"
    const val WATER_INTAKE = "water_intake"
    const val RELAXATION_TOOLS = "relaxation_tools"
    const val BREATHING = "breathing"
    const val MEDITATION = "meditation"
    const val SOUNDS = "sounds"
    const val SLEEP_INFO = "sleep_info"
    const val ARTICLE = "article/{articleId}"
    const val QUIZ = "quiz"
    const val HELP = "help"

    fun article(id: Int) = "article/$id"
}

@Composable
fun SleepAidApp() {
    val navController = rememberNavController()

    val navigate: (String) -> Unit = { route -> navController.navigate(route) }

    val goBack: () -> Unit = {
        if (navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }

    val goHome: () -> Unit = {
        navController.popBackStack(Routes.HOME, inclusive = false)
    }

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(onNavigate = navigate)
        }
        composable(Routes.SLEEP_TOOLS) {
            SleepToolsScreen(onBack = goBack, onHome = goHome, onNavigate = navigate)
        }
        composable(Routes.RELAXATION_TOOLS) {
            RelaxationToolsScreen(onBack = goBack, onHome = goHome, onNavigate = navigate)
        }
        composable(Routes.SLEEP_CYCLE) {
            SleepCycleScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.WATER_INTAKE) {
            WaterIntakeScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.BREATHING) {
            BreathingScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.MEDITATION) {
            MeditationScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.SOUNDS) {
            SoundsScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.SLEEP_INFO) {
            SleepInfoScreen(onBack = goBack, onHome = goHome, onNavigate = navigate)
        }
        composable(
            route = Routes.ARTICLE,                               // "article/{articleId}"
            arguments = listOf(navArgument("articleId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("articleId") ?: 0   // read the number
            ArticleScreen(articleId = id, onBack = goBack, onHome = goHome)
        }
        composable(Routes.QUIZ) {
            QuizScreen(onBack = goBack, onHome = goHome)
        }
        composable(Routes.HELP) {
            HelpScreen(onBack = { navController.popBackStack() })
        }
    }
}