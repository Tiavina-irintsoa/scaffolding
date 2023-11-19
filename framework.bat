@echo off
setlocal enabledelayedexpansion

if "%1" == "generate" (
    set "nomfichier=dbconfigs.json"
    if exist "%nomfichier%" (
        set /p "motdepasse=Mot de passe : "
        set /p "chiffrelangage=Language (1:java, 2:C#) : "
        if !chiffrelangage! == "1" (
            set "langage=java"
        ) else if !chiffrelangage! == "2" (
            set "langage=csharp"
        ) else (
            echo Veuillez specifier le language utilise
            exit /b
        )
        set /p "table=Table : "
        if not "%table%"=="" (
            set "defaultmodel=%table%"
            set /p "modele=Nom du modele (!defaultmodel!): "
        )
        set /p "package=Nom du package :"
        if "%modele%"=="" set "modele=!defaultmodel!"
        java run.Generate !motdepasse! !table! !modele! !package! !langage!
    ) else (
        echo Veuillez configurer votre base de donnees avec la commande 'framework configure'.
    )
) else if "%1" == "configure" (
    echo Configuration...
    set /p "sgbd=SGBD (1:postgresql ou 2:mysql) : "
    if "%sgbd%" == "1" (
        set "defaultport=5432"
        set "defaultuser=postgres"
        set "sgbd=postgresql"
    ) else if "%sgbd%" == "2" (
        set "defaultport=3306"
        set "defaultuser=root"
        set "sgbd=mysql"
    ) else (
        echo Veuillez spécifier le SGBD
        exit /b
    )

    set /p "hote=Hote (localhost) : "
    if "%hote%"=="" set "hote=localhost"

    set /p "port=Port (!defaultport!) : "
    if "%port%"=="" set "port=!defaultport!"

    set /p "user=Nom d'utilisateur (!defaultuser!) :"
    if "%user%"=="" set "user=!defaultuser!"

    set /p "base=Nom de la base de donnees : "
    if "%base%"=="" (
        echo Veuillez preciser la base de donnees
        exit /b
    )

    set "configuration={
        "SGBD": "%sgbd%",
        "hote": "%hote%",
        "port": "%port%",
        "base_de_donnees": "%base%",
        "nom_utilisateur": "%user%"
    }"

    echo !configuration! > dbconfigs.json
    echo Configuration terminee.
) else (
    if "%1" == "" (
        echo Aucune commande spécifiée.
    ) else (
        echo Commande non reconnue.
    )
)

endlocal
