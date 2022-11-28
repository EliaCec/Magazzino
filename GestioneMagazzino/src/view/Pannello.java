package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.Magazzino;

public class Pannello {
	
	// costruttore
	public Pannello(Magazzino mag) {
		InterfacciaOperaio i = new InterfacciaOperaio(mag);
		InterfacciaResponsabile r = new InterfacciaResponsabile(mag);
		this.creaInterfacce(new InterfacciaDirigente(mag, i, r), i, r);
	}

	private void creaInterfacce(final JFrame... interfacce) {
		for (final JFrame inter : interfacce) {
			inter.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			inter.setVisible(true);
		}
	}
	
}
