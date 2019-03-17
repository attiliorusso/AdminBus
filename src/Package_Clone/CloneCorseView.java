package Package_Clone;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Prenotazioni.View.FormPartenzaArrivo;
import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Corsa;

public class CloneCorseView {
	
	private JFrame frame;
	private SportelloSystem controller;
	
	public CloneCorseView(List<Corsa> corse) {
		controller = SportelloSystem.getInstance();
		frame=new JFrame("Admin bus App - Corse");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lab1 = new JLabel();
		lab1.setText("Seleziona una corsa per la tratta: " + controller.getPartenza() + " - " + controller.getArrivo());
		JPanel p1 = new JPanel();
		p1.add(lab1);
		JLabel lab2 = new JLabel();
		JLabel lab3 = new JLabel();
		
		JButton indietro = new JButton("Indietro");
		JButton prenota = new JButton("Prenota biglietto");
		prenota.setEnabled(false);
		JButton compra = new JButton("Acquista abbonamento");
		compra.setEnabled(false);
		DefaultListModel<Corsa> listModel = new DefaultListModel<>();
		for(Corsa c:corse) {
			listModel.addElement(c);
		}
		JList<Corsa> list = new JList<>(listModel);
		JPanel p2 = new JPanel(new GridLayout(3, 1));
		p2.add(list, 0);
		JPanel p4 = new JPanel(new GridLayout(1, 2));
		p4.add(lab2, 0);
		p4.add(lab3, 1);
		p2.add(p4, 1);
		JPanel p3 = new JPanel();
		p3.add(indietro);
		p3.add(prenota);
		p3.add(compra);
		
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() > -1) {
					Corsa corsaSelezionata = list.getSelectedValue();
					controller.setCorsaSelezionata(corsaSelezionata);
					
					if(corsaSelezionata.getPostiDisponibili() == 0) {
						prenota.setEnabled(false);
						compra.setEnabled(false);
					}
					else {
						double prezzoBiglietto = controller.calcolaPrezzoBiglietto(controller.getPartenza(), controller.getArrivo());
						lab2.setText("Costo biglietto: " + prezzoBiglietto + " Ä");
						if(controller.verificaDisponibilit‡Abbonamento(corsaSelezionata)) {
							double prezzoAbbonamento = controller.calcolaPrezzoAbbonamento(prezzoBiglietto);
							lab3.setText("Costo abbonamento: " + prezzoAbbonamento + " Ä");
							prenota.setEnabled(true);
							compra.setEnabled(true);
						}
						else {	
							prenota.setEnabled(true);
							compra.setEnabled(false);
						}
					
					}
				}
				
			}	
		});	
		
		ActionListener indietroListener=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				controller.annullaOperazione();
				new FormPartenzaArrivo();
				
			}
		};
		
		indietro.addActionListener(indietroListener);
		
		ActionListener prenotaListener=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Corsa corsa = controller.prenotaBiglietto(list.getSelectedValue().getIDCorsa());
				new ConfermaBigliettoView(corsa);
			}
		};
		
		ActionListener compraListener=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				controller.setCorsaSelezionata(list.getSelectedValue());
				List<Corsa> corsePerAbbonamento = controller.getCorseAbbonamento();
				new ConfermaAbbonamento(corsePerAbbonamento);
			}
		};
		
		indietro.addActionListener(indietroListener);
		prenota.addActionListener(prenotaListener);
		compra.addActionListener(compraListener);
		
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.CENTER);
		frame.add(p3, BorderLayout.SOUTH);

		frame.setLocation(700, 400);
		frame.setVisible(true);
		frame.pack();
	}
}

