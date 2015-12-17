/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Endereco;
import modelo.Usuario;
import persistencia.UsuarioDAO;

/**
 *
 * @author Iury
 */
public class UsuarioDAOJDBC implements UsuarioDAO{

    private final FabricaDAOJDBC fabrica;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final Connection con;

    public UsuarioDAOJDBC()throws SQLException{
        this.fabrica = FabricaDAOJDBC.getInstance();
        this.con = fabrica.getConnection();
    }
    
    @Override
    public void save(Usuario u){
            try{
		
		stmt = con.prepareStatement("INSERT INTO tb_usuario (nome, senha, telefone, endereco) VALUES(?,?,?,?)");
		
		stmt.setString(1,u.getNome());
		stmt.setString(2,u.getSenha());
		stmt.setString(3,u.getTelefone());
                stmt.setLong(4,u.getEndereco().getId());
		stmt.executeUpdate();
                stmt.close();
            }
            catch(SQLException e){
                System.out.println("Houve erro na abertura de conexão! " + e);
            }    
	}
        
	@Override
	public void delete(Usuario u){
		try{
                    stmt = con.prepareStatement("DELETE FROM tb_usuario WHERE id_usuario = ?");
                    stmt.setLong(1,u.getId());
                    stmt.executeUpdate();
                    stmt.close();
                }catch(SQLException e){
                    System.out.println("Houve um erro de banco! " + e);
                }
		
	}
        
        @Override
        public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Usuario usuario;
            stmt = con.prepareStatement("SELECT * FROM tb_usuario");
            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setTelefone(rs.getString(4));
                usuario.setEndereco(new EnderecoDAOJDBC().getById(rs.getLong(5)));
                usuarios.add(usuario);
            }
            stmt.close();
            return usuarios;
        } catch (SQLException e) {
            System.out.println("Houve um erro de banco! " + e);
            return usuarios;
        }
    }

    @Override
    public Usuario getById(long pk) {
        Usuario usuario = new Usuario();
        try {
            stmt = con.prepareStatement("SELECT * FROM tb_usuario");
            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setNome(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setTelefone(rs.getString(4));
                usuario.setEndereco(new EnderecoDAOJDBC().getById(rs.getLong(5)));
            }
            stmt.close();
            return usuario;
        } catch (SQLException e) {
            System.out.println("Houve um erro de banco! " + e);
            return usuario;
        }
    }

    @Override
    public String retornaSenha(String telefone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
