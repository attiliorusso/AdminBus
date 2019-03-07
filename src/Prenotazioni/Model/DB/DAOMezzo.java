package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prenotazioni.Model.Beans.Mezzo;
import Prenotazioni.Model.DB.DAOInterface;
import Prenotazioni.Model.DB.DataSource;

public class DAOMezzo implements DAOInterface<Mezzo> {
	
	public DAOMezzo() {
	}

	@Override
	public List<Mezzo> doRetrieveAll() {
		List<Mezzo> listaMezzi = new ArrayList<Mezzo>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM mezzi");
			
			while(result.next()) {
				String targa = result.getString("targa");
				int numeroPosti = result.getInt("numero_posti");
				
				Mezzo mezzo = new Mezzo(targa, numeroPosti);
				
				listaMezzi.add(mezzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listaMezzi;
	}

	
	public Mezzo doRetrieveByKey(String targa) {
		Mezzo mezzo = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM mezzi WHERE targa = '"+targa+"'");
			
			if(result.next()) {
				int numeroPosti = result.getInt("numero_posti");
				mezzo = new Mezzo(targa, numeroPosti);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return mezzo;
	}

	@Override
	public void saveOrUpdate(Mezzo m) {
		// TO DO
	}

	@Override
	public void delete(Mezzo m) {
		// TO DO
	}

}

