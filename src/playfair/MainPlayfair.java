/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package playfair;

/**
 *
 * @author User
 */
public class MainPlayfair {
    public static void main(String[] args) {
        PlayfairCipher pc = new PlayfairCipher();
        
        System.out.println(pc.standardizeKey("HaloApakabar"));
        System.out.println(pc.getRemainderAlpha("CandyOliviaMawalim"));
        pc.createPlayfairTable("HaloApakabar");
        System.out.println(pc.encriptPlainText("CANDY OLIVIA"));
        System.out.println(pc.decriptCipherText("BOUKZV"));
    }
}
