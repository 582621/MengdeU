package no.hvl.dat102.medlem;

public class Datakontakt {
	
	private final static int STDK = 100;
	private Medlem[] mtab;
	private int antall;
	
	public Datakontakt() {
		mtab = new Medlem[STDK];
		antall = 0;
	}
	
	public Medlem[] getMedlemstabell() {
		return mtab;
	}
	
	public Medlem getMedlem(String navn) {
		int indeks = finnMedlemsIndeks(navn);
		Medlem m = null;
		
		if (indeks != -1)
			m = mtab[indeks];
		
		return m;
	}
	
	public Medlem[] getMedlemTabell() {
		return mtab;
	}
	
	public int getAntall() {
		return antall;
	}
	
	public void leggTilMedlem(Medlem medlem) {
		if (finnMedlemsIndeks(medlem.getNavn()) != -1) {
			System.out.println(medlem.getNavn() + " er allerede medlem");
			return;
		}
		
		mtab[antall] = medlem;
		antall++;
	}
	
	public int finnPartnerFor(String navn) {
		boolean funnet = false;
		
		int m1 = finnMedlemsIndeks(navn);
		int m2 = -1;
		int indeks = -1;
		
		for (int i = 0; i < antall && !funnet; i++) {
			m2 = i;
			if (mtab[m2].passerTil(mtab[m1]) && mtab[m2].getStatusIndeks() == -1
					&& m1 != m2) {
				mtab[m2].setStatusIndeks(m1);
				mtab[m1].setStatusIndeks(m2);
				indeks = m2;
				funnet = true;
			}
		}
		
		return indeks;
	}
	
	public void tilbakestillStatusIndeks(String navn) {
		int p1 = finnMedlemsIndeks(navn);
		int p2 = mtab[p1].getStatusIndeks();
	
		if (p2 != -1) {
			mtab[p1].setStatusIndeks(-1);
			mtab[p2].setStatusIndeks(-1);
		}
	}
	
	public int finnMedlemsIndeks(String navn) {
		for (int i = 0; i < antall; i++)
			if (mtab[i].getNavn().equals(navn))
				return i;

		return -1;
	}
}
