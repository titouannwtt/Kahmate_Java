# __Kamhate__ 

** Structure du projet : **
	
	* _/bin -> tous les fichiers comilésse trouvent dans ce repertoire
	* _/docs -> documentation du projet (word, etc)
	* _/javadoc -> javadoc du projet
	* _/rsc ->ressources du projet (images, sons, etc)
		* _/images -> images du projet
	* _/src -> ensembles des fichiers java
		* _/api -> ensemble des fichiers java pour l'api du bot
		* _/controller -> ensemble des fichiers java pour les controlleurs
		* _/helper -> classes "outils"
		* _/model -> ensemble des fichiers java pour les modeles
		* _/ui -> ensemble des fichiers java pour les composants graphiques
	* .gitignore 
	* Makefile
	* README 

** La commande make dev permet de compiler seulement les sources, make permet de compiler et de generer la javadoc** 

** make clean permet de supprimer les binaires, make docclean permet de supprimer la javadoc**

** /!\ Bien penser a inclure les packages necessaires pour les vues, les modeles et les controleurs **

** Le rapport se situe dans l'onglet Wiki de ce depot git **