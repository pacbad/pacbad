insert into User(identifiant, hash, mail, licence, nom, prenom, role, dateCreation) values('benjamin', 'f7fc424486cc16fb42ebcf003d20908e9dedc1e738f371121deeed3db1a1ce6b3872156d4fb155e1d9ad66100e977d7cf3f05b2bd9501c0b13d864cd61912e80', 'durandben@gmail.com', 6638740, 'Durand', 'Benjamin', 'administrateur', '2017-09-19 13:45:10');

insert into Tournoi(nom, date_debut, date_fin,lieu,departement,region,nb_joueurs_max,nb_terrains,identifiant_poona,decoupage_cote) values ('Gradignan','2017-10-07', '2017-10-09', 'Gradignan', '33', 'Nouvelle Aquitaine', 250, 9, 'id1', true);
insert into Tournoi(nom, date_debut, date_fin,lieu,departement,region,nb_joueurs_max,nb_terrains,identifiant_poona,decoupage_cote) values ('Palaiseau','2017-10-25', '2017-10-29', 'Palaiseau', '91', 'Ile de France', 300, 7, 'id2', false);

insert into Parametre(clef, valeur) values ('system.logger.level', 'DEBUG');
insert into Parametre(clef, valeur) values ('poona.login', 'inconnu');
insert into Parametre(clef, valeur) values ('poona.password', 'inconnu');
