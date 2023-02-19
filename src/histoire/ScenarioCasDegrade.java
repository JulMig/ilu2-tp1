package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		
		
		Gaulois gaulois = new Gaulois("gaulois", 10);
		etal.occuperEtal(gaulois, "Potion", 200);
		etal.acheterProduit(100, null);
		
		System.out.println("Fin du test");
		}


}
