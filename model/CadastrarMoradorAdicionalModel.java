package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.MoradorAdicional;

public class CadastrarMoradorAdicionalModel extends DAO{
	public List<MoradorAdicional> selectAll(String cpf) {
		String sql = "select * from moradoresdependentes where cpf_representante=?";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			List<MoradorAdicional> moradores = new ArrayList<MoradorAdicional>();
			
			while(rs.next()) {
				MoradorAdicional morador = new MoradorAdicional();
				morador.setId(rs.getInt("idMorador"));
				morador.setNome(rs.getString("nome"));
				morador.setParentesco(rs.getString("parentesco"));
				morador.setDataNasc(rs.getString("nascimento"));
				
				moradores.add(morador);
			}
			return moradores;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public void submit(MoradorAdicional entity, String cpf) {
		String sql = "insert into moradoresdependentes (cpf_representante,nome,parentesco,nascimento) values(?,?,?,?);";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setString(2, entity.getNome());
			ps.setString(3, entity.getParentesco());
			ps.setString(4, entity.getDataNasc());
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveChanges(MoradorAdicional entity, String cpf, int id) {
		String sql = "update moradoresdependentes set nome=?, parentesco=?, nascimento=? where cpf_representante=? and idMorador=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getParentesco());
			ps.setString(3, entity.getDataNasc());
			ps.setString(4, cpf);
			ps.setInt(5, id);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mudanças salvas");
	}
	
	public MoradorAdicional loadData(String cpf, int id) {
		String sql = "select * from moradoresdependentes where cpf_representante=? and idMorador=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, cpf);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			MoradorAdicional morador = new MoradorAdicional();
			while(rs.next()) {
				morador.setNome(rs.getString("nome"));
				morador.setParentesco(rs.getString("parentesco"));
				morador.setDataNasc(rs.getString("nascimento"));
			}
			return morador;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void delete(String cpf, int id) {
		String sql = "delete from moradoresdependentes where cpf_representante=? and idMorador=?;";
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
