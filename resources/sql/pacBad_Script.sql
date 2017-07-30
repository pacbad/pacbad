create database pacBad;

use pacBad;

create table RefClassement (
	id INT AUTO_INCREMENT NOT NULL,
	serie VARCHAR(3) NOT NULL,
	coteMin FLOAT NOT NULL,
	coteMax FLOAT NOT NULL,
	PRIMARY KEY (id)
);

create table RefTableau (
	id INT AUTO_INCREMENT NOT NULL,
	nomCourt VARCHAR(2) NOT NULL,
	nomLong VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

create table RefTypeInstance (
	id INT AUTO_INCREMENT NOT NULL,
	niveau VARCHAR(50),
	PRIMARY KEY (id)
);

create table Joueur (
	licence BIGINT NOT NULL,
	nom VARCHAR(100) NOT NULL,
	prenom VARCHAR(100) NOT NULL,
	primary key (licence)
);

create table Cote (
	id INT AUTO_INCREMENT NOT NULL,
	valeur FLOAT NOT NULL,
	rang INTEGER NOT NULL,
	licence BIGINT NOT NULL,
	idRefTableau INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (licence) REFERENCES Joueur(licence),
	FOREIGN KEY (idRefTableau) REFERENCES RefTableau(id)
);

create table InstanceOrganisationnelle (
	id INT AUTO_INCREMENT NOT NULL,
	nom VARCHAR(100),
	idRefTypeInstance INT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (idRefTypeInstance) REFERENCES RefTypeInstance(id)
);

create table RefFonctionUser (
	id INT AUTO_INCREMENT NOT NULL,
	fonction VARCHAR(50),
	PRIMARY KEY (id)
);

create table JoinFonctionUser (
	idInstanceOrganisationnelle INT NOT NULL,
	licenceJoueur BIGINT NOT NULL,
	idRefFonctionUser INT NOT NULL,
	PRIMARY KEY (idInstanceOrganisationnelle,licenceJoueur,idRefFonctionUser),
	FOREIGN KEY (idInstanceOrganisationnelle) REFERENCES InstanceOrganisationnelle(id),
	FOREIGN KEY (licenceJoueur) REFERENCES Joueur(licence),
	FOREIGN KEY (idRefFonctionUser) REFERENCES RefFonctionUser(id)
);

create table Tournoi (
	id INT AUTO_INCREMENT NOT NULL,
	lieu VARCHAR(100),
	region VARCHAR(2) NOT NULL,
	dateDebut DATE NOT NULL,
	dateFin DATE NOT NULL,
	nbJoueursMax INT NOT NULL,
	nbTerrains INT NOT NULL,
	identifiantPoona INT NOT NULL,
	decoupageParCote BOOLEAN default false,
	idInstanceOrganisationnelle INT NOT NULL,
	licenceJugeArbitre BIGINT,
	PRIMARY KEY(id),
	FOREIGN KEY (idInstanceOrganisationnelle) REFERENCES InstanceOrganisationnelle(id),
	FOREIGN KEY (licenceJugeArbitre) REFERENCES Joueur(licence)
);

create table Prix (
	id INT AUTO_INCREMENT NOT NULL,
	nombreTableau INT NOT NULL,
	montant FLOAT NOT NULL,
	idTournoi INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (idTournoi) REFERENCES Tournoi(id)
);

create table RefCategorie (
	id INT AUTO_INCREMENT NOT NULL,
	nomCourt VARCHAR(4) NOT NULL,
	nomLong VARCHAR(30) NOT NULL,
	age INT,
	PRIMARY KEY(id)
);

create table Tableau (
	id INT AUTO_INCREMENT NOT NULL,
	nbJoueursMax INT,
	coteMin FLOAT NOT NULL,
	coteMax FLOAT NOT NULL,
	PRIMARY KEY(id)
);

create table JoinTableauCategorie (
	idRefCategorie INT NOT NULL,
	idTableau INT NOT NULL,
	PRIMARY KEY(idRefCategorie,idTableau),
	FOREIGN KEY (idRefCategorie) REFERENCES RefCategorie(id),
	FOREIGN KEY (idTableau) REFERENCES Tableau(id)
);

create table JoinTableauJoueur (
	licenceJoueur BIGINT NOT NULL,
	idTableau INT NOT NULL,
	PRIMARY KEY(licenceJoueur,idTableau),
	FOREIGN KEY (licenceJoueur) REFERENCES Joueur(licence),
	FOREIGN KEY (idTableau) REFERENCES Tableau(id)
);