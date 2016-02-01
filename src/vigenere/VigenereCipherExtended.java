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
public class VigenereCipherExtended {
    private int numASCII = 256; //jumlah karakter ASCII
    private char vigenereTable[][];
    private String key = "DEFAULT";
    
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
        vigenereTable = new char[numASCII][numASCII];
        for (int i = 0; i < numASCII; i++) {
            for (int j = 0; j < numASCII; j++) {
                if ((j+i)>255) {
                    vigenereTable[i][j] = (char)((j+i)-255);
                } else {
                    vigenereTable[i][j] = (char)(j+i);
                }
                
                //System.out.print(vigenereTable[i][j]+" ");
            }
            //System.out.println();
        }
    }
    
    public int getCharPosition(char cc) {
        int pos = 0;
        for (int i = 0; i < numASCII; i++) {
            if(cc == vigenereTable[0][i]) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    public String EncryptPlainText(String plaintext) {
        String res = "";
        String keytemp = "";
        
        while (keytemp.length() < plaintext.length()) {
            keytemp += key;
        }
        
        for (int i = 0; i < plaintext.length(); i++) {
            res += vigenereTable[getCharPosition(plaintext.charAt(i))][getCharPosition(keytemp.charAt(i))];
            
        }
        
        return res;
    }
    
    public String EncryptPlainTextGroup(String plaintext, int num) {
        String res = "";
        String keytemp = "";
        
        while (keytemp.length() < plaintext.length()) {
            keytemp += key;
        }
        
        for (int i = 0; i < plaintext.length(); i++) {
            res += vigenereTable[getCharPosition(plaintext.charAt(i))][getCharPosition(keytemp.charAt(i))];
            if ((i+1)%num == 0) {
                res += " ";
            }
        }
        
        return res;
    }
    
    public int checkCharPosition(char cc, char ckey) {
        int cek = 0;
        
        for (int i = 0; i < numASCII; i++) {
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
        
        ciphertext = ciphertext.replace(" ", "");
        while (keytemp.length() < ciphertext.length()) {
            keytemp += key;
        }
        
        for (int i = 0; i < ciphertext.length(); i++) {
            res += vigenereTable[0][(checkCharPosition(ciphertext.charAt(i),keytemp.charAt(i)))];
            
        }
        
        return res;
    }
    
}
