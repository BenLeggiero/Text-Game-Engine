package org.bh.tools.textGame.interaction

interface TextOutput {
    fun textForOutput(kind: OutputKind): String
}

enum class OutputKind {
    /** The text will be printed to a console */
    printedToConsole,

    /** The text will be used as a button label */
    labelOnButton
}
