package com.travelingbeersman.controller

import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future
import com.typesafe.scalalogging.StrictLogging


trait BeerAdvocateController extends Controller {

  def version(): EssentialAction
}

class BeerAdvocateControllerImpl
  extends BeerAdvocateController
  with StrictLogging {

  override def version(): EssentialAction = Action.async { request =>
    Future.successful(Ok(Json.toJson("it worked")))
  }

}
