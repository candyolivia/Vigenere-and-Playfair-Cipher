/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vigenere;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class VigenereCipher {
    private int numChar = 26; //jumlah huruf alfabet
    private char vigenereTable[][];
    private String key = "DEFAULT";
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //GETTER AND SETTER
    public char[][] getVigenereTable() {
        return vigenereTable;
    }

    public void setVigenereTable(char[][] vigenereTable) {
        this.vigenereTable = vigenereTable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    //FUNCTIONS AND PROCEDURES
    public void createVigenereTable() {
        vigenereTable = new char[numChar][numChar];
        for (int i = 0; i < numChar; i++) {
            for (int j = 0; j < numChar; j++) {
                if ((j+i+65)>90) {
                    vigenereTable[i][j] = (char)((j+65+i)-90+65);
                } else {
                    vigenereTable[i][j] = (char)(j+65+i);
                }
                
                //System.out.print(vigenereTable[i][j]+" ");
            }
            //System.out.println();
        }
    }
    
    public int getCharPosition(char cc) {
        int pos = 0;
        for (int i = 0; i < numChar; i++) {
            if(cc == vigenereTable[0][i]) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public boolean isAlpha(char c){
        if (alpha.indexOf(c) != -1){
            return true;
        } else {
            return false;
        }
    }
    
    public String normalizedPlainText(String plaintext) {
        String res = "";

        
        plaintext = plaintext.toUpperCase();
        for (int i = 0; i < plaintext.length(); i++) {
            if (isAlpha(plaintext.charAt(i))){
                res += plaintext.charAt(i);
            } else if (plaintext.charAt(i)== ' ') {
                res += " ";
            }
        }
        
        return res;
    }
    
    public boolean containSpace (String text) {
        return text.contains(" ");
    }
    
    public String EncriptPlainTextSpace (String plaintext) {
        String res = "";
        String keytemp = "";
        
        while (keytemp.length() < plaintext.length()) {
            keytemp += key;
        }
        
        plaintext = normalizedPlainText(plaintext);
        
        for (int i = 0; i < plaintext.length(); i++) {
            if (plaintext.charAt(i) == ' ') {
                res += " ";
            } else {
                res += vigenereTable[getCharPosition(plaintext.charAt(i))][getCharPosition(keytemp.charAt(i))];
            
            }
        }
        
        return res;
    }
    
    public String EncryptPlainTextGroup(String plaintext, int num) {
        String res = "";
        String keytemp = "";
        
        if (containSpace(plaintext)) {
            plaintext = plaintext.replace(" ", "");
        }
        
        while (keytemp.length() < plaintext.length()) {
            keytemp += key;
        }
        
        plaintext = normalizedPlainText(plaintext);
        
        for (int i = 0; i < plaintext.length(); i++) {
            res += vigenereTable[getCharPosition(plaintext.charAt(i))][getCharPosition(keytemp.charAt(i))];
            if ((i+1)%num == 0) {
                res += " ";
            }
        }
        
        return res;
    }
    
    public String EncryptPlainText(String plaintext) {
        String res = "";
        String keytemp = "";
        
        plaintext = plaintext.replace(" ", "");
        while (keytemp.length() < plaintext.length()) {
            keytemp += key;
        }
        
        plaintext = normalizedPlainText(plaintext);
        
        for (int i = 0; i < plaintext.length(); i++) {
            res += vigenereTable[getCharPosition(plaintext.charAt(i))][getCharPosition(keytemp.charAt(i))];
            
        }
        
        return res;
    }
    
    public int checkCharPosition(char cc, char ckey) {
        int cek = 0;
        
        for (int i = 0; i < numChar; i++) {
            if (cc == vigenereTable[getCharPosition(ckey)][i]) {
                cek = i;
                break;
            }
        }
        
        return cek;
    }
    
    public String DecryptCipherText(String ciphertext) {
        String res = "";
        String keytemp = "";
        
        while (keytemp.length() < ciphertext.length()) {
            keytemp += key;
        }
        
        for (int i = 0; i < ciphertext.length(); i++) {
            if (ciphertext.charAt(i) == ' ') {
                
            } else {
                res += alpha.charAt(checkCharPosition(ciphertext.charAt(i),keytemp.charAt(i)));
            }
            
            
        }
        
        return res;
    }
}
