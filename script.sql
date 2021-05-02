

drop database AgenceDB;
create database AgenceDB;

use AgenceDB;
ALTER DATABASE AgenceDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table user(
  username varchar(50) primary key not null,
  password varchar(50),
  nom varchar(50),
  prenom varchar(50),
  typeUser varchar(50)
);


create table produit(
  reference varchar(100) primary key not null,
  designation varchar(100),
  prix real default 0,
  Qte_stock real default 0,
  rupture integer default 0
);

create table commande(
  numeroArrive integer primary key auto_increment not null,
  dateArrive datetime,
  etatCommande varchar(60),
  codeClient integer not null,

  constraint fk_codeClient foreign key (codeClient) references client(codeClient)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

create table ligneCommande(
  numeroArrive integer,
  reference varchar(100),
  qte_commande real,

  constraint pk_ligneCommande primary key (numeroArrive,reference),

  constraint fk_numArrive_ligneC foreign key (numeroArrive) references commande(numeroArrive)
  ON DELETE CASCADE
  ON UPDATE CASCADE,

  constraint fk_reference_ligneC foreign key (reference) references produit(reference)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

create table client(
  codeClient integer primary key auto_increment not null,
  nomCl varchar(50),
  prenomCl varchar(50),
  raisonSociale varchar(100),
  adress varchar(100),
  numeroTel varchar(15),
  etatClient varchar(600)
);


create table bonLivraison(
  numeroBon integer primary key auto_increment not null,
  dateBon datetime,
  poid real,
  nbColis integer,
  numeroArrive integer,
  nomPreparateur varchar(60),

  constraint fk_numArrive foreign key (numeroArrive) references commande(numeroArrive)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

create table livrer(
  numeroBon integer,
  reference varchar(100),
  qte_livrer real,

  constraint pk_livrer primary key (numeroBon,reference),

  constraint fk_numBl_livrer foreign key (numeroBon) references bonLivraison(numeroBon)
  ON DELETE CASCADE
  ON UPDATE CASCADE,

  constraint fk_reference_livrer foreign key (reference) references produit(reference)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

	
create table facture(
  numeroFacture integer primary key auto_increment not null,
  dateFacture datetime,
  dateEcheance datetime,
  numeroBon integer,
  dateRelancement datetime,
  montant_tot double,
  
  constraint fk_numBl foreign key (numeroBon) references bonLivraison(numeroBon)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);


create table payement(
  codeClient integer,
  numeroFacture integer,
  datePayement datetime,
  montant real,
  constraint pk_payement primary key (datePayement,codeClient,numeroFacture),

  constraint fk_payCl foreign key (codeClient) references client(codeClient)
  ON DELETE CASCADE
  ON UPDATE CASCADE,

  constraint fk_payFac foreign key (numeroFacture) references facture(numeroFacture)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE produit CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE client CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE commande CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE bonLivraison CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE facture CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE ligneCommande CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE livrer CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE payement CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE factureRelance CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;
ALTER TABLE preparateur CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;



describe user;
describe produit;
describe client;
describe commande;
describe bonLivraison;
describe facture;
describe ligneCommande;
describe livrer;
describe payement;
describe factureRelance;
describe preparateur;


