package com.travelingbeersman.module

import com.travelingbeersman.controller._
import com.softwaremill.macwire.wire

trait TravelingBeersmanControllerModule {

  lazy val travelingBeersmanController: TravelingBeersmanController = wire[TravelingBeersmanControllerImpl]
  lazy val beerAdvocateController: BeerAdvocateController = wire[BeerAdvocateControllerImpl]
}
