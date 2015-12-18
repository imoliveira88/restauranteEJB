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
public class EstrategiaAleatoria implements EstrategiaAbstrata{

    @Override
    public String geraSenha() {
        return " " + (int) Math.floor(1000000*Math.random());
    }
    
}
