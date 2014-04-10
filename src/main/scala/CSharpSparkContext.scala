package org.apache.spark

import org.apache.spark.rdd.RDD
import scala.reflect._
import cli.CSharpUtilities.CSharpUtil
import scala.Function3
import scala.collection.mutable.{ArrayBuilder, WrappedArray}

/**
 * Created by zhenjiao on 3/31/2014.
 */
class CSharpSparkContext (
                           config: SparkConf
                           ) extends Logging{
  private[spark] val context = new SparkContext(config)

  def parallelize[T: ClassTag](seq: cli.System.Collections.ArrayList, numSlices: Int): RDD[T] = {
    var i = 0
    var seq2: List[T] = List()
    while(i < seq.get_Count()){
      seq2 = seq2.::(seq.get_Item(i).asInstanceOf[T])
      i += 1
    }
    context.parallelize[T](seq2, numSlices)
  }
  def stop() = {
    context.stop()
  }

}


