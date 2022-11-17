package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.Magazzino;

@SuppressWarnings("serial")
public class Pannello {
	
	// costruttore
	public Pannello(Magazzino mag) {
		this.creaInterfacce(new InterfacciaDirigente(mag), new InterfacciaOperaio(mag), new InterfacciaResponasbile(mag));
	}

	private void creaInterfacce(final JFrame... interfacce) {
		for (final JFrame inter : interfacce) {
			inter.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			inter.setVisible(true);
		}
	}
	
}
