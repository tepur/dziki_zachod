package dzikizachod;

public class Szeryf extends Gracz{
	public static Rola ROLA = Rola.SZERYF;
	private static int LICZBA_ZYC = 5;
	
	Szeryf(StrategiaSzeryfa ss){
		super(ss, LICZBA_ZYC);
	}
	Szeryf(){
		this(new StrategiaSzeryfaDomyslna());
	}
	@Override
	public String toString(){
		return "Szeryf";
	}
	@Override
	public Rola rola(){
		return ROLA;
	}
}
