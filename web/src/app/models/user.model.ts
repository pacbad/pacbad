export class User {
  /* champs nécessaires au moins pour la connexion */
  identifiant: string;
  password: string;

  /* champs nécessaires uniquement pour la création et la modification de compte */
  passwordRepeat: string;

  /* champs nécessaires uniquement pour la modification de compte */
  ancienPassword: string;

  /* autres champs */
  dateNaissance: Date;
  licence: number;
  mail: string;
  nom: string;
  prenom: string;

  dateCreation: Date;
}

export class Claims {
  identifiant: string;
  licence: number;
  mail: string;
  nom: string;
  prenom: string;

  dateCreation: Date;
}
