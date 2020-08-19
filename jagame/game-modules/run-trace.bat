@echo off
echo Current time is %date%,%time%
del trace.txt
echo starting ...
call run.bat >> trace.txt
echo finish.
pause