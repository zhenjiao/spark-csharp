using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    public class HelperFuncs
    {
        public string MapFunc(string input)
        {
            var rand = new Random();
            double x = rand.NextDouble() * 2 - 1;
            double y = rand.NextDouble() * 2 - 1;
            return x * x + y * y < 1 ? "1" : "0";
        }
        public string ReduceFunc(string x, string y)
        {
            return (int.Parse(x) + int.Parse(y)).ToString();
        }
    }
}
