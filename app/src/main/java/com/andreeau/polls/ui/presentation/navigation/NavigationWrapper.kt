package com.andreeau.polls.ui.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andreeau.pollcore.utils.NetworkMonitor

import com.andreeau.polls.ui.presentation.views.Screens.CreatePollScreen
import com.andreeau.polls.ui.presentation.views.Screens.ResultsScreenFromCreate
import com.andreeau.polls.ui.presentation.views.Screens.MenuScreen
import com.andreeau.polls.ui.presentation.views.Screens.OfflineScreen
import com.andreeau.polls.ui.presentation.views.Screens.ResultsScreen
import com.andreeau.polls.ui.presentation.views.Screens.SplashScreen
import com.andreeau.polls.ui.presentation.views.Screens.VotePollScreen

@Composable
fun PollsNavigation(
    networkMonitor: NetworkMonitor,
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val isOnline by networkMonitor.isOnline
        .collectAsState(initial = true)
    val backStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute = backStackEntry?.destination?.route

    LaunchedEffect(isOnline, currentRoute) {
        if (currentRoute == null) return@LaunchedEffect

        if (!isOnline &&
            currentRoute != PollRoute.OfflineView.route
        ) {
            navController.navigate(PollRoute.OfflineView.route) {
                launchSingleTop = true
            }
        } else if (
            isOnline &&
            currentRoute == PollRoute.OfflineView.route
        ) {
            navController.popBackStack()
        }
    }
    NavHost(
        navController = navController,
        startDestination = PollRoute.Splash.route
    ) {
        // Splash
        composable(PollRoute.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(PollRoute.Menu.route) {
                        popUpTo(PollRoute.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Offline
        composable(PollRoute.OfflineView.route) {
            BackHandler {
                (context as? Activity)?.finish()
            }

            OfflineScreen(
                onQuitApp = {
                    (context as? Activity)?.finish()
                }
            )
        }

        // Main Menu
        composable(PollRoute.Menu.route) {
            MenuScreen(
                onCreatePoll = {
                    navController.navigate(PollRoute.CreatePoll.route)
                },
                onPollClosed = { pollCode ->
                    navController.navigate(
                        "${PollRoute.VoteResults.route.removeSuffix("/{pollCode}")}/$pollCode"
                    )
                },
                onVotePoll = { pollCode ->
                    navController.navigate(
                        "${PollRoute.VotePoll.route}/$pollCode"
                    )
                }
            )
        }

        // Create Poll
        composable(PollRoute.CreatePoll.route) {
            CreatePollScreen(
                onPollCreated = { pollCode ->
                    navController.navigate("create_results/$pollCode") {
                        popUpTo(PollRoute.CreatePoll.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Create Poll Results
        composable(
            route = PollRoute.CreateResults.route,
            arguments = listOf(
                navArgument("pollCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val pollCode =
                backStackEntry.arguments?.getString("pollCode") ?: ""

            ResultsScreenFromCreate(
                pollCode = pollCode,
                onBackToMenu = {
                    navController.popBackStack(
                        PollRoute.Menu.route,
                        inclusive = false
                    )
                }
            )
        }

        // Vote Poll
        composable(
            route = "${PollRoute.VotePoll.route}/{pollCode}",
            arguments = listOf(
                navArgument("pollCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val pollCode =
                backStackEntry.arguments?.getString("pollCode") ?: ""

            VotePollScreen(
                pollCode = pollCode,
                onVoteCompleted = {
                    navController.navigate(
                        "${PollRoute.VoteResults.route.removeSuffix("/{pollCode}")}/$pollCode"
                    ) {
                        popUpTo(backStackEntry.destination.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Results after voting
        composable(
            route = PollRoute.VoteResults.route,
            arguments = listOf(
                navArgument("pollCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val pollCode =
                backStackEntry.arguments?.getString("pollCode") ?: ""

            ResultsScreen(
                pollCode = pollCode,
                onBackToMenu = {
                    navController.popBackStack(
                        PollRoute.Menu.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}