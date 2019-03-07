package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prenotazioni.Model.Beans.Localit�;
import Prenotazioni.Model.DB.DAOInterface;
import Prenotazioni.Model.DB.DataSource;

public class DAOLocalit� implements DAOInterface<Localit�> {
	
	
	public DAOLocalit�() {
	}

	@Override
	public List<Localit�> doRetrieveAll() {
		List<Localit�> listaLocalit� = new ArrayList<Localit�>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM localit�");
			
			while(result.next()) {
				String nome = result.getString("nome");
				
				Localit� localit� = new Localit�(nome);
				
				listaLocalit�.add(localit�);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listaLocalit�;
	}

	
	public Localit� doRetrieveByKey(String nome) {
		Localit� localit� = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM localit� WHERE nome = '"+nome+"'");
			
			if(result.next())
				localit� = new Localit�(result.getString("nome"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(" - " + localit�.getNome());
		return localit�;
	}

	@Override
	public void saveOrUpdate(Localit� l) {
		// TO DO
	}

	@Override
	public void delete(Localit� l) {
		// TO DO
	}

}
