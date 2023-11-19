## Bienvenue

Générer des classes JAVA et C# a partir d'une table dans une base de donnees MySQL ou PostgreSQL

## Structure du depot:
    
- `framework` : contenant les codes sources (/src) et les librairies (/lib)utilisees
- `templates` : contenant des templates de classes C# et JAVA 
- `test_scaffolding` : un dossier de test du scaffolgind
- `framework.bat` : le script BAT executé lors du scaffolding et de la configuration de la base de donnees (Pour Windows) NON TESTE
- `framework.sh` : le script BASH executé lors du scaffolding et de la configuration de la base de donnees (Pour Linux)
- `framework.jar` : la librairie jar du projet

## Instructions d'installation pour Linux

- Mettre dans la variable d'environnement nommee `framework` le chemin vers le script sh. C'est-a-dire ajouter au fichier `/etc/bash.bashrc` : 
```sh
    alias framework='/chemin/vers/framework.sh'
```
- Mettre dans la classpath les librairies `gson-2.8.2.jar` (dans /framework/lib) et `framework.jar`. C'est-a-dire ajouter au fichier `/etc/bash.bashrc` :
```sh
    export CLASSPATH='.:/chemin/vers/framework.jar:/chemin/vers/gson-2.8.2.jar:/vos/autres/dependances/java/.jar'
```
- Mettre dans la variable d'environnement nommee `templateDIR` le chemin vers le dossier `/templates`. C'est-a-dire ajouter au fichier `/etc/bash.bashrc` :
```sh 
    export templateDIR='/chemin/vers/templates'
```

## Instructions d'installations pour WIndows:
 (A venir)

## Tester pour Linux

- Pour configurer la base de donnees: 
    ```sh 
        framework configure
    ```
- Pour effectuer un scaffolding: 
    ```sh
        framework generate
    ```
