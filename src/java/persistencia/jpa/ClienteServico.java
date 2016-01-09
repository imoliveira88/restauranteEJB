/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import acesso.Cliente;

/**
 *
 * @author Iury
 */
public class ClienteServico extends ServicoGenerico<Long, Cliente>{

    public ClienteServico() {
        super();
    }
    
    public Cliente getById(long pk) {
        return super.getById(pk);
    }
    
}
