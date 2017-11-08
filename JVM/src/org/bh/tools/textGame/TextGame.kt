package org.bh.tools.textGame

import org.bh.tools.base.state.BasicStateStore
import org.bh.tools.textGame.io.GamePersistor

/**
 * The basic definition of a text game
 *
 * @author Ben Leggiero
 * @since 2017-06-14
 */
abstract class TextGame<GameStateType, GameStateChangeType>

    (
            newGameState: GameStateType,
            gamePersistor: GamePersistor<GameStateType, GameStateChangeType>? = null
    )

    where GameStateType: ChangeableGameState<GameStateType, GameStateChangeType>,
          GameStateChangeType : GameStateChange<GameStateChangeType, GameStateType>
{

    var stateStore = BasicStateStore<GameStateType, GameStateChangeType>(baseState = newGameState)

    init {
        gamePersistor?.loadStateByMutatingStore(stateStore)
    }

    /**
     * Starts the text game
     */
    abstract fun start()
}