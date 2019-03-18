package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Prenotazioni.Controller.SportelloSystem;
import Prenotazioni.Model.Beans.Corsa;
import Prenotazioni.Model.Beans.Linea;
import Prenotazioni.Model.Beans.Mezzo;
import Prenotazioni.Model.DB.DAOFactory;
import Prenotazioni.Model.DB.DAOInterface;
import Prenotazioni.Model.DB.DataSource;

public class DAOCorsa implements DAOInterface<Corsa> {
	
	public DAOCorsa() {
	}

	@Override
	public List<Corsa> doRetrieveAll() {
		List<Corsa> listaCorse = new ArrayList<Corsa>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM corse");
			
			while(result.next()) {
				int ID = result.getInt("id");
				Linea linea = DAOFactory.getDAOLinea().doRetrieveByKey(result.getString("nome_linea"));
				Date dataOra = new Date(result.getTimestamp("data_ora").getTime());
				int postiDisponibili = result.getInt("posti_disponibili");
				Mezzo mezzo = DAOFactory.getDAOMezzo().doRetrieveByKey(result.getString("targa_mezzo"));	
				Corsa c = new Corsa(ID, linea, dataOra, postiDisponibili, mezzo);

				listaCorse.add(c);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaCorse;
	}
	
	
	public List<Corsa> doRetrieveByLinea(String nome) {
		List<Corsa> listaCorse = new ArrayList<Corsa>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM corse WHERE nome_linea = '"+nome+"'");
			
			while(result.next()) {
				int ID = result.getInt("id");
				Linea linea = DAOFactory.getDAOLinea().doRetrieveByKey(result.getString("nome_linea"));
				Date dataOra = new Date(result.getTimestamp("data_ora").getTime());
				int postiDisponibili = result.getInt("posti_disponibili");
				Mezzo mezzo = DAOFactory.getDAOMezzo().doRetrieveByKey(result.getString("targa_mezzo"));
				
				Corsa c = new Corsa(ID, linea, dataOra, postiDisponibili, mezzo);

				
				listaCorse.add(c);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaCorse;
	}
	
	
	public List<Corsa> doRetrieveByAbbonamento(int ID) {
		List<Corsa> listaCorse = new ArrayList<Corsa>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM abbonamenti_corse WHERE id_abbonamento = '"+ID+"'");
			
			while(result.next()) {
				Corsa corsa = doRetrieveByKey(result.getInt("id_corsa"));
				
				listaCorse.add(corsa);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaCorse;
	}
	
	
	public List<Corsa> doRetrieveByPlace(String from, String to) {
		List<Corsa> listaCorse = new ArrayList<Corsa>();

		List<Linea> linee = DAOFactory.getDAOLinea().doRetrieveByPlace(from, to);

		for(Linea l : linee) {
			try {
				Connection connection = DataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM corse WHERE nome_linea = '"+l.getNome()+"' and data_ora >= CURTIME()");
				while(result.next()) {
					Corsa corsa = doRetrieveByKey(result.getInt("id"));
					listaCorse.add(corsa);				
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaCorse;
	}
	
	public List<Corsa> doRetrieveByPlace2(String from, String to) {
		List<Corsa> listaCorse2 = new ArrayList<Corsa>();

		List<Linea> linee = DAOFactory.getDAOLinea().doRetrieveByPlace(from, to);

		for(Linea l : linee) {
			try {
				Connection connection = DataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT * FROM corse WHERE nome_linea = '"+l.getNome()+"' and data_ora >= CURTIME()");
				while(result.next()) {
					Corsa corsa = doRetrieveByKey(result.getInt("id"));
					listaCorse2.add(corsa);				
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaCorse2;
	}

	
	public Corsa doRetrieveByKey(int ID) {
		Corsa corsa = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM corse WHERE id = '"+ID+"'");
			
			if(result.next()) {
				Linea linea = DAOFactory.getDAOLinea().doRetrieveByKey(result.getString("nome_linea"));
				Date dataOra = new Date(result.getTimestamp("data_ora").getTime());
				int postiDisponibili = result.getInt("posti_disponibili");
				Mezzo mezzo = DAOFactory.getDAOMezzo().doRetrieveByKey(result.getString("targa_mezzo"));
				corsa = new Corsa(ID, linea, dataOra, postiDisponibili, mezzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return corsa;
	}

	@Override
	public void saveOrUpdate(Corsa c) {
		try {
			Connection conn = DataSource.getConnection();
			Statement statement = conn.createStatement();
			SportelloSystem controller = SportelloSystem.getInstance();
			String sql = "update corse set posti_disponibili = " + (c.getPostiDisponibili() - (controller.getCostoBiglietto() / SportelloSystem.getInstance().calcolaPrezzoBiglietto(controller.getPartenza(), controller.getArrivo()))) 
						+ " where id = " + c.getIDCorsa();
			int esito = statement.executeUpdate(sql);
			if(esito!=1)
				throw new SQLException("Update corsa non effettuato");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
			
	}
	

	@Override
	public void delete(Corsa c) {
		// TO DO
	}

}
