/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.*;
import persistencia.*;

public class GeraTabelas {
   
    public static void main(String[] args) throws ParseException, SQLException {
        
        //Primeiro Bandeira 
        //testando método save;
        
        Prato p = new Prato("Batata frita",5.98,"Outro prato bom!");
        
        try{
            PratoDAO fo = new PratoDAOJPA();
            fo.save(p);
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        /*
        DAOGenericoJPA a = new DAOGenericoJPA();
        
        Bandeira bandeiraVisa = new Bandeira("VISA");
        
        Bandeira bandeiraMaster = new Bandeira("MasterCart");
        
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");  
        Date data = formatador.parse("01/09/2020");  
        Cartao cartao = new Cartao(bandeiraVisa,"4593160175356291",data);
        
        bandeiraVisa.addCartao(cartao);
        
        Endereco endereco = new Endereco("Rua","Sá e Souza",45,"43.220-340","Recife","PE");
        Endereco endereco2 = new Endereco("Rua","dos Besouros Artificiais",103,"12.456-330","Maceió","AL");
        
        Cliente cliente = new Cliente("Ronaldo dos Santos","magno","3454-5454",endereco,cartao);
        
        Funcionario func = new Funcionario("José Aldo Ferreira","nordestino","3456-9876",endereco2,3200.0,"Garçom");
        
        Prato p = new Prato("Filé com fritas",24.00,"Rapaz... é bom o bicho!");      
        Prato p2 = new Prato("Macaxeira com charque",19.00,"Regional todo...");
        
        Pedido pedido = new Pedido();
        cliente.addPedido(pedido);
        
        ItemPedido ip = new ItemPedido(p,2,pedido);
        
        ItemPedido ip2 = new ItemPedido(p2,5,pedido);
        
        pedido.addItem(ip);
        pedido.addItem(ip2);
        pedido.setCliente(cliente);
        
        Pagamento pag = new Pagamento();
        pag.setForma("Cartão");
        pag.setPedido(pedido);
        pag.setValorPago(100.0);
        
        a.save(bandeiraVisa);
        a.save(bandeiraMaster);
        
        a.save(cartao);
        a.save(endereco);
        a.save(endereco2);
        
        a.save(cliente);
        a.save(func);
        a.save(p);
        a.save(p2);
        
        a.save(ip);
        a.save(ip2);
        a.save(pedido);
        
        a.save(pag);
        //sempre é necessario fechar 
        FabricaDAOJPA.destroyInstance();
                */
        
        
    }
}