package dzikizachod;

public class PomocnikSzeryfa extends Gracz{
	public static Rola ROLA = Rola.POMOCNIK_SZERYFA;
	
	private static int MIN_LICZBA_ZYC = 3;
	private static int MAX_LICZBA_ZYC = 4;
	
	public PomocnikSzeryfa(){
		this(new StrategiaPomocnikaSzeryfaDomyslna());
	}
	public PomocnikSzeryfa(StrategiaPomocnikaSzeryfa sps){
		super(sps, Gracz.losujLiczbeZyc(MIN_LICZBA_ZYC, MAX_LICZBA_ZYC));
	}
	@Override
	public String toString(){
		return "Pomocnik Szeryfa";
	}
	@Override
	public Rola rola(){
		return ROLA;
	}
}
