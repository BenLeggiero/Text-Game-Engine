@file:Suppress("unused")

package TextGameEngine

import TextGameEngine.GameObject.*
import org.bh.tools.base.collections.extensions.*


/**
 * A [GameObject] that's used as a character
 *
 * @author Ben Leggiero
 * @since 2018-07-08
 */
typealias Character = GameObject



fun GameObject.Companion.asCharacter(name: String, delegate: CharacterController.Delegate): Character {
    val nameProperty = NameProperty(name)
    val characterController = CharacterController(nameProperty = nameProperty, delegate = delegate)
    return Character(properties = kotlin.collections.mutableListOf(
            nameProperty,
            characterController
    ))
}



fun Character.isCharacter() = properties.any { it is CharacterController }
val GameObject.name get() = properties
        .firstMappedOrNull { it as? CharacterController }?.name
        ?: properties.firstMappedOrNull { it as? NameProperty }?.name



class CharacterController(
        var nameProperty: NameProperty,
        var delegate: CharacterController.Delegate
) : Property {

    constructor(name: String,
                delegate: CharacterController.Delegate)
            : this(NameProperty(name), delegate)

    override val propertyKey = "CharacterController"
    var name: String
        get() = nameProperty.name
        set(newName) { nameProperty.name = newName }



    interface Delegate
}



data class NameProperty(var name: String): GameObject.Property {
    override val propertyKey: String = "name"
}



/**
 * A [GameObject] that's used as a non-playable character
 */
typealias NPC = Character
