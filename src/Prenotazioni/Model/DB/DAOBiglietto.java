package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Prenotazioni.Model.Beans.Biglietto;
import Prenotazioni.Model.Beans.Corsa;

public class DAOBiglietto implements DAOInterface<Biglietto> {
	
	public DAOBiglietto() {
	}

	@Override
	public List<Biglietto> doRetrieveAll() {
		List<Biglietto> listaBiglietti = new ArrayList<Biglietto>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM biglietti");
			
			while(result.next()) {
				int ID = result.getInt("id");
				Corsa corsa = DAOFactory.getDAOCorsa().doRetrieveByKey(result.getInt("id"));
				Date dataOraEmissione = new Date(result.getTimestamp("data_ora_emissione").getTime());;
				String luogoPartenza = result.getString("luogo_partenza");
				String luogoArrivo = result.getString("luogo_arrivo");
				double prezzo = result.getDouble("prezzo");
				
				Biglietto b = new Biglietto(ID,
						                    corsa,
						                    dataOraEmissione,
						                    luogoPartenza,
						                    luogoArrivo,
						                    prezzo);
				
				listaBiglietti.add(b);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaBiglietti;
	}

	public Biglietto doRetrieveByKey(int ID) {
		Biglietto biglietti = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM biglietti WHERE id = '"+ID+"'");
			
			if(result.next()) {
				Corsa corsa = DAOFactory.getDAOCorsa().doRetrieveByKey(result.getInt("id"));
				Date dataOraEmissione = new Date(result.getTimestamp("data_ora_emissione").getTime());;
				String luogoPartenza = result.getString("luogo_partenza");
				String luogoArrivo = result.getString("luogo_arrivo");
				double prezzo = result.getDouble("prezzo");
				
				biglietti = new Biglietto(ID,
										  corsa,
						                  dataOraEmissione,
						                  luogoPartenza,
						                  luogoArrivo,
						                  prezzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return biglietti;
	}

	@Override
	public void saveOrUpdate(Biglietto b) {
		try {
			Connection conn = DataSource.getConnection();
			Statement statement = conn.createStatement();
			String sql = "insert into biglietti (id, id_corsa, data_ora_emissione, luogo_partenza, luogo_arrivo, prezzo) " 
						+ "values (" + b.getID() + ", " + b.getCorsa().getIDCorsa() + ", CURTIME(), \"" 
						+ b.getLocalit‡Partenza() + "\", \"" + b.getLocalit‡Arrivo() + "\", " + b.getPrezzo() + ")";		
			int esito = statement.executeUpdate(sql);
			if(esito!=1)
				throw new SQLException("Insert biglietto non effettuato");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void delete(Biglietto b) {
		// TO DO
	}

}
