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
		this.setLocation(670, 320);
		
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
		final JButton numSemilavorati = new JButton("n° semilavorati usati");
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

		// bottone per numero prodotti finiti costruiti
		final JButton numProdottiFiniti = new JButton("n° prodotti finiti costruiti");
		numProdottiFiniti.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
						  .getOperaiAttivi()
						  .get(listaOperai.getSelectedIndex())
						  .costruzioniPerProdottoFinito(elencoProdottiFiniti.getSelectedItem().toString())) + "\n");
		});

		// bottone per numero prodotti finiti costruibili
		final JButton numProdFinitiCostruibili = new JButton("n° prodotti finiti costruibili");
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
		pannelloPfiniti.add(elencoProdottiFiniti);											// aggiunto combobox al pannello prodotti finiti
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
	public void inizializzaCostruzione(JPanel pannelloPrincipale, JComboBox<String> listaOperai) {
		
		// creazione pannello per la costruzione
		final JPanel pannelloCostruzione = new JPanel();
		pannelloCostruzione.setPreferredSize(new Dimension(380, 100));
		pannelloCostruzione.setBorder(BorderFactory.createTitledBorder("pannello costruzioni"));
		
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

		pannelloCostruzione.add(elencoProdottiFiniti1);							// aggiunto combobox dei prodotti finiti al pannello principale
		pannelloQuant.add(testo);												// aggiunto testo a quantità
		pannelloQuant.add(testoQuantità);										// aggiunto text area a quantità
		pannelloCostruzione.add(pannelloQuant);									// aggiunto quantità al pannello principale
		pannelloCostruzione.add(pannelloData);									// aggiunto data al pannello principale
		pannelloCostruzione.add(costruisci);									// aggiunto bottone costruisco al pannello principale
		pannelloPrincipale.add(pannelloCostruzione, BorderLayout.LINE_END);		// aggiunto pannello gestione al pannello principale
	}
}
