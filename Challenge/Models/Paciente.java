package Models;

public class Paciente {
	
	private int Id;
	private String Nome;
	private String DataNascimento;
	private String Cpf;
	private String Endereco;
	private String Telefone;
	
	public Paciente() {
		
	}
	public Paciente(String Nome, String DataNascimento, String Cpf, String Endereco, String Telefone) {
		this.Nome = Nome;
		this.DataNascimento = DataNascimento;
		this.Cpf = Cpf;
		this.Endereco = Endereco;
		this.Telefone = Telefone;
	}
	
	public String getNome() { 
		return Nome; 
	}
	
	public void setNome(String Nome) { 
		this.Nome = Nome; 
	}
	
	public String getDataNascimento() { 
		return DataNascimento; 
	}
	
	public void setDataNascimento(String DataNascimento) { 
		this.DataNascimento = DataNascimento; 
	}
	
	public String getCpf() { 
		return Cpf; 
	}
	
	public void setCpf(String Cpf) { 
		this.Cpf = Cpf; 
	}
	
	public String getEndereco() {
		return Endereco;
	}
	
	public void setEndereco(String Endereco) {
		this.Endereco = Endereco;
	}
	
	public String getTelefone() {
		return Telefone;
	}
	
	public void setTelefone(String Telefone) {
		this.Telefone = Telefone;
	}
}