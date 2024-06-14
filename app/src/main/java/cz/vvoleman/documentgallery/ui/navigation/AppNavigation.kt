package cz.vvoleman.documentgallery.ui.navigation

import com.ramcosta.composedestinations.annotation.NavGraph
import cz.vvoleman.documentgallery.ui.screen.FOLDER_ROUTE
import cz.vvoleman.documentgallery.ui.screen.HOME_ROUTE

@NavGraph
annotation class MainNavGraph(
    val start: Boolean = false
)