import com.travelingbeersman.module._

import controllers.Assets
import play.api._
import play.api.routing.Router
import play.api.ApplicationLoader.Context
import router.Routes

import com.softwaremill.macwire._

class TravelingBeersmanApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    LoggerConfigurator(context.environment.classLoader)
      .foreach(_.configure(context.environment))

    (new BuiltInComponentsFromContext(context) with TravelingBeersmanComponents).application
  }
}

trait TravelingBeersmanComponents
  extends BuiltInComponents
  with TravelingBeersmanControllerModule
  with TravelingBeersmanFilterModule {

  override lazy val httpFilters = filters.toSeq

  lazy val assets: Assets = wire[Assets]
  lazy val prefix: String = "/"
  lazy val router: Router = wire[Routes]
}
