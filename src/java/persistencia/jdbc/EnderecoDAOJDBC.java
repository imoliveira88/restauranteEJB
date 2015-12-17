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
import modelo.Endereco;
import persistencia.EnderecoDAO;

/**
 *
 * @author Magalhães Oliveira
 */
public class EnderecoDAOJDBC implements EnderecoDAO{

    private final FabricaDAOJDBC fabrica;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final Connection con;

    public EnderecoDAOJDBC()throws SQLException{
        this.fabrica = FabricaDAOJDBC.getInstance();
        this.con = fabrica.getConnection();
    }

    @Override
    public Endereco getById(long pk) {
        Endereco endereco = new Endereco();
        try {
            stmt = con.prepareStatement("SELECT * FROM tb_endereco");
            rs = stmt.executeQuery();
            while (rs.next()) {
                endereco.setId(rs.getLong(1));
                endereco.setTipoLogradouro(rs.getString(2));
                endereco.setLogradouro(rs.getString(3));
                endereco.setNumero(rs.getInt(4));
                endereco.setCep(rs.getString(5));
                endereco.setCidade(rs.getString(6));
                endereco.setEstado(rs.getString(7));
            }
            stmt.close();
            return endereco;
        } catch (SQLException e) {
            System.out.println("Houve um erro de banco! " + e);
            return endereco;
        }
    }

    @Override
    public void save(Endereco c) {
        try{
		
		stmt = con.prepareStatement("INSERT INTO tb_endereco (END_TIPOLOGRADOURO,END_LOGRADOURO,END_NUMERO,END_CEP,END_CIDADE,END_ESTADO) VALUES(?,?,?,?,?,?)");
		
		stmt.setString(1,c.getTipoLogradouro());
		stmt.setString(2,c.getLogradouro());
                stmt.setInt(3,c.getNumero());
                stmt.setString(4,c.getCep());
                stmt.setString(5,c.getCidade());
                stmt.setString(6,c.getEstado());
	
		stmt.executeUpdate();
                
            }
            catch(SQLException e){
                System.out.println("Houve erro na abertura de conexão! " + e);
            }   
    }

    @Override
    public void delete(Endereco b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
