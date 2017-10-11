package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PulaAkcji{
	private List<Akcja> aktualnaPula = new ArrayList<>();
	private List<Akcja> odrzuconeKarty = new ArrayList<>();
	public void dodaj(Akcja typ, int liczba){
		for(int i = 0; i < liczba; i++){
			aktualnaPula.add(typ);
		}
	}
	public void potasuj(){
		Collections.shuffle(aktualnaPula);
	}
	public Akcja nastepnaKarta(){
		if(aktualnaPula.isEmpty()){
			aktualnaPula.addAll(odrzuconeKarty);
			Collections.shuffle(aktualnaPula);
			odrzuconeKarty.clear();
		}
		Akcja karta = aktualnaPula.get(0);
		aktualnaPula.remove(0);
		return karta;
	}
	public void dodajDoOdrzuconych(Akcja karta){
		odrzuconeKarty.add(karta);
	}
	public void dodajDoOdrzuconych(List<Akcja> karty){
		odrzuconeKarty.addAll(karty);
		karty.clear();
	}
}
