copy %IKVMTOOLDIR%\*.* .
%IKVMTOOLDIR%\ikvmstub.exe -nostdlib -r:%windir%\Microsoft.NET\Framework\v4.0.30319\mscorlib.dll CSharpSparkLib.dll -out:CSharpSparkLib.jar