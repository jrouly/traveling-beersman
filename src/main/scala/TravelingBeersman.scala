import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.spark.graphx._

import scala.io.Source


package net.rouly {

  object TravelingBeersman {


    /* Defines an enumeration of possible categories. */
    object Category extends Enumeration {
      type Category = Value
      val brewery, eatery, store, homebrew = Value
    }
    import Category._


    /* Defines an Establishment object with name, location, and category. */
    class Establishment(name: String,
                        location: String,
                        category: Category) {

      val this.name = name // name of the establishment
      val this.location = location // point location
      val this.category = category // category (brewery, eatery, etc.)

      override def toString(): String =
        return s"""[$category] "$name" at $location"""

    }


    /* Main Spark/GraphX executable. */
    def main(args: Array[String]) {

      val conf = new SparkConf().setAppName("The Traveling Beersman")
      val sc = new SparkContext(conf)

      println("Startup complete.")

      val establishments = for {
        line <- Source.fromFile("input.txt").getLines
        split <- line.split(" _ ")
      } yield split

      for( est <- establishments ) println( est )

    }

  }

}
