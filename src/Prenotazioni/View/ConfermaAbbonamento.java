package Prenotazioni.View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Corsa;

public class ConfermaAbbonamento {
	private JFrame frame;
	private SportelloSystem controller;
	
	public ConfermaAbbonamento(List<Corsa> corse) {
		controller = SportelloSystem.getInstance();
		frame=new JFrame("Admin bus App");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Confermi acquisto?");
		p1.add(l1);
		JButton b1 = new JButton("Conferma");
		JButton b2 = new JButton("Annulla");
		JPanel p2  = new JPanel();
		p2.add(b1);
		p2.add(b2);
		
		frame.add(p1, BorderLayout.NORTH);
		frame.add(p2, BorderLayout.SOUTH);
		
		ActionListener confermaListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				if(controller.verificaMaterialeStampa()) {
					new FormAbbonamento(corse);
				}
				else {
					controller.annullaOperazione();
					JOptionPane.showMessageDialog(null, "Operazione annullata: materiale stampa esaurito");
				}				
			}
		};
		
		ActionListener indietroListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CorseView(controller.elencaCorse(controller.getPartenza(), controller.getArrivo()));
			}
		};
		
		b2.addActionListener(indietroListener);
		b1.addActionListener(confermaListener);
		
		frame.pack();
		frame.setLocation(700, 400);
		frame.setVisible(true);
	}
}
