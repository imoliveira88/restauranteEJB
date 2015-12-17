/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Magalhães Oliveira
 */
public class BuilderCliente implements AbstractBuiderCliente{
    private Cliente c;

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }    

    @Override
    public void criarNome(String n) {
        getC().setNome(n);
    }

    @Override
    public void criarSenha() {
        getC().setSenha(" " + Math.random());
    }
    
}
