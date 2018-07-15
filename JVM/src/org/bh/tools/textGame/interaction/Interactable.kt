@file:Suppress("unused")

package org.bh.tools.textGame.interaction

import TextGameEngine.Character
import org.bh.tools.base.math.geometry.*


/**
 * Something with which you can interact in a text game
 *
 * @author Ben Leggiero
 * @since 2017-06-14
 */
interface Interactable<InteractionBaseType : InteractionEvent>
    : TextDescribable
{
    /**
     * Lists interaction triggers for this [Interactable], filtered appropriately.
     * These may or may not be presented to the user.
     *
     * @param filter The filter by which interaction triggers are included or excluded
     *
     * @return All interaction triggers appropriate for the given filter
     */
    fun interactionTriggers(filter: InteractionFilter): List<InteractionTrigger>


    /**
     * Conditionally interacts with this, using the given [InteractionEvent]
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
sealed class InteractionFilter {
    /**
     * All possible interactions. These need not be sorted.
     */
    object all: InteractionFilter()

    /**
     * All interactions that make sense right now. All of these should appear with [all], but not all of those must
     * appear in this.
     *
     * These should be sorted such that the most likely one is first.
     */
    object currentlyAvailable: InteractionFilter()

    /**
     * All interactions that are currently visibly presented to the player's (or any other) character. All of these
     * should appear in [currentlyAvailable], but not all of those need appear in this.
     *
     * These should be sorted in the way you intend them to be presented to the user.
     *
     * @param character The character in question. `null` signifies any character.
     */
    class visibleToCharacter(val character: Character?): InteractionFilter()
}


/**
 * Some interaction event in a text game
 */
interface InteractionEvent {
    /**
     * That which triggered this interaction event
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
    data class matchesRegex(val regex: Regex): InteractionTrigger()

    /**
     * Signifies that one of a predefined set of actions, like `walk north` vs `walk east`, triggers this interaction.
     */
    data class enum<T: Enum<T>>(val enum: T): InteractionTrigger()

    /**
     * A set of coordinates, like some point on the screen, or a button in a grid
     */
    data class coordinates<T: Number>(val point: Point<T>): InteractionTrigger()

    /**
     * An interaction that's triggered by something that I as the API designer didn't account for. Sorry!
     *
     * Be sure to let me know what I missed by filing an issue on GitHub (link in readme)
     */
    data class any<out T>(val value: T): InteractionTrigger()
}



interface InteractionResult<InitialInteraction : InteractionEvent> : TextDescribable