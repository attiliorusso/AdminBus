package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Prenotazioni.Model.Beans.Localit‡;
import Prenotazioni.Model.Beans.Tratta;
import Prenotazioni.Model.DB.DataSource;

public class DAOTratta implements DAOInterface<Tratta> {
	
	public DAOTratta() {
	}

	@Override
	public List<Tratta> doRetrieveAll() {
		List<Tratta> listaTratte = new ArrayList<Tratta>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM tratte");
			
			while(result.next()) {
				Localit‡ localit‡Partenza = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_partenza"));
				Localit‡ localit‡Arrivo = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_arrivo"));
				int durata = result.getInt("durata");
				double prezzo = result.getDouble("prezzo");
				
				Tratta t = new Tratta(localit‡Partenza,
						              localit‡Arrivo,
                                      durata,
						              prezzo);
				
				listaTratte.add(t);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listaTratte;
	}
	
	
	public Set<Tratta> doRetrieveByLinea(String nome) {
		Set<Tratta> listaTratte = new HashSet<Tratta>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM linee_tratte WHERE nome_linea = '"+nome+"'");
			
			while(result.next()) {
				Tratta tratta = doRetrieveByKey(result.getString("localit‡_partenza"), result.getString("localit‡_arrivo"));
				listaTratte.add(tratta);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaTratte;
	}

	
	public Tratta doRetrieveByKey(String from, String to) {
		Tratta tratta = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM tratte WHERE localit‡_partenza = '"+from+"' and localit‡_arrivo = '"+to+"'");
			
			if(result.next()) {
				Localit‡ localit‡Partenza = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_partenza"));
				Localit‡ localit‡Arrivo = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_arrivo"));
				int durata = result.getInt("durata");
				double prezzo = result.getDouble("prezzo");
				
				tratta = new Tratta(localit‡Partenza,
			                        localit‡Arrivo,
                                    durata,
			                        prezzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tratta;
	}

	@Override
	public void saveOrUpdate(Tratta t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tratta t) {
		// TODO Auto-generated method stub
		
	}

}
