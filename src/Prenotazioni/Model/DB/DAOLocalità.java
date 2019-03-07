package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prenotazioni.Model.Beans.Località;
import Prenotazioni.Model.DB.DAOInterface;
import Prenotazioni.Model.DB.DataSource;

public class DAOLocalità implements DAOInterface<Località> {
	
	
	public DAOLocalità() {
	}

	@Override
	public List<Località> doRetrieveAll() {
		List<Località> listaLocalità = new ArrayList<Località>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM località");
			
			while(result.next()) {
				String nome = result.getString("nome");
				
				Località località = new Località(nome);
				
				listaLocalità.add(località);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listaLocalità;
	}

	
	public Località doRetrieveByKey(String nome) {
		Località località = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM località WHERE nome = '"+nome+"'");
			
			if(result.next())
				località = new Località(result.getString("nome"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(" - " + località.getNome());
		return località;
	}

	@Override
	public void saveOrUpdate(Località l) {
		// TO DO
	}

	@Override
	public void delete(Località l) {
		// TO DO
	}

}
