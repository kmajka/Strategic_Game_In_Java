
call mvn clean
call mvn install
rd /s/q dist

mkdir dist
cd dist
mkdir config
mkdir library
mkdir data
mkdir help

xcopy /a /e /k ..\..\..\wsg\config config
xcopy /a /e /k ..\..\..\wsg\data data
xcopy /a /e /k ..\..\..\wsg\library library
xcopy /a /e /k ..\..\..\wsg\help help
copy ..\..\..\wsg\target\wsg-1.0.jar
copy ..\..\..\wsg\description.txt
echo copy ..\start_game.bat
copy ..\start_game.bat