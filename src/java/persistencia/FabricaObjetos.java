/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import persistencia.jpa.*;
import persistencia.jdbc.*;
import java.sql.SQLException;


/**
 *
 * @author Magalhães Oliveira
 * Bandeira, Cartão, CLiente, Endereco, Funcionario, ItemPedido, Pagamento, PEDido, Prato -> 1 - 9
 * JDBC = 1, JPA = 2
 */

public class FabricaObjetos {
    
    public static Object Fabrica(int persist, int classe)throws SQLException{
        switch(classe){
            case 1:
                if(persist == 1) return (BandeiraDAO) new BandeiraDAOJPA();
                else return (BandeiraDAO) new BandeiraDAOJDBC();
            case 2:
                if(persist == 1) return (CartaoDAO) new CartaoDAOJPA();
                else return (CartaoDAO) new CartaoDAOJDBC();
            case 3:
                if(persist == 1) return (ClienteDAO) new ClienteDAOJPA();
                else return (ClienteDAO) new ClienteDAOJDBC();
            case 4:
                if(persist == 1) return (EnderecoDAO) new EnderecoDAOJPA();
                else return (EnderecoDAO) new EnderecoDAOJDBC();
            case 5:
                if(persist == 1) return (FuncionarioDAO) new FuncionarioDAOJPA();
                else return (FuncionarioDAO) new FuncionarioDAOJDBC();
            case 6:
                if(persist == 1) return (ItemPedidoDAO) new ItemPedidoDAOJPA();
                else return (ItemPedidoDAO) new ItemPedidoDAOJDBC();
            case 7:
                if(persist == 1) return (PedidoDAO) new PedidoDAOJPA();
            default:
                if(persist == 1) return (PratoDAO) new PratoDAOJPA();
                else return (PratoDAO) new PratoDAOJDBC();
        }
    }
}
