package view;

import java.awt.Dimension;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.Magazzino;

@SuppressWarnings("serial")
public class InterfacciaDirigente extends JFrame {
	
	private Magazzino mag;
	
	// costruttore
	public InterfacciaDirigente(Magazzino mag) {
		this.mag = mag;
		this.setTitle("Dirigente");
		this.setLocation(10,100);
		this.inizializzaInterfaccia();
		pack();
	}

	private void inizializzaInterfaccia() {
		setResizable(false);
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BoxLayout(pannelloPrincipale, BoxLayout.Y_AXIS));
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		// creazione casella di testo output
		JTextArea res = new JTextArea();
		// creazione bottoni
		this.creaPannelloBottoni(pannelloPrincipale, res);
		res.setPreferredSize(new Dimension(400, 100));
		pannelloPrincipale.add(new JScrollPane(res));
		this.add(pannelloPrincipale);
	}
	
	// metodo che genera il pannello dello storico dei semilavorati usati in un giorno
	@SuppressWarnings("deprecation")
	private void creaPannelloBottoni(JPanel pp, JTextArea res) {
		JPanel pannelloBot = new JPanel();
		pannelloBot.setBorder(BorderFactory.createTitledBorder("Storici giornalieri"));
		
		// acquisizione anno in input
		JTextArea anno = new JTextArea();
		anno.setPreferredSize(new Dimension(70, 30));
		anno.setText(String.valueOf(2022));
		pannelloBot.add(anno);
		// acquisizione mese in input
		JTextArea mese = new JTextArea();
		mese.setPreferredSize(new Dimension(70, 30));
		mese.setText(String.valueOf(1));
		pannelloBot.add(mese);
		// acquisizione giorno in input
		JTextArea giorno = new JTextArea();
		giorno.setPreferredSize(new Dimension(70, 30));
		giorno.setText(String.valueOf(1));
		pannelloBot.add(giorno);
		
		// creazione bottone per storico giornaliero semilavorati utilizzati
		JButton btnStoricoSemi = new JButton("Ricerca semilavorati utilizzati");
		btnStoricoSemi.addActionListener(e -> {
			//this.mag.getDirigente().storicoSemilavoratiUsatiGiornaliero(new Date(2022, 10, 11)).forEach(n -> res.append("ciao" + n.getSemilavorato().getNome() + "\n"));
			//mag.getDirigente().storicoSemilavoratiUsatiGiornaliero(new Date(Integer.parseInt(anno.getText()),Integer.parseInt(mese.getText()),Integer.parseInt(giorno.getText()))).forEach(n -> res.append(n.getSemilavorato().getNome() + "\n"));
			res.append(String.valueOf(this.mag.getDirigente().storicoSemilavoratiUsatiGiornaliero(new Date(2022, 10, 11)).size()));
		});
		pannelloBot.add(btnStoricoSemi);
		
	// creazione bottone per storico giornaliero vendite effettuate
	JButton btnStoricoVendite = new JButton("Ricerca vendite effettuate");
	btnStoricoVendite.addActionListener(e -> {
		res.append(String.valueOf(this.mag.getDirigente().storicoVenditeGiornaliero(new Date(2022, 10, 11)).size()));
	});
	pannelloBot.add(btnStoricoVendite);
	
	// creazione bottone per storico giornaliero costruzioni effettuate
	JButton btnStoricoCostr = new JButton("Ricerca costruzioni effettuate");
	btnStoricoCostr.addActionListener(e -> {
		res.append(String.valueOf(this.mag.getDirigente().storicoCostruzioniGiornaliero(new Date(2022, 10, 11)).size()));
	});
	pannelloBot.add(btnStoricoCostr);
	pp.add(pannelloBot);
	}
	
}
