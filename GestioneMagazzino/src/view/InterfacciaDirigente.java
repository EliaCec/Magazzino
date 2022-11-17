package view;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.Magazzino;

@SuppressWarnings("serial")
public class InterfacciaDirigente extends JFrame {
	
	private Magazzino mag;
	
	// costruttore
	public InterfacciaDirigente(Magazzino mag) {
		this.mag = mag;
		setTitle("Dirigente");
		this.inizializzaInterfaccia();
		pack();
	}

	private void inizializzaInterfaccia() {
		setResizable(false);
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BoxLayout(pannelloPrincipale, BoxLayout.Y_AXIS));
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.creaPannelloStoricoVendite(pannelloPrincipale);
		this.creaPannelloStoricoCostruzioni(pannelloPrincipale);
		this.creaPannelloStoricoSemilavoratiUsati(pannelloPrincipale);
		this.add(pannelloPrincipale);
	}
	
	// metodo che genera il pannello dello storico dei semilavorati usati in un giorno
	private void creaPannelloStoricoSemilavoratiUsati(JPanel pp) {
		JPanel pannelloSemi = new JPanel();
		pannelloSemi.setBorder(BorderFactory.createTitledBorder("Storico semilavorati giornalieri"));
		// creazione casella di testo output
		JTextArea res = new JTextArea();
		res.setPreferredSize(new Dimension(400, 100));
		pannelloSemi.add(res);
		// acquisizione giorno in input
		JFormattedTextField inputData = new JFormattedTextField(new SimpleDateFormat("mm-dd-yyyy"));
		pannelloSemi.add(inputData);
		// creazione bottone per ricerca
		JButton btn = new JButton("Ricerca");
		btn.addActionListener(e -> mag.getDirigente().storicoSemilavoratiUsatiGiornaliero((Date) inputData.getValue()));
		pannelloSemi.add(btn);
		pp.add(pannelloSemi);
	}

	private void creaPannelloStoricoCostruzioni(JPanel pp) {
		// TODO Auto-generated method stub
	}

	private void creaPannelloStoricoVendite(JPanel pp) {
		// TODO Auto-generated method stub
		
	}
	
}
