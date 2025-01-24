package br.com.snowpet.core.navigation.base

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import br.com.snowpet.R

enum class AnimationTransition(
    @AnimRes @AnimatorRes val enter: Int,
    @AnimRes @AnimatorRes val exit: Int,
    @AnimRes @AnimatorRes val popEnter: Int,
    @AnimRes @AnimatorRes val popExit: Int,
) {
    RIGHT_TO_LEFT(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left_out, R.anim.slide_in_right_out,),
    STATIONARY(0, 0, 0, 0),
}
