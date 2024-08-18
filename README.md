<h1 align="center">
  <img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/minecraft_title.png">
</h1>

# Plugin Minecraft - Pattern MVC

### **Objectif:**

Ce plugin Minecraft a pour but pédagogique de démontrer l'implémentation du **pattern MVC (Model-View-Controller)** dans le développement d'un **plugin** Minecraft Spigot.
Il n'a pas pour but de vous apprendre a crée des plugins, il faut avoir des bases de java pour comprendre.

### **Fonctionnalités:**

* Gestion des utilisateurs simples avec pattern Observer
* Interface graphique simple avec ScoreBoard et BossBar
* Stockage de données utilisateur dans un fichier JSON
* Système simple de RPG avec level et expérience
* Persistence des données

[![Exemple en vidéo](https://img.youtube.com/vi/RI41xBlDplI/0.jpg)](https://www.youtube.com/watch?v=RI41xBlDplI)

### **Structure du plugin:**

Le plugin est organisé selon le pattern MVC:

* **Model:** Contient les classes représentant les données du plugin (ex: CraftPlayer)
* **View:** Contient les classes responsables de l'affichage et des interactions avec Minecraft (ex: ScoreBoard, Connexion du joueur)
* **Controller:** Contient les classes qui gèrent la logique et l'interaction entre le modèle et la vue (ex: CraftPlayerController)

Il utilise notamment le design pattern d'Observer.

<h1 align="center">
  <img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/mvc_exemple.png">
</h1>

L'architecture est multi-tier (3 exactement), une première qui est l'IHM (Minecraft), une seconde qui est la Logic est enfin le Storage qui contient la sauvegarde en Json.

Le storage utilise un DAO, avec des méthodes CRUD (create/read/update/delete) avec de la sérialisation en json utilisant Gson qui est nativement intégré a la librairie de spigot.

<h1 align="center">
  <img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/conception/Logic.jpg">
<img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/conception/Storage.jpg">
<img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/conception/IHM.jpg">
<img src="https://raw.githubusercontent.com/Sorax5/SpigotPluginMVC/master/assets/conception/Package.jpg">
</h1>

### **Build du plugin:**

1. Clonez le repo GitHub
2. Ouvrez avec un IDE (Intellij de préférences)
3. Chargez le projet gradle (devrais se charger automatiquement par Intellij)
4. Build le projet avec la commande `clean build` de gradle
5. Récupérez le plugins dans le dossier `build/libs/` sous ne nom `SpigotPluginMVC-1.0-SNAPSHOT-all.jar`
6. Placez le plugin dans le dossier `plugins/` de vôtre serveur
2. Démarrez votre serveur Minecraft
3. Rejoignez le serveur

### **Ressources:**

* Tutoriel MVC Minecraft: [https://www.spigotmc.org/threads/mvc-pattern-with-events.544327/](https://www.spigotmc.org/threads/mvc-pattern-with-events.544327/)
* Pattern MVC: [https://developer.mozilla.org/en-US/docs/Glossary/MVC](https://developer.mozilla.org/en-US/docs/Glossary/MVC)
* Pattern Observer [https://refactoring.guru/design-patterns/observer](https://refactoring.guru/design-patterns/observer)
* FastBoard [https://github.com/MrMicky-FR/FastBoard](https://github.com/MrMicky-FR/FastBoard)

### **Licence:**

Ce plugin est sous licence GPL-3.0 license.

### **Contributeurs:**

<a href="https://github.com/Sorax5/SpigotPluginMVC/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Sorax5/SpigotPluginMVC" />
</a>

Made with [contrib.rocks](https://contrib.rocks).

### **Remarques:**

* Ce plugin est un exemple simple et pédagogique. Il peut être étendu et amélioré pour des fonctionnalités plus complexes.
* N'hésitez pas à contribuer au projet en forkant le repo et en soumettant des pull requests.

### **Prochaines étapes:**

* Ajout de commandes d'administration

N'hésitez pas à proposer de nouvelles features que vous voudriez voir apparaître dans le plugin !

### **Problèmes connus:**

* Aucun problème connu pour le moment.

### **Soumettre un bug:**

Si vous rencontrez un problème avec le plugin, veuillez le signaler en ouvrant une issue sur le repo GitHub.
