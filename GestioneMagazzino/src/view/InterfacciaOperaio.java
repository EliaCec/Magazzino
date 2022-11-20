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
		
		this.add(pannelloPrincipale);
		
		// creazione pannello per il gestionale
		final JPanel pannelloSx = new JPanel();
		pannelloSx.setBorder(BorderFactory.createTitledBorder("Gestionale"));
		BorderLayout layoutSinistro = new BorderLayout();
		layoutSinistro.setVgap(5);
		pannelloSx.setLayout(layoutSinistro);
		
		// creo textArea
		final JTextArea areaStampa = new JTextArea(10, 10);
		pannelloSx.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);
		

		// creo JcomboBox per operai
		JComboBox<String> operai = new JComboBox<>();
		operai.addActionListener(e -> {
			areaStampa.setText("");
		});
		for (int i = 0; i < mag.getDirigente().getOperaiAttivi().size(); i++) {
		operai.addItem(mag.getDirigente().getOperaiAttivi().get(i).getNomeCognome());
		}
		pannelloPrincipale.add(operai, BorderLayout.PAGE_START);
		
		//creazione pannello per inserimento dei pulsanti 
		final JPanel pannelloPulsanti = new JPanel();
		GridLayout layout = new GridLayout (3, 3);
		layout.setHgap(10);
		layout.setVgap(10);
		pannelloPulsanti.setLayout(layout);
		
		// bottone per Lista semilavorati usati
		final JButton primo = new JButton("Lista semilavorati");
		primo.addActionListener(e -> {
			this.mag.getDirigente().getOperaiAttivi().get(operai.getSelectedIndex())
									 				 .getSemilavoratiPrelevati()
									 				 .forEach(n -> areaStampa.append(n.getSemilavorato().getNome() + "\n"));
		});
		pannelloPulsanti.add(primo);
		
		// bottone per lista prodotti finiti costruiti
		final JButton secondo = new JButton("Lista prodotti finiti costruiti");
		secondo.addActionListener(e -> {
				this.mag.getDirigente().getOperaiAttivi().get(operai.getSelectedIndex())
									   .getProdottiCostruiti()
									   .forEach(n -> areaStampa.append(n.getProdotto().getNome() + "\n"));
		});
		pannelloPulsanti.add(secondo);
		

		// creo JcomboBox per lista semilavorati
		JComboBox<String> semilav = new JComboBox<>();
		for (int i = 0; i < mag.getDirigente().getReparti().size(); i++) {
			for(int j = 0; j < mag.getDirigente().getReparti().get(i).getListaRepartiSemilavorati().size(); j++) {
					semilav.addItem(mag.getDirigente().getReparti().get(i)
										  .getListaRepartiSemilavorati().get(j).getGiacenzaReparto().getNome());
			}
		}
		
		// aggiunta lista semilavorati usati e creazione pannello semilavorati
		final JPanel pannelloSemilavorati = new JPanel();
		pannelloPulsanti.add(pannelloSemilavorati);
		pannelloSemilavorati.add(semilav);
		
		// bottone per numero semilavorati usati
		final JButton terzo = new JButton("numero semilavorati usati");
		terzo.addActionListener(e -> {
			areaStampa.append(String.valueOf(this.mag.getDirigente()
													 .getOperaiAttivi()
													 .get(operai.getSelectedIndex())
					 								 .prelievoPerSemilavorato(semilav.getSelectedItem().toString())) + "\n");
		});
		pannelloSemilavorati.add(terzo);

		// bottone per numero prodotti finiti costruiti
		final JButton quarto = new JButton("numero prodotti finiti costruiti");
		quarto.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
						  .getOperaiAttivi()
						  .get(operai.getSelectedIndex())
						  .costruzioniPerProdottoFinito(/* BARRA PER IL PRODOTTO FINITO */null)) + "\n");
		});
		pannelloPulsanti.add(quarto);

		// bottone per numero prodotti finiti costruibili
		final JButton quinto = new JButton("numero prodotti finiti costruibili");
		quinto.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
					  .getOperaiAttivi()
					  .get(operai.getSelectedIndex())
					  .calcoloProdottiFinitiCostruibili(/* BARRA PER IL PRODOTTO FINITO */null)) + "\n");
		});
		
		pannelloPulsanti.add(quinto);
		
		// bottone per ripulire textarea
		final JButton sesto = new JButton("clear");
		sesto.addActionListener(e -> {
				areaStampa.setText("");
		});
		pannelloPulsanti.add(sesto);

		
		pannelloSx.add(pannelloPulsanti,  BorderLayout.PAGE_START);
		
		//aggiungo pannello gestione al pannello principale
		pannelloPrincipale.add(pannelloSx, BorderLayout.LINE_START);
		
		
		// creazione pannello per la costruzione
		final JPanel pannelloDx = new JPanel();
		pannelloDx.setBorder(BorderFactory.createTitledBorder("pannello costruzioni"));
		
		//aggiungo pannello gestione al pannello principale
		pannelloPrincipale.add(pannelloDx, BorderLayout.LINE_END);


	}
}
