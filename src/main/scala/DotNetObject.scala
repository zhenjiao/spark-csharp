/**
 * Created by zhenjiao on 4/3/2014.
 */
package org.apache.spark

import scala.Array
import scala.reflect.ClassTag
import java.io.{ObjectInputStream, ObjectOutputStream, IOException}
import org.apache.hadoop.io.ObjectWritable
import org.apache.hadoop.conf.Configuration

class DotNetObject (
                     _value: cli.System.Object,
                     _datatype: String
                    ) extends cli.System.Object with Serializable{
  var value = _value
  var datatype:Int = _datatype match {
    case "String" => 0
    case "Int32" => 1
    }

  private def writeObject(out: ObjectOutputStream) {
        out.writeByte(datatype)
        out.writeChars(value.ToString())
  }

  private def readObject(in: ObjectInputStream) {
    datatype = in.readByte()
    val buf: Array[Byte] = new Array[Byte](100)
    in.read(buf)
    value = buf.toString().asInstanceOf[cli.System.Object]
  }
}

object Types {
  val Int32: ClassTag[cli.System.Int32] = ClassTag.apply(Class.forName("cli.System.Int32"))
  val String: ClassTag[java.lang.String] = ClassTag.apply(Class.forName("java.lang.String"))
  val Object: ClassTag[java.lang.Object] = ClassTag.apply(Class.forName("java.lang.Object"))
  val DotNetObject: ClassTag[cli.CSharpUtilities.SerializableObject] =
    ClassTag.apply(Class.forName("org.apache.spark.DotNetObject"))
}
