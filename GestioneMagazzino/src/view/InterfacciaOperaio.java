package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import controller.Magazzino;

@SuppressWarnings("serial")
public class InterfacciaOperaio extends JFrame {
	
	private final Magazzino mag;
	// costruttore
	public InterfacciaOperaio(Magazzino mag) {
		this.mag = mag;
		
		this.setTitle("Pannello operaio");
		this.setLocation(800, 100);
		
		InizializzaInterfaccia();
		
		pack();
	}
		
	private void InizializzaInterfaccia() {
		setResizable(false);
		
		// creazione pannello principale
		final JPanel pannelloPrincipale = new JPanel();
		pannelloPrincipale.setLayout(new BorderLayout());
		pannelloPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// creazione pannello per il gestionale
		final JPanel pannelloSx = new JPanel();
		pannelloSx.setBorder(BorderFactory.createTitledBorder("Gestionale"));
		BorderLayout layoutSinistro = new BorderLayout();
		layoutSinistro.setVgap(5);
		pannelloSx.setLayout(layoutSinistro);
		
		//creazione pannello per inserimento dei pulsanti 
		final JPanel pannelloPulsanti = new JPanel();
		GridLayout layout = new GridLayout (5, 2);
		layout.setHgap(10);
		layout.setVgap(10);
		pannelloPulsanti.setLayout(layout);
		
		// creazione pannello semilavorati e pannello prodotti finiti
		final JPanel pannelloSemilavorati = new JPanel();
		final JPanel pannelloPfiniti = new JPanel();
		
		// creo textArea
		final JTextArea areaStampa = new JTextArea(10, 10);
		
		// creo JcomboBox per operai
		JComboBox<String> operai = new JComboBox<>();
		operai.addActionListener(e -> {
			areaStampa.setText("");
		});
		for (int i = 0; i < mag.getDirigente().getOperaiAttivi().size(); i++) {
		operai.addItem(mag.getDirigente().getOperaiAttivi().get(i).getNomeCognome());
		}
		
		// bottone per Lista semilavorati usati
		final JButton listaSemilavorati = new JButton("Lista semilavorati");
		listaSemilavorati.addActionListener(e -> {
			this.mag.getDirigente().getOperaiAttivi().get(operai.getSelectedIndex())
									 				 .getSemilavoratiPrelevati()
									 				 .forEach(n -> areaStampa.append(n.getSemilavorato().getNome() + "\n"));
		});
		
		// bottone per lista prodotti finiti costruiti
		final JButton listaProdottiFiniti = new JButton("Lista prodotti finiti costruiti");
		listaProdottiFiniti.addActionListener(e -> {
				this.mag.getDirigente().getOperaiAttivi().get(operai.getSelectedIndex())
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
													 .get(operai.getSelectedIndex())
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
						  .get(operai.getSelectedIndex())
						  .costruzioniPerProdottoFinito(elencoProdottiFiniti.getSelectedItem().toString())) + "\n");
		});

		// bottone per numero prodotti finiti costruibili
		final JButton numProdFinitiCostruibili = new JButton("n° prodotti finiti costruibili");
		numProdFinitiCostruibili.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
					  .getOperaiAttivi()
					  .get(operai.getSelectedIndex())
					  .calcoloProdottiFinitiCostruibili(mag.repPfinit(elencoProdottiFiniti.getSelectedItem().toString()))) + "\n");
		});
		
		// bottone per ripulire textarea
		final JButton Clear = new JButton("clear");
		Clear.addActionListener(e -> {
				areaStampa.setText("");
		});

		pannelloSemilavorati.add(elencoSemilavorati);								// aggiunto combobox al pannello Semilavorati
		pannelloPfiniti.add(elencoProdottiFiniti);									// aggiunto combobox al pannello prodotti finiti
		pannelloSemilavorati.add(numSemilavorati);									// aggiungo bottone per numero semilavorati usati
		pannelloPfiniti.add(numProdottiFiniti);										// aggiungo bottone per numero prodotti finiti costruiti
		pannelloPfiniti.add(numProdFinitiCostruibili);								// aggiungo bottone per numero prodotti finiti costruibili
		pannelloPulsanti.add(pannelloPfiniti);										// aggiunto al pannello pulsanti il pannello prodotti finiti
		pannelloPulsanti.add(pannelloSemilavorati);									// aggiunto al pannello pulsanti il pannello semilavorati
		pannelloPulsanti.add(listaSemilavorati);									// aggiungo bottone per Lista semilavorati usati
		pannelloPulsanti.add(listaProdottiFiniti);									// aggiungo bottone per lista prodotti finiti costruiti
		pannelloPulsanti.add(Clear);												// aggiungo bottone per ripulire textarea
		pannelloSx.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);		// aggiungo casella di testa al pannello sinistro
		pannelloSx.add(pannelloPulsanti,  BorderLayout.PAGE_START);					// aggiungo pannello pulsanti al pannello sinistro
		pannelloPrincipale.add(operai, BorderLayout.PAGE_START);					// aggiungo combobox per scelta operaio al pannello principale
		pannelloPrincipale.add(pannelloSx, BorderLayout.LINE_START);				// aggiungo pannello gestione al pannello principale
		this.add(pannelloPrincipale);												// aggiungo pannello al frame
		
		
		// creazione pannello per la costruzione
		final JPanel pannelloDx = new JPanel();
		pannelloDx.setBorder(BorderFactory.createTitledBorder("pannello costruzioni"));
		
		//aggiungo pannello gestione al pannello principale
		pannelloPrincipale.add(pannelloDx, BorderLayout.LINE_END);


	}
}
