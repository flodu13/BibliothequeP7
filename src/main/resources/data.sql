INSERT INTO auteur VALUES (1, 'Martin' , 'Pierre');
INSERT INTO auteur VALUES (2, 'Durand' , 'Yves');

INSERT INTO ouvrage(id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (1, 2, 'Fables de la Fontaine' , 1 , 'Gallimard');
INSERT INTO ouvrage (id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (2, 1, 'Le monde' , 2 , 'Flammarion');

INSERT INTO utilisateur Values (1, 'Florent' , 'ros-florent@hotmail.fr' , 'flo13');
INSERT INTO utilisateur Values (2, 'Serge' , 'serge@hotmail.fr' , 'serge13');

INSERT INTO emprunt VALUES (1, '2021-04-11' , '2021-04-11' , '2021-04-11', 1, 1);
INSERT INTO emprunt VALUES (2, '2021-02-11' , '2021-02-11' , '2021-02-11', 1, 2);