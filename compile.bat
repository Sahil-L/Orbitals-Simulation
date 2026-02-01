@echo off
setlocal
set LIB=lib\*
if not exist bin mkdir bin
javac -cp "%LIB%" -d bin src\*.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b %errorlevel%
)
echo Compilation succeeded.
endlocal
