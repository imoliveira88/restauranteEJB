/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;


/**
 *
 * @author Magalhães Oliveira
 * Bandeira, Cartão, CLiente, Endereco, Funcionario, ItemPedido, Pagamento, PEDido, Prato -> 1 - 9
 * JDBC = 1, JPA = 2
 */

public class FabricaObjetos {
    private int persistencia;
    private int classe;
    
    public static Object Fabrica(int persist, int classe)throws SQLException{
        switch(classe){
            case 1:
                //if(persist == 1) return new BandeiraDAOJDBC();
                return new BandeiraDAOJPA();
            case 2:
                //if(persist == 1) return new CartaoDAOJDBC();
                return new CartaoDAOJPA();
            case 3:
                //if(persist == 1) return new ClienteDAOJDBC();
                return new ClienteDAOJPA();
            case 4:
                //if(persist == 1) return new EnderecoDAOJDBC();
                return new EnderecoDAOJPA();
            case 5:
                //if(persist == 1) return new FuncionarioDAOJDBC();
                return new FuncionarioDAOJPA();
            case 6:
                //if(persist == 1) return new ItemPedidoDAOJDBC();
                return new ItemPedidoDAOJPA();
            case 7:
                //if(persist == 1) return new PagementoDAOJDBC();
                return new PedidoDAOJPA();
            default:
                //if(persist == 1) return (PratoDAO) new PratoDAOJDBC();
                return (PratoDAO) new PratoDAOJPA();
        }
    }
}
