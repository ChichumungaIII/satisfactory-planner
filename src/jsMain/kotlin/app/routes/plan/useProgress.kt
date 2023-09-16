package app.routes.plan

import app.game.logic.Progress
import react.createContext
import react.useContext

val ProgressContext = createContext<Progress>()
fun useProgress() = useContext(ProgressContext)
  ?: throw Error("Cannot call `useProgress` outside of a Progress context.")
