INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Martin' , 'Pierre');
INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Durand' , 'Yves');

INSERT INTO ouvrage(id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 2, 'Fables de la Fontaine' , 1 , 'Gallimard');
INSERT INTO ouvrage (id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 1, 'Le monde' , 2 , 'Flammarion');

INSERT INTO utilisateur(id, nom, is_admin, email, mot_de_passe) VALUES (nextval('utilisateur_seq'), 'Florent' , false, 'ros-florent@hotmail.fr' , '$2a$10$vRhObTPIOt/P7tjro/ft5ORDNvEvzu.5xBL9AVVtlcuieu/.JqYLK');
INSERT INTO utilisateur(id, nom, is_admin, email, mot_de_passe) VALUES (nextval('utilisateur_seq'), 'Serge' , false, 'ros-florent@hotmail.fr' , '$2a$10$vRhObTPIOt/P7tjro/ft5ORDNvEvzu.5xBL9AVVtlcuieu/.JqYLK');

INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-04-11' , '2021-05-11' , null, false,'2021-04-11', 1, 1);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , null, false,'2021-04-11', 1, 2);

INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-04-11' , '2021-05-11' , null, false,null, 1, 1);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , null, false,null, 1, 2);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , null, false,null, 2, 2);

