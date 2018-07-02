package org.bh.tools.textGame.basics

import org.bh.tools.textGame.interaction.*

/**
 * @author Ben Leggiero
 * @since 2018-01-25
 */
interface TextGameUiElement
    <InteractionType>
    : Interactable<InteractionType>
    where InteractionType : Interaction



/**
 * @author Ben Leggiero
 * @since 2018-07-01
 */
interface RootTextGameUiElement
    <SharedInteractionType>
    : TextGameUiElement<SharedInteractionType>
    where SharedInteractionType : Interaction {

    /**
     * Clears any current state and loads the given game into this UI
     */
    fun load(game: TextGame<*, *>)

    /**
     * Presents this UI to the user
     */
    fun show()
}
