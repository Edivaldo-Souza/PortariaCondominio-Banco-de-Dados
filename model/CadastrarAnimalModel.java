package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Animal;

public class CadastrarAnimalModel extends DAO{
	public void submit(Animal entity, String cpf) {
		String sql = "insert into animais (cpf_representante,especie,nome,cor,raca,tipo_animal) values(?,?,?,?,?,?);";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, entity.getEspecie());
			ps.setString(3, entity.getNome());
			ps.setString(4, entity.getCor());
			ps.setString(5, entity.getRaca());
			ps.setInt(6, entity.getTipo_animal());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void saveChanges(Animal entity, String cpf, int id) {
		String sql = "update animais set especie=?, nome=?, cor=?, raca=?, tipo_animal=? where cpf_representante=? and idAnimal=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, entity.getEspecie());
			ps.setString(2, entity.getNome());
			ps.setString(3, entity.getCor());
			ps.setString(4, entity.getRaca());
			ps.setInt(5, entity.getTipo_animal());
			ps.setString(6, cpf);
			ps.setInt(7, id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mudanças salvas");
	}
	public List<Animal> selectAll(String cpf) {
		String sql = "select * from animais where cpf_representante=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			List<Animal> entities = new ArrayList<Animal>();
			
			while(rs.next()) {
				Animal entity = new Animal();
				entity.setId(rs.getInt("idAnimal"));
				entity.setNome(rs.getString("nome"));
				entity.setRaca(rs.getString("raca"));
				entity.setCor(rs.getString("cor"));
				entity.setEspecie(rs.getString("especie"));
				
				entities.add(entity);
			}
			return entities;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	public Animal loadData(String cpf, int id) {
		String sql = "select * from animais where cpf_representante=? and idAnimal=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			Animal entity = new Animal();
			while(rs.next()) {
				entity.setNome(rs.getString("nome"));
				entity.setRaca(rs.getString("raca"));
				entity.setCor(rs.getString("cor"));
				entity.setEspecie(rs.getString("especie"));
				entity.setTipo_animal(rs.getInt("tipo_animal"));
			}
			return entity;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void delete(String cpf, int id) {
		String sql = "delete from animais where cpf_representante=? and idAnimal=?;";
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
