package Prenotazioni.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Abbonamento;
import Prenotazioni.Model.Beans.Corsa;
import Prenotazioni.Model.Beans.TitoloViaggio;

public class FormAbbonamento {
	private JFrame frame;
	private SportelloSystem controller;
	
	public FormAbbonamento(List<Corsa> corse) {
		controller = SportelloSystem.getInstance();
		frame=new JFrame("Admin bus App - Form Abbonamento");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel(new GridLayout(3, 2));
		JLabel l1 = new JLabel("Nome: ");
		JLabel l2 = new JLabel("Cognome: ");
		JLabel l3 = new JLabel("Codice fiscale: ");
		JTextField t1 = new JTextField();
		JTextField t2 = new JTextField();
		JTextField t3 = new JTextField();
		p1.add(l1, 0);
		p1.add(t1, 1);
		p1.add(l2, 2);
		p1.add(t2, 3);
		p1.add(l3, 4);
		p1.add(t3, 5);
		JPanel p2 = new JPanel();
		JButton avanti = new JButton("Avanti");
		JButton annulla = new JButton("Annulla");
		p2.add(avanti);
		p2.add(annulla);
		frame.add(p1, BorderLayout.CENTER);
		frame.add(p2, BorderLayout.SOUTH);
		
		ActionListener avantiListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Completare tutti i campi");
				else if(t3.getText().length()!=16)
					JOptionPane.showMessageDialog(null, "Controllare la lunghezza del codice fiscale (16 caratteri)");
				else {
					
					frame.setVisible(false);
					
					Corsa corsaSelezionata = controller.getCorsaSelezionata();
					Abbonamento abb = controller.creaAbbonamento(corsaSelezionata);
					abb.setNome(t1.getText());
					abb.setCognome(t2.getText());
					abb.setCodiceFiscale(t3.getText());
					
					new FormPagamento(abb);
				}
			}
		};
		
		avanti.addActionListener(avantiListener);
		
		ActionListener annullaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				JOptionPane.showMessageDialog(null, "Abbonamento annullato");
				controller.annullaOperazione();
				new FormPartenzaArrivo();
				
			}
		};
		
		annulla.addActionListener(annullaListener);
		
		frame.pack();
		frame.setLocation(700, 400);
		frame.setVisible(true);
		
	}
}
