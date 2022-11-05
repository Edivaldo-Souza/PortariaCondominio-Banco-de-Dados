package model;

import java.util.List;
import java.util.ArrayList;
import entities.MoradorRepresentation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaginaPrincipalModel extends DAO{
	public List<MoradorRepresentation> selectAll() {
		String sql = "select * from moradoresrepresentantes order by bloco;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<MoradorRepresentation> moradores = new ArrayList<MoradorRepresentation>();
			
			while(rs.next()) {
				MoradorRepresentation morador = new MoradorRepresentation();
				morador.setAp(rs.getString("apartamento"));
				morador.setBloco(rs.getString("bloco"));
				morador.setNome(rs.getString("nome"));
				morador.setCpf(rs.getString("cpf"));
				
				moradores.add(morador);
			}
			return moradores;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public MoradorRepresentation selectByCpf(String cpf) {
		String sql = "select * from moradoresrepresentantes where cpf=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				MoradorRepresentation morador = new MoradorRepresentation();
				morador.setAp(rs.getString("apartamento"));
				morador.setBloco(rs.getString("bloco"));
				morador.setNome(rs.getString("nome"));
				morador.setCpf(rs.getString("cpf"));
				return morador;
			}
			else return null;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	
	public void delete(String cpf) {
		String sql = "delete from moradoresrepresentantes where cpf=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from moradoresdependentes where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from empregados where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from veiculos where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from animais where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from lista_emergencia where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		sql = "delete from informacoes_imobiliaria where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ps.execute();
			
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
