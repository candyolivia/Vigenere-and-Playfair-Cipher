package playfair;

import java.awt.List;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PlayfairCipher {
    private int size = 5; //jumlah karakter ASCII
    private char playfairTable[][];
    private String key = "DEFAULT";
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private java.util.List<Character> listedChar = new ArrayList<>();

    
    //GETTER AND SETTER
    public char[][] getPlayfairTable() {
        return playfairTable;
    }

    public void setVigenereTable(char[][] playfairTable) {
        this.playfairTable = playfairTable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    //FUNCTIONS AND PROCEDURES
    
    public boolean checkDuplicate(char cc) {
        boolean cek = false;
        for (int i = 0; i < listedChar.size(); i++) {
            if (listedChar.get(i) == cc) {
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public boolean isAlpha(char c){
        if (alpha.indexOf(c) != -1){
            return true;
        } else {
            return false;
        }
    }
    
    public String standardizeKey(String keyword) {
        String res = "";
        keyword = keyword.toUpperCase();
        listedChar = new ArrayList<>();
        for (int i = 0; i < keyword.length(); i++) {
            if ((!checkDuplicate(keyword.charAt(i)))&&(isAlpha(keyword.charAt(i)))) {
                res+=keyword.charAt(i);
                listedChar.add(keyword.charAt(i));
            }
        }
        return res;
    }
    
    public String getRemainderAlpha(String keyword){
        String res = alpha;
        
        keyword = keyword.toUpperCase();
        for (int i = 0; i < keyword.length(); i++) {
            res = res.replace(keyword.charAt(i), ' ');
        }
        
        res = res.replace(" ", "");
        res = res.replace("J", "");
        
        return res;
    }
    
    public void createPlayfairTable(String keyword) {
        key = standardizeKey(keyword);
        System.out.println(key);
        
        int keysize = key.length();
        int pointer = 0;
        int pointerAlpha = 0;
        
        
        String remAlpha = getRemainderAlpha(keyword);
        System.out.println(remAlpha);
        
        
        playfairTable = new char[size+1][size+1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (pointer < keysize) {
                    playfairTable[i][j] = key.charAt(pointer);
                    pointer++;
                    System.out.print(playfairTable[i][j]+ " ");
                } else {
                    playfairTable[i][j] = remAlpha.charAt(pointerAlpha);
                    pointerAlpha++;
                    System.out.print(playfairTable[i][j]+ " ");
                }
            }
            System.out.println();
        }
        
        for (int i = 0; i < size; i++) {
            playfairTable[i][size] = playfairTable[i][0];
        }
        
        for (int i = 0; i < size; i++) {
            playfairTable[size][i] = playfairTable[0][i];
        }
    }
    
    public void printPlayfairTable() {
        for (int i = 0; i < size+1; i++) {
            for (int j = 0; j < size+1; j++) {
                System.out.print(playfairTable[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public int isOneRow(char c1, char c2) {
        int cek = -1;
        int cek1 = -1,cek2 = -1;
        for (int i = 0; i < size; i++) {
            cek1 = whichColumn(i, c1);
            cek2 = whichColumn(i, c2);
            if ((cek2!=-1)&&(cek1!=-1)) {
                cek = i;
            }
        }
        return cek;
    }
    
    public int isOneColumn(char c1, char c2) {
        int cek = -1;
        int cek1 = -1,cek2 = -1;
        for (int i = 0; i < size; i++) {
            cek1 = whichRow(i, c1);
            cek2 = whichRow(i, c2);
            if ((cek2!=-1)&&(cek1!=-1)) {
                cek = i;
            }
        }
        
        
        return cek;
    }
    
    public int whichRow (int col, char cc) {
        int row = -1;
        for (int i = 0; i < size; i++) {
            if (cc == playfairTable[i][col]) {
                row = i;
            }
        }
        return row;
    }
    
    public int whichColumn (int row, char cc) {
        int col = -1;
        for (int i = 0; i < size; i++) {
            if (cc == playfairTable[row][i]) {
                col = i;
            }
        }
        return col;
    }
    
    public String encriptPlainText (String plainText) {
        String res = "";
        plainText = plainText.toUpperCase();
        plainText = plainText.replace('J', 'I');
        
        int textSize = plainText.length();
        
        if (textSize%2 == 1) { //ganjil
            plainText += "Z";
        } 
        
        System.out.println(plainText);
        
        for (int i = 0; i < (plainText.length()/2); i++) {
            
            if (isOneColumn(plainText.charAt(i*2), plainText.charAt(i*2+1))!=-1) {
                int col = isOneColumn(plainText.charAt(i*2), plainText.charAt(i*2+1));
                
                res += playfairTable[whichRow(col, plainText.charAt(i*2))+1][col];
                res += playfairTable[whichRow(col, plainText.charAt(i*2+1))+1][col];
                
            } else if (isOneRow(plainText.charAt(i*2), plainText.charAt(i*2+1))!=-1) {
                int row = isOneRow(plainText.charAt(i*2), plainText.charAt(i*2+1));
                
                res += playfairTable[row][whichColumn(row, plainText.charAt(i*2))+1];
                res += playfairTable[row][whichColumn(row, plainText.charAt(i*2+1))+1];
                
            } else {
                int x1 = -1,x2 = -1,y1 = -1,y2 = -1;
                for (int j = 0; j < size; j++) {
                    if (whichColumn(j, plainText.charAt(i*2))!=-1) {
                        x1 = whichColumn(j, plainText.charAt(i*2));
                    }
                    if (whichColumn(j, plainText.charAt(i*2+1))!=-1) {
                        x2 = whichColumn(j, plainText.charAt(i*2+1));
                    }
                    if (whichRow(j, plainText.charAt(i*2))!=-1) {
                        y1 = whichRow(j, plainText.charAt(i*2));
                    }
                    if (whichRow(j, plainText.charAt(i*2+1))!=-1) {
                        y2 = whichRow(j, plainText.charAt(i*2+1));
                    }
                }
                //System.out.println(x1+","+y1+"  "+x2+","+y2);
                res+= playfairTable[y1][x2];
                res+= playfairTable[y2][x1];
            }
        }
        
        return res;
    }
    
    public String decriptCipherText (String cipherText) {
        String res = "";
        cipherText = cipherText.toUpperCase();
        cipherText = cipherText.replace('J', 'I');
        
        int textSize = cipherText.length();
        
        System.out.println(cipherText);
        
        for (int i = 0; i < (cipherText.length()/2); i++) {
            //System.out.println(cipherText.charAt(i*2)+" "+cipherText.charAt(i*2+1));
            if (isOneColumn(cipherText.charAt(i*2), cipherText.charAt(i*2+1))!=-1) {
                int col = isOneColumn(cipherText.charAt(i*2), cipherText.charAt(i*2+1));
                if (whichRow(col, cipherText.charAt(i*2))==0) {
                    res += playfairTable[size-1][col];
                } else {
                    res += playfairTable[whichRow(col, cipherText.charAt(i*2))-1][col];
                }
                
                if (whichRow(col, cipherText.charAt(i*2+1))==0) {
                    res += playfairTable[size-1][col];
                } else {
                    res += playfairTable[whichRow(col, cipherText.charAt(i*2+1))-1][col];
                }
            } else if (isOneRow(cipherText.charAt(i*2), cipherText.charAt(i*2+1))!=-1) {
                int row = isOneRow(cipherText.charAt(i*2), cipherText.charAt(i*2+1));
                if (whichColumn(row, cipherText.charAt(i*2))==0) {
                    res += playfairTable[row][size-1];
                } else {
                    res += playfairTable[row][whichColumn(row, cipherText.charAt(i*2))-1];
                }
                
                if (whichColumn(row, cipherText.charAt(i*2+1))==0) {
                    res += playfairTable[row][size-1];
                } else {
                    res += playfairTable[row][whichColumn(row, cipherText.charAt(i*2+1))-1];
                }
            } else {
                int x1 = -1,x2 = -1,y1 = -1,y2 = -1;
                for (int j = 0; j < size; j++) {
                    if (whichColumn(j, cipherText.charAt(i*2))!=-1) {
                        x1 = whichColumn(j, cipherText.charAt(i*2));
                    }
                    if (whichColumn(j, cipherText.charAt(i*2+1))!=-1) {
                        x2 = whichColumn(j, cipherText.charAt(i*2+1));
                    }
                    if (whichRow(j, cipherText.charAt(i*2))!=-1) {
                        y1 = whichRow(j, cipherText.charAt(i*2));
                    }
                    if (whichRow(j, cipherText.charAt(i*2+1))!=-1) {
                        y2 = whichRow(j, cipherText.charAt(i*2+1));
                    }
                }
                //System.out.println(x1+","+y1+"  "+x2+","+y2);
                res+= playfairTable[y1][x2];
                res+= playfairTable[y2][x1];
            }
            
        }
        
        return res;
    }
}
