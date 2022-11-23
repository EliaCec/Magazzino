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
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import controller.Magazzino;
import model.RepartoProdottiFiniti;

@SuppressWarnings("serial")
public class InterfacciaResponasbile extends JFrame {
	
	private final Magazzino mag;

	// costruttore
	public InterfacciaResponasbile(Magazzino mag) {
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
		
		// crea combobox operaio e resto dell'interfaccia
		this.inizializzaResponsabili(pannelloPrincipale);
	}
	
	private void inizializzaResponsabili(JPanel pannelloPrincipale) {
		// creo JcomboBox per operai
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
		
		// ogni volta che cambio operaio ripulisco l'output
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
		pannelloVenditePfiniti.add(elencoProdottiFiniti);									// aggiunto combobox al pannello prodotti finiti
		pannelloDepSemilavorati.add(numSemilavoratiDepositati);								// aggiungo bottone per numero semilavorati usati
		pannelloVenditePfiniti.add(numProdottiFiniti);										// aggiungo bottone per numero prodotti finiti costruiti
		pannelloPulsanti.add(pannelloVenditePfiniti);										// aggiunto al pannello pulsanti il pannello prodotti finiti
		pannelloPulsanti.add(pannelloDepSemilavorati);										// aggiunto al pannello pulsanti il pannello semilavorati
		pannelloPulsanti.add(listaSemilavoratiDepositati);									// aggiungo bottone per Lista semilavorati usati
		pannelloPulsanti.add(listaProdottiFinitiVenduti);									// aggiungo bottone per lista prodotti finiti costruiti
		pannelloPulsanti.add(pulisci);														// aggiungo bottone per ripulire textarea
		pannelloGestionale.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);		// aggiungo casella di testa al pannello sinistro
		pannelloGestionale.add(pannelloPulsanti,  BorderLayout.PAGE_START);					// aggiungo pannello pulsanti al pannello sinistro
		pannelloPrincipale.add(pannelloGestionale, BorderLayout.LINE_START);				// aggiungo pannello gestione al pannello principale
		this.add(pannelloPrincipale);														// aggiungo pannello al frame	
	}
	
	@SuppressWarnings("deprecation")
	public void inizializzaDestra(JPanel pannelloPrincipale, JComboBox<String> listaOperai) {
		
		// creazione pannello per la costruzione
		final JPanel pannelloDestro = new JPanel();
		pannelloDestro.setPreferredSize(new Dimension(380, 100));
		pannelloDestro.setBorder(BorderFactory.createTitledBorder("pannello vendite"));
		
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
		
		// bottone per far partire la costruzione
		final JButton costruisci = new JButton("costruisci");
		costruisci.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				RepartoProdottiFiniti rep = mag.getRepPfinit(elencoProdottiFiniti1.getSelectedItem().toString());
				int q = Integer.parseInt(testoQuantità.getText());
				Date data =  new Date(Integer.parseInt(anno.getText()),
						   			  Integer.parseInt(mese.getText()),
						   			  Integer.parseInt(giorno.getText()));
				
				String ris = mag.costruisciProdotti(rep, listaOperai.getSelectedIndex(), q, data);
	
				JOptionPane.showMessageDialog(costruisci, ris);
			}	
		});
	
		pannelloDestro.add(elencoProdottiFiniti1);							// aggiunto combobox dei prodotti finiti al pannello principale
		pannelloQuant.add(testo);												// aggiunto testo a quantità
		pannelloQuant.add(testoQuantità);										// aggiunto text area a quantità
		pannelloDestro.add(pannelloQuant);									// aggiunto quantità al pannello principale
		pannelloDestro.add(pannelloData);									// aggiunto data al pannello principale
		pannelloDestro.add(costruisci);									// aggiunto bottone costruisco al pannello principale
		pannelloPrincipale.add(pannelloDestro, BorderLayout.LINE_END);		// aggiunto pannello gestione al pannello principale
	}
}
