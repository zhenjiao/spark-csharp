copy %windir%\microsoft.net\framework\v4.0.30319\system.data.dll %targetdir%
copy %windir%\microsoft.net\framework\v4.0.30319\system.drawing.dll %targetdir%
copy %windir%\microsoft.net\framework\v4.0.30319\mscorlib.dll %targetdir%
copy %windir%\microsoft.net\framework\v4.0.30319\system.dll %targetdir%
copy %scalahome%\lib\scala-library.jar %targetdir%
copy %libdir%\*.jar %targetdir%

%IKVMTOOLDIR%\ikvmc.exe @..\ikvmc.txt




