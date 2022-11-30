package view;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.Magazzino;
import model.Operaio;
import model.Responsabile;
import model.classi.OperaioImpl;
import model.classi.ResponsabileImpl;

@SuppressWarnings("serial")
public class InterfacciaDirigente extends JFrame {
	
	private Magazzino mag;						// controller
	private InterfacciaOperaio intOper;			// interfaccia operaio
	private InterfacciaResponsabile intResp;	// interfaccia responsabile 
	
	// costruttore
	public InterfacciaDirigente(Magazzino mag, InterfacciaOperaio i, InterfacciaResponsabile r) {
		this.mag = mag;
		this.intOper = i;
		this.intResp = r;
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
		
		this.creaPannelloSinistra(pannelloPrincipale);		// creazione pannelloSinistra
		this.creaPannelloDestra(pannelloPrincipale);		// creazione pannelloDestra
		
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
		pp.add(pannelloSx, BorderLayout.LINE_START);		// aggiunto pannello degli storici al pannello principale
	}
	
	// metodo che crea il pannello dei bottoni per gli storici giornalieri
	private void creaPannelloDestra(JPanel pp) {
		// creazione pannello destro
		JPanel pannelloDx = new JPanel();
		pannelloDx.setLayout(new BorderLayout());
		
		this.creaPannelloCambiTurni(pannelloDx);
		this.creaPannelloAssunzioni(pannelloDx);
		pp.add(pannelloDx);									// aggiunto pannello dei cambi turni al pannello principale
	}
	
