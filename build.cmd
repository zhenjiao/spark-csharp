set DEPENDENCIESDIR=%cd%\dependencies
set IKVMTOOLDIR=%DEPENDENCIESDIR%\ikvm-7.2.4630.5\bin
set scalahome=C:\scala\scala-2.10.2
set srcdir=%cd%\src
set libdir=%DEPENDENCIESDIR%\libs
set targetdir=%cd%\target
set javahome=C:\Program Files (x86)\Java\jdk1.7.0_51
pushd src\main\csharp
call build.cmd
popd
call sbt compile
pushd target
"%javahome%\bin\jar" cvf csharp_spark.jar scala-2.10\classes\org\apache\spark\
call ..\dll2jar.cmd
call ..\jar2dll.cmd
popd
pushd examples\CSharpSparkPi
call build.cmd
popd
