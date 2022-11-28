package view;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import java.util.Date;
import controller.Magazzino;
import model.RepartoProdottiFiniti;
import model.RepartoSemilavorati;

@SuppressWarnings("serial")
public class InterfacciaResponsabile extends JFrame {
	
	private final Magazzino mag;

	// costruttore
	public InterfacciaResponsabile(Magazzino mag) {
		this.mag = mag;
		
		this.setTitle("Pannello responsabile");
		this.setLocation(0, 320);
		
		InizializzaInterfaccia();
		
		pack();
	}
	
	
	private void InizializzaInterfaccia() {
		setResizable(false);
		
		// creazione pannello principale
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BorderLayout());
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// crea combobox responsabile e resto dell'interfaccia
		this.inizializzaResponsabili(pannelloPrincipale);
	}
	
	private void inizializzaResponsabili(JPanel pannelloPrincipale) {
		// creo JcomboBox per responsabili
		JComboBox<String> listaResponsabili = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getResponsabiliAttivi().size(); i++) {
			listaResponsabili.addItem(mag.getDirigente().getResponsabiliAttivi().get(i).getNomeCognome());
		}
		
		pannelloPrincipale.add(listaResponsabili, BorderLayout.PAGE_START);		// aggiungo combobox per scelta responsabile al pannello principale
	
		// crea resto dei panel
		this.inizializzaGestionale(pannelloPrincipale, listaResponsabili);
		this.inizializzaDestra(pannelloPrincipale, listaResponsabili);
	}
	
	private void inizializzaGestionale(JPanel pannelloPrincipale, JComboBox<String> listaResponsabili) {
		
		// creazione pannello per il gestionale
		final JPanel pannelloGestionale = new JPanel();
		pannelloGestionale.setBorder(BorderFactory.createTitledBorder("Gestionale"));
		BorderLayout layoutSinistro = new BorderLayout();
		layoutSinistro.setVgap(5);
		pannelloGestionale.setLayout(layoutSinistro);
		
		//creazione pannello per inserimento dei pulsanti 
		final JPanel pannelloPulsanti = new JPanel();
		GridLayout layout = new GridLayout(5, 1);
		layout.setHgap(10);
		layout.setVgap(10);
		pannelloPulsanti.setLayout(layout);
		
		// creazione pannello deposito semilavorati e pannello vendita prodotti finiti
		final JPanel pannelloDepSemilavorati = new JPanel();
		final JPanel pannelloVenditePfiniti = new JPanel();
		
		// creo textArea
		final JTextArea areaStampa = new JTextArea(10, 10);
		areaStampa.setEditable(false);
		
		// ogni volta che cambio responsabile ripulisco l'output
		listaResponsabili.addActionListener(e -> {
			areaStampa.setText("");
		});
		
		// bottone per Lista semilavorati depositati
		final JButton listaSemilavoratiDepositati = new JButton("Semilavorati depositati");
		listaSemilavoratiDepositati.addActionListener(e -> {
			this.mag.getDirigente().getResponsabiliAttivi().get(listaResponsabili.getSelectedIndex())
									 				 .getSemilavoratiDepositati()
									 				 .forEach(n -> areaStampa.append(n.getSemilavorato().getNome() + "\n"));
		});
		
		// bottone per lista prodotti finiti venduti
		final JButton listaProdottiFinitiVenduti = new JButton("Prodotti finiti Venduti");
		listaProdottiFinitiVenduti.addActionListener(e -> {
				this.mag.getDirigente().getResponsabiliAttivi().get(listaResponsabili.getSelectedIndex())
									   .getProdottiVenduti()
									   .forEach(n -> areaStampa.append(n.getProdottoFinito().getNome() + "\n"));
		});		
	
		// creo JcomboBox per lista semilavorati
		JComboBox<String> elencoSemilavorati = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			for(int j = 0; j < mag.getDirigente().getReparti().get(i).getListaRepartiSemilavorati().size(); j++) {
					elencoSemilavorati.addItem(mag.getDirigente().getReparti().get(i)
										  		  .getListaRepartiSemilavorati().get(j).getGiacenzaReparto().getNome());
			}
		}
		
		// bottone per numero semilavorati depositati
		final JButton numSemilavoratiDepositati = new JButton("n° semilavorati depositati");
		numSemilavoratiDepositati.addActionListener(e -> {
			areaStampa.append(String.valueOf(this.mag.getDirigente()
													 .getResponsabiliAttivi()
													 .get(listaResponsabili.getSelectedIndex())
					 								 .depositoPerSemilavorato(elencoSemilavorati.getSelectedItem().toString())) + "\n");
		});
		
		// creo JcomboBox per lista Pfiniti
		JComboBox<String> elencoProdottiFiniti = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			elencoProdottiFiniti.addItem(mag.getDirigente().getReparti().get(i).getGiacenzaReparto().getNome());
		}
		
		// bottone per numero p finiti attuali
		final JButton btnAttualiPfiniti = new JButton("Tot quantità attuale");
		btnAttualiPfiniti.addActionListener(e -> {
			mag.getRepPfinit(elencoProdottiFiniti.getSelectedItem().toString())
																   .scorteAttuali()
																   .forEach(n -> areaStampa.append(n.getNome() + "\n"));
		});
		
		// bottone per numero semilavorati attuali
		final JButton btnAttualiSemi = new JButton("Tot quantità attuale");
		btnAttualiSemi.addActionListener(e -> {
			mag.getRepSemi(elencoSemilavorati.getSelectedItem().toString())
			   												   .scorteAttuali()
			   												   .forEach(n -> areaStampa.append(n.getNome() + "\n"));
		});
	
		// bottone per numero prodotti finiti venduti
		final JButton numProdottiFiniti = new JButton("n° prodotti finiti venduti");
		numProdottiFiniti.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
						  .getResponsabiliAttivi()
						  .get(listaResponsabili.getSelectedIndex())
						  .venditaPerTipologia(elencoProdottiFiniti.getSelectedItem().toString())) + "\n");
		});
	
		// bottone per ripulire textarea
		final JButton pulisci = new JButton("pulisci");
		pulisci.addActionListener(e -> {
				areaStampa.setText("");
		});
	
		pannelloDepSemilavorati.add(elencoSemilavorati);									// aggiunto combobox al pannello Semilavorati
		pannelloDepSemilavorati.add(btnAttualiSemi);										// aggiunto bottone qta totali attuali al pannello semilavorati
		pannelloVenditePfiniti.add(elencoProdottiFiniti);									// aggiunto combobox al pannello prodotti finiti
		pannelloVenditePfiniti.add(btnAttualiPfiniti);										// aggiunto bottone qta totali attuali al pannello prodotti finiti
		pannelloDepSemilavorati.add(numSemilavoratiDepositati);								// aggiungo bottone per numero semilavorati depositati
		pannelloVenditePfiniti.add(numProdottiFiniti);										// aggiungo bottone per numero prodotti finiti venduti
		pannelloPulsanti.add(pannelloVenditePfiniti);										// aggiunto al pannello pulsanti il pannello vendite dei prodotti finiti
		pannelloPulsanti.add(pannelloDepSemilavorati);										// aggiunto al pannello pulsanti il pannello deposito dei semilavorati
		pannelloPulsanti.add(listaSemilavoratiDepositati);									// aggiungo bottone per lista semilavorati depositati
		pannelloPulsanti.add(listaProdottiFinitiVenduti);									// aggiungo bottone per lista prodotti finiti venduti
		pannelloPulsanti.add(pulisci);														// aggiungo bottone per ripulire textarea
		pannelloGestionale.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);		// aggiungo casella di testa al pannello sinistro
		pannelloGestionale.add(pannelloPulsanti,  BorderLayout.PAGE_START);					// aggiungo pannello pulsanti al pannello sinistro
		pannelloPrincipale.add(pannelloGestionale, BorderLayout.LINE_START);				// aggiungo pannello gestione al pannello principale
		this.add(pannelloPrincipale);														// aggiungo pannello al frame	
	}
	
	private void inizializzaDestra(JPanel pannelloPrincipale, JComboBox<String> listaResponsabili) {
		
		// creazione pannello per la costruzione
		final JPanel pannelloDestro = new JPanel();
		pannelloDestro.setLayout(new BorderLayout());
		
		this.inizializzaDeposito(pannelloDestro, listaResponsabili);
		this.inizializzaVendita(pannelloDestro, listaResponsabili);
		
		pannelloPrincipale.add(pannelloDestro, BorderLayout.LINE_END);		// aggiunto pannello gestione al pannello principale
	}

	@SuppressWarnings("deprecation")
	private void inizializzaDeposito(JPanel pannelloDestro, JComboBox<String> listaResponsabili) {
		
		final JPanel pannelloDeposito = new JPanel();
		pannelloDeposito.setBorder(BorderFactory.createTitledBorder("pannello deposito"));
		pannelloDeposito.setPreferredSize(new Dimension(400, 200));
		
		// creo JcomboBox per lista semilavorati
		JComboBox<String> elencoSemilavorati = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			for(int j = 0; j < mag.getDirigente().getReparti().get(i).getListaRepartiSemilavorati().size(); j++) {
					elencoSemilavorati.addItem(mag.getDirigente().getReparti().get(i)
										  		  .getListaRepartiSemilavorati().get(j).getGiacenzaReparto().getNome());
			}
		}
		
		// PANNELLO QUANTITA ---------------------------------
		// creato pannello
		JPanel pannelloQuant = new JPanel();
		// creato testo quantità
		JLabel testo = new JLabel();
		testo.setText("quantità");
		// creo Text area per quantità
		JTextArea testoQuantità = new JTextArea();
		testoQuantità.setPreferredSize(new Dimension(70, 18));
		
		// PANNELLO DATA ---------------------------------
        //creazione pannello data
		JPanel pannelloDataTotale = new JPanel();
		pannelloDataTotale.setLayout(new BorderLayout());
        JPanel pannelloData = new JPanel();
        // acquisizione titolo
        JTextArea minuto = new JTextArea();
        JLabel testoIniziale = new JLabel();
        testoIniziale.setText("Data (hh/mm/dd/MM/yyyy): ");
        // acquisizione ora in input
        JTextArea ora = new JTextArea();
        ora.setPreferredSize(new Dimension(70, 18));
        ora.setText(String.valueOf(7));
        pannelloData.add(ora);
        // acquisizione minuto in input
        JLabel lminuto = new JLabel();
        lminuto.setText(":");     
        minuto.setPreferredSize(new Dimension(70, 18));
        minuto.setText(String.valueOf(0));
        pannelloData.add(lminuto);
        pannelloData.add(minuto);
        // acquisizione giorno in input
        JTextArea giorno = new JTextArea();
        giorno.setPreferredSize(new Dimension(70, 18));
        giorno.setText(String.valueOf(1));
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
        // aggiunto titolo e data al pannelloDataTotale
        pannelloDataTotale.add(testoIniziale, BorderLayout.PAGE_START);
        pannelloDataTotale.add(pannelloData, BorderLayout.PAGE_END);
		
		// bottone per far partire la costruzione
		final JButton deposita = new JButton("deposita");
		deposita.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				RepartoSemilavorati rep = mag.getRepSemi(elencoSemilavorati.getSelectedItem().toString());
				int q = Integer.parseInt(testoQuantità.getText());
				Date data =  new Date(Integer.parseInt(anno.getText()),
			   			  			  Integer.parseInt(mese.getText()),
			   			  			  Integer.parseInt(giorno.getText()),
			   			  			  Integer.parseInt(ora.getText()),
			   			  			  Integer.parseInt(minuto.getText()));
				
				String ris = mag.depositaSemilavorati(rep, listaResponsabili.getSelectedIndex(), q, data);
	
				JOptionPane.showMessageDialog(deposita, ris);
			}	
		});
	
		pannelloDeposito.add(elencoSemilavorati);							// aggiunto combobox dei prodotti finiti al pannello principale
		pannelloQuant.add(testo);											// aggiunto testo a quantità
		pannelloQuant.add(testoQuantità);									// aggiunto text area a quantità
		pannelloDeposito.add(pannelloQuant);								// aggiunto quantità al pannello principale
		pannelloDeposito.add(pannelloDataTotale);							// aggiunto data al pannello principale
		pannelloDeposito.add(deposita);										// aggiunto bottone costruisco al pannello principale
		pannelloDestro.add(pannelloDeposito, BorderLayout.PAGE_START);		// aggiunti depositi al pannello destro
				
	}

	@SuppressWarnings("deprecation")
	private void inizializzaVendita(JPanel pannelloDestro, JComboBox<String> listaResponsabili) {
		
		final JPanel pannelloVendite = new JPanel();
		pannelloVendite.setBorder(BorderFactory.createTitledBorder("pannello vendite"));
		pannelloVendite.setPreferredSize(new Dimension(440, 200));
		
		// creo JcomboBox per lista Pfiniti
		JComboBox<String> elencoProdottiFiniti1 = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			elencoProdottiFiniti1.addItem(mag.getDirigente().getReparti().get(i).getGiacenzaReparto().getNome());
		}
		
		// PANNELLO QUANTIA ---------------------------------
		// creato pannello
		JPanel pannelloQuant = new JPanel();
		// creato testo quantità
		JLabel testo = new JLabel();
		testo.setText("quantità");
		// creo Text area per quantità
		JTextArea testoQuantità = new JTextArea();
		testoQuantità.setPreferredSize(new Dimension(70, 18));
		
		// PANNELLO DATA ---------------------------------
        //creazione pannello data
		JPanel pannelloDataTotale = new JPanel();
		pannelloDataTotale.setLayout(new BorderLayout());
        JPanel pannelloData = new JPanel();
        // acquisizione titolo
        JTextArea minuto = new JTextArea();
        JLabel testoIniziale = new JLabel();
        testoIniziale.setText("Data (mm/hh/dd/MM/yyyy): ");
        // acquisizione ora in input
        JTextArea ora = new JTextArea();
        ora.setPreferredSize(new Dimension(70, 18));
        ora.setText(String.valueOf(7));
        pannelloData.add(ora);
        // acquisizione minuto in input
        JLabel lora = new JLabel();
        lora.setText("/");
        minuto.setPreferredSize(new Dimension(70, 18));
        minuto.setText(String.valueOf(0));
        pannelloData.add(lora);
        pannelloData.add(minuto);  
        // acquisizione giorno in input
        JTextArea giorno = new JTextArea();
        giorno.setPreferredSize(new Dimension(70, 18));
        giorno.setText(String.valueOf(1));
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
        // aggiunto titolo e data al pannelloDataTotale
        pannelloDataTotale.add(testoIniziale, BorderLayout.PAGE_START);
        pannelloDataTotale.add(pannelloData, BorderLayout.PAGE_END);
		
		// bottone per far partire la costruzione
		final JButton vendi = new JButton("vendi");
		vendi.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				RepartoProdottiFiniti rep = mag.getRepPfinit(elencoProdottiFiniti1.getSelectedItem().toString());
				int q = Integer.parseInt(testoQuantità.getText());
				Date data =  new Date(Integer.parseInt(anno.getText()),
			   			  			  Integer.parseInt(mese.getText()),
			   			  			  	Integer.parseInt(giorno.getText()),
			   			  			  	Integer.parseInt(ora.getText()),
			   			  			  	Integer.parseInt(minuto.getText()));
				
				String ris = mag.vendiProdotti(rep, listaResponsabili.getSelectedIndex(), q, data);
	
				JOptionPane.showMessageDialog(vendi, ris);
			}	
		});
	
		pannelloVendite.add(elencoProdottiFiniti1);							// aggiunto combobox dei prodotti finiti al pannello principale
		pannelloQuant.add(testo);											// aggiunto testo a quantità
		pannelloQuant.add(testoQuantità);									// aggiunto text area a quantità
		pannelloVendite.add(pannelloQuant);									// aggiunto quantità al pannello principale
		pannelloVendite.add(pannelloDataTotale);							// aggiunto data al pannello principale
		pannelloVendite.add(vendi);											// aggiunto bottone costruisco al pannello principale
		pannelloDestro.add(pannelloVendite, BorderLayout.PAGE_END);			// aggiunte vendite al pannello destro
	}
	
	// metodo che permette il refresh edll'intera interfaccia grafica dei responsabili
	public InterfacciaResponsabile aggiorna(InterfacciaResponsabile i) {
		i.dispose();
		InterfacciaResponsabile iNuova = new InterfacciaResponsabile(mag);
		iNuova.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iNuova.setVisible(true);
		return iNuova;
	}
	
}
