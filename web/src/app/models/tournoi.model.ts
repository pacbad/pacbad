export class Tournoi {
  id: number;

  nom: string;

  dateDebut: Date;
  dateFin: Date;

  lieu: string;
  departement: string;
  region: string;

  nbJoueursMax: number;
  nbTerrains: number;

  identifiantPoona: string;

  decoupageParCote: boolean;
}
