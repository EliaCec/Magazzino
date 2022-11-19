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

		// creo JcomboBox per operai
		JComboBox<String> Operai = new JComboBox<>();
		
		
		// creo textArea
		final JTextArea areaStampa = new JTextArea(10, 10);
		pannelloSx.add(new JScrollPane(areaStampa),  BorderLayout.PAGE_END);
		
		//creazione pannello per inserimento dei pulsanti 
		final JPanel pannelloPulsanti = new JPanel();
		GridLayout layout = new GridLayout (3, 2);
		layout.setHgap(10);
		layout.setVgap(10);
		pannelloPulsanti.setLayout(layout);
		
		// bottone per Lista semilavorati usati
		final JButton primo = new JButton("Lista semilavorati usati");
		primo.addActionListener(e -> {
			this.mag.getDirigente().getOperaiAttivi().get(/*FUNZIONE PER SELEZIONARE L'OPERAIO*/0)
									 				 .getSemilavoratiPrelevati()
									 				 .forEach(n -> areaStampa.append(n.getSemilavorato().getNome() + "\n"));
		});
		pannelloPulsanti.add(primo);
		
		// bottone per lista prodotti finiti costruiti
		final JButton secondo = new JButton("Lista prodotti finiti costruiti");
		secondo.addActionListener(e -> {
				this.mag.getDirigente().getOperaiAttivi().get(/*FUNZIONE PER SELEZIONARE L'OPERAIO*/0)
									   .getProdottiCostruiti()
									   .forEach(n -> areaStampa.append(n.getProdotto().getNome() + "\n"));
		});
		pannelloPulsanti.add(secondo);
		
		// bottone per numero semilavorati usati
		final JButton terzo = new JButton("numero semilavorati usati");
		terzo.addActionListener(e -> {
			areaStampa.append(String.valueOf(this.mag.getDirigente()
													 .getOperaiAttivi()
													 .get(/*FUNZIONE PER SELEZIONARE L'OPERAIO*/0)
					 								 .prelievoPerSemilavorato(/* BARRA PER IL SEMILAVORATO */null)));
		});
		pannelloPulsanti.add(terzo);

		// bottone per numero prodotti finiti costruiti
		final JButton quarto = new JButton("numero prodotti finiti costruiti");
		quarto.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
						  .getOperaiAttivi()
						  .get(/*FUNZIONE PER SELEZIONARE L'OPERAIO*/0)
						  .costruzioniPerProdottoFinito(/* BARRA PER IL SEMILAVORATO */null)));
		});
		pannelloPulsanti.add(quarto);

		// bottone per numero prodotti finiti costruibili
		final JButton quinto = new JButton("numero prodotti finiti costruibili");
		quinto.addActionListener(e -> {
				areaStampa.append(String.valueOf(this.mag.getDirigente()
					  .getOperaiAttivi()
					  .get(/*FUNZIONE PER SELEZIONARE L'OPERAIO*/0)
					  .calcoloProdottiFinitiCostruibili(null)));
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
		pannelloDx.setBounds(250,0,250,250);
		pannelloDx.setBorder(BorderFactory.createTitledBorder("pannello costruzioni"));
		
		//aggiungo pannello gestione al pannello principale
		pannelloPrincipale.add(pannelloDx, BorderLayout.LINE_END);


	}
}
