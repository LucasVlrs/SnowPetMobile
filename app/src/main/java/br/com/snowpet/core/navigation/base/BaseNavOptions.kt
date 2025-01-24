package br.com.snowpet.core.navigation.base

import androidx.annotation.IdRes

fun baseNavOptions(optionsBuild: BaseNavOptions.() -> Unit): BaseNavOptions =
    BaseNavOptions().apply(optionsBuild)

class BaseNavOptions(
    @IdRes var popUpToId: Int? = null,
    var inclusive: Boolean = false,
    var launchSingleTop: Boolean = true,
    var animTransition: AnimationTransition? = null
)
