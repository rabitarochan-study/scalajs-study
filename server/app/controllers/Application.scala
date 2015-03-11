package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

import com.github.rabitarochan.study.scalajs.shared._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(SharedMessage.message))
  }

  def test = Action { implicit request =>
    val json = Json.obj(
      "name" -> "kengo",
      "age"  -> 26
    )
    Ok(json)
  }

}