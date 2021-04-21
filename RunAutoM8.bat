dir /b /o:gn *.jar > tmp.txt
sort /R tmp.txt /o tmp.txt
set /p latestVersion= <tmp.txt
echo %latestVersion%
del tmp.txt
java -jar %latestVersion%
pause