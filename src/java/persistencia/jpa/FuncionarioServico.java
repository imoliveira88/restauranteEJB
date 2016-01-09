/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.jpa;

import acesso.Funcionario;
import javax.persistence.EntityManager;

/**
 *
 * @author Iury
 */
public class FuncionarioServico extends ServicoGenerico<Long, Funcionario>{

    public FuncionarioServico() {
        super();
    }
    
    public Funcionario getById(long pk) {
        return super.getById(pk);
    }
}
