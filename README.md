# Back Bibliothèque
Pour lancer l'application il faut exécuter la ligne de commande : ```java -jar API-0.0.1-SNAPSHOT.jar --spring.mail.username=<outlookusername> --spring.mail.password=<password> ```

## Configuration compte mail : 
* spring.mail.host: smtp.office365.com
* spring.mail.port: 587
* spring.mail.username: <username>
* spring.mail.password: <password>
* spring.mail.properties.mail.smtp.auth: true
* spring.mail.properties.mail.smtp.starttls.enable: true
* spring.mail.properties.mail.transport.protocol: smtp

## Configuration base de donnée
* spring.datasource.url : ex jdbc:postgresql://localhost:5432/gestionBiblioP7
* spring.datasource.username : <username>
* spring.datasource.password : <password>  
* spring.datasource.initialization-mode : always  
  
## Comptes usagers :     
2 utilisateurs peuvent se connecter :  
* login= Florent mot de passe=serge13
* login= Serge mot de passe=serge13

 ## Déploiement de la base de donnée 
Le fichier data.sql est directement intégré dans l'application. Nous utilisons une base de donnée postgresql. Le fait d'intégrer directement le fichier permet de déployer le schéma de la base de donnée au démarrage de l'application grace aux dépendences : 
<dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.2.14</version>
            <scope>runtime</scope>
        </dependency>
  
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
