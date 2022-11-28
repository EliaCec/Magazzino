package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import java.util.Date;

import controller.Magazzino;
import model.RepartoProdottiFiniti;

@SuppressWarnings("serial")
public class InterfacciaOperaio extends JFrame {
	
	private final Magazzino mag;
	// costruttore
	public InterfacciaOperaio(Magazzino mag) {
		this.mag = mag;
		
		this.setTitle("Pannello operaio");
		this.setLocation(450, 300);
		
		InizializzaInterfaccia();
		
		pack();
	}
		
	private void InizializzaInterfaccia() {
		setResizable(false);
		
		// creazione pannello principale
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BorderLayout());
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// crea combobox operaio e resto dell'interfaccia
		this.inizializzaOperai(pannelloPrincipale);
	}
	
	private void inizializzaOperai(JPanel pannelloPrincipale) {
		// creo JcomboBox per operai
		JComboBox<String> listaOperai = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getOperaiAttivi().size(); i++) {
			listaOperai.addItem(mag.getDirigente().getOperaiAttivi().get(i).getNomeCognome());
		}
		
		pannelloPrincipale.add(listaOperai, BorderLayout.PAGE_START);		// aggiungo combobox per scelta operaio al pannello principale

		// crea resto dei panel
		this.inizializzaGestionale(pannelloPrincipale, listaOperai);
		this.inizializzaCostruzione(pannelloPrincipale, listaOperai);
	}
	
	private void inizializzaGestionale(JPanel pannelloPrincipale, JComboBox<String> listaOperai) {
		
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
		
		// creazione pannello semilavorati e pannello prodotti finiti
		final JPanel pannelloSemilavorati = new JPanel();
		final JPanel pannelloPfiniti = new JPanel();
		
		// creo textArea
		final JTextArea areaStampa = new JTextArea(10, 10);
		areaStampa.setEditable(false);
		
		// ogni volta che cambio operaio ripulisco l'output
		listaOperai.addActionListener(e -> {
			areaStampa.setText("");
		});
		
		// bottone per Lista semilavorati usati
		final JButton listaSemilavorati = new JButton("Semilavorati usati");
		listaSemilavorati.addActionListener(e -> {
			this.mag.getDirigente().getOperaiAttivi().get(listaOperai.getSelectedIndex())
									 				 .getSemilavoratiPrelevati()
									 				 .forEach(n -> areaStampa.append(n.getSemilavorato().getNome() + "\n"));
		});
		
		// bottone per lista prodotti finiti costruiti
		final JButton listaProdottiFiniti = new JButton("Prodotti finiti costruiti");
		listaProdottiFiniti.addActionListener(e -> {
				this.mag.getDirigente().getOperaiAttivi().get(listaOperai.getSelectedIndex())
									   .getProdottiCostruiti()
									   .forEach(n -> areaStampa.append(n.getProdotto().getNome() + "\n"));
		});		

		// creo JcomboBox per lista semilavorati
		JComboBox<String> elencoSemilavorati = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			for(int j = 0; j < mag.getDirigente().getReparti().get(i).getListaRepartiSemilavorati().size(); j++) {
					elencoSemilavorati.addItem(mag.getDirigente().getReparti().get(i)
										  		  .getListaRepartiSemilavorati().get(j).getGiacenzaReparto().getNome());
			}
		}
		
		// bottone per numero semilavorati usati
		final JButton numSemilavorati = new JButton("n� semilavorati usati");
		numSemilavorati.addActionListener(e -> {
			areaStampa.append(String.valueOf(this.mag.getDirigente()
													 .getOperaiAttivi()
													 .get(listaOperai.getSelectedIndex())
					 								 .prelievoPerSemilavorato(elencoSemilavorati.getSelectedItem().toString())) + "\n");
		});
		
		// creo JcomboBox per lista Pfiniti
		JComboBox<String> elencoProdottiFiniti = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			elencoProdottiFiniti.addItem(mag.getDirigente().getReparti().get(i).getGiacenzaReparto().getNome());
		}

		// bottone per numero p finiti attuali
		final JButton btnAttualiPfiniti = new JButton("Tot quantit� attuale");
		btnAttualiPfiniti.addActionListener(e -> {
			mag.getRepPfinit(elencoProdottiFiniti.getSelectedItem().toString())
																   .scorteAttuali()
																   .forEach(n -> areaStampa.append(n.getNome() + "\n"));
		});
		
		// bottone per numero semilavorati attuali
		final JButton btnAttualiSemi = new JButton("Tot quantit� attuale");
		btnAttualiSemi.addActionListener(e -> {
			mag.getRepSemi(elencoSemilavorati.getSelectedItem().toString())
			   												   .scorteAttuali()
			   												   .forEach(n -> areaStampa.append(n.getNome() + "\n"));
		});
		
		// bottone per numero prodotti finiti costruiti
		final JButton numProdottiFiniti = new JButton("n� prodotti finiti costruiti");
		numProdottiFiniti.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
						  .getOperaiAttivi()
						  .get(listaOperai.getSelectedIndex())
						  .costruzioniPerProdottoFinito(elencoProdottiFiniti.getSelectedItem().toString())) + "\n");
		});

		// bottone per numero prodotti finiti costruibili
		final JButton numProdFinitiCostruibili = new JButton("n� prodotti finiti costruibili");
		numProdFinitiCostruibili.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
					  .getOperaiAttivi()
					  .get(listaOperai.getSelectedIndex())
					  .calcoloProdottiFinitiCostruibili(mag.getRepPfinit(elencoProdottiFiniti.getSelectedItem().toString()), 0)) + "\n");
		});
		
		// bottone per ripulire textarea
		final JButton pulisci = new JButton("pulisci");
		pulisci.addActionListener(e -> {
				areaStampa.setText("");
		});

		pannelloSemilavorati.add(elencoSemilavorati);										// aggiunto combobox al pannello Semilavorati
		pannelloSemilavorati.add(btnAttualiSemi);											// aggiunto bottone qta totali al pannello semilavorati
		pannelloPfiniti.add(elencoProdottiFiniti);											// aggiunto combobox al pannello prodotti finiti
		pannelloPfiniti.add(btnAttualiPfiniti);												// aggiunto bottone qta totali al pannello prodotti finiti
		pannelloSemilavorati.add(numSemilavorati);											// aggiungo bottone per numero semilavorati usati
		pannelloPfiniti.add(numProdottiFiniti);												// aggiungo bottone per numero prodotti finiti costruiti
		pannelloPfiniti.add(numProdFinitiCostruibili);										// aggiungo bottone per numero prodotti finiti costruibili
		pannelloPulsanti.add(pannelloPfiniti);												// aggiunto al pannello pulsanti il pannello prodotti finiti
		pannelloPulsanti.add(pannelloSemilavorati);											// aggiunto al pannello pulsanti il pannello semilavorati
		pannelloPulsanti.add(listaSemilavorati);											// aggiungo bottone per Lista semilavorati usati
		pannelloPulsanti.add(listaProdottiFiniti);											// aggiungo bottone per lista prodotti finiti costruiti
		pannelloPulsanti.add(pulisci);														// aggiungo bottone per ripulire textarea
		pannelloGestionale.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);		// aggiungo casella di testa al pannello sinistro
		pannelloGestionale.add(pannelloPulsanti,  BorderLayout.PAGE_START);					// aggiungo pannello pulsanti al pannello sinistro
		pannelloPrincipale.add(pannelloGestionale, BorderLayout.LINE_START);				// aggiungo pannello gestione al pannello principale
		this.add(pannelloPrincipale);														// aggiungo pannello al frame	
	}
	
	@SuppressWarnings("deprecation")
	private void inizializzaCostruzione(JPanel pannelloPrincipale, JComboBox<String> listaOperai) {
		
		// creazione pannello per la costruzione
		final JPanel pannelloCostruzione = new JPanel();
		pannelloCostruzione.setPreferredSize(new Dimension(440, 100));
		pannelloCostruzione.setBorder(BorderFactory.createTitledBorder("pannello costruzioni"));
		
		// creo JcomboBox per lista Pfiniti
		JComboBox<String> elencoProdottiFiniti1 = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			elencoProdottiFiniti1.addItem(mag.getDirigente().getReparti().get(i).getGiacenzaReparto().getNome());
		}
		
		// PANNELLO QUANTIA ---------------------------------
		// creato pannello
		JPanel pannelloQuant = new JPanel();
		// creato testo quantit�
		JLabel testo = new JLabel();
		testo.setText("quantit�");
		// creo Text area per quantit�
		JTextArea testoQuantit� = new JTextArea();
		testoQuantit�.setPreferredSize(new Dimension(70, 18));
		
		// PANNELLO DATA ---------------------------------
        //creazione pannello data
		JPanel pannelloDataTotale = new JPanel();
		pannelloDataTotale.setLayout(new BorderLayout());
        JPanel pannelloData = new JPanel();
        // acquisizione titolo
        JTextArea minuto = new JTextArea();
        JLabel testoIniziale = new JLabel();
        testoIniziale.setText("Data (hh/mm/dd/MM/yyyy): ");
        // acquisizione minuto in input
        minuto.setPreferredSize(new Dimension(70, 18));
        minuto.setText(String.valueOf(0));
        pannelloData.add(minuto);
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
		final JButton costruisci = new JButton("costruisci");
		costruisci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RepartoProdottiFiniti rep = mag.getRepPfinit(elencoProdottiFiniti1.getSelectedItem().toString());
				int q = Integer.parseInt(testoQuantit�.getText());
				Date data =  new Date(Integer.parseInt(anno.getText()),
						   			  Integer.parseInt(mese.getText()),
						   			  Integer.parseInt(giorno.getText()),
						   			  Integer.parseInt(ora.getText()),
						   			  Integer.parseInt(minuto.getText()));
				
				String ris = mag.costruisciProdotti(rep, listaOperai.getSelectedIndex(), q, data);

				JOptionPane.showMessageDialog(costruisci, ris);
			}	
		});

		pannelloCostruzione.add(elencoProdottiFiniti1);							// aggiunto combobox dei prodotti finiti al pannello principale
		pannelloQuant.add(testo);												// aggiunto testo a quantit�
		pannelloQuant.add(testoQuantit�);										// aggiunto text area a quantit�
		pannelloCostruzione.add(pannelloQuant);									// aggiunto quantit� al pannello principale
		pannelloCostruzione.add(pannelloDataTotale);							// aggiunto data al pannello principale
		pannelloCostruzione.add(costruisci);									// aggiunto bottone costruisco al pannello principale
		pannelloPrincipale.add(pannelloCostruzione, BorderLayout.LINE_END);		// aggiunto pannello gestione al pannello principale
	}
	
	// metodo che permette il refresh edll'intera interfaccia grafica degli operai
	public InterfacciaOperaio aggiorna(InterfacciaOperaio i) {
		i.dispose();
		InterfacciaOperaio iNuova = new InterfacciaOperaio(mag);
		iNuova.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iNuova.setVisible(true);
		return iNuova;
	}
	
}
