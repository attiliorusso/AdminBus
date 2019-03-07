package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prenotazioni.Model.Beans.Corsa;
import Prenotazioni.Model.Beans.Abbonamento;

public class DAOAbbonamento implements DAOInterface<Abbonamento> {
	
	public DAOAbbonamento() {
		
	}

	@Override
	public List<Abbonamento> doRetrieveAll() {
		List<Abbonamento> listaAbbonamenti = new ArrayList<Abbonamento>();
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM abbonamenti");
			
			while(result.next()) {
				int ID = result.getInt("id");
				List<Corsa> corse = DAOFactory.getDAOCorsa().doRetrieveByAbbonamento(ID);
				Date dataInizio = result.getDate("data_fine");
				Date dataOraEmissione = new Date(result.getTimestamp("data_ora_emissione").getTime());
				String codiceFiscale = result.getString("codice_fiscale");
				String nome = result.getString("nome");
				String cognome = result.getString("cognome");
				String luogoPartenza = result.getString("luogo_partenza");
				String luogoArrivo = result.getString("luogo_arrivo");
				double prezzo = result.getDouble("prezzo");
				
				Abbonamento a = new Abbonamento(ID,
						                        corse,
						                        dataInizio,
						                        dataOraEmissione,
						                        codiceFiscale,
						                        nome,
						                        cognome,
						                        luogoPartenza,
						                        luogoArrivo,
						                        prezzo);
				
				listaAbbonamenti.add(a);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAbbonamenti;
	}

	
	public Abbonamento doRetrieveByKey(int ID) {
		Abbonamento abbonamento = null;
		try {
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM abbonamenti WHERE id = '"+ID+"'");
			
			if(result.next()) {
				List<Corsa> corse = DAOFactory.getDAOCorsa().doRetrieveByAbbonamento(ID);
				Date dataInizio = result.getDate("data_fine");
				Date dataOraEmissione = new Date(result.getTimestamp("data_ora_emissione").getTime());
				String codiceFiscale = result.getString("codice_fiscale");
				String nome = result.getString("nome");
				String cognome = result.getString("cognome");
				String luogoPartenza = result.getString("luogo_partenza");
				String luogoArrivo = result.getString("luogo_arrivo");
				double prezzo = result.getDouble("prezzo");
				
				abbonamento = new Abbonamento(ID,
						                      corse,
						                      dataInizio,
						                      dataOraEmissione,
						                      codiceFiscale,
						                      nome,
						                      cognome,
						                      luogoPartenza,
						                      luogoArrivo,
						                      prezzo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return abbonamento;
	}

	@Override
	public void saveOrUpdate(Abbonamento a) {
		try {
			Connection conn = DataSource.getConnection();
			Statement statement = conn.createStatement();
			Date dataFine = new Date(a.getDataFine().getTime());
			String sql = "insert into abbonamenti (id, data_fine, codice_fiscale, nome, cognome, data_ora_emissione, luogo_partenza, luogo_arrivo, prezzo) " 
						+ "values (" + a.getID() + ", \"" + dataFine + "\", \"" + a.getCodiceFiscale() + "\", \"" + a.getNome() + "\", \"" 
						+ a.getCognome() + "\"" + ", CURTIME(), \"" + a.getLocalit‡Partenza() + "\", \"" + a.getLocalit‡Arrivo() + "\", " + a.getPrezzo() + ")";
			int esito = statement.executeUpdate(sql);
			if(esito!=1)
				throw new SQLException("Insert abbonamento non effettuato");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	public void saveOrUpdate(Abbonamento a, List<Corsa> corseAbbonamenti) {
		try {
			Connection conn = DataSource.getConnection();
			Statement statement = conn.createStatement();
			for(Corsa c:corseAbbonamenti) {
				String sql = "insert into abbonamenti_corse (id_abbonamento, id_corsa) " 
						+ "values (" + a.getID() + ", " + c.getIDCorsa() + ")";
				int esito = statement.executeUpdate(sql);
				if(esito!=1)
					throw new SQLException("Insert abbonamento non effettuato");
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void delete(Abbonamento a) {
		// TO DO
	}

}

