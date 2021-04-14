package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammiDAO {
	
	public boolean checkAnagram(String parola) {
		
		final String sql = "SELECT p.nome "
				+ "FROM parola p "
				+ "WHERE p.nome=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, parola);

			ResultSet rs = st.executeQuery();

			if(rs.next())
				return true;
			
			rs.close();
			st.close();
			conn.close();
			}
		catch (SQLException e) {
			throw new RuntimeException("Errore nella lettura del dizionario", e);
		}
		return false;
	}
	
}
