import org.apache.spark.rdd.RDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

import org.apache.spark.graphx._

import scala.io.Source


package net.rouly {

  object TravelingBeersman {


    /* Defines an Establishment object with name, location, and category. */
    @SerialVersionUID(100L)
    class Establishment(val name: String,
                        val location: (Double, Double),
                        val category: String) extends Serializable {

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


    /* Calculate the distance between two Establishments by ID. */
    def distanceBetweenPairs( pair: ((VertexId, Establishment), (VertexId, Establishment)) ): (VertexId, VertexId, Double) = {
      val src = pair._1
      val dst = pair._2
      val dx = src._2.location._1 - dst._2.location._1
      val dy = src._2.location._2 - dst._2.location._2
      return (src._1, dst._1, math.sqrt(dx*dx + dy*dy))
    }


    /* Main Spark/GraphX executable. */
    def main(args: Array[String]) {

      val conf = new SparkConf().setAppName("The Traveling Beersman")
      val sc = new SparkContext(conf)

      println("Startup complete.")


      /* Read from input file, convert them to Establishment objects. */
      val lines = Source.fromFile("input.txt").getLines
      val establishments =
        lines.map(lineToEstablishment)
             .zipWithIndex
             .map({case (a,b) => (b.toLong,a)})
             .toSeq
      val vertices: RDD[(VertexId, Establishment)] =
        sc.parallelize(establishments)

      println("Read-in complete.")


      /* Generate the pairwise distances between vertices and store those
         weights on the corresponding edges. */
      val edges: RDD[Edge[Double]] =
        vertices.cartesian(vertices)
                .filter({case ((srcID, _), (dstID, _)) => srcID != dstID})
                .map(distanceBetweenPairs)
                .map({case (srcID, dstID, attr) => new Edge(srcID, dstID, attr)})

      println("Edge generation complete.")


      /* Construct a GraphX graph data object. */
      val graph = Graph( vertices, edges )

      println("Graph generation complete.")

    }

  }

}
