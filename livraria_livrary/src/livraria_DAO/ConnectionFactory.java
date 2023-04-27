
package livraria_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    Connection conexao;
    
    public Connection getConexao(){
        try{
            conexao = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/livrary", "root", "");
            if(conexao!=null){
                System.out.println("CONEXÃO ESTABELECIDA COM O BANCO DE DADOS!");
            }
        }catch(SQLException e){
            System.out.println("ERRO no BANCOD" + e);
        }
        return conexao;
    }
}
