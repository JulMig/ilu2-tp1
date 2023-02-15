package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private static Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}
	
	private static class Marche {
		private Etal[] etals;

		public Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				this.etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			
			return -1;
			
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbEtalLibre = 0;
			for (Etal etal : etals) {
				if (etal.contientProduit(produit)) {
					nbEtalLibre += 1;
				}
			}
			
			Etal[] etalProduit = new Etal[nbEtalLibre];
			int nbEtalPlace = 0;
			
			for (Etal etal : etals) {
				if (etal.contientProduit(produit)) {
					etalProduit[nbEtalPlace] = etal;
					nbEtalPlace ++;
				}

			}
			
			return etalProduit;
			
		}
		
		public Etal trouverVendeur(Gaulois gaulois){
			for (Etal etal : etals) {
				if( etal.getVendeur() == gaulois) {
					return etal;
				}
			}
			return null;
		}
		
		public void afficherMarche() {
			int nbEtalLibre = 0;
			
			for(Etal etal: etals) {
				if(etal.isEtalOccupe()) {
					etal.afficherEtal();
				} else {
					nbEtalLibre ++;
				}
			}
			System.out.println("Il reste " + nbEtalLibre +  " étals non utilisés dans le marché. \n");
		}
		
	}
		
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		String dialogue = "Le vendeur "+ vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".";
		int etalLibre = marche.trouverEtalLibre();
		if (etalLibre != -1) {
			marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
			dialogue += "\nLe vendeur " + vendeur.getNom() + " vend des " + produit + " sur l'étal n°" + etalLibre + ".";
		}
		return dialogue;
	}

	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}