package view;

import java.awt.Dimension;
import java.text.DateFormat;
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
		this.creaPannelloBottoni(pannelloPrincipale);
		// creazione casella di testo output
		JTextArea res = new JTextArea();
		res.setPreferredSize(new Dimension(400, 100));
		pannelloPrincipale.add(res);
		this.add(pannelloPrincipale);
	}
	
	// metodo che genera il pannello dello storico dei semilavorati usati in un giorno
	@SuppressWarnings("deprecation")
	private void creaPannelloBottoni(JPanel pp) {
		JPanel pannelloBot = new JPanel();
		pannelloBot.setBorder(BorderFactory.createTitledBorder("Storici giornalieri"));
		
		// acquisizione giorno in input
		JFormattedTextField inputData = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		inputData.setValue(new Date(2022, 0, 1));
		inputData.setPreferredSize(new Dimension(70, 30));
		pannelloBot.add(inputData);
		// creazione bottone per storico giornaliero semilavorati utilizzati
		JButton btnStoricoSemi = new JButton("Ricerca semilavorati utilizzati");
		btnStoricoSemi.addActionListener(e -> mag.getDirigente().storicoSemilavoratiUsatiGiornaliero((Date)inputData.getValue()));
		pannelloBot.add(btnStoricoSemi);
		// creazione bottone per storico giornaliero vendite effettuate
		JButton btnStoricoVendite = new JButton("Ricerca vendite effettuate");
		btnStoricoVendite.addActionListener(e -> mag.getDirigente().storicoSemilavoratiUsatiGiornaliero((Date)inputData.getValue()));
		pannelloBot.add(btnStoricoVendite);
		// creazione bottone per storico giornaliero semilavorati utilizzati
		JButton btnStoricoCostr = new JButton("Ricerca costruzioni effettuate");
		btnStoricoCostr.addActionListener(e -> mag.getDirigente().storicoSemilavoratiUsatiGiornaliero((Date)inputData.getValue()));
		pannelloBot.add(btnStoricoCostr);
		pp.add(pannelloBot);
	}
	
}
