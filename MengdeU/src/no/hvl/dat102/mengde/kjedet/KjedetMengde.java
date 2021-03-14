package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");
		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		int i = 0;
		forgjenger = start;
		while(i < antall-1 && !funnet) {
			if(start.getElement().equals(element)) {
				resultat = start.getElement();
				start = start.getNeste();
				funnet = true;
				antall--;
			}
			else if(forgjenger.getNeste().getElement().equals(element)) {
				aktuell = forgjenger.getNeste();
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste());
				funnet = true;
				antall--;
			}		
			forgjenger = forgjenger.getNeste();
			i++;
			
		}
		return resultat;
	}//

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}

	@Override
	public boolean equals(Object m2) {
		KjedetMengde<T> km2 = (KjedetMengde<T>) m2;
		boolean likeMengder = km2.antall() == antall;
		Iterator<T> km2i = km2.oppramser();
		while(km2i.hasNext() && likeMengder) {
			if(!inneholder(km2i.next())) {
				likeMengder = false;
			}
		}
		return likeMengder;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<T>();
		Iterator<T> it = m2.oppramser();
		begge.leggTilAlle(this);
		while(it.hasNext()) {
			((KjedetMengde<T>) begge).leggTil(it.next()); 
		}
		return begge;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		Iterator<T> it = m2.oppramser();
		while(it.hasNext()) {
			T neste = it.next();
			if (this.inneholder(neste))
				((KjedetMengde<T>) snittM).settInn(neste);
		}
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		differensM.leggTilAlle(this);
		Iterator<T> it = m2.oppramser();
		while(it.hasNext()) {
			T neste = it.next();
			if(differensM.inneholder(neste)) {
				((KjedetMengde<T>) differensM).fjern(neste);
			}
		}
		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		Iterator<T> it = m2.oppramser();
		while(it.hasNext()) {
			if(!this.inneholder(it.next())){
				erUnderMengde = false;
			}				
		}
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}
	
	public String toString() {
		String resultat = "";
		LinearNode<T> aktuell = start;
		while(aktuell != null) {
			resultat +=aktuell.getElement().toString() + "\t";
			aktuell = aktuell.getNeste();
		}
		return resultat;
	}

}// class
