package org.bh.tools.textGame.interaction

interface TextDescribable {
    fun textDescription(kind: DescriptionPresentation): String
}

enum class DescriptionPresentation {
    /** The text will be printed to a console */
    printedToConsole,

    /** The text will be used as a button label */
    labelOnButton
}
