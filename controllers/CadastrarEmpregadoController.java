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

import javafx.scene.control.RadioButton;

import javafx.scene.layout.Pane;
import model.CadastrarEmpregadoModel;
import entities.Empregado;
// showConfirmDialog
public class CadastrarEmpregadoController {
	@FXML
	private TextField nome;
	@FXML
	private TextField rg;
	@FXML
	private Label rotuloNome;
	@FXML
	private Label rotuloRG;
	@FXML
	private Label rotuloCargo;
	@FXML
	private Pane painel;
	@FXML
	private TextField cargo;
	@FXML
	private Label rotuloID;
	@FXML
	private Button quit;
	@FXML
	private Label id;
	@FXML
	private RadioButton integral;
	@FXML
	private RadioButton manha;
	@FXML
	private RadioButton tarde;
	@FXML
	private RadioButton noite;
	
	private static String cpf_representante;
	protected  String idSelecionado = "...";
	
	protected final int TOOLBAR_HEIGHT = 40;
	protected final int TOOLBAR_WIDTH = 800;
	
	private final int INTEGRAL = 0;
	private final int MANHA = 1;
	private final int TARDE = 2;
	private final int NOITE = 3;
	
	public static void setIdCpf(String cpf) {
		if(!cpf.isEmpty()) cpf_representante = cpf;
	}
	
	public void limparEspacos() {
		this.nome.setText("");
		this.rg.setText("");
		this.cargo.setText("");
		integral.setSelected(false);
		tarde.setSelected(false);
		manha.setSelected(false);
		noite.setSelected(false);
	}
	
	public void removerTodosMoradores() {
		while(!painel.getChildren().isEmpty()) {
			for(int i = 0; i<painel.getChildren().size(); i++) {
				painel.getChildren().remove(i);
			}
		}
	}
	public void setID() {
		idSelecionado = "...";
		id.setText(idSelecionado);
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
		Empregado entity = new Empregado();
		CadastrarEmpregadoModel model = new CadastrarEmpregadoModel();
		
		if(idSelecionado == "...") {
			if(!nome.getText().isEmpty() || !rg.getText().isEmpty() || !cargo.getText().isEmpty()) {
				entity.setNome(nome.getText());
				entity.setRg(rg.getText());
				entity.setCargo(cargo.getText());
				if(integral.isSelected()) {
					entity.setTipo_turno(INTEGRAL);
					entity.setTurno(integral.getText());
				}
				else if(manha.isSelected()) {
					entity.setTipo_turno(MANHA);
					entity.setTurno(manha.getText());
				}
				else if(tarde.isSelected()) {
					entity.setTipo_turno(TARDE);
					entity.setTurno(tarde.getText());
				}
				else if(noite.isSelected()) {
					entity.setTipo_turno(NOITE);
					entity.setTurno(noite.getText());
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
			entity.setRg(rg.getText());
			entity.setCargo(cargo.getText());
			if(integral.isSelected()) {
				entity.setTipo_turno(INTEGRAL);
				entity.setTurno(integral.getText());
			}
			else if(manha.isSelected()) {
				entity.setTipo_turno(MANHA);
				entity.setTurno(manha.getText());
			}
			else if(tarde.isSelected()) {
				entity.setTipo_turno(TARDE);
				entity.setTurno(tarde.getText());
			}
			else if(noite.isSelected()) {
				entity.setTipo_turno(NOITE);
				entity.setTurno(noite.getText());
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
		Main.pageCadastrarVeiculo(cpf_representante);
	}
	@FXML
	public void previousPage(ActionEvent event) {
		Main.pageCadastrarMoradorAdicional(cpf_representante);
	}
	// Event Listener on Button.onAction
	@FXML
	public void exibir(ActionEvent event) {
		removerTodosMoradores();
		CadastrarEmpregadoModel model = new CadastrarEmpregadoModel();
		List<Empregado> entities = model.selectAll(cpf_representante);
		Iterator<Empregado> it = entities.iterator();
		painel.setPrefHeight(entities.size()*TOOLBAR_HEIGHT);;
		
		List<ToolBar> listaMoradores = new ArrayList<ToolBar>();
		int contador = 0;
		while(it.hasNext()) {
			
			Empregado entity = it.next();
			
			Label Lid = new Label(Integer.toString(entity.getId()));
			Lid.setAlignment(rotuloID.getAlignment());
			Lid.setPrefWidth(rotuloID.getWidth());
			
			Label Lnome = new Label(entity.getNome());
			Lnome.setAlignment(rotuloNome.getAlignment());
			Lnome.setPrefWidth(rotuloNome.getWidth());
			
			Label Lrg = new Label(entity.getRg());
			Lrg.setAlignment(rotuloRG.getAlignment());
			Lrg.setPrefWidth(rotuloRG.getWidth());
			
			Label Lturno = new Label(entity.getTurno());
			Lturno.setAlignment(rotuloCargo.getAlignment());
			Lturno.setPrefWidth(rotuloCargo.getWidth());
			
			Button editar = new Button("editar");
			editar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					idSelecionado = Integer.toString(entity.getId());
					id.setText(idSelecionado);
					Empregado m = model.loadData(cpf_representante, entity.getId());
					nome.setText(m.getNome());
					rg.setText(m.getRg());
					cargo.setText(m.getCargo());
					if(m.getTipo_turno()==INTEGRAL) {
						integral.setSelected(true);
					}
					else if(m.getTipo_turno()==MANHA) {
						manha.setSelected(true);
					}
					else if(m.getTipo_turno()==TARDE) {
						tarde.setSelected(true);
					}
					else if(m.getTipo_turno()==NOITE) {
						noite.setSelected(true);
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
			
			listaMoradores.add(new ToolBar(Lid,Lnome,Lrg,Lturno,editar,excluir));

			listaMoradores.get(contador).setLayoutX(0);
			listaMoradores.get(contador).setLayoutY(contador*TOOLBAR_HEIGHT);
			listaMoradores.get(contador).setPrefSize(TOOLBAR_WIDTH,TOOLBAR_HEIGHT);
			painel.getChildren().add(listaMoradores.get(contador));
			
			contador++;
		}
		System.out.println(contador);
	}
}
