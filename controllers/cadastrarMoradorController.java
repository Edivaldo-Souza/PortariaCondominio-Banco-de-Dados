package controllers;

import model.CadastrarMoradorModel;
import application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import javax.swing.JOptionPane;
import entities.Imobiliaria;
import entities.MoradorRepresentation;

public class cadastrarMoradorController {
	@FXML
	private TextField nome;
	@FXML
	private TextField cpf;
	@FXML
	private TextField rg;
	@FXML
	private TextField dataNasc;
	@FXML
	private TextField email;
	@FXML
	private TextField tel_Residencial;
	@FXML
	private TextField tel_Comercial;
	@FXML
	private TextField tel_Celular;
	@FXML
	private TextField ap;
	@FXML
	private TextField bloco;
	@FXML
	private RadioButton prop;
	@FXML
	private RadioButton loc;
	@FXML
	private TextField dataMudanca;
	@FXML
	private TextField dataFim;
	@FXML
	private TextField empresa;
	@FXML
	private TextField contato;
	@FXML
	private TextField telContato;
	@FXML
	private CheckBox desejaIncluir;
	@FXML
	private Button prox;
	@FXML
	private Button quit;
	CadastrarMoradorModel model;
	
	@FXML
	public void submit(ActionEvent event) {
		model = new CadastrarMoradorModel();
		if((cpf.getText().length() > 14 || cpf.getText().length() < 1) || !ap.getText().isEmpty() ||
			!bloco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,"CPF inválido");
		}
		else {
			
			if(JOptionPane.showConfirmDialog(null, "Deseja salvar essas informaçõe?") == 0) {
				
				boolean isPropietario = false;
				if(prop.isSelected()) isPropietario = true;
				else if (loc.isSelected()) isPropietario = false;
				MoradorRepresentation morador = new MoradorRepresentation();
				Imobiliaria imob = new Imobiliaria();
				
				morador.setAp(ap.getText());
				morador.setAutorizacao(desejaIncluir.isSelected());
				morador.setBloco(bloco.getText());
				morador.setCpf(cpf.getText());
				morador.setData(dataNasc.getText());
				morador.setEmail(email.getText());
				morador.setNome(nome.getText());
				morador.setPropietario(isPropietario);
				morador.setRg(rg.getText());
				morador.setTel_celular(tel_Celular.getText());
				morador.setTel_comercial(tel_Comercial.getText());
				morador.setTel_residencial(tel_Residencial.getText());
				
				imob.setDataMudanca(dataMudanca.getText());
				imob.setDataFim(dataFim.getText());
				imob.setContato(contato.getText());
				imob.setEmpresa(empresa.getText());
				imob.setTelContato(telContato.getText());
				
				model.submit(morador,imob);
				Main.pageCadastrarMoradorAdicional(cpf.getText());
			}
		}
	}
	
	@FXML
	public void returnToMainPage(ActionEvent event) {
		Main.mainPage();
	}
	
	@FXML
	public void lockTextField(ActionEvent event) {
		dataMudanca.setDisable(true);
		dataFim.setDisable(true);
		contato.setDisable(true);
		empresa.setDisable(true);
		telContato.setDisable(true);
	}
	@FXML
	public void unlockTextField(ActionEvent event) {
		dataMudanca.setDisable(false);
		dataFim.setDisable(false);
		contato.setDisable(false);
		empresa.setDisable(false);
		telContato.setDisable(false);
	}

}
