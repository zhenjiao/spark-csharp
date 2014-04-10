using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Text.RegularExpressions;
using System.Collections;
using System.Reflection;
using scala;
using org.apache.spark;
using org.apache.spark.api.csharp;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
    if (args.Length == 0) {
      System.Console.WriteLine("Usage: SparkPi <master> [<slices>]");
      return;
    }
    var sparkConf = new SparkConf();
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.JavaSerializer");

    var host = args[0];

    sparkConf.setMaster(host).setAppName("SparkPi");
    var spark = new CSharpSparkContext(sparkConf);
//    var spark = new CSharpSparkContext(args[0], "SparkPi",
  //    System.Environment.GetEnvironmentVariable("SPARK_HOME"));
    var slices = args.Length > 1 ? int.Parse(args[1]) : 2;
    var n = 100000 * slices;
    ArrayList data = new ArrayList();
    for(int i = 0; i < n; i++)
    {
        data.Add(i.ToString());
    }
    var rdd = spark.parallelize(data, slices, Types.Object());
    //System.Console.WriteLine("rdd.count=" + rdd.count());
    var csharpRdd = new CSharpRDD(rdd, Types.Object());
    rdd = csharpRdd.map(Assembly.GetExecutingAssembly().GetName().Name, "ConsoleApplication1.HelperFuncs", "MapFunc", Types.Object());
    csharpRdd = new CSharpRDD(rdd, Types.Object());
    int count = int.Parse(csharpRdd.reduce(Assembly.GetExecutingAssembly().GetName().Name, "ConsoleApplication1.HelperFuncs", "ReduceFunc").ToString());
    //var rdd = rdd.map { i =>
      //val x = random * 2 - 1
      //val y = random * 2 - 1
      //if (x*x + y*y < 1) 1 else 0
    //}.reduce({
      //logInfo("calling reduce")
      //_ + _
    //})
    System.Threading.Thread.Sleep(10000);
    System.Console.WriteLine("Pi is roughly {0}", 4.0 * count / n);
    spark.stop();

        }
    }
}
