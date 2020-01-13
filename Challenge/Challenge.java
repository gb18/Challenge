import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Models.Paciente;
import Models.WriteFile;
import java.sql.*;
import java.util.*;

public class Challenge{
	public static void main(String[] args) throws IOException{
		//Connecting database (Using ElephantSQL)
		System.out.println("Configurando conexao com o banco...");
		
		try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/vzfxjigf";
        String username = "vzfxjigf";
        String password = "K5Ygl7BxtHAQFK1s4uEu37M1YUt1gaGG";
		
		Connection db = null;
		
		String SQL = "INSERT INTO paciente(nome, datanascimento, cpf, endereco, telefone) VALUES(?,?,?,?,?)";

        try {
            db = DriverManager.getConnection(url, username, password);
			System.out.println("Database Connected!");
			
		}
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
		//------------------------------------------------------------------------
		boolean onMenu = true;
		while(onMenu){
			System.out.println("\nEscolha a opcao desejada:");
			System.out.println("(1) Cadastrar paciente");
			System.out.println("(2) Just save a collection of patients, select on database and save on txt file");
			System.out.println("(0) Sair\n");
			
			try {			
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				
				int menu = Integer.parseInt(reader.readLine());

				switch(menu) {
					case 0:
						onMenu = false;
						break;
					case 1:
						
						Paciente paciente = new Paciente();
						
						System.out.println("Insira seu nome:");
						paciente.setNome(reader.readLine());

						System.out.println("Insira a data de nascimento (DD/MM/AAAA):");
						paciente.setDataNascimento(reader.readLine());
						
						System.out.println("Insira o CPF (xxx.xxx.xxx-xx):");
						paciente.setCpf(reader.readLine());
						
						System.out.println("Insira o endereco:");
						paciente.setEndereco(reader.readLine());
						
						System.out.println("Insira o telefone ((xx)xxxx-xxxx):");
						paciente.setTelefone(reader.readLine());
						
						PreparedStatement pstmt = db.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, paciente.getNome());
						pstmt.setString(2, paciente.getDataNascimento());
						pstmt.setString(3, paciente.getCpf());
						pstmt.setString(4, paciente.getEndereco());
						pstmt.setString(5, paciente.getTelefone());
						pstmt.executeUpdate();
						break;
					
					case 2:
						System.out.println("Populando Collection...");
						
						List<Paciente> lista = new ArrayList<Paciente>();
						Paciente um = new Paciente("um", "01/01/2001", "111.111.111-11", "Um", "(11)1111-1111");
						Paciente dois = new Paciente("dois", "02/02/2002", "222.222.222-22", "Dois", "(22)2222-2222");
						Paciente tres = new Paciente("tres", "03/03/2003", "333.333.333-33", "Tres", "(33)3333-3333");
						lista.add(um);
						lista.add(dois);
						lista.add(tres);
						
						System.out.println("Salvando na base de dados...");
						
						Iterator<Paciente> pa = lista.iterator();
						PreparedStatement pstmt2 = db.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
						while(pa.hasNext()){
							Paciente p = pa.next();
							pstmt2.setString(1, p.getNome());
							pstmt2.setString(2, p.getDataNascimento());
							pstmt2.setString(3, p.getCpf());
							pstmt2.setString(4, p.getEndereco());
							pstmt2.setString(5, p.getTelefone());
							pstmt2.addBatch();
							pstmt2.executeBatch();
						}
						
						System.out.println("Consultando banco de dados...");
						
						Statement st = db.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM paciente");
						System.out.println("Insira o nome do arquivo:");
						WriteFile data = new WriteFile( reader.readLine());
						while (rs.next()) {
							try {
								data.writeToFile(rs.getString("nome"));
								data.writeToFile(rs.getString("datanascimento"));
								data.writeToFile(rs.getString("cpf"));
								data.writeToFile(rs.getString("endereco"));
								data.writeToFile(rs.getString("telefone"));
							} catch(IOException e) {
								System.out.println(e.getMessage());
							}
						}
						System.out.println("Arquivo criado com sucesso!\n");
						break;
					default:
						System.out.println("A entrada deve estar dentro das opcoes do menu...");
						break;
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}