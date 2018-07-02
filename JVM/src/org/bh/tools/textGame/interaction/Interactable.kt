@file:Suppress("unused")

package org.bh.tools.textGame.interaction

import org.bh.tools.base.math.geometry.*


/**
 * Something with which you can interact in a text game
 *
 * @author Ben Leggiero
 * @since 2017-06-14
 */
interface Interactable<InteractionBaseType : Interaction>
    : TextDescribable
{
    /**
     * Lists interaction triggers for this [Interactable], filtered appropriately
     *
     * @param filter The filter by which interaction triggers are included or excluded
     *
     * @return All interaction triggers appropriate for the given filter
     */
    fun interactionTriggers(filter: InteractionFilter): List<InteractionTrigger>


    /**
     * Conditionally interacts with this, using the given [Interaction]
     *
     * @param interaction The Interaction that triggered this function
     *
     * @return The result of the interaction
     */
    fun attemptInteraction(interaction: InteractionBaseType): InteractionResult<InteractionBaseType>
}


/**
 * A method by which interactions are filtered andor sorted
 */
enum class InteractionFilter {
    /**
     * All possible interactions. These need not be sorted.
     */
    all,

    /**
     * All interactions that make sense right now. All of these should appear with [all], but not all of those must
     * appear in this.
     *
     * These should be sorted such that the most likely one is first.
     */
    currentlyAvailable,

    /**
     * All interactions that are currently visibly presented to the player's (or any other) character. All of these
     * should appear in [currentlyAvailable], but not all of those need appear in this.
     *
     * These should be sorted in the way you intend them to be presented to the user.
     */
    visibleToCharacter
}


/**
 * Some potential interaction in a text game
 */
interface Interaction {
    /**
     * That which triggered this interaction
     */
    val trigger: InteractionTrigger
}


/**
 * An interaction's trigger in a text game
 */
sealed class InteractionTrigger {
    /**
     * Signifies that any arbitrary string could trigger this interaction.
     * This is useful for "you can't use that here" responses.
     */
    object arbitraryString : InteractionTrigger()

    /**
     * Signifies that a string matching a particular regex is required to trigger this interaction.
     */
    class matchesRegex(val regex: Regex): InteractionTrigger()

    /**
     * Signifies that one of a predefined set of actions, like `walk north` vs `walk east`, triggers this interaction.
     */
    class enum<T: Enum<T>>(val enum: T): InteractionTrigger()

    /**
     * A set of coordinates, like some point on the screen, or a button in a grid
     */
    class coordinates<T: Number>(val point: Point<T>): InteractionTrigger()

    /**
     * An interaction that's triggered by something that I as the API designer didn't account for. Sorry!
     *
     * Be sure to let me know what I missed by filing an issue on GitHub (link in readme)
     */
    class any<out T>(val value: T): InteractionTrigger()
}



interface InteractionResult<InitialInteraction : Interaction> : TextDescribable



/**
 * An [Interactable] that's used as a character
 */
interface Character<I : Interaction> : Interactable<I> {
    val name: String?
}



/**
 * An [Interactable] that's used as a non-playable character
 */
interface NPC<I : Interaction> : Character<I>



/**
 * An [Interactable] that's used as a game object
 */
interface GameObject<I : Interaction> : Interactable<I>
