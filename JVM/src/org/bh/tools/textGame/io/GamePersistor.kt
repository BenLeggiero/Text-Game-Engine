package org.bh.tools.textGame.io

import org.bh.tools.base.state.BasicStateStore
import org.bh.tools.base.state.ResettableStateStore
import org.bh.tools.textGame.GameStateChange
import org.bh.tools.textGame.ChangeableGameState
import java.io.File

open class GamePersistor<StateType, StateChangeType>
(
    val gameSaveDirectory: File
)
        where StateType: ChangeableGameState<StateType, StateChangeType>,
              StateChangeType : GameStateChange<StateChangeType, StateType>
{

    fun loadStateByMutatingStore(stateStore: BasicStateStore<StateType, StateChangeType>) {
        val savedState = loadState()
        val resettableStore = (stateStore as? ResettableStateStore<StateType, *>)
        if (resettableStore == null) {
            stateStore.pushState(savedState.changeValue)
        }
        else {
            resettableStore.reset(savedState)
        }
    }

    private fun loadState() : StateType {
        TODO("Load state from disc")
    }
}