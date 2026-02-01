@echo off
setlocal
set LIB=lib\*
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp "bin;%LIB%" Simulation
endlocal