	private void creaPannelloAssunzioni(JPanel pD) {
		JPanel pannelloAssunzioni = new JPanel();
		pannelloAssunzioni.setBorder(BorderFactory.createTitledBorder("Assunzioni dipendenti"));
		// etichetta
		JLabel nDip = new JLabel("Nome dipendente: ");
		// campo di testo per inserire il nome
		JTextArea nomeDipendente = new JTextArea();
		nomeDipendente.setPreferredSize(new Dimension(250, 18));
		// menu a tendina per scegliere il tipo di dipendente
		JComboBox<String> tipoDipendente = new JComboBox<>();
		tipoDipendente.addItem("Operaio");
		tipoDipendente.addItem("Responsabile");
		// bottone per effettuare l'assunzione
		JButton btnAssunzioni = new JButton("Assumi");
		btnAssunzioni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipoDipendente.getSelectedItem().toString().equals("Operaio")) {
					mag.assumiDipendente(new OperaioImpl(nomeDipendente.getText()));
					JOptionPane.showMessageDialog(btnAssunzioni, "L'operaio " + nomeDipendente.getText() + " è stato aggiunto con successo");
				} else {
					mag.assumiDipendente(new ResponsabileImpl(nomeDipendente.getText()));
					JOptionPane.showMessageDialog(btnAssunzioni, "Il responsabile " + nomeDipendente.getText() + " è stato aggiunto con successo");
				}
				aggiornaDirigente();
			}	
		});
		pannelloAssunzioni.add(nDip);
		pannelloAssunzioni.add(nomeDipendente);
		pannelloAssunzioni.add(tipoDipendente);
		pannelloAssunzioni.add(btnAssunzioni);
		pD.add(pannelloAssunzioni, BorderLayout.PAGE_END);
	}
	
	// metodo che crea pannello per cambi turno
	private void creaPannelloCambiTurni(JPanel pD) {
		JPanel pannelloTurni = new JPanel();
		pannelloTurni.setLayout(new BorderLayout());
		pannelloTurni.setBorder(BorderFactory.createTitledBorder("Cambi turni"));
		JPanel pannelloDipendenti = new JPanel();
		pannelloDipendenti.setLayout(new BorderLayout());
		JPanel pannelloOperai = new JPanel();
		pannelloOperai.setBorder(BorderFactory.createTitledBorder("Operai assunti"));
		JPanel pannelloResponsabili = new JPanel();
		pannelloResponsabili.setBorder(BorderFactory.createTitledBorder("Responsabili assunti"));
		// PANNELLO CAMBI TURNO ----------------------------------------------------------------------------------------------
		List<JCheckBox> listaBoxOperai = new LinkedList<>();
		List<JCheckBox> listaBoxResponsabili = new LinkedList<>();
		// checkbox per operai
		for (int i = 0; i < this.mag.getDirigente().getOperaiAssunti().size(); i++) {
			JCheckBox operaio = new JCheckBox(this.mag.getDirigente().getOperaiAssunti().get(i).getNomeCognome());
			listaBoxOperai.add(operaio);
			pannelloOperai.add(operaio);
		}
		// checkbox per responsabili
		for (int i = 0; i < this.mag.getDirigente().getResponsabiliAssunti().size(); i++) {
			JCheckBox responsabile = new JCheckBox(this.mag.getDirigente().getResponsabiliAssunti().get(i).getNomeCognome());
			listaBoxResponsabili.add(responsabile);
			pannelloResponsabili.add(responsabile);
		}
		pannelloDipendenti.add(pannelloOperai, BorderLayout.PAGE_START);
		pannelloDipendenti.add(pannelloResponsabili, BorderLayout.PAGE_END);
		pannelloTurni.add(pannelloDipendenti, BorderLayout.PAGE_START);
		
		List<Operaio> nuoviOperai = new LinkedList<>();
		List<Responsabile> nuoviResposanbili = new LinkedList<>();
		// listener modifica di eventi delle checkbox degli operai
		for (JCheckBox j : listaBoxOperai) {
			j.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int state = e.getStateChange();
					if (state == ItemEvent.SELECTED) {
						nuoviOperai.add((Operaio)mag.getDirigente().cercaDipendentePerNome(j.getText(), mag.getDirigente().getOperaiAssunti()));
					} else {
						nuoviOperai.remove((Operaio)mag.getDirigente().cercaDipendentePerNome(j.getText(), mag.getDirigente().getOperaiAssunti()));
					}
				}
			});
		}
		// listener modifica di eventi delle checkbox dei responsabili
		for (JCheckBox j : listaBoxResponsabili) {
			j.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int state = e.getStateChange();
					if (state == ItemEvent.SELECTED) {
						nuoviResposanbili.add((Responsabile)mag.getDirigente().cercaDipendentePerNome(j.getText(), mag.getDirigente().getResponsabiliAssunti()));
					} else {
						nuoviResposanbili.remove((Responsabile)mag.getDirigente().cercaDipendentePerNome(j.getText(), mag.getDirigente().getResponsabiliAssunti()));
					}
				}
			});
		}
		
		// PANNELLO DATA ------------------------------------------------------------------------------------------------------
        //creazione pannello data
        JPanel pannelloData = new JPanel();
        // acquisizione ora in input
  		JTextArea ora = new JTextArea();
  		JLabel lOra = new JLabel();
        lOra.setText("Data (hh:mm dd/MM/yyyy): ");
        ora.setPreferredSize(new Dimension(70, 18));
        ora.setText(String.valueOf(7));
        pannelloData.add(lOra);
        pannelloData.add(ora);
        // acquisizione minuti in input
        JTextArea minuti = new JTextArea();
        JLabel lMinuti = new JLabel();
        lMinuti.setText(":");
        minuti.setPreferredSize(new Dimension(70, 18));
        minuti.setText(String.valueOf(0));
        pannelloData.add(lMinuti);
        pannelloData.add(minuti);
        // acquisizione giorno in input
        JTextArea giorno = new JTextArea();
        JLabel lGiorno = new JLabel();
        lGiorno.setText("");
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
		pannelloTurni.add(pannelloData, BorderLayout.CENTER);
		// creazione bottone per effettuare cambio turno
		JButton btnCambioTurno = new JButton("Cambia turno");
		btnCambioTurno.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(nuoviOperai.size() > 0) || !(nuoviResposanbili.size() > 0)) {
					JOptionPane.showMessageDialog(btnCambioTurno, "Errore, selezionare almeno un dipendente e un responsabile");
				} else {
					String ris = mag.cambioTurno(nuoviOperai, nuoviResposanbili, new Date(Integer.parseInt(anno.getText()),
						   												 			  Integer.parseInt(mese.getText()),
						   												 			  Integer.parseInt(giorno.getText()),
						   												 			  Integer.parseInt(ora.getText()),
						   												 			  Integer.parseInt(minuti.getText())));
					intOper = intOper.aggiorna(intOper);
					intResp = intResp.aggiorna(intResp);
					JOptionPane.showMessageDialog(btnCambioTurno, ris);
				}
			}	
		});
		
		pannelloTurni.add(btnCambioTurno, BorderLayout.PAGE_END);
		pD.add(pannelloTurni, BorderLayout.PAGE_START);
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
			this.mag.getDirigente().storicoSemilavoratiUsatiGiornaliero(new Date(Integer.parseInt(anno.getText()),
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
	
	// metodo che permette il refresh dell'intera interfaccia grafica del dirigente
	private void aggiornaDirigente() {
		this.dispose();
		InterfacciaDirigente iNuova = new InterfacciaDirigente(mag, this.intOper, this.intResp);
		iNuova.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iNuova.setVisible(true);
	}
}
