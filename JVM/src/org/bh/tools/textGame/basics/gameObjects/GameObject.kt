package TextGameEngine

/**
 * @author Ben Leggiero
 * @since 2018-07-07
 */
class GameObject(var properties: MutableList<Property>) {

    companion object



    interface Property {
        val propertyKey: String
    }
}
