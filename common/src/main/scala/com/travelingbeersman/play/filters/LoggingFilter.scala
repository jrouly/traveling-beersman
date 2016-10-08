package com.travelingbeersman.play.filters

import akka.stream.Materializer
import com.typesafe.scalalogging.StrictLogging
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}


class LoggingFilter(implicit ec: ExecutionContext)
  extends EssentialFilter
  with StrictLogging {

  def apply(nextFilter: EssentialAction): EssentialAction = new EssentialAction {
    def apply(requestHeader: RequestHeader) = {

      val startTime = System.currentTimeMillis
      logger.info(s"Starting request: ${requestHeader.method} ${requestHeader.uri}")

      nextFilter(requestHeader).map { result =>
        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime

        logger.info(s"Completed request: ${requestHeader.method} ${requestHeader.uri} durationMs=${requestTime}ms status=${result.header.status}")

        result.withHeaders("Request-Time" -> requestTime.toString)
      }
    }
  }
}
