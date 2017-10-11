package dzikizachod;

public class Bandyta extends Gracz{
	private final static Rola ROLA = Rola.BANDYTA;
	private final static int MIN_LICZBA_ZYC = 3;
	private final static int MAX_LICZBA_ZYC = 4;
	
	public Bandyta(){
		this(new StrategiaBandytyDomyslna());
	}
	public Bandyta(StrategiaBandyty sb){
		super(sb, Gracz.losujLiczbeZyc(MIN_LICZBA_ZYC, MAX_LICZBA_ZYC));
	}
	@Override
	public String toString(){
		return "Bandyta";
	}
	@Override
	public Rola rola(){
		return ROLA;
	}
}
