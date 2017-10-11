package dzikizachod;

import java.util.HashMap;
import java.util.Map;

//Liczy bilans zabójstw dla ka¿dego gracza na podstawie podanych mu informacji
public class LicznikBilansu{
	Map<Gracz, Integer> bilansGracza = new HashMap<>();  
	//+1 za zabitego pomocnika szeryfa, -1 za zabitego bandytê
		
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		if(info == Informacja.SMIERC && strzelec != null){
			if(cel.rola() == Rola.BANDYTA){
				bilansGracza.put(strzelec, bilansGracza.getOrDefault(strzelec, 0) + 1);
			}
			else if(cel.rola() == Rola.POMOCNIK_SZERYFA){
				bilansGracza.put(strzelec, bilansGracza.getOrDefault(strzelec, 0) - 1);
			}
		}
	}

	public boolean podejrzany(Gracz gracz){
		return bilansGracza.getOrDefault(gracz, 0) < 0;
	}
}
