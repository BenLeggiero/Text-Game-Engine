package org.bh.tools.textGame.oversight

import org.bh.tools.base.abstraction.Fraction
import org.bh.tools.base.func.observing
import org.bh.tools.textGame.basics.*


typealias Difficulty = Fraction



/**
 * That which controls the game.
 *
 * This should manage dice rolls, NPC positions and actions, etc.
 *
 * @author Ben Leggiero
 * @since 2018-01-24
 */
class GameMaster
<
        out Game : TextGame<GameStateType, ChangeType>,
        GameStateType : ChangeableGameState<GameStateType, ChangeType>,
        ChangeType : GameStateChange<ChangeType, GameStateType>
        >
(
        val textGame: Game,
        difficulty: Difficulty
) {
    var difficulty by observing(difficulty, didSet = { _, _ -> })
}
