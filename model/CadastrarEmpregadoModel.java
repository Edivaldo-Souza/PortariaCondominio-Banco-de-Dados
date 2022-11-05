package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Empregado;


public class CadastrarEmpregadoModel extends DAO{
	public void submit(Empregado entity, String cpf) {
		String sql = "insert into empregados (cpf_representante,nome,cargo,rg,turno,tipo_turno) values(?,?,?,?,?,?);";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, entity.getNome());
			ps.setString(3, entity.getCargo());
			ps.setString(4, entity.getRg());
			ps.setString(5, entity.getTurno());
			ps.setInt(6, entity.getTipo_turno());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void saveChanges(Empregado entity, String cpf, int id) {
		String sql = "update empregados set nome=?, cargo=?, rg=?, turno=?, tipo_turno=? where cpf_representante=? and idEmpregado=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getCargo());
			ps.setString(3, entity.getRg());
			ps.setString(4, entity.getTurno());
			ps.setInt(5, entity.getTipo_turno());
			ps.setString(6, cpf);
			ps.setInt(7, id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mudanças salvas");
	}
	public List<Empregado> selectAll(String cpf) {
		String sql = "select * from empregados where cpf_representante=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			List<Empregado> entities = new ArrayList<Empregado>();
			
			while(rs.next()) {
				Empregado entity = new Empregado();
				entity.setId(rs.getInt("idEmpregado"));
				entity.setNome(rs.getString("nome"));
				entity.setRg(rs.getString("rg"));
				entity.setCargo(rs.getString("cargo"));
				entity.setTurno(rs.getString("turno"));
				
				entities.add(entity);
			}
			return entities;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	public Empregado loadData(String cpf, int id) {
		String sql = "select * from empregados where cpf_representante=? and idEmpregado=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			Empregado entity = new Empregado();
			while(rs.next()) {
				entity.setNome(rs.getString("nome"));
				entity.setRg(rs.getString("rg"));
				entity.setCargo(rs.getString("cargo"));
				entity.setTurno(rs.getString("turno"));
				
			}
			return entity;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public void delete(String cpf, int id) {
		String sql = "delete from empregados where cpf_representante=? and idEmpregado=?;";
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
