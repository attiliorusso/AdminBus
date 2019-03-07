package Prenotazioni.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Abbonamento;
import Prenotazioni.Model.Beans.TitoloViaggio;

public class FormPagamento {

	private JFrame frame;
	private SportelloSystem controller;
	private double sommaCorrente = 0;
	private double prezzo = 0;
	
	public FormPagamento(TitoloViaggio titolo) {
		prezzo = titolo.getPrezzo();
		controller = SportelloSystem.getInstance();
		frame = new JFrame("Admin bus App - Pagamento");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Costo titolo viaggio: " + titolo.getPrezzo() + " Ä");
		p1.add(l1);
		JLabel l2 = new JLabel("Inserisci importo: ");
		JLabel l3 = new JLabel();
		JTextField txt1 = new JTextField();
		JPanel p2 = new JPanel(new GridLayout(2,3));
		p2.add(l2,0);
		p2.add(txt1,1);
		p2.add(l3,2);
		
		JLabel l = new JLabel("Numero posti ");
		JComboBox<Integer> combo = new JComboBox<Integer>();
		int [] posti = { 1, 2, 3, 4, 5};	
		for(Integer i:posti) {
			combo.addItem(i);
		}
		JPanel p5 = new JPanel(new GridLayout(1, 2));
		p5.add(l, 0);
		p5.add(combo, 1);
		p2.add(p5, 3);
		if(titolo instanceof Abbonamento) {
			combo.setEnabled(false);
		}		
		
		JButton insert = new JButton("Insert pay");
		JButton annulla = new JButton("Annulla");
		JPanel p4 = new JPanel(new GridLayout(1, 2));
		p4.add(insert);
		p4.add(annulla);
		
		
		ActionListener comboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if((combo.getSelectedIndex()+1) <= controller.getCorsaSelezionata().getPostiDisponibili()) {
					insert.setEnabled(true);
					prezzo = (combo.getSelectedIndex()+1) * titolo.getPrezzo();
					l1.setText ("Costo: " + prezzo + " Ä");
				}
				else
					insert.setEnabled(false);
			}
		};
		
		combo.addActionListener(comboListener);
		
		ActionListener annullaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				JOptionPane.showMessageDialog(null, "Pagamento annullato");
				new FormPartenzaArrivo();
			}
		};
		
		annulla.addActionListener(annullaListener);
		
		ActionListener insertListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double sommaInserita = 0;
				try {
					sommaInserita = Double.parseDouble(txt1.getText());
					titolo.setPrezzo(prezzo);
					sommaCorrente = sommaCorrente + sommaInserita;
					l3.setText("Importo: " + sommaCorrente + " Ä");
					if(sommaCorrente >= prezzo) {
						double resto = controller.calcolaResto(sommaCorrente, titolo.getPrezzo());
						boolean disponibilit‡ = controller.verificaDisponibilit‡Resto(resto);
						if(disponibilit‡) {
							frame.setVisible(false);
							controller.setCostoBiglietto(prezzo);
							controller.setsommaInserita(sommaCorrente);	
							controller.acquistaTitoloViaggio(titolo);
							JOptionPane.showMessageDialog(null, "Acquisto effettuato con successo\nBuon Vaggio!");
							
						}
						else {
							frame.setVisible(false);
							controller.annullaOperazione();
							JOptionPane.showMessageDialog(null, "Resto non disponibile!");
							JOptionPane.showMessageDialog(null, "Restituzione importo avvenuta con successo!");
							System.out.println("Somma restituita: " + sommaCorrente + " Ä");
						}
						new FormPartenzaArrivo();
					}			
				} catch(NumberFormatException e1) {
					sommaInserita = 0;
					JOptionPane.showMessageDialog(null, "Inserire valori numerici");
				}
			}	
				
		};
		
		insert.addActionListener(insertListener);
		
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.CENTER);
		frame.add(p4, BorderLayout.SOUTH);
		frame.setLocation(700, 400);
		frame.pack();
		frame.setVisible(true);
	}
}
