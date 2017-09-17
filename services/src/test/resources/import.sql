insert into User(identifiant, hash, mail, licence, nom, prenom, role) values('test', 'f7fc424486cc16fb42ebcf003d20908e9dedc1e738f371121deeed3db1a1ce6b3872156d4fb155e1d9ad66100e977d7cf3f05b2bd9501c0b13d864cd61912e80', 'durandben@gmail.com', 6638740, 'Durand', 'Benjamin', 'administrateur');

insert into Tournoi(nom, date_debut, date_fin) values ('Gradignan','2017-10-07', '2017-10-09');
insert into Tournoi(nom, date_debut, date_fin) values ('Palaiseau','2017-10-25', '2017-10-29');

insert into Parametre(clef, valeur) values ('system.logger.level', 'DEBUG');
insert into Parametre(clef, valeur) values ('poona.login', 'inconnu');
insert into Parametre(clef, valeur) values ('poona.password', 'inconnu');
