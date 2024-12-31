// Filename: TransitionsUpdater.kt
package com.lkacz.pola

import android.content.Context

/**
 * Parses lines in the protocol for transitions directives, e.g.:
 *   Transitions;off   or   Transitions;slide
 * And stores them in SharedPreferences, so MainActivity knows which fragment animations to use.
 */
object TransitionsUpdater {

    private val pattern = Regex("^Transitions;(off|slide)$", RegexOption.IGNORE_CASE)

    fun updateTransitionFromLine(context: Context, line: String): Boolean {
        val match = pattern.find(line.trim())
        if (match != null) {
            val mode = match.groupValues[1].lowercase() // either "off" or "slide"
            TransitionManager.setTransitionMode(context, mode)
            return true
        }
        return false
    }
}
