INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Martin' , 'Pierre');
INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Durand' , 'Yves');

INSERT INTO ouvrage(id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 2, 'Fables de la Fontaine' , 1 , 'Gallimard');
INSERT INTO ouvrage (id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 1, 'Le monde' , 2 , 'Flammarion');

INSERT INTO utilisateur Values (nextval('utilisateur_seq'), 'Florent' , 'ros-florent@hotmail.fr' , 'flo13');
INSERT INTO utilisateur Values (nextval('utilisateur_seq'), 'Serge' , 'serge@hotmail.fr' , 'serge13');

INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-04-11' , '2021-04-11' , '2021-04-11', false,'2021-04-11', 1, 1);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-02-11' , '2021-02-11', false,'2021-04-11', 1, 2);
