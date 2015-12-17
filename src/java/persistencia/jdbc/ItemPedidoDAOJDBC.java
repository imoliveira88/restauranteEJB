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
import modelo.ItemPedido;

/**
 *
 * @author Magalhães Oliveira
 */
public class ItemPedidoDAOJDBC {
    
    private final FabricaDAOJDBC fabrica;
    private PreparedStatement stmt;
    private ResultSet rs;
    private final Connection con;

    public ItemPedidoDAOJDBC() throws SQLException {
        this.fabrica = FabricaDAOJDBC.getInstance();
        this.con = fabrica.getConnection();
    }
    
    public void save(ItemPedido c)throws SQLException, IOException{
            try{
		
		stmt = con.prepareStatement("INSERT INTO tb_itempedido(id_prato,id_pedido,itempedido_quantidade) VALUES(?,?,?)");
		
		stmt.setLong(1,c.getPrato().getId());
		stmt.setLong(2,c.getPedido().getId());
		stmt.setInt(3,c.getQuantidade());
		stmt.executeUpdate();
                
            }
            catch(SQLException e){
                System.out.println("Houve erro na abertura de conexão! " + e);
            }    
	}
        
	
	public void delete(ItemPedido c)throws Exception{
		try{
                    stmt = con.prepareStatement("DELETE FROM tb_itempedido WHERE id_itempedido = ?");
                    stmt.setLong(1,c.getId());
                    stmt.executeUpdate();
                    stmt.close();
                }catch(SQLException e){
                    System.out.println("Houve um erro de banco! " + e);
                }
		
	}
        
    
}
