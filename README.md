# projet_jee

## Démarrage de l'application. 

> Démarrer le code dans votre back-end avec le bouton run de votre IDE, puis rendez vous sur un navigateur web et effectuez la recherche suivante : 
> > > - http://localhost:8080/
> 
> Ensuite libre à vous d'explorer le site en suivant ces trois différents chemins : 
> 
> > > - Connectez vous en tant qu'administrateur avec le mot de passes suivant : hohzewP4 et l'identifiant : admin .
> > > - Connectez vous avec ce compte créé pour la démonstration avec le mot de passe suivant : user1f64 et l'identifiant : user.
> > > - Inscrivez vous sur le site via la page d'inscription puis connectez vous. 
> > > 
> 
## Explications des pages. 

> Vous pouvez découvrir quelques information sur le site et visionner les différents portfolios publics mis à disposition.
> 
> Il y a les pages d'inscription et de connexion.
> 
> La première page où vous vous connectez sont les pages user ou admin (en fonction de votre rôle), ici vous pouvez choisir de retourner à la page d'accueil, vous rendre dans la page de création de portfolilios ou dans votre profil.
> 
> La page profil contient vos informatiosn et vous permet de générer un code ami, afin qu'un de vos amis vous envoie un portfolio. 
> 
> La page de création de portfolio vous permet de visionner tous vos portfolios, d'en créer, de les modifier, de les rendre public ou privé, et de les supprimer. On peut les envoyer à un ami graĉe à un code. 
> 
> La page de création de projets où vous pouvez en créer, les modifier et les supprimer.
> 
> La page visualiseur qui permet de voir un portfolio sous sa forme finale. 
> 
## Auto-évaluation. 

- L'application utilise SpringBoot et SpringData avec JPA, SpringMVC et Thymeleaf).
- L'application gère 5 entités : les users, les projets, les images, les portfolios, le userSécurity et des tables liant les projets aux portfolios et les portfolios aux users. Celle-ci contient 2 relations N-N.
- L'application fournit les fonctions d'un CRUD ( avec les métodes GET, PUT, POST et DELETE pour tous les controllers) + partage avec clé d'ami.
- Repository git https://github.com/LF2Zetrei/projet_jee

- L'application permet d'insérer, mettre à jour, supprimer et chercher une entité en bdd ( ici création, modification, suppression de portfolios et projets et le partage fonctionne avec la recherche d'utilisateur via clé d'ami).
- Liaison de entités en BDD ( portfolios, projets).
- Pour une entité donnée, créer un lien à une autre entité en bdd (la créations  de projets les lies à une entité déjà existante : le portfolio que l'on souhaite modifier !).
- design MVC, tous nos templates ne sont accessible qu'avec des requêtes http et ne sont accessible que si leur rôle les y autorise.
- contrôlleurs GET, PUT, POST, DELETE et HTTP dans les fonctions qui utilisent de l'ajax dans le front.
- Utilisation de Model et de Principal

- Pas de framwork css mais je trouve ça jolie
- repo github incluant les commit et le read me de bonne qualité
- Oui pour Lucas Fernandes(LF2Zetrei)...

