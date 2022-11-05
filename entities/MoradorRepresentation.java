package entities;

public class MoradorRepresentation {
	private String ap;
	private String bloco;
	private String nome;
	private String cpf;
	private String rg;
	private String email;
	private String tel_comercial;
	private String tel_celular;
	private String tel_residencial;
	private String data;
	private boolean isPropietario;
	private boolean autorizacao;
	
	public boolean isPropietario() {
		return isPropietario;
	}
	public void setPropietario(boolean isPropietario) {
		this.isPropietario = isPropietario;
	}
	public boolean isAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(boolean autorizacao) {
		this.autorizacao = autorizacao;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getBloco() {
		return bloco;
	}
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_comercial() {
		return tel_comercial;
	}
	public void setTel_comercial(String tel_comercial) {
		this.tel_comercial = tel_comercial;
	}
	public String getTel_celular() {
		return tel_celular;
	}
	public void setTel_celular(String tel_celular) {
		this.tel_celular = tel_celular;
	}
	public String getTel_residencial() {
		return tel_residencial;
	}
	public void setTel_residencial(String tel_residencial) {
		this.tel_residencial = tel_residencial;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
