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
public class EstrategiaPadrao implements EstrategiaAbstrata{
    @Override
    public String geraSenha(){
        return "123456";
    }
}
