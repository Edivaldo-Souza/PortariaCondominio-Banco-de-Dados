package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import model.CadastrarVeiculoModel;
import entities.Veiculo;

public class CadastrarVeiculoController extends CadastrarEmpregadoController{
	@FXML
	private TextField modelo;
	@FXML
	private Label rotuloModelo;
	@FXML
	private Label rotuloCor;
	@FXML
	private Label rotuloPlaca;
	@FXML
	private Pane painel;
	@FXML
	private Label rotuloID;
	@FXML
	private Button quit;
	@FXML
	private Label id;
	@FXML
	private TextField marca;
	@FXML
	private TextField cor;
	@FXML
	private TextField placa;
	
	private static String cpf_representante;
	
	public static void setIdCpf(String cpf) {
		if(!cpf.isEmpty()) cpf_representante = cpf;
	}
	
	@Override
	public void limparEspacos() {
		modelo.setText("");
		marca.setText("");
		cor.setText("");
		placa.setText("");
	}

	// Event Listener on Button.onAction
	@FXML
	public void adicionar(ActionEvent event) {
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
		Veiculo entity = new Veiculo();
		CadastrarVeiculoModel model = new CadastrarVeiculoModel();
		
		if(idSelecionado == "...") {
			if(!modelo.getText().isEmpty() || !marca.getText().isEmpty() || !cor.getText().isEmpty() || !placa.getText().isEmpty()) {
				entity.setModelo(modelo.getText());
				entity.setMarca(marca.getText());
				entity.setCor(cor.getText());
				entity.setPlaca(placa.getText());
				model.submit(entity, cpf_representante);
				limparEspacos();
				JOptionPane.showMessageDialog(null, "Dados Salvos");
			}
			else {
				JOptionPane.showMessageDialog(null, "Informe todos os dados!");
			}
		}
		else {
			entity.setModelo(modelo.getText());
			entity.setMarca(marca.getText());
			entity.setCor(cor.getText());
			entity.setPlaca(placa.getText());
			int idNum = Integer.parseInt(idSelecionado);
			model.saveChanges(entity, cpf_representante, idNum);
			limparEspacos();
			setID();
			JOptionPane.showMessageDialog(null, "Dados Salvos");
		}
	}
	// Event Listener on Button.onAction
	@FXML
	public void nextPage(ActionEvent event) {
		Main.pageCadastrarAnimal(cpf_representante);
	}
	// Event Listener on Button.onAction
	@FXML
	public void exibir(ActionEvent event) {
		removerTodosMoradores();
		CadastrarVeiculoModel model = new CadastrarVeiculoModel();
		List<Veiculo> entities = model.selectAll(cpf_representante);
		Iterator<Veiculo> it = entities.iterator();
		painel.setPrefHeight(entities.size()*TOOLBAR_HEIGHT);;
		
		List<ToolBar> listaMoradores = new ArrayList<ToolBar>();
		int contador = 0;
		while(it.hasNext()) {
			
			Veiculo entity = it.next();
			
			Label Lid = new Label(Integer.toString(entity.getId()));
			Lid.setAlignment(rotuloID.getAlignment());
			Lid.setPrefWidth(rotuloID.getWidth());
			
			Label LModelo = new Label(entity.getModelo());
			LModelo.setAlignment(rotuloModelo.getAlignment());
			LModelo.setPrefWidth(rotuloModelo.getWidth());
			
			Label LCor = new Label(entity.getCor());
			LCor.setAlignment(rotuloCor.getAlignment());
			LCor.setPrefWidth(rotuloCor.getWidth());
			
			Label LPlaca = new Label(entity.getPlaca());
			LPlaca.setAlignment(rotuloPlaca.getAlignment());
			LPlaca.setPrefWidth(rotuloPlaca.getWidth());
			
			Button editar = new Button("editar");
			editar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					idSelecionado = Integer.toString(entity.getId());
					id.setText(idSelecionado);
					Veiculo m = model.loadData(cpf_representante, entity.getId());
					modelo.setText(m.getModelo());
					marca.setText(m.getMarca());
					cor.setText(m.getCor());
					placa.setText(m.getPlaca());
				}
			});
			Button excluir = new Button("X");
			excluir.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					limparEspacos();
					model.delete(cpf_representante,entity.getId());
					exibir(event);
					
				}
			});
			
			listaMoradores.add(new ToolBar(Lid,LModelo,LCor,LPlaca,editar,excluir));

			listaMoradores.get(contador).setLayoutX(0);
			listaMoradores.get(contador).setLayoutY(contador*TOOLBAR_HEIGHT);
			listaMoradores.get(contador).setPrefSize(TOOLBAR_WIDTH,TOOLBAR_HEIGHT);
			painel.getChildren().add(listaMoradores.get(contador));
			
			contador++;
		}
		System.out.println(contador);
	}
	// Event Listener on Button.onAction
	@FXML
	public void previousPage(ActionEvent event) {
		Main.pageCadastrarEmpregado(cpf_representante);
	}
}
