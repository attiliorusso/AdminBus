package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Prenotazioni.Model.Beans.Linea;
import Prenotazioni.Model.Beans.Localit‡;
import Prenotazioni.Model.Beans.Tratta;
import Prenotazioni.Model.DB.DAOFactory;
import Prenotazioni.Model.DB.DAOInterface;
import Prenotazioni.Model.DB.DataSource;

public class DAOLinea implements DAOInterface<Linea> {
	
	public DAOLinea() {
	}
	
	@Override
	public List<Linea> doRetrieveAll() {
		List<Linea> listaLinee = new ArrayList<Linea>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM linee");
			
			while(result.next()) {
				String nome = result.getString("nome");
				Localit‡ localit‡Partenza = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_partenza"));
				Localit‡ localit‡Arrivo = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_arrivo"));
				Set<Tratta> tratte = DAOFactory.getDAOTratta().doRetrieveByLinea(nome);
				
				Linea l = new Linea(nome,
						            localit‡Partenza,
						            localit‡Arrivo,
						            tratte);
				
				listaLinee.add(l);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaLinee;
	}

	public List<Linea> doRetrieveByPlace(String from, String to){
		List<Linea> listaLinee = new ArrayList<Linea>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "select nome_linea from linee_tratte" + 
							" where localit‡_partenza = '" + from + "'" +
							" and localit‡_arrivo = '" + to + "'";

			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				Linea linea = doRetrieveByKey(result.getString("nome_linea"));
				
				listaLinee.add(linea);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaLinee;
	}
	
	public Linea doRetrieveByKey(String nome) {
		Linea linea = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM linee WHERE nome = '"+nome+"'");
			
			if(result.next()) {
				Localit‡ localit‡Partenza = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_partenza"));
				Localit‡ localit‡Arrivo = DAOFactory.getDAOLocalit‡().doRetrieveByKey(result.getString("localit‡_arrivo"));
				Set<Tratta> tratte = DAOFactory.getDAOTratta().doRetrieveByLinea(nome);
				
				linea = new Linea(nome,
			                      localit‡Partenza,
			                      localit‡Arrivo,
			                      tratte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return linea;
	}

	@Override
	public void saveOrUpdate(Linea l) {
		// TO DO
	}

	@Override
	public void delete(Linea l) {
		// TO DO
	}

}

