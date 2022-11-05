package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Contato;

public class CadastrarContatoModel extends DAO{
	public void submit(Contato entity, String cpf) {
		String sql = "insert into lista_emergencia (cpf_representante,nome,parentesco,contato) values(?,?,?,?);";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, entity.getNome());
			ps.setString(3, entity.getParentesco());
			ps.setString(4, entity.getTelefone());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveChanges(Contato entity, String cpf, int id) {
		String sql = "update lista_emergencia set nome=?, parentesco=?, contato=? where cpf_representante=? and idContato=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getParentesco());
			ps.setString(3, entity.getTelefone());
			ps.setString(4, cpf);
			ps.setInt(5, id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mudanças salvas");
	}
	public List<Contato> selectAll(String cpf) {
		String sql = "select * from lista_emergencia where cpf_representante=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			List<Contato> entities = new ArrayList<Contato>();
			
			while(rs.next()) {
				Contato entity = new Contato();
				entity.setId(rs.getInt("idContato"));
				entity.setNome(rs.getString("nome"));
				entity.setParentesco(rs.getString("parentesco"));
				entity.setTelefone(rs.getString("contato"));
				
				entities.add(entity);
			}
			return entities;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	public Contato loadData(String cpf, int id) {
		String sql = "select * from lista_emergencia where cpf_representante=? and idContato=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			Contato entity = new Contato();
			while(rs.next()) {
				entity.setNome(rs.getString("nome"));
				entity.setParentesco(rs.getString("parentesco"));
				entity.setTelefone(rs.getString("contato"));
			}
			return entity;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void delete(String cpf, int id) {
		String sql = "delete from lista_emergencia where cpf_representante=? and idContato=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.setInt(2, id);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
