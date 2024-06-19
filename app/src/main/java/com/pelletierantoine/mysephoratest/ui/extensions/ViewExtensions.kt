package com.pelletierantoine.mysephoratest.ui.extensions

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup

fun View.fadeIn(): ObjectAnimator {
    visibility = View.VISIBLE
    return ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f)
}

/*fun View.fadeOut(visibility: Int = View.GONE): ObjectAnimator {
    val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0f)

    animator.addOnEndAnimation {
        this.visibility = visibility
    }

    return animator
}*/

fun View.visible() {
    visibility = View.VISIBLE
}

fun visible(vararg views: View) {
    views.forEach { it.visible() }
}

fun View.gone() {
    visibility = View.GONE
}

fun gone(vararg views: View) {
    views.forEach { it.gone() }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setMargins(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    val lp = layoutParams as? ViewGroup.MarginLayoutParams
        ?: return

    lp.setMargins(
        left ?: lp.leftMargin,
        top ?: lp.topMargin,
        right ?: lp.rightMargin,
        bottom ?: lp.bottomMargin
    )

    layoutParams = lp
}