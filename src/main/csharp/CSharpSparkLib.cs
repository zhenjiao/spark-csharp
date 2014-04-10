using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Reflection;

namespace CSharpSparkLib
{
    public static class CSharpUtil
    {
        public static object Invoke(string assemblyName, string className, string methodName, object[] paramList)
        {
            var assembly = Assembly.Load(assemblyName);
            if (assembly == null)
                Console.WriteLine("assemly is null");
            Type magicType = assembly.GetType(className);
            if (magicType == null)
                Console.WriteLine("type is null");
            ConstructorInfo magicConstructor = magicType.GetConstructor(Type.EmptyTypes);
            if (magicConstructor == null)
                Console.WriteLine("ctor is null");
            object magicClassObject = magicConstructor.Invoke(new object[] { });
            if (magicClassObject == null)
                Console.WriteLine("magicClassObject is null");

            MethodInfo magicMethod = magicType.GetMethod(methodName);
            if (magicMethod == null)
                Console.WriteLine("magicMethod is null");
            object magicValue = magicMethod.Invoke(magicClassObject, paramList);

            return magicValue;
        }
        
    }

}
