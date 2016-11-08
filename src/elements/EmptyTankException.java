/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author Agathe
 */
public class EmptyTankException extends Exception{

    public EmptyTankException() {
        System.out.println("Le réservoir est vide: toute l'eau a été versée.");
    }
    
}
