@echo off
set dir=%1

if not exist %dir% mkdir %dir%
mkdir %dir%\adapter
mkdir %dir%\adapter\in\web
mkdir %dir%\adapter\out\persistence

mkdir %dir%\application
mkdir %dir%\application\exception
mkdir %dir%\application\port
mkdir %dir%\application\port\in
mkdir %dir%\application\port\out
mkdir %dir%\application\service

mkdir %dir%\config
mkdir %dir%\domain

exit /b 0