package com.travelingbeersman.module

import com.travelingbeersman.play.filters.LoggingFilter
import com.softwaremill.macwire._
import play.api.mvc.EssentialFilter
import scala.concurrent.ExecutionContext.Implicits.global

trait TravelingBeersmanFilterModule {

  lazy val loggingFilter: LoggingFilter = wire[LoggingFilter]

  lazy val filters: Set[EssentialFilter] = wireSet[EssentialFilter]
}
