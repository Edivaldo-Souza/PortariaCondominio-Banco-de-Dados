package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import entities.Imobiliaria;
import entities.MoradorRepresentation;

public class CadastrarMoradorModel extends DAO{
	public void submit(MoradorRepresentation entityM,Imobiliaria entity) {
		if(entityM.getCpf().isEmpty()) {
			JOptionPane.showMessageDialog(null,"CPF Obrigatório");
		}
		else {
			String sql = "insert into moradoresrepresentantes (apartamento,bloco,condicao,autorizacaoWP,nome,cpf,rg,nascimento,email,tel_residencial,tel_comercial,tel_celular) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			try {
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, entityM.getAp());
				ps.setString(2, entityM.getBloco());
				ps.setBoolean(3, entityM.isPropietario());
				ps.setBoolean(4, entityM.isAutorizacao());
				ps.setString(5, entityM.getNome());
				ps.setString(6, entityM.getCpf());
				ps.setString(7, entityM.getRg());
				ps.setString(8, entityM.getData());
				ps.setString(9, entityM.getEmail());
				ps.setString(10, entityM.getTel_residencial());
				ps.setString(11, entityM.getTel_comercial());
				ps.setString(12, entityM.getTel_celular());
				ps.execute();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			if(!entityM.isPropietario()) {
				try {
					sql = "insert into informacoes_imobiliaria (cpf_representante,data_mudanca,empresa_nome,nome_contato,telefone,data_fim_contrato) values (?,?,?,?,?,?);";
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1, entityM.getCpf());
					ps.setString(2, entity.getDataMudanca());
					ps.setString(3, entity.getEmpresa());
					ps.setString(4, entity.getContato());
					ps.setString(5, entity.getTelContato());
					ps.setString(6, entity.getDataFim());
					ps.execute();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("dados submetidos");
		}
	}
}
