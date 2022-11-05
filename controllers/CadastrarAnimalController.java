package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;
import entities.Animal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

import javafx.scene.layout.Pane;
import model.CadastrarAnimalModel;

public class CadastrarAnimalController extends CadastrarEmpregadoController{
	@FXML
	private TextField outraEsp;
	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloEspecie;
	@FXML
	private Label rotuloRaca;
	@FXML
	private Pane painel;
	@FXML
	private Label rotuloID;
	@FXML
	private Button quit;
	@FXML
	private Label id;
	@FXML
	private TextField nome;
	@FXML
	private TextField cor;
	@FXML
	private TextField raca;
	@FXML
	private RadioButton cachorro;
	@FXML
	private ToggleGroup grupoEspecie;
	@FXML
	private RadioButton gato;
	@FXML
	private RadioButton outro;
	
	private static String cpf_representante;
	
	private final int GATO = 1;
	private final int CACHORRO = 0;
	private final int OUTRO_TIPO = 2;
	
	public static void setIdCpf(String cpf) {
		if(!cpf.isEmpty()) cpf_representante = cpf;
	}
	
	@Override
	public void limparEspacos() {
		outraEsp.setText("");
		nome.setText("");
		cor.setText("");
		raca.setText("");
		cachorro.setSelected(false);
		gato.setSelected(false);
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
		Animal entity = new Animal();
		CadastrarAnimalModel model = new CadastrarAnimalModel();
		
		if(idSelecionado == "...") {
			if(!nome.getText().isEmpty() || !cor.getText().isEmpty() || !raca.getText().isEmpty() || !outraEsp.getText().isEmpty()
					|| (!cachorro.isSelected() && !gato.isSelected() && !outraEsp.getText().isEmpty())) {
				entity.setNome(nome.getText());
				entity.setRaca(raca.getText());
				entity.setCor(cor.getText());
				if(!outraEsp.getText().isEmpty() && outro.isSelected()) {
					entity.setTipo_animal(OUTRO_TIPO);
					entity.setEspecie(outraEsp.getText());
				}
				else if(gato.isSelected()) {
					entity.setTipo_animal(GATO);
					entity.setEspecie("Gato");
				}
				else if(cachorro.isSelected()){
					entity.setTipo_animal(CACHORRO);
					entity.setEspecie("Cachorro");
				}
				model.submit(entity, cpf_representante);
				limparEspacos();
				JOptionPane.showMessageDialog(null, "Dados Salvos");
			}
			else {
				JOptionPane.showMessageDialog(null, "Informe todos os dados!");
			}
		}
		else {
			entity.setNome(nome.getText());
			entity.setRaca(raca.getText());
			entity.setCor(cor.getText());
			if(!outraEsp.getText().isEmpty()) {
				entity.setTipo_animal(OUTRO_TIPO);
				entity.setEspecie(outraEsp.getText());
			}
			else if(gato.isSelected()) {
				entity.setTipo_animal(GATO);
				entity.setEspecie("Gato");
			}
			else if(cachorro.isSelected()){
				entity.setTipo_animal(CACHORRO);
				entity.setEspecie("Cachorro");
			}
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
		Main.pageCadastrarContato(cpf_representante);
	}
	// Event Listener on Button.onAction
	@FXML
	public void exibir(ActionEvent event) {
		removerTodosMoradores();
		CadastrarAnimalModel model = new CadastrarAnimalModel();
		List<Animal> entities = model.selectAll(cpf_representante);
		Iterator<Animal> it = entities.iterator();
		painel.setPrefHeight(entities.size()*TOOLBAR_HEIGHT);;
		
		List<ToolBar> listaMoradores = new ArrayList<ToolBar>();
		int contador = 0;
		while(it.hasNext()) {
			
			Animal entity = it.next();
			
			Label Lid = new Label(Integer.toString(entity.getId()));
			Lid.setAlignment(rotuloID.getAlignment());
			Lid.setPrefWidth(rotuloID.getWidth());
			
			Label LNome = new Label(entity.getNome());
			LNome.setAlignment(rotuloNome.getAlignment());
			LNome.setPrefWidth(rotuloNome.getWidth());
			
			Label LEspecie = new Label(entity.getEspecie());
			LEspecie.setAlignment(rotuloEspecie.getAlignment());
			LEspecie.setPrefWidth(rotuloEspecie.getWidth());
			
			Label LRaca = new Label(entity.getRaca());
			LRaca.setAlignment(rotuloRaca.getAlignment());
			LRaca.setPrefWidth(rotuloRaca.getWidth());
			
			Button editar = new Button("editar");
			editar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					idSelecionado = Integer.toString(entity.getId());
					id.setText(idSelecionado);
					Animal m = model.loadData(cpf_representante, entity.getId());
					nome.setText(m.getNome());
					raca.setText(m.getRaca());
					cor.setText(m.getCor());
					if(m.getTipo_animal()==0) {
						cachorro.setSelected(true);
					}
					else if(m.getTipo_animal()==1) {
						gato.setSelected(true);
					}
					else {
						outraEsp.setText(m.getEspecie());
					}
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
			
			listaMoradores.add(new ToolBar(Lid,LNome,LEspecie,LRaca,editar,excluir));

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
		Main.pageCadastrarVeiculo(cpf_representante);
	}
	@FXML
	public void unlockTextField(ActionEvent event) {
		outraEsp.setDisable(false);
	}
	@FXML
	public void lockTextField(ActionEvent event) {
		outraEsp.setDisable(true);
	}
}
