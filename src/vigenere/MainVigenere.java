/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vigenere;

/**
 *
 * @author User
 */
public class MainVigenere {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        VigenereCipher vc = new VigenereCipher();
        vc.createVigenereTable();
        System.out.println(vc.getCharPosition('F'));
        System.out.println(vc.EncryptPlainText("HALO APA kabarmu"));
        System.out.println(vc.checkCharPosition('K', 'D'));
        System.out.println(vc.DecryptCipherText("KEQOUBTNEGAMXO"));
        */
        
        VigenereCipherExtended vce = new VigenereCipherExtended();
        vce.createVigenereTable();
        System.out.println(vce.EncryptPlainText("HALO APA kabarmu"));
        System.out.println(vce.DecryptCipherText("u¤e±¢·­Æ±º"));
        
    }
}
