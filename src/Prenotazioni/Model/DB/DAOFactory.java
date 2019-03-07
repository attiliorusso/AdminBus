package Prenotazioni.Model.DB;

import Prenotazioni.Model.DB.DAOCorsa;
import Prenotazioni.Model.DB.DAOLocalità;
import Prenotazioni.Model.DB.DAOLinea;
import Prenotazioni.Model.DB.DAOMezzo;
import Prenotazioni.Model.DB.DAOTratta;

public class DAOFactory {
	
	private DAOFactory() { }
	
	public static DAOLinea getDAOLinea() { return new DAOLinea(); }
	public static DAOTratta getDAOTratta() { return new DAOTratta(); }
	public static DAOCorsa getDAOCorsa() { return new DAOCorsa(); }
	public static DAOLocalità getDAOLocalità() { return new DAOLocalità(); }
	public static DAOMezzo getDAOMezzo() { return new DAOMezzo(); }
	public static DAOBiglietto getDAOBiglietto() {return new DAOBiglietto();}
	public static DAOAbbonamento getDAOAbbonamento() {return new DAOAbbonamento();}
	public static DAOContanti getDAOContanti() {return new DAOContanti();}
	public static DAOCassa getDAOCassa() {return new DAOCassa();}
}
