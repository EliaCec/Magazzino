package view;

import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import controller.Magazzino;

@SuppressWarnings("serial")
public class InterfacciaDirigente extends JFrame {
	
	private Magazzino mag;

	// costruttore
	public InterfacciaDirigente(Magazzino mag) {
		this.mag = mag;
		this.setTitle("Pannello dirigente");
		this.setLocation(100, 0);
		this.inizializzaInterfaccia();
		pack();
	}

	// metodo che inizializza l'interfaccia grafica
	private void inizializzaInterfaccia() {
		setResizable(false);
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BorderLayout());
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.creaPannelloSinistra(pannelloPrincipale);	// creazione pannelloSinistra
		this.creaPannelloDestra(pannelloPrincipale);	// creazione pannelloDestra
		
		this.add(pannelloPrincipale);
	}
	
	// metodo che crea il pannello del Gestionale
	private void creaPannelloSinistra(JPanel pp) {
		// creazione pannello sinistro
		JPanel pannelloSx = new JPanel();
		BorderLayout l = new BorderLayout();
		l.setVgap(8);
		pannelloSx.setBorder(BorderFactory.createTitledBorder("Gestionale"));
		pannelloSx.setLayout(l);
		this.creaPannelloBottoni(pannelloSx);
		pp.add(pannelloSx, BorderLayout.LINE_START);
	}
	
	// metodo che crea il pannello dei bottoni per gli storici giornalieri
	private void creaPannelloDestra(JPanel pp) {
		// creazione pannello destro
		JPanel pannelloDx = new JPanel();
		pannelloDx.setLayout(new BorderLayout());
		pannelloDx.setBorder(BorderFactory.createTitledBorder("Cambi turni"));
		this.creaPannelloCambiTurni(pannelloDx);
		pp.add(pannelloDx);
	}
	
	// metodo che crea pannello per cambi turno
	private void creaPannelloCambiTurni(JPanel pD) {
		JPanel pannelloTurni = new JPanel();
		pannelloTurni.setLayout(new BorderLayout());
		JPanel pannelloOperai = new JPanel();
		pannelloOperai.setBorder(BorderFactory.createTitledBorder("Operai assunti"));
		JPanel pannelloResponsabili = new JPanel();
		pannelloResponsabili.setBorder(BorderFactory.createTitledBorder("Responsabili assunti"));
		// PANNELLO CAMBI TURNO ----------------------------------------------------------------------------------------------
		// checkbox per operai
		for (int i = 0; i < mag.getDirigente().getOperaiAssunti().size(); i++) {
			JCheckBox operaio = new JCheckBox(mag.getDirigente().getOperaiAssunti().get(i).getNomeCognome());
			pannelloOperai.add(operaio);
		}
		// checkbox per responsabili
		for (int i = 0; i < mag.getDirigente().getResponsabiliAssunti().size(); i++) {
			JCheckBox responsabile = new JCheckBox(mag.getDirigente().getResponsabiliAssunti().get(i).getNomeCognome());
			pannelloResponsabili.add(responsabile);
		}
		pannelloTurni.add(pannelloOperai, BorderLayout.PAGE_START);
		pannelloTurni.add(pannelloResponsabili, BorderLayout.PAGE_END);
		pD.add(pannelloTurni, BorderLayout.PAGE_START);
		
		// PANNELLO DATA ------------------------------------------------------------------------------------------------------
        //creazione pannello data
        JPanel pannelloData = new JPanel();
        // acquisizione giorno in input
        JTextArea giorno = new JTextArea();
        JLabel lGiorno = new JLabel();
        lGiorno.setText("Data (dd/MM/yyyy): ");
        giorno.setPreferredSize(new Dimension(70, 18));
        giorno.setText(String.valueOf(1));
        pannelloData.add(lGiorno);
        pannelloData.add(giorno);
        // acquisizione mese in input
        JTextArea mese = new JTextArea();
        JLabel lMese = new JLabel();
        lMese.setText("/");
        mese.setPreferredSize(new Dimension(70, 18));
        mese.setText(String.valueOf(1));
        pannelloData.add(lMese);
        pannelloData.add(mese);
        // acquisizione anno in input
        JTextArea anno = new JTextArea();
        JLabel lAnno = new JLabel();
        lAnno.setText("/");
        anno.setPreferredSize(new Dimension(70, 18));
        anno.setText(String.valueOf(2022));
        pannelloData.add(lAnno);
        pannelloData.add(anno);
		pD.add(pannelloData, BorderLayout.CENTER);
		// creazione bottone per effettuare cambio turno
		JButton btnCambioTurno = new JButton("Cambia turno");
		pD.add(btnCambioTurno, BorderLayout.PAGE_END);
	}
	
	// metodo che crea il pannello dei bottoni per gli storici giornalieri
	@SuppressWarnings("deprecation")
	private void creaPannelloBottoni(JPanel pS) {
		//creazione pannello bottoni
		JPanel pannelloBot = new JPanel();
		// creazione casella di testo output
		JTextArea ris = new JTextArea(10, 10);
		ris.setEditable(false);
		
		// PANNELLO DATA ---------------------------------
		//creazione pannello data
		JPanel pannelloData = new JPanel();
		// acquisizione giorno in input
		JTextArea giorno = new JTextArea();
		JLabel lGiorno = new JLabel();
		lGiorno.setText("Data (dd/MM/yyyy): ");
		giorno.setPreferredSize(new Dimension(70, 18));
		giorno.setText(String.valueOf(1));
		pannelloData.add(lGiorno);
		pannelloData.add(giorno);
		// acquisizione mese in input
		JTextArea mese = new JTextArea();
		JLabel lMese = new JLabel();
		lMese.setText("/");
		mese.setPreferredSize(new Dimension(70, 18));
		mese.setText(String.valueOf(1));
		pannelloData.add(lMese);
		pannelloData.add(mese);
		// acquisizione anno in input
		JTextArea anno = new JTextArea();
		JLabel lAnno = new JLabel();
		lAnno.setText("/");
		anno.setPreferredSize(new Dimension(70, 18));
		anno.setText(String.valueOf(2022));
		pannelloData.add(lAnno);
		pannelloData.add(anno);
		
		// PANNELLO BOTTONI ------------------------------
		// creazione bottone per storico giornaliero semilavorati utilizzati
		JButton btnStoricoSemi = new JButton("Ricerca semilavorati utilizzati");
		btnStoricoSemi.addActionListener(e -> {
			mag.getDirigente().storicoSemilavoratiUsatiGiornaliero(new Date(Integer.parseInt(anno.getText()),
																			Integer.parseInt(mese.getText()),
																			Integer.parseInt(giorno.getText())))
																			.forEach(n -> ris.append(n.getSemilavorato().getNome() + "\n"));
		});
		pannelloBot.add(btnStoricoSemi);
		// creazione bottone per storico giornaliero vendite effettuate
		JButton btnStoricoVendite = new JButton("Ricerca vendite effettuate");
		btnStoricoVendite.addActionListener(e -> {
			this.mag.getDirigente().storicoVenditeGiornaliero(new Date(Integer.parseInt(anno.getText()),
																	   Integer.parseInt(mese.getText()),
																	   Integer.parseInt(giorno.getText())))
																	   .forEach(n -> ris.append(n.getProdottoFinito().getNome() + "\n"));
		});
		pannelloBot.add(btnStoricoVendite);
		// creazione bottone per storico giornaliero costruzioni effettuate
		JButton btnStoricoCostr = new JButton("Ricerca costruzioni effettuate");
		btnStoricoCostr.addActionListener(e -> {
			this.mag.getDirigente().storicoCostruzioniGiornaliero(new Date(Integer.parseInt(anno.getText()),
																		   Integer.parseInt(mese.getText()),
																		   Integer.parseInt(giorno.getText())))
																	   	   .forEach(n -> ris.append(n.getProdotto().getNome() + "\n"));
		});
		pannelloBot.add(btnStoricoCostr);
		
		// creazione bottone per cancellare il contenuto dell'output
		JButton btnPulisci = new JButton("Pulisci");
		btnPulisci.addActionListener(e -> {
			ris.setText("");
		});
		pannelloBot.add(btnPulisci);
		
		pS.add(pannelloData, BorderLayout.PAGE_START);
		pS.add(pannelloBot, BorderLayout.CENTER);
		pS.add(new JScrollPane(ris), BorderLayout.PAGE_END);
	}
	
}
