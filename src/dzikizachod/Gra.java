package dzikizachod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Gra{
	public final static int OBRAZENIA_DYNAMITU = 3;
	public final static int OBRAZENIA_STRZALU = 1;
	public final static int WARTOSC_LECZENIA = 1;
	
	private final static int DOMYSLNY_ROZMIAR_KOSTKI = 6;
	
	private StringBuffer wciecie = new StringBuffer();
	private Kostka kostka = new Kostka(DOMYSLNY_ROZMIAR_KOSTKI);
	private Rola zwyciezca = null;
	
	private int iloscBandytow;
	
	public static Gracz znajdzSzeryfa(Collection<Gracz> gracze){
		for(Gracz g : gracze){
			if(g.rola() == Rola.SZERYF){
				return g;
			}
		}
		return null;
	}
	
	private boolean graTrwa(){
		return (zwyciezca == null);
	}
	
	//Wypisuje tekst z odpowiednim wciêciem
	private void wypisz(String s){
		System.out.print(wciecie.toString() + s);
	}
	
	private void wypiszGraczy(List<Gracz> gracze){
		Gracz gracz;
		wypisz("Gracze: \n");
		wciecie.append("  ");
		for(int i = 1; i <= gracze.size(); i++){
			wypisz(i + ": ");
			gracz = gracze.get(i - 1);
			if(gracz.zywy()){
				System.out.println(gracz + " (liczba ¿yæ: " + gracz.liczbaZyc() + ")");
			}
			else{
				System.out.println("X (" + gracz + ")");
			}
		}
		wciecie.setLength(wciecie.length() - 2);
	}
	
	private static String tekstAkcji(Gracz gracz){
		String wynik = "[";
		for(Akcja karta : gracz.karty()){
			wynik += karta.toString() + ", ";
		}
		wynik = wynik.substring(0, wynik.length() - 2);
		wynik += "]";
		return wynik;
	}
	
	private void dajDynamit(Gracz gracz){
		gracz.wezDynamit();
	}
	
	public void rozgrywka(List<Gracz> gracze, PulaAkcji pulaAkcji){
		
		iloscBandytow = 0;
		for(Gracz gracz : gracze){
			if(gracz.rola() == Rola.BANDYTA){
				iloscBandytow++;
			}
		}
		
		Collections.shuffle(gracze);
		pulaAkcji.potasuj();
		
		int indeksSzeryfa = gracze.indexOf(znajdzSzeryfa(gracze));
		
		List<Gracz> zywiGracze = new ArrayList<>();
		zywiGracze.addAll(gracze);
		
		wypisz("** START\n");
		
		wciecie.append("  ");
		wypiszGraczy(gracze);
		wciecie.setLength(wciecie.length() - 2);
		
		for(int tura = 1; tura <= 42; tura++){
			wypisz("** TURA " + tura + "\n");
			wciecie.append("  ");
			
			int indeksAktGracza = indeksSzeryfa;
			Gracz aktGracz;
			do{
				aktGracz = gracze.get(indeksAktGracza);
				wypisz("GRACZ " + (indeksAktGracza + 1) + " (" + aktGracz + ")" + ":\n");
				
				wciecie.append("  ");
				
				if(!aktGracz.zywy()){
					wypisz("MARTWY\n");
				}
				else{
					while(aktGracz.iloscKart() < 5){
						aktGracz.wezKarte(pulaAkcji.nastepnaKarta());
					}
					wypisz("Akcje: " + tekstAkcji(aktGracz) + "\n");
					
					if(aktGracz.maDynamit()){
						wypisz("Dynamit: ");
						int wynikRzutu = aktGracz.rzucKostka(kostka);
						if(wynikRzutu == 1){
							System.out.println("WYBUCH£");
							zadajObrazeniaGraczowi(null, aktGracz, OBRAZENIA_DYNAMITU, pulaAkcji, zywiGracze);
						}
						else{
							System.out.println("PRZECHODZI DALEJ");
							aktGracz.oddajDynamit();

							dajDynamit(ListyCykliczne.nastepnyElement(zywiGracze, aktGracz));
						}
					}
					wypisz("Ruchy: \n");
					
					wciecie.append("  ");
					if(aktGracz.zywy()){
						aktGracz.posortujKarty();
						
						for(int i = 0; i < aktGracz.karty().size(); i++){
							Akcja aktKarta = aktGracz.karty().get(i);
							Gracz cel = aktGracz.podejmijDecyzje(aktKarta, zywiGracze);
							if(wykonaj(aktKarta, aktGracz, cel, pulaAkcji, zywiGracze)){
								wypisz(aktKarta.toString() + " ");
								if(cel != aktGracz){
									System.out.print(gracze.indexOf(cel) + 1);
								}
								System.out.println();
								
								if(aktKarta != Akcja.DYNAMIT){
									pulaAkcji.dodajDoOdrzuconych(aktKarta);
								}
								aktGracz.wyrzucKarte(i);
								i--; //po usuniêciu i-tej karty, dalsze karty przesun¹ siê o 1 w lewo
								
								if(!graTrwa()){
									break;
								}
							}
						}
						
					}
					else{
						wypisz("MARTWY\n");
					}	
					wciecie.setLength(wciecie.length() - 2);
				}
				indeksAktGracza++;
				indeksAktGracza %= gracze.size();
				
				wciecie.setLength(wciecie.length() - 2);
				if(aktGracz.zywy()){
					wypiszGraczy(gracze);
				}
				
			}while(indeksAktGracza != indeksSzeryfa && graTrwa());
			
			wciecie.setLength(wciecie.length() - 2);
			
			if(!graTrwa()){
				break;
			}
		}
		wypisz("** KONIEC\n");
		wciecie.append("  ");
		if(graTrwa()){
			wypisz("REMIS - OSI¥GNIÊTO LIMIT TUR\n");
		}
		else{
			wypisz("WYGRANA STRONA: ");
			if(zwyciezca == Rola.SZERYF || zwyciezca == Rola.POMOCNIK_SZERYFA){
				System.out.println("szeryf i pomocnicy");
			}
			else{
				System.out.println("bandyci");
			}
		}
		wciecie.setLength(wciecie.length() - 2);
	}
	
	//Zwraca true/false w zale¿noœci od tego, czy akcja zostanie wykonana
	private boolean wykonaj(Akcja akcja, Gracz wykonawca, Gracz cel, PulaAkcji pulaAkcji, List<Gracz> zywiGracze){
		
		if(cel == null)return false;
		
		if(akcja == Akcja.ZASIEG_PLUS_JEDEN){
			wykonawca.zwiekszZasieg(1);
		}
		else if(akcja == Akcja.ZASIEG_PLUS_DWA){
			wykonawca.zwiekszZasieg(2);
		}
		else if(akcja == Akcja.ULECZ){
			cel.ulecz(1);
		}
		else if(akcja == Akcja.DYNAMIT){
			dajDynamit(ListyCykliczne.nastepnyElement(zywiGracze, wykonawca));
		}
		else{
			zadajObrazeniaGraczowi(wykonawca, cel, 1, pulaAkcji, zywiGracze);
		}
		
		return true;
	}
	
	private void przekazInformacjeGraczom(List<Gracz> gracze, Informacja info, Gracz wykonawca, Gracz cel){
		for(Gracz g : gracze){
			g.przyjmijInformacje(info, wykonawca, cel);
		}
	}
	
	//W przypadku, gdy zadaj¹cym obra¿enia jest dynamit, strzelec jest rowny null
	private void zadajObrazeniaGraczowi(Gracz strzelec, Gracz cel, int obrazenia, PulaAkcji pulaAkcji, List<Gracz> zywiGracze){
		cel.otrzymajObrazenia(obrazenia);
		przekazInformacjeGraczom(zywiGracze, Informacja.STRZAL, strzelec, cel);
		
		if(!cel.zywy()){
			//Nie mo¿na zadawaæ obra¿eñ nie¿yj¹cym graczom, wiêc ten case oznacza œmieræ celu
			przekazInformacjeGraczom(zywiGracze, Informacja.SMIERC, strzelec, cel);
			pulaAkcji.dodajDoOdrzuconych(cel.karty());
			if(cel.rola() == Rola.BANDYTA){
				iloscBandytow--;
				if(iloscBandytow == 0){
					zwyciezca = Rola.SZERYF;
				}
			}
			else if(cel.rola() == Rola.SZERYF){
				zwyciezca = Rola.BANDYTA;
			}
			
			zywiGracze.remove(cel);
		}
	}

	public static void main(String[] args){
		Gra gra = new Gra();
		List<Gracz> gracze = new ArrayList<>();
		gracze.add(new Szeryf(new StrategiaSzeryfaZliczajaca()));
		gracze.add(new PomocnikSzeryfa(new StrategiaPomocnikaSzeryfaZliczajaca()));
		gracze.add(new PomocnikSzeryfa(new StrategiaPomocnikaSzeryfaZliczajaca()));
		gracze.add(new Bandyta(new StrategiaBandytySprytna()));
		gracze.add(new Bandyta(new StrategiaBandytySprytna()));
		gracze.add(new Bandyta(new StrategiaBandytySprytna()));
		
		PulaAkcji pulaAkcji = new PulaAkcji();
		pulaAkcji.dodaj(Akcja.STRZEL, 60);
		pulaAkcji.dodaj(Akcja.ULECZ, 20);
		pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_JEDEN, 10);
		pulaAkcji.dodaj(Akcja.ZASIEG_PLUS_DWA, 10);
		pulaAkcji.dodaj(Akcja.DYNAMIT, 1);
		
		gra.rozgrywka(gracze, pulaAkcji);
	}
}
