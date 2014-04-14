/**
 * Created by zhenjiao on 4/3/2014.
 */
package org.apache.spark

import scala.reflect.ClassTag

object Types {
  val Int32: ClassTag[cli.System.Int32] = ClassTag.apply(Class.forName("cli.System.Int32"))
  val String: ClassTag[java.lang.String] = ClassTag.apply(Class.forName("java.lang.String"))
  val Object: ClassTag[java.lang.Object] = ClassTag.apply(Class.forName("java.lang.Object"))
}
