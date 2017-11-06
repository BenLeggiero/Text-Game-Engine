package org.bh.tools.textGame

import org.bh.tools.base.state.ChangeableState
import org.bh.tools.base.state.State
import org.bh.tools.base.state.StateChange

interface GameState: State

interface ChangeableGameState<Self, Change>
    : GameState,
      ChangeableState<Self, Change>
        where Self : ChangeableGameState<Self, Change>,
              Change : GameStateChange<Change, Self>

interface GameStateChange<Self, State>
    : StateChange<Self, State>
        where Self: GameStateChange<Self, State>,
              State: ChangeableGameState<State, Self>
