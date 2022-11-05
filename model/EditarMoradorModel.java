package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.MoradorRepresentation;
import entities.Imobiliaria;

public class EditarMoradorModel extends DAO{
	
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
				morador.setRg(rs.getString("rg"));
				morador.setCpf(rs.getString("cpf"));
				morador.setEmail(rs.getString("email"));
				morador.setData(rs.getString("nascimento"));
				morador.setTel_celular(rs.getString("tel_celular"));
				morador.setTel_residencial(rs.getString("tel_residencial"));
				morador.setTel_comercial(rs.getString("tel_comercial"));
				morador.setTel_celular(rs.getString("tel_celular"));
				morador.setPropietario(rs.getBoolean("condicao"));
				morador.setAutorizacao(rs.getBoolean("autorizacaoWP"));
			
				return morador;
			}
			else return null;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
	}
	public Imobiliaria selectImobByCpf(String cpf) {
		String sql = "select * from informacoes_imobiliaria where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,cpf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Imobiliaria imob = new Imobiliaria();
				imob.setDataMudanca(rs.getString("data_mudanca"));
				imob.setDataFim(rs.getString("data_fim_contrato"));
				imob.setEmpresa(rs.getString("empresa_nome"));
				imob.setContato(rs.getString("nome_contato"));
				imob.setTelContato(rs.getString("telefone"));
			
				return imob;
			}
			else return null;
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		
	}
	public void saveChanges(MoradorRepresentation e) {
		String sql = "update moradoresrepresentantes set apartamento=?, bloco=?, condicao=?, autorizacaoWP=?, nome=?, rg=? where cpf=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, e.getAp());
			ps.setString(2, e.getBloco());
			ps.setBoolean(3, e.isPropietario());
			ps.setBoolean(4, e.isAutorizacao());
			ps.setString(5, e.getNome());
			ps.setString(6, e.getRg());
			ps.setString(7, e.getCpf());
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		sql = "update moradoresrepresentantes set nascimento=?, email=?, tel_residencial=?, tel_comercial=?, tel_celular=? where cpf=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, e.getData());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getTel_residencial());
			ps.setString(4, e.getTel_comercial());
			ps.setString(5, e.getTel_celular());
			ps.setString(6, e.getCpf());
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void saveChangesImob(Imobiliaria e, String cpf) {
		String sql = "update informacoes_imobiliaria set data_mudanca=?, data_fim_contrato=?, empresa_nome=?, nome_contato=?, telefone=? where cpf_representante=?;";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, e.getDataMudanca());
			ps.setString(2, e.getDataFim());
			ps.setString(3, e.getEmpresa());
			ps.setString(4, e.getContato());
			ps.setString(5, e.getTelContato());
			ps.setString(6, cpf);
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
