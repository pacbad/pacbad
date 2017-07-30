insert into RefCategorie(nomCourt,nomLong,age) values ("MBad","Minibad",-10);
insert into RefCategorie(nomCourt,nomLong,age) values ("Pou","Poussin",7);
insert into RefCategorie(nomCourt,nomLong,age) values ("Ben1","Benjamin 1",9);
insert into RefCategorie(nomCourt,nomLong,age) values ("Ben2","Benjamin 2",10);
insert into RefCategorie(nomCourt,nomLong,age) values ("Min1","Minime 1",11);
insert into RefCategorie(nomCourt,nomLong,age) values ("Min2","Minime 2",12);
insert into RefCategorie(nomCourt,nomLong,age) values ("Cad1","Cadet 1",13);
insert into RefCategorie(nomCourt,nomLong,age) values ("Cad2","Cadet 2",14);
insert into RefCategorie(nomCourt,nomLong,age) values ("Jun1","Junior 1",15);
insert into RefCategorie(nomCourt,nomLong,age) values ("Jun2","Junior 2",16);
insert into RefCategorie(nomCourt,nomLong,age) values ("Sen","Senior",17);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet1","Veteran 1",34);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet2","Veteran 2",39);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet3","Veteran 3",44);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet4","Veteran 4",49);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet5","Veteran 5",54);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet6","Veteran 6",59);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet7","Veteran 7",64);
insert into RefCategorie(nomCourt,nomLong,age) values ("Vet8","Veteran 8",69);


insert into RefClassement(serie,coteMin,coteMax) values ("N1", 2000, 1000000000);
insert into RefClassement(serie,coteMin,coteMax) values ("N2", 1000, 2000);
insert into RefClassement(serie,coteMin,coteMax) values ("N3", 600, 1000);
insert into RefClassement(serie,coteMin,coteMax) values ("R4", 300, 600);
insert into RefClassement(serie,coteMin,coteMax) values ("R5", 128, 300);
insert into RefClassement(serie,coteMin,coteMax) values ("R6", 64, 128);
insert into RefClassement(serie,coteMin,coteMax) values ("D7", 32, 64);
insert into RefClassement(serie,coteMin,coteMax) values ("D8", 16, 32);
insert into RefClassement(serie,coteMin,coteMax) values ("D9", 8, 16);
insert into RefClassement(serie,coteMin,coteMax) values ("P10", 4, 8);
insert into RefClassement(serie,coteMin,coteMax) values ("P11", 2, 4);
insert into RefClassement(serie,coteMin,coteMax) values ("P12", 0.001, 2);
insert into RefClassement(serie,coteMin,coteMax) values ("NC", 0, 0.001);


insert into RefFonctionUser(fonction) values("Président");
insert into RefFonctionUser(fonction) values("Responsable compétiteur");
insert into RefFonctionUser(fonction) values("Joueur");
insert into RefFonctionUser(fonction) values("Organisateur tournoi");


insert into RefTableau(nomCourt,nomLong) values("SH","Simple Homme");
insert into RefTableau(nomCourt,nomLong) values("SD","Simple Dame");
insert into RefTableau(nomCourt,nomLong) values("DH","Double Homme");
insert into RefTableau(nomCourt,nomLong) values("DD","Double Dame");
insert into RefTableau(nomCourt,nomLong) values("DX","Double Mixte");


insert into RefTypeInstance(niveau) values("Fédération");
insert into RefTypeInstance(niveau) values("Ligue");
insert into RefTypeInstance(niveau) values("Codep");
insert into RefTypeInstance(niveau) values("Club");