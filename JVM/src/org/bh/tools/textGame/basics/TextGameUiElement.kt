package org.bh.tools.textGame.basics

import org.bh.tools.textGame.interaction.*

/**
 * @author Ben Leggiero
 * @since 2018-01-25
 */
interface TextGameUIElement
    <InteractionType>
    : Interactable<InteractionType>
    where InteractionType : Interaction
