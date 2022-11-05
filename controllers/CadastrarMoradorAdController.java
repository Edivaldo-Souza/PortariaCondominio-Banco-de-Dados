package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.Main;
import entities.MoradorAdicional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

import javafx.scene.layout.Pane;
import model.CadastrarMoradorAdicionalModel;

public class CadastrarMoradorAdController {
	@FXML
	private TextField nome;
	@FXML
	private TextField nascimento;
	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloParentesco;
	@FXML
	private Label rotuloData;
	@FXML
	private Pane painelMoradores;
	@FXML
	private TextField parentesco;
	@FXML
	private Label rotuloID;
	@FXML
	private Button quit;
	@FXML
	private  Label idMoradorSelecionado;
	
	private static String cpf_representante;
	private  String idSelecionado = "...";
	
	private final int TOOLBAR_HEIGHT = 40;
	private final int TOOLBAR_WIDTH = 800;
	
	public static void setIdCpf(String cpf) {
		if(!cpf.isEmpty()) cpf_representante = cpf;
	}
	
	public void limparEspacos() {
		this.nome.setText("");
		this.parentesco.setText("");
		this.nascimento.setText("");
	}
	
	public void removerTodosMoradores() {
		while(!painelMoradores.getChildren().isEmpty()) {
			for(int i = 0; i<painelMoradores.getChildren().size(); i++) {
				painelMoradores.getChildren().remove(i);
			}
		}
	}
	public void setID() {
		idSelecionado = "...";
		idMoradorSelecionado.setText(idSelecionado);
	}

	// Event Listener on Button.onAction
	@FXML
	public void adicionarMorador(ActionEvent event) {
		limparEspacos();
		setID();
	}
	// Event Listener on Button[#quit].onAction
	@FXML
	public void returnToMainPage(ActionEvent event) {
		Main.mainPage();
	}
	// Event Listener on Button.onAction
	@FXML
	public void salvarDados(ActionEvent event) {
		MoradorAdicional morador = new MoradorAdicional();
		CadastrarMoradorAdicionalModel model = new CadastrarMoradorAdicionalModel();
		
		if(idSelecionado == "...") {
			if(!nome.getText().isEmpty() || !parentesco.getText().isEmpty() || !nascimento.getText().isEmpty()) {
				morador.setNome(nome.getText());
				morador.setParentesco(parentesco.getText());
				morador.setDataNasc(nascimento.getText());
				model.submit(morador, cpf_representante);
				limparEspacos();
				JOptionPane.showMessageDialog(null, "Dados Salvos");
			}
			else {
				JOptionPane.showMessageDialog(null, "Informe todos os dados!");
			}
		}
		else {
			morador.setNome(nome.getText());
			morador.setParentesco(parentesco.getText());
			morador.setDataNasc(nascimento.getText());
			int idNum = Integer.parseInt(idSelecionado);
			model.saveChanges(morador, cpf_representante, idNum);
			limparEspacos();
			setID();
			JOptionPane.showMessageDialog(null, "Dados Salvos");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void nextPage(ActionEvent event) {
		Main.pageCadastrarEmpregado(cpf_representante);
	}
	@FXML
	public void previousPage(ActionEvent event) {
		Main.pageEditarMorador_A(cpf_representante);
	}
	// Event Listener on Button.onAction
	@FXML
	public void exibirMoradores(ActionEvent event) {
		removerTodosMoradores();
		CadastrarMoradorAdicionalModel model = new CadastrarMoradorAdicionalModel();
		List<MoradorAdicional> moradores = model.selectAll(cpf_representante);
		Iterator<MoradorAdicional> moradores_It = moradores.iterator();
		painelMoradores.setPrefHeight(moradores.size()*TOOLBAR_HEIGHT);;
		
		List<ToolBar> listaMoradores = new ArrayList<ToolBar>();
		int contador = 0;
		while(moradores_It.hasNext()) {
			
			MoradorAdicional morador = moradores_It.next();
			
			Label Lid = new Label(Integer.toString(morador.getId()));
			Lid.setAlignment(rotuloID.getAlignment());
			Lid.setPrefWidth(rotuloID.getWidth());
			
			Label Lnome = new Label(morador.getNome());
			Lnome.setAlignment(rotuloNome.getAlignment());
			Lnome.setPrefWidth(rotuloNome.getWidth());
			
			Label Lparentesco = new Label(morador.getParentesco());
			Lparentesco.setAlignment(rotuloParentesco.getAlignment());
			Lparentesco.setPrefWidth(rotuloParentesco.getWidth());
			
			Label Lnascimento = new Label(morador.getDataNasc());
			Lnascimento.setAlignment(rotuloData.getAlignment());
			Lnascimento.setPrefWidth(rotuloData.getWidth());
			
			Button editar = new Button("editar");
			editar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					idSelecionado = Integer.toString(morador.getId());
					idMoradorSelecionado.setText(idSelecionado);
					MoradorAdicional m = model.loadData(cpf_representante, morador.getId());
					nome.setText(m.getNome());
					parentesco.setText(m.getParentesco());
					nascimento.setText(m.getDataNasc());
				}
			});
			Button excluir = new Button("X");
			excluir.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					model.delete(cpf_representante,morador.getId());
					exibirMoradores(event);
				}
			});
			
			listaMoradores.add(new ToolBar(Lid,Lnome,Lparentesco,Lnascimento,editar,excluir));

			listaMoradores.get(contador).setLayoutX(0);
			listaMoradores.get(contador).setLayoutY(contador*TOOLBAR_HEIGHT);
			listaMoradores.get(contador).setPrefSize(TOOLBAR_WIDTH,TOOLBAR_HEIGHT);
			painelMoradores.getChildren().add(listaMoradores.get(contador));
			
			contador++;
		}
		System.out.println(contador);
	}
}
