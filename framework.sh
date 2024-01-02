#!/bin/bash

case "$1" in
    "generate")
        case "$2" in "model")
            read -s -p "Mot de passe : " motdepasse
            read -p "Language [1:java, 2:C#] : " chiffrelangage
            if [ "$chiffrelangage" == "1" ]; then
                langage="java"
            elif [ "$chiffrelangage" == "2" ]; then 
                langage="csharp"
            else 
                echo "Veuillez specifier le language utilise"
                exit
            fi
            read -p "Table [*]: " table
            if [ -z "$table" ]; then
                modele=""
            else
                defaultmodel=$table
                read -p "Nom du modele [$defaultmodel]: " modele
            fi
            if [ -z "$modele" ]; then
                modele=$defaultmodel
            fi
            read -p "Nom du package :" package
            java run.GenerateModel $motdepasse $table $modele $package $langage
            esac
            ;;
    "new")
        case "$2" in "project")
            defaultconfiguration='{
                "SGBD": "",
                "hote": "",
                "port": "",
                "base_de_donnees": "",
                "nom_utilisateur": ""
            }'
            read -p "Nom du projet [ ~ ]: " project_name
            if [ -z "$project_name" ]; then
                cd .
            else 
                mkdir "$project_name"
                cd "$project_name" 
            fi
            mkdir controllers
            mkdir models
            echo "$defaultconfiguration" > dbconfigs.json
            ;;
        esac
        ;;
    "configure")
        echo "Configuration..."
        read -p "SGBD [1:postgresql ou 2:mysql] : " sgbd
        if [ "$sgbd" = "1" ]; then
            defaultport="5432"
            defaultuser="postgres"
            sgbd="postgresql"
        elif [ "$sgbd" = "2" ]; then
            defaultport=3306
            defaultuser="root"
            sgbd="mysql"
        elif [ -z "$sgbd"]; then
            echo "Veuillez spécifier le SGBD"
            exit
        else
            echo "Veuillez spécifier le SGBD"
            exit
        fi

        read -p "Hote [localhost] : " hote
        if [ -z "$hote" ]; then 
            hote="localhost"
        fi

        read -p "Port [$defaultport] : " port
        if [ -z "$port" ]; then 
            port=$defaultport
        fi
        read -p "Nom d'utilisateur [$defaultuser] :" user
        if [ -z "$user" ]; then 
            user=$defaultuser
        fi

        read -p "Nom de la base de donnees: " base
        if [ -z "$base" ];then 
            echo "Veuillez preciser la base de donnees"
            exit
        fi
         

        configuration='{
            "SGBD": "'"$sgbd"'",
            "hote": "'"$hote"'",
            "port": "'"$port"'",
            "base_de_donnees": "'"$base"'",
            "nom_utilisateur": "'"$user"'"
        }'
        echo "$configuration" > dbconfigs.json
        echo "Configuration terminee."
        ;;
    *)
        if [ -z "$1" ]; then
            echo "Aucune commande spécifiée."
        else
            echo "Commande non reconnue."
        fi
        ;;
esac
