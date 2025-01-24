package br.com.snowpet.core.navigation

import androidx.annotation.IdRes

sealed class NavigationFlow(
    val deepLink: String,
    @IdRes val idLayout: Int = 0,
    val popUpToStartDestination: Boolean = false,
)
