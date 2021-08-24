INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Martin' , 'Pierre');
INSERT INTO auteur VALUES (nextval('auteur_seq'), 'Durand' , 'Yves');

INSERT INTO ouvrage(id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 2, 'Fables de la Fontaine' , 1 , 'Gallimard');
INSERT INTO ouvrage (id, nombre_exemplaire, titre, auteur_id, maison_edition) VALUES (nextval('ouvrage_seq'), 1, 'Le monde' , 2 , 'Flammarion');

INSERT INTO utilisateur(id, nom, is_admin, email, mot_de_passe) VALUES (nextval('utilisateur_seq'), 'Florent' , false, 'ros-florent@hotmail.fr' , '$2a$10$5Fz84nOqSte969IBf5iDU.FE7DqcB50XascRxDtYSdWVD//Mrida6');
INSERT INTO utilisateur(id, nom, is_admin, email, mot_de_passe) VALUES (nextval('utilisateur_seq'), 'Serge' , false, 'ros-florent@hotmail.fr' , '$2a$10$VdWZvYM1mkoGBvZIvZde6.L4q3yf.IQJ4e2aR834mZbl6ofglRhrS');
INSERT INTO utilisateur(id, nom, is_admin, email, mot_de_passe) VALUES (nextval('utilisateur_seq'), 'admin' , true, 'ros-florent@hotmail.fr' , 'admin13');

INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-04-11' , '2021-05-11' , '2021-04-11', false,'2021-04-11', 1, 1);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , '2021-02-11', false,'2021-04-11', 1, 2);

INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-04-11' , '2021-05-11' , '2021-04-11', false,null, 1, 1);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , '2021-02-11', false,null, 1, 2);
INSERT INTO emprunt VALUES (nextval('emprunt_seq'), '2021-02-11' , '2021-03-11' , '2021-02-11', false,null, 2, 2);

