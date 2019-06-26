package javatraining.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javatraining.entity.COntact;

public class JDBCContactsDao implements ContactsDAO{

	private ResultSet  keys;

	@Override
	public COntact addContact(COntact contact) throws DaoException {
		String sql ="insert into contacts(name,gender,email,phone,city,country) values(?,?,?,?,?,?)";
		try(
				Connection	 con=DBUtil.getConnection();
				PreparedStatement stmt= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		){
			stmt.setString(1,contact.getName());
			stmt.setString(2,contact.getGender());
			stmt.setString(2, contact.getGender());
			stmt.setString(3,contact.getEmail());
			stmt.setString(4,contact.getPhone());
			stmt.setString(5, contact.getCity());
			stmt.setString(6, contact.getCountry());
			stmt.executeUpdate();
			 keys = stmt.getGeneratedKeys();
			 keys.next();
			 contact.setId(keys.getInt(1));
			 return(contact);
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public COntact findById(Integer id) throws DaoException {
		String sql = "select * from contacts where id = ?";
		try(
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
		)
		{
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				COntact c = buildContact(rs);
				rs.close();
				return c;
			}
		}catch(Exception e) {
			throw new DaoException(e);
		}
		return null;
		
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private COntact buildContact(ResultSet rs) throws SQLException {
		COntact c= new COntact();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setGender(rs.getString("gender"));
		c.setEmail(rs.getString("email"));
		c.setPhone(rs.getString("phone"));
		c.setCity(rs.getString("country"));
		c.setCountry(rs.getString("country"));
		return c;
	}

	@Override
	public COntact updateContact(COntact ct) throws DaoException {
		String sql = "update contacts set name =?,gender =?,email = ?,phone = ?,city = ?,country=? where id = ?";
		try(
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt= con.prepareStatement(sql);
				
		){
			stmt.setString(1, ct.getName());
			stmt.setString(2, ct.getGender());
			stmt.setString(3, ct.getEmail());
			stmt.setString(4, ct.getPhone());
			stmt.setString(5, ct.getCity());
			stmt.setString(6, ct.getCountry());
			stmt.setInt(7, ct.getId());
			int count = stmt.executeUpdate();
			if(count ==0) {
				throw new DaoException("No record was updated for contact, as invalid contact id: "+ct.getId());
				}
			
			
			
		}
		catch(Exception e) {
			throw new DaoException(e);
		}
		return ct;
	}

	@Override
	public void deleteContact(Integer id) throws DaoException {
		String sql = "delete  from contacts where id =?";
		try(
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				
		)
		{
			stmt.setInt(1, id);
			int count = stmt.executeUpdate();
			if(count ==0) {
				throw new DaoException("Record was not deleted, invalid id: "+id);
			}
			
		}catch(Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<COntact> findAll() throws DaoException {
		String sql = "select * from contacts";
		ArrayList<COntact> cts = new ArrayList<>();
		try(
				
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				
		)
		{
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				COntact c= buildContact(rs);
				cts.add(c);
				
			}
			return cts;
			
		}catch(Exception e) {
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<COntact> findBYCity(String city) throws DaoException {
		String sql = "select * from contacts where city = ?";
		ArrayList<COntact> clist = new ArrayList<>();
		try(
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				
	){
		stmt.setString(1, city);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			COntact c = buildContact(rs);
			clist.add(c);
		}
		return clist;
		
			
		}catch(Exception e) {
			throw new DaoException(e);
			
			
			
		}
		
		
		
		
		
		
	}

	@Override
	public List<COntact> findByCountry(String country) throws DaoException {
		String sql = "select * from contacts where country = ?";
		ArrayList<COntact> clist= new ArrayList<>();
		try(
				Connection con = DBUtil.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
		){
			stmt.setString(1, country);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				COntact c = buildContact(rs);
				clist.add(c);
			}
			return clist;
			
		}catch(Exception e) {
			throw new DaoException(e);
		}
		
		
		
		
	}

}
