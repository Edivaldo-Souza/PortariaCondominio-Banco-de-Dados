package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Veiculo;

public class CadastrarVeiculoModel extends DAO{
	public void submit(Veiculo entity, String cpf) {
		String sql = "insert into veiculos (cpf_representante,modelo,marca,cor,placa) values(?,?,?,?,?);";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, entity.getModelo());
			ps.setString(3, entity.getMarca());
			ps.setString(4, entity.getCor());
			ps.setString(5, entity.getPlaca());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void saveChanges(Veiculo entity, String cpf, int id) {
		String sql = "update veiculos set modelo=?, marca=?, cor=?, placa=? where cpf_representante=? and idVeiculo=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, entity.getModelo());
			ps.setString(2, entity.getMarca());
			ps.setString(3, entity.getCor());
			ps.setString(4, entity.getPlaca());
			ps.setString(5, cpf);
			ps.setInt(6, id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mudanças salvas");
	}
	public List<Veiculo> selectAll(String cpf) {
		String sql = "select * from veiculos where cpf_representante=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			List<Veiculo> entities = new ArrayList<Veiculo>();
			
			while(rs.next()) {
				Veiculo entity = new Veiculo();
				entity.setId(rs.getInt("idVeiculo"));
				entity.setModelo(rs.getString("modelo"));
				entity.setMarca(rs.getString("marca"));
				entity.setCor(rs.getString("cor"));
				entity.setPlaca(rs.getString("placa"));
				
				entities.add(entity);
			}
			return entities;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	public Veiculo loadData(String cpf, int id) {
		String sql = "select * from veiculos where cpf_representante=? and idVeiculo=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			Veiculo entity = new Veiculo();
			while(rs.next()) {
				entity.setModelo(rs.getString("modelo"));
				entity.setMarca(rs.getString("marca"));
				entity.setCor(rs.getString("cor"));
				entity.setPlaca(rs.getString("placa"));
				
			}
			return entity;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void delete(String cpf, int id) {
		String sql = "delete from veiculos where cpf_representante=? and idVeiculo=?;";
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
