package cz.vvoleman.documentgallery.base.ui

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.Direction

class Navigator {

    private var navController: NavController? = null

    fun setNavController(navController: NavController){
        this.navController = navController
    }

    fun navigate(route: String){
        navController?.navigate(route)
    }

    fun navigate(direction: Direction) {
        navController?.navigate(direction)
    }

}