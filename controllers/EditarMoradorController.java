package controllers;

import application.Main;
import model.EditarMoradorModel;
import entities.MoradorRepresentation;
import entities.Imobiliaria;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import javax.swing.JOptionPane;

public class EditarMoradorController{
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
	private CheckBox desejaIncluir;
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
	private Button salvar;
	@FXML
	private Button quit;
	
	private boolean dadosCarregados = false;
	
	private static String idCpf;
	
	public static void setIdCpf(String cpf) {
		if(!cpf.isEmpty()) idCpf = cpf;
	}
	
	@FXML
	public void definirDados() {
		EditarMoradorModel model = new EditarMoradorModel();
		MoradorRepresentation morador = model.selectByCpf(idCpf);
		nome.setText(morador.getNome());
		cpf.setText(morador.getCpf());
		rg.setText(morador.getRg());
		dataNasc.setText(morador.getData());
		tel_Residencial.setText(morador.getTel_residencial());
		tel_Comercial.setText(morador.getTel_comercial());
		tel_Celular.setText(morador.getTel_celular());
		ap.setText(morador.getAp());
		bloco.setText(morador.getBloco());
		email.setText(morador.getEmail());
		if(morador.isPropietario()) prop.setSelected(true);
		else loc.setSelected(true);
		desejaIncluir.setSelected(morador.isAutorizacao());
		
		if(!morador.isPropietario()) {
			Imobiliaria imob = model.selectImobByCpf(idCpf);
			dataMudanca.setText(imob.getDataMudanca());
			dataFim.setText(imob.getDataFim());
			empresa.setText(imob.getEmpresa());
			contato.setText(imob.getContato());
			telContato.setText(imob.getTelContato());
			unlockTextField();
		}
		dadosCarregados = true;
	}

	// Event Listener on Button[#prox].onAction
	@FXML
	public void salvarAlteracoes(ActionEvent event) {
		if(dadosCarregados) {
			EditarMoradorModel model = new EditarMoradorModel();
			MoradorRepresentation morador = new MoradorRepresentation();
			morador.setNome(nome.getText());
			morador.setCpf(cpf.getText());
			morador.setRg(rg.getText());
			morador.setEmail(email.getText());
			morador.setTel_celular(tel_Celular.getText());
			morador.setTel_comercial(tel_Comercial.getText());
			morador.setTel_residencial(tel_Residencial.getText());
			morador.setData(dataNasc.getText());
			morador.setAp(ap.getText());
			morador.setBloco(bloco.getText());
			if(prop.isSelected()) morador.setPropietario(true);
			else if (loc.isSelected()) morador.setPropietario(false);
			morador.setAutorizacao(desejaIncluir.isSelected());
			model.saveChanges(morador);
			
			if(!morador.isPropietario()) {
				if(dataMudanca.getText().isEmpty() || dataFim.getText().isEmpty() 
						|| empresa.getText().isEmpty()) {
					Imobiliaria imob = model.selectImobByCpf(idCpf);
					imob.setDataMudanca(dataMudanca.getText());
					imob.setDataFim(dataFim.getText());
					imob.setEmpresa(empresa.getText());
					imob.setContato(contato.getText());
					imob.setTelContato(telContato.getText());
					model.saveChangesImob(imob, idCpf);
				}
			}
			
			JOptionPane.showMessageDialog(null,"Dados Salvos");
		}
	}
	
	
	// Event Listener on Button[#quit].onAction
	@FXML
	public void returnToMainPage(ActionEvent event) {
		Main.mainPage();
	}
	@FXML
	public void nextPage(ActionEvent event) {
		Main.pageCadastrarMoradorAdicional(idCpf);
	}
	@FXML
	public void lockTextField() {
		dataMudanca.setDisable(true);
		dataFim.setDisable(true);
		contato.setDisable(true);
		empresa.setDisable(true);
		telContato.setDisable(true);
	}
	@FXML
	public void unlockTextField() {
		dataMudanca.setDisable(false);
		dataFim.setDisable(false);
		contato.setDisable(false);
		empresa.setDisable(false);
		telContato.setDisable(false);
	}
}
