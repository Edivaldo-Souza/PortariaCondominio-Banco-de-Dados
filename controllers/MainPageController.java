package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import application.Main;
import entities.MoradorRepresentation;
import model.PaginaPrincipalModel;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class MainPageController{
	@FXML
	private ScrollPane scrollP;
	@FXML
	private Pane painelMoradores;
	@FXML
	private Label rotuloAp;
	@FXML
	private Label rotuloBloco;
	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloCpf;
	
	@FXML
	private TextField campoDeBusca;
	
	PaginaPrincipalModel model;
	
	private final int TOOLBAR_HEIGHT = 40;
	private final int TOOLBAR_WIDGHT = 529;
	
	@FXML
	public void cadastrarMorador(ActionEvent event) {
		Main.pageCadastrarMorador();
		System.out.println("cadastar morador");
	}
	
	@FXML
	public void exibirTodosMoradores() {
		removerTodosMoradores();
		model = new PaginaPrincipalModel();
		List<MoradorRepresentation> moradores = model.selectAll();
		Iterator<MoradorRepresentation> moradores_It = moradores.iterator();
		painelMoradores.setPrefHeight(moradores.size()*TOOLBAR_HEIGHT);;
		
		List<ToolBar> listaMoradores = new ArrayList<ToolBar>();
		int contador = 0;
		while(moradores_It.hasNext()) {
			
			MoradorRepresentation morador = moradores_It.next();
			
			Label ap = new Label(morador.getAp());
			ap.setAlignment(rotuloAp.getAlignment());
			ap.setPrefWidth(rotuloAp.getWidth());
			
			Label bloco = new Label(morador.getBloco());
			bloco.setAlignment(rotuloBloco.getAlignment());
			bloco.setPrefWidth(rotuloBloco.getWidth());
			
			Label nome = new Label(morador.getNome());
			nome.setAlignment(rotuloNome.getAlignment());
			nome.setPrefWidth(rotuloNome.getWidth());
			
			Label cpf = new Label(morador.getCpf());
			cpf.setAlignment(rotuloCpf.getAlignment());
			cpf.setPrefWidth(rotuloCpf.getWidth());
			
			Button editar = new Button("editar");
			editar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Main.pageEditarMorador_A(morador.getCpf());
				}
			});
			Button excluir = new Button("X");
			excluir.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					model.delete(morador.getCpf());
					exibirTodosMoradores();
				}
			});
			
			listaMoradores.add(new ToolBar(ap,bloco,nome,cpf,editar,excluir));

			listaMoradores.get(contador).setLayoutX(0);
			listaMoradores.get(contador).setLayoutY(contador*TOOLBAR_HEIGHT);
			listaMoradores.get(contador).setPrefSize(TOOLBAR_WIDGHT,TOOLBAR_HEIGHT);
			painelMoradores.getChildren().add(listaMoradores.get(contador));
			
			contador++;
		}
		System.out.println(contador);
	}
		
	public void removerTodosMoradores() {
		while(!painelMoradores.getChildren().isEmpty()) {
			for(int i = 0; i<painelMoradores.getChildren().size(); i++) {
				painelMoradores.getChildren().remove(i);
			}
		}
	}
	
	@FXML
	public void exibirPorCpf() {
		removerTodosMoradores();
		model = new PaginaPrincipalModel();
		MoradorRepresentation morador = model.selectByCpf(campoDeBusca.getText());
		
		Label ap = new Label(morador.getAp());
		ap.setAlignment(rotuloAp.getAlignment());
		ap.setPrefWidth(rotuloAp.getWidth());
		
		Label bloco = new Label(morador.getBloco());
		bloco.setAlignment(rotuloBloco.getAlignment());
		bloco.setPrefWidth(rotuloBloco.getWidth());
		
		Label nome = new Label(morador.getNome());
		nome.setAlignment(rotuloNome.getAlignment());
		nome.setPrefWidth(rotuloNome.getWidth());
		
		Label cpf = new Label(morador.getCpf());
		cpf.setAlignment(rotuloCpf.getAlignment());
		cpf.setPrefWidth(rotuloCpf.getWidth());
		
		Button editar = new Button("editar");
		editar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.pageEditarMorador_A(morador.getCpf());
			}
		});
		Button excluir = new Button("X");
		excluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				model.delete(morador.getCpf());
				exibirTodosMoradores();
			}
		});
		
		ToolBar moradorSelecionado = new ToolBar(ap,bloco,nome,cpf,editar,excluir);
		moradorSelecionado.setLayoutX(0);
		moradorSelecionado.setLayoutY(0);
		moradorSelecionado.setPrefSize(TOOLBAR_WIDGHT,TOOLBAR_HEIGHT);
		painelMoradores.getChildren().add(moradorSelecionado);
	}	
	
}
