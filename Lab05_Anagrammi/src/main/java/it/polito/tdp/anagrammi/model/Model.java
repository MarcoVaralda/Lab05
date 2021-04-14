package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.anagrammi.DAO.AnagrammiDAO;

public class Model {
	
	AnagrammiDAO dao = new AnagrammiDAO();
		
	public List<String> risolvi(String parola) {
		List<String> risultato = new ArrayList<>();
		permuta(0,"",parola,risultato);
		return risultato;
	}
	
	// Livello = lunghezza della soluzione parziale (livello iniziale = 0)
	// parziale = stringa che contiene l'anagramma incompleto in fase di costruzione
	// lettere = lettere non ancora utilizzate (=== il sotto problema che dobbiamo risolvere)
	private void permuta(int livello, String parziale, String lettere, List<String> risultato) {
		
		// Caso terminale
		if(lettere.length()==0) {
			risultato.add(parziale);
		}
		else {
			
			for(int i = 0; i<lettere.length(); i++) {
				char tentativo = lettere.charAt(i);
				String nuovaParziale = parziale +tentativo;
				String nuovaLettere = lettere.substring(0, i)+lettere.substring(i+1);
				permuta(livello+1,nuovaParziale,nuovaLettere,risultato);
			}
			
		}
	}
	
	public String getAnagrammiCorretti(String parola) {
		
		String risultato = "";
		for(String s : risolvi(parola))
			if(dao.checkAnagram(s)==true)
				risultato = risultato +s +"\n";
		
		return risultato;
	}
	
    public String getAnagrammiErrati(String parola) {
    	
    	String risultato = "";
		for(String s : risolvi(parola))
			if(dao.checkAnagram(s)==false)
				risultato = risultato +s +"\n";
		
		return risultato;
	}

}
