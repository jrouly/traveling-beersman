import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.spark.graphx._

import scala.io.Source


package net.rouly {

  object TravelingBeersman {


    /* Defines an Establishment object with name, location, and category. */
    @SerialVersionUID(100L)
    class Establishment(name: String,
                        location: (Double, Double),
                        category: String) extends Serializable {

      val this.name = name // name of the establishment
      val this.location = location // point location
      val this.category = category // category (brewery, eatery, etc.)

      override def toString(): String =
        return s"""[$category] "$name" at $location"""

    }


    /* Simple string->double convenience method. */
    def parseDouble(s: String) =
      try { Some(s.toDouble) }
      catch { case _ : Throwable => None }


    /* Simple converter from an input line to an Establishment object. */
    def lineToEstablishment(line: String): Establishment = {
      val splits = line.split(" _ ").toList
      splits match {
        case List(category: String,
                  name: String,
                  latitude: String,
                  longitude: String) =>
          new Establishment(name,
                            (parseDouble(latitude).get,
                             parseDouble(longitude).get),
                            category)
          }
    }


    /* Main Spark/GraphX executable. */
    def main(args: Array[String]) {

      val conf = new SparkConf().setAppName("The Traveling Beersman")
      val sc = new SparkContext(conf)

      println("Startup complete.")

      /* Read from input file, convert them to Establishment objects. */
      val lines = Source.fromFile("input.txt").getLines
      val establishments =
        lines.map(line => lineToEstablishment( line ))
      val establishmentsIDs =
        establishments.zipWithIndex.map({case (a,b) => (b.toLong,a)})
      val vertices: RDD[(VertexId, Establishment)] =
        sc.parallelize(establishmentsIDs.toSeq)

      println("Read-in complete.")

    }

  }

}
