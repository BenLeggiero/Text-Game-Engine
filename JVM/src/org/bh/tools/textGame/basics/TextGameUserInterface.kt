package org.bh.tools.textGame.basics

import org.bh.tools.textGame.interaction.*

/**
 * @author Ben Leggiero
 * @since 2018-01-25
 */
interface TextGameUserInterface {
    fun <InteractableType: Interactable<InteractionType>,
            InteractionType: Interaction,
            Result: InteractionResult<InteractionType>>
            userDidInteract(interactable: InteractableType, interaction: InteractionType): Result
}
