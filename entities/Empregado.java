package entities;

public class Empregado {
	private int id;
	private int tipo_turno;
	private String nome;
	private String rg;
	private String cargo;
	private String turno;
	public int getTipo_turno() {
		return tipo_turno;
	}
	public void setTipo_turno(int tipo_turno) {
		this.tipo_turno = tipo_turno;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	
}
