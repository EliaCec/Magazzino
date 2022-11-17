package application;

import controller.Magazzino;
import controller.MagazzinoFactory;
import view.Pannello;

public class Main {
	
	public static void main(final String[] args) {
		final Magazzino mag = MagazzinoFactory.creaMagazzino();
		new Pannello(mag);
	}
	
}
