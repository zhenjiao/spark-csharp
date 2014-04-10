/**
 * Created by zhenjiao on 4/1/2014.
 */
package org.apache.spark.api.csharp

import java.io._
import java.net._
import java.util.{List => JList, ArrayList => JArrayList, Map => JMap, Collections}

import scala.collection.JavaConversions._
import scala.reflect.ClassTag

import org.apache.spark.api.java.{JavaSparkContext, JavaPairRDD, JavaRDD}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.util.Utils
import cli.CSharpUtilities.CSharpUtil

class CSharpRDD[T: ClassTag](
    _rdd: RDD[T])
    extends Logging with Serializable{

  var rdd: RDD[T] = _rdd

  def map[U: ClassTag](assemblyName: String, className: String, methodName: String): RDD[U] = {
    def f(input: T): U = {
      val inputParams = new Array[Object](1)
      inputParams(0) = input.asInstanceOf[Object]
      logDebug("assemly=" + assemblyName)
      logDebug("className=" + className)
      logDebug("methodName=" + methodName)
      logDebug("inputParams=" + input)
      val result = CSharpUtil.Invoke(assemblyName, className, methodName, inputParams).asInstanceOf[U]
      logDebug("map.result=" + result)
      result
    }
    rdd.map[U](f)
  }

  def reduce(assemblyName: String, className: String, methodName: String): T = {
    def f(x: T, y: T): T = {
      val inputParams = new Array[Object](2)
      inputParams(0) = x.asInstanceOf[Object]
      inputParams(1) = y.asInstanceOf[Object]
      logDebug("assemly=" + assemblyName)
      logDebug("className=" + className)
      logDebug("methodName=" + methodName)
      logDebug("x=" + x +",y=" + y)
      val result = CSharpUtil.Invoke(assemblyName, className, methodName, inputParams).asInstanceOf[T]
      logDebug("reduce result=" + result)
      result
    }
    rdd.reduce(f)
  }

 }

