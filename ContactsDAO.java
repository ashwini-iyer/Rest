package javatraining.dao;

import java.util.List;

import javatraining.entity.COntact;

public interface ContactsDAO {

	public COntact addContact(COntact contact)throws DaoException;
	public COntact findById(Integer id)throws DaoException;
	public COntact updateContact(COntact ct)throws DaoException;
	public void deleteContact(Integer id)throws DaoException;
	public List<COntact> findAll() throws DaoException;
	public List<COntact> findBYCity(String city)throws DaoException;
	public List<COntact> findByCountry(String country)throws DaoException;
}
