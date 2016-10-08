package com.travelingbeersman.controller

import play.api.mvc._
import scala.concurrent.Future
import com.typesafe.scalalogging.StrictLogging


trait TravelingBeersmanController extends Controller {

  def index(): EssentialAction
}

class TravelingBeersmanControllerImpl
  extends TravelingBeersmanController
  with StrictLogging {

  override def index(): EssentialAction = Action.async { request =>
    Future.successful(Ok("It worked!"))
  }
}
