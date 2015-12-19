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
import modelo.Prato;
import persistencia.PratoDAO;

/**
 *
 * @author Magalhães Oliveira
 */
public class PratoDAOJDBC implements PratoDAO{
    
    private final FabricaDAOJDBC fabrica;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final Connection con;

    public PratoDAOJDBC()throws SQLException{
        this.fabrica = FabricaDAOJDBC.getInstance();
        this.con = fabrica.getConnection();
    }
    
    
    @Override
    public void save(Prato c){
            try{
		
		stmt = con.prepareStatement("INSERT INTO tb_prato (prato_nome,prato_preco, prato_descricao) VALUES(?,?,?)");
		
		stmt.setString(1,c.getNome());
		stmt.setDouble(2,c.getPreco());
		stmt.setString(3,c.getDescricao());
		stmt.executeUpdate();
                
            }
            catch(SQLException e){
                System.out.println("Houve erro na abertura de conexão! " + e);
            }    
	}
        
	@Override
	public void delete(Prato c){
		try{
                    stmt = con.prepareStatement("DELETE FROM tb_prato WHERE prato_nome = ?");
                    stmt.setString(1,c.getNome());
                    System.out.println("Apagouuuuuuuuu! ");
                    stmt.executeUpdate();
                    stmt.close();
                }catch(SQLException e){
                    System.out.println("Houve um erro de banco! " + e);
                }
		
	}
        
        @Override
	public void update(Prato c){
		try{
                    stmt = con.prepareStatement("UPDATE tb_prato SET prato_preco = ?, prato_descricao = ?, prato_imagem = ? WHERE prato_nome = ?");
                    stmt.setDouble(1,c.getPreco());
                    stmt.setString(2,c.getDescricao());
                    stmt.setString(3,c.getImagem());
                    stmt.setString(4,c.getNome());
                    System.out.println("Atualizou! ");
                    stmt.executeUpdate();
                    stmt.close();
                }catch(SQLException e){
                    System.out.println("Houve um erro de banco! " + e);
                }
		
	}
        
        @Override
        public List<Prato> findAll() {
        List<Prato> pratos = new ArrayList<>();
        try {
            Prato prato;
            stmt = con.prepareStatement("SELECT * FROM tb_prato ORDER BY nome");
            rs = stmt.executeQuery();
            while (rs.next()) {
                prato = new Prato();
                prato.setId(rs.getLong(1));
                prato.setImagem(rs.getString(3));
                prato.setDescricao(rs.getString(2));
                prato.setPreco(rs.getDouble(5));
                prato.setNome(rs.getString(4));
                pratos.add(prato);
            }
            stmt.close();
            return pratos;
        } catch (SQLException e) {
            System.out.println("Houve um erro de banco! " + e);
            return pratos;
        }
    }
    
}
