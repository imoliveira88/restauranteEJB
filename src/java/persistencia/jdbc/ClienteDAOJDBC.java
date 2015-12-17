/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Cliente;
import persistencia.ClienteDAO;

/**
 *
 * @author Magalhães Oliveira
 */
public class ClienteDAOJDBC extends UsuarioDAOJDBC implements ClienteDAO{

    private final FabricaDAOJDBC fabrica;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final Connection con;

    public ClienteDAOJDBC()throws SQLException{
        this.fabrica = FabricaDAOJDBC.getInstance();
        this.con = fabrica.getConnection();
    }

    @Override
    public Cliente getById(long pk) {
        return (Cliente) super.getById(pk);
    }

    @Override
    public void save(Cliente b) {
        try{
		stmt = con.prepareStatement("INSERT INTO tb_cliente (nome, senha, telefone, endereco, cartao) VALUES(?,?,?,?,?)");
		stmt.setString(1,b.getNome());
		stmt.setString(2,b.getSenha());
		stmt.setString(3,b.getTelefone());
                stmt.setLong(4,b.getEndereco().getId());
                stmt.setLong(5,b.getCartao().getId());
		stmt.executeUpdate();
                stmt.close();
            }
            catch(SQLException e){
                System.out.println("Houve erro na abertura de conexão! " + e);
            }    
	}

    @Override
    public void delete(Cliente b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
