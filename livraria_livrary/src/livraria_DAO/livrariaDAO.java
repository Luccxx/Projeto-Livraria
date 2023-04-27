
package livraria_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import livraria.MODEL.book;
import java.util.ArrayList;

public class livrariaDAO {
    public Connection conexao;
    public PreparedStatement pstm;
    ResultSet rs;
    ArrayList<book> lista=new ArrayList<>();
    
    public livrariaDAO(){
        conexao = new ConnectionFactory().getConexao();
    }
    
    public void createBook(book objbook){
        String sql = "INSERT INTO book(nome, autor, editora, idioma, paginas) VALUES(?,?,?,?,?)";
        
        try{
            pstm=conexao.prepareStatement(sql);
            pstm.setString(1, objbook.getNome());
            pstm.setString(2, objbook.getAutor());
            pstm.setString(3, objbook.getEditora());
            pstm.setString(4, objbook.getIdioma());
            pstm.setInt(5, objbook.getPaginas());
            pstm.execute();
            pstm.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Cadastrar Book: " + e);
        }
        
    }
    
    public ArrayList<book> listBook(){
        String sql="SELECT * FROM book";
        try{
            pstm=conexao.prepareStatement(sql);
            rs=pstm.executeQuery();
            while(rs.next()){
                book book = new book();
                book.setIdLivro(rs.getInt("idLivro"));
                book.setNome(rs.getString("nome"));
                book.setAutor(rs.getString("autor"));
                book.setEditora(rs.getString("editora"));
                book.setIdioma(rs.getString("idioma"));
                book.setPaginas(rs.getInt("paginas"));
                lista.add(book);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ListBook " + e);
        }
        return lista;
    
    }
    
    public void deleteBook(book book){
        String sql = "delete from book where idLivro=?";
        
        try{
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            pstm.setInt(1, book.getIdLivro());
            pstm.execute();
            pstm.close(); 
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Delete Book: " + e);
        }
    }
    
    public void updateBook(book book){
        String sql = "update book set nome= ?, autor=?, editora=?, idioma=?, paginas=? where idLivro=?";
        
         try{
            pstm = (PreparedStatement) conexao.prepareStatement(sql);
            pstm.setString(1, book.getNome());
            pstm.setString(2, book.getAutor());
            pstm.setString(3, book.getEditora());
            pstm.setString(4, book.getIdioma());
            pstm.setInt(5, book.getPaginas());
            pstm.setInt(6, book.getIdLivro());
            pstm.execute();
            pstm.close(); 
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "UpdateBook: " + e);
        }
    }
    
    public ArrayList<book> searchBook(String descricao){
        String sql = "select * from book where nome like '%"+descricao+"%' or autor like '%"+descricao+"%'";
        
        try{
            pstm=conexao.prepareStatement(sql);
            rs=pstm.executeQuery();
            while(rs.next()){
                book book = new book();
                book.setIdLivro(rs.getInt("idLivro"));
                book.setNome(rs.getString("nome"));
                book.setAutor(rs.getString("autor"));
                book.setEditora(rs.getString("editora"));
                book.setIdioma(rs.getString("idioma"));
                book.setPaginas(rs.getInt("paginas"));
                lista.add(book);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Pesquisar " + e);
        }
        return lista;
    }
}
