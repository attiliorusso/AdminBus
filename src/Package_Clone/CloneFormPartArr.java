package Package_Clone;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Corsa;
import Prenotazioni.Model.Beans.Località;
import Prenotazioni.Model.DB.DAOFactory;
import Prenotazioni.View.CorseView;

public class CloneFormPartArr {
	public CloneFormPartArr() {
		frame = new JFrame();
		frame.setTitle("Admin bus App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3, 2));
		JLabel lab1 = new JLabel("Partenza (From): ");
		panel1.add(lab1);
		List<Località> l1 = DAOFactory.getDAOLocalità().doRetrieveAll();
		JComboBox<Località> c1 = new JComboBox<>();
		for(Località l:l1)
			c1.addItem(l);
		panel1.add(c1);
		JLabel lab2 = new JLabel("Arrivo (To): ");
		panel1.add(lab2);
		List<Località> l2 = DAOFactory.getDAOLocalità().doRetrieveAll();
		JComboBox<Località> c2 = new JComboBox<>();
		for(Località l:l2)
			c2.addItem(l);
		panel1.add(c2);
		JButton btn_cerca = new JButton("Elenca corse");
		JPanel panel2 = new JPanel();
		panel2.add(btn_cerca);
		
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.SOUTH);
		
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller = SportelloSystem.getInstance();
				if(c1.getSelectedItem().toString().equals(c2.getSelectedItem().toString())) {
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Si scelga una destinazione diversa dalla partenza");
				}
				else {
					List<Corsa> listaCorse = controller.elencaCorse(c1.getSelectedItem().toString(), c2.getSelectedItem().toString());
					frame.setVisible(false);
					if(listaCorse.size() == 0) {
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null, "Nessuna corsa disponibile!");
					}
					else
						new CorseView(listaCorse);
				}
				
			}
		};
		
		btn_cerca.addActionListener(listener);
		
		frame.setLocation(700, 400);
		frame.pack();
		
		frame.setVisible(true);
	}
}
