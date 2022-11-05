package entities;

public class Animal {
	private int id;
	private int tipo_animal;
	private String especie;
	private String nome;
	private String cor;
	private String raca;
	public int getTipo_animal() {
		return tipo_animal;
	}
	public void setTipo_animal(int tipo_animal) {
		this.tipo_animal = tipo_animal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
}
