package br.com.snowpet.core.navigation.base

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import br.com.snowpet.core.navigation.NavigationFlow
import java.io.Serializable


internal fun NavController.navigateToDeeplink(
    navFlow: NavigationFlow,
    sbNavOptions: BaseNavOptions? = null
) {
    val builder = setupNavOptions(sbNavOptions = sbNavOptions)
    if (navFlow.popUpToStartDestination) {
        builder.setPopUpTo(graph.startDestinationId, true)
    }

    navigate(
        buildDeepLink(navFlow.deepLink),
        builder.build()
    )
}

fun <T : Serializable> NavController.observeResultForKey(
    lifecycleOwner: LifecycleOwner,
    key: String,
    cleanBackStack: Boolean = true,
    onResult: (T) -> Unit
) {
    this.currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observe(lifecycleOwner) { result ->
        if (cleanBackStack) {
            this.currentBackStackEntry?.savedStateHandle?.remove<T>(key)
        }
        onResult(result)
    }
}

fun <T : Serializable> NavController.postResult(key: String, value: T) {
    this.previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T : Serializable> NavController.navigateWithResult(
    navFlow: NavigationFlow,
    key: String,
    value: T,
    inclusive: Boolean = false
) {
    getBackStackEntry(navFlow.idLayout).savedStateHandle.set(key, value)

    popBackStack(navFlow.idLayout, inclusive)
}

private fun NavController.setupNavOptions(
    @IdRes id: Int? = null,
    directions: NavDirections? = null,
    sbNavOptions: BaseNavOptions? = null,
): NavOptions.Builder {
    val navOptions = NavOptions.Builder()
    val xmlNavOptions = currentDestination?.getAction(directions?.actionId ?: id ?: 0)?.navOptions

    navOptions.setLaunchSingleTop(true)

    xmlNavOptions?.let {
        navOptions.setPopUpTo(it.popUpToId, it.isPopUpToInclusive())
    }

    sbNavOptions?.let { opts ->
        opts.popUpToId?.let {
            navOptions.setPopUpTo(it, opts.inclusive)
        }
        navOptions.setLaunchSingleTop(opts.launchSingleTop)
    }

    navOptions.buildTransitionAnimations(sbNavOptions)
    return navOptions
}
