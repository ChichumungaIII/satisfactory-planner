package app.routes.home.newsavecard

import app.AppRoute
import app.routes.home.common.HomePageCard
import react.FC
import react.Props
import react.create
import react.router.useNavigate

external interface NewSaveCardProps : Props

val NewSaveCard = FC<NewSaveCardProps>("NewSaveCard") {
  val navigate = useNavigate()

  HomePageCard {
    onClick = { navigate(to = AppRoute.CREATE_SAVE.url) }
    content = NewSaveCardContent.create {}
  }
}
