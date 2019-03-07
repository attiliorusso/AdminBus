package Prenotazioni.Model.DB;

import java.util.List;

public interface DAOInterface<E> {
	
	List<E> doRetrieveAll();
	void saveOrUpdate(E e);
	void delete(E e);
	
}
