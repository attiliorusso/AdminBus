package Prenotazioni.Model.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Prenotazioni.Model.Beans.Contanti;

public class DAOContanti implements DAOInterface<Contanti> {
	
	public DAOContanti() {
	}

	
	public List<Contanti> doRetrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Contanti doRetrieveByKey(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Contanti p) {
		try {
			Connection conn = DataSource.getConnection();
			Statement statement = conn.createStatement();
			String sql = "insert into contanti (importo, data_ora, id_titolo_viaggio, resto) " 
						+ "values (" + p.getsommaInserita() + ", CURTIME(), " + p.getTitoloViaggio().getID() + ", " + p.getResto() + ")";		
			int esito = statement.executeUpdate(sql);
			if(esito!=1)
				throw new SQLException("Insert pagamento non effettuato");
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}

	@Override
	public void delete(Contanti p) {
		// TODO Auto-generated method stub
		
	}

}

