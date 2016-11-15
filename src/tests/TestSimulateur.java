/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import Simulateur.Simulateur;
import java.io.File;

/**
 *
 * @author Sylvain
 */
public class TestSimulateur {
    public static void main(String args[]){
        Simulateur simu = new Simulateur("." + File.separator + "cartes" + File.separator + "carteSujet.map", 80);
    }
}
