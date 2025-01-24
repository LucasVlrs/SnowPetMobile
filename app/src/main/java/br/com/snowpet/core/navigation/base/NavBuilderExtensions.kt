package br.com.snowpet.core.navigation.base

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions


internal const val baseDeepLink = "android-app://snowpet/"

internal fun buildDeepLink(destination: String) =
    NavDeepLinkRequest.Builder
        .fromUri("$baseDeepLink$destination".toUri())
        .build()

internal fun NavOptions.Builder.buildTransitionAnimations(
    baseNavOptions: BaseNavOptions? = null,
): NavOptions.Builder {

    val animationTransition = baseNavOptions?.animTransition ?: AnimationTransition.RIGHT_TO_LEFT

    return apply {
        setEnterAnim(animationTransition.enter)
        setExitAnim(animationTransition.exit)
        setPopEnterAnim(animationTransition.popEnter)
        setPopExitAnim(animationTransition.popExit)
    }
}
