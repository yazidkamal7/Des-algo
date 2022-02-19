package com.company;

import java.util.Arrays;

public class DES {


    String Message = "";
    String KeyInput = "";

    static int [] pc1 = { 57, 49, 41, 33, 25 ,17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4 };

    int [] shifting = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };

    int [] pc2 = { 14, 17, 11, 24, 1, 5, 3, 28,
            15, 6, 21, 10, 23, 19, 12, 4,
            26, 8, 16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55, 30, 40,
            51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32 };

    int [] ip = {58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};

    int [] Ebox = {32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29, 28,
            29, 30, 31, 32, 1 };

    int[] P = { 16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25 };

    int[][][] sbox = {
                    { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                    { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                    { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                    { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } },

                     { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                    { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                    { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                    { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } },
                    { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                    { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                    { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                    { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } },
            { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                    { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                    { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                    { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } },
            { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                    { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                    { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                    { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } },
            { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                    { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                    { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                    { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } },
            { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                    { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                    { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                    { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } },
            { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                    { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                    { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                    { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } }};


    int [] ip1 = {40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25 };

    int [][] c = new int[16][28];
    int [][] d = new int[16][28];
    int [][] keys = new int[16][56];
    int [][] key = new int[16][48];
    int [][] L = new int[16][32];
    int [][] R = new int[16][32];
    int [] RL = new int[64];

    public DES(String message, String keyInput) {
        Message = message;
        KeyInput = keyInput;


//        Key gen Start
        String kBinary = txtToBinnary(KeyInput);
        String [] kspliter = kBinary.split("(?<=\\G.{64})");
        System.out.println("K="+kspliter[0]);
        int [] Kpc = PC(kspliter[0]);
        System.out.print("K+");
        printArray(Kpc,Kpc.length);

        System.out.print("left side");

        for (int i = 0; i < Kpc.length/2 ; i++) {

            c[0][i] =Kpc[i];
            System.out.print(c[0][i] + ",");
        }

        int j = 0;
        System.out.print("\nRight side");
        for (int i = Kpc.length / 2; i < Kpc.length; i++, j++) {

            d[0][j] = Kpc[i];
            System.out.print(d[0][j] + ",");

        }

        leftShifting();
        printing();
        genKey();

//        keyes gen end

        System.out.println("\n--------*******-----******-----\n \n \n ");


        String plainTextBinary = txtToBinnary(Message);
        String [] plainTextspliter = plainTextBinary.split("(?<=\\G.{64})");
        System.out.println("M= "+plainTextspliter[0]);
        int [] iptext = IP(plainTextspliter[0]);
        System.out.print("\nIP=");
        printArray(iptext,iptext.length);
        int [] firtLeft = new int[32];
        int [] firtstRight = new int[32];
        for (int i = 0; i < iptext.length/2; i++) {
            firtLeft[i]= iptext[i];
            firtstRight[i]=iptext[i+firtLeft.length];
        }
        System.out.print("\nfirst left");printArray(firtLeft,firtLeft.length);
        System.out.print("\nfirst right");printArray(firtstRight,firtstRight.length);
        for (int i = 0; i < firtstRight.length; i++) {
            L[0][i]=firtstRight[i];
        }
        int [] ertemp = ep(firtstRight);
        System.out.print("\nE(R0)=");printArray(ertemp,ertemp.length);
        int [] rxortemp = xor(ertemp,key[0]);
        System.out.print("\nK0XoRE(R0)=");printArray(rxortemp,rxortemp.length);
        String xortemp = arrayToString(rxortemp);
        String [] xorSpliter = xortemp.split("(?<=\\G.{6})");

        String sBox = "";
        for (int i = 0; i < xorSpliter.length; i++) {
           sBox = sBox + SBox(xorSpliter[i],i);

        }
        int []rtemp = stringToArrayInt(sBox);
        System.out.print("\nSbox = ");printArray(rtemp,rtemp.length);
        int [] f = function(rtemp);
        System.out.print("\nf=");
        printArray(f,f.length);



        int [] temp = xor(firtLeft,f);
        System.out.print("R0=(L0+f)");
        printArray(temp,temp.length);


        for (int i = 0; i < temp.length; i++) {
            R[0][i]=temp[i];
        }

        for (int i = 1; i < 16; i++) {
            copyToL(i);
            ertemp = ep(R[i-1]);
           rxortemp = xor(ertemp,key[i]);
            xortemp = arrayToString(rxortemp);
             xorSpliter = xortemp.split("(?<=\\G.{6})");
            sBox = "";
            for ( j = 0; j < xorSpliter.length; j++) {
                sBox = sBox + SBox(xorSpliter[j],j);
            }
            rtemp = stringToArrayInt(sBox);
           f = function(rtemp);

            temp = xor(L[i-1],f);
            for ( j= 0; j < temp.length;j++) {
                R[i][j]=temp[j];
            }

        }


        for (int i = 0; i < R[1].length; i++) {
            RL[i]=R[15][i];
            int t = i + L[15].length;
           RL[t]=L[15][i];
        }

        int [] cipherTextBinnart = IP1();
        String textCipher = arrayToString(cipherTextBinnart);
        System.out.println("IP-1= "+textCipher);
//        To HEX
        String []textCipherSpliter = textCipher.split("(?<=\\G.{28})");
        String hex = "";
        for (int i = 0; i < textCipherSpliter.length; i++) {
            hex = hex + binnaryToHex(textCipherSpliter[i]);
        }
        System.out.println("C="+hex);
        String texttext = binnaryToTxt(textCipher);
        System.out.println(texttext);



//        int decimal = Integer.parseInt(textCipher,2);
//        String hexStr = Integer.toString(decimal,16);
//        System.out.println(hexStr);







    }

    private String binnaryToHex(String s) {
        int decimal = Integer.parseInt(s,2);
        String hexStr = Integer.toString(decimal,16);
        return hexStr.toUpperCase();
    }

    void printArray(int [] x , int w ){
        System.out.println("");
        for (int i = 0; i < w; i++) {
            System.out.print(x[i]);
        }
        System.out.println("");
    }

    private int[] IP1() {
        int [] c = new int[ip1.length];
        for (int i = 0; i < ip1.length; i++) {
            c[i]= RL[ip1[i]-1];
        }
        return c;
    }

    private void copyToL(int x) {
        for (int i = 0; i < L[x].length; i++) {
            L[x][i]=R[x-1][i];

        }
    }



    private int[] function(int[] rtemp) {
        int [] temp = new int [rtemp.length];
        for (int i = 0; i < rtemp.length; i++) {
            temp[i]=rtemp[P[i]-1];
        }
        return temp;
    }

    private int[] stringToArrayInt(String sBox) {
        int [] temp = new int[sBox.length()];
        for (int i = 0; i < temp.length; i++) {
            temp[i]=Character.getNumericValue(sBox.charAt(i));

        }
        return temp;
    }

    private String SBox(String s, int i) {
        int x = Integer.parseInt((String.valueOf(s.charAt(0))+String.valueOf(s.charAt(5))),2);
        int y = Integer.parseInt(s.substring(1,5),2);
        String sb = Integer.toBinaryString(sbox[i][x][y]);
        if(sb.length()<4){
            for (int j = sb.length(); j < 4; j++) {
                sb = "0"+sb;
            }
        }
        return sb;

    }

    private String arrayToString(int[] rxortemp) {
        return Arrays.toString(rxortemp).replaceAll("\\s+", "").replaceAll(",","").replaceAll("\\[","").replaceAll("\\]","");
    }


    public DES() {
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getKeyInput() {
        return KeyInput;
    }

    public void setKeyInput(String keyInput) {
        KeyInput = keyInput;
    }
//change text to Binnary 0&1
    public String txtToBinnary(String input){
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int charACII = (int)input.charAt(i);
            for (int j = 0; j < (8 - Integer.toBinaryString(charACII).length()); j++) {
                st.append("0");
            }
            st.append(Integer.toBinaryString(charACII));

        }
        return st.toString();
    }
    public String binnaryToTxt(String input){
        StringBuilder plain = new StringBuilder();
        String c;
        for (int i = 0; i < input.length(); i+=8) {
            c=input.substring(i,i+8);
            char ch = (char) Integer.parseInt(c,2);
            plain.append(ch);
        }
        return plain.toString();
    }
    public  int [] PC (String k){
        int [] firstpc = new int[pc1.length];
        for (int i = 0; i < pc1.length; i++) {
            firstpc[i]=Character.getNumericValue(k.charAt(pc1[i]-1));
        }
        return firstpc;
    }
    public void leftShifting (){
        int [] ltemp = new int[c[0].length];
        int [] rtemp = new int[d[0].length];
        for (int i = 0; i < ltemp.length; i++) {
            ltemp[i]=c[0][i];
            rtemp[i]=d[0][i];

        }
        printArray(ltemp,ltemp.length);
        printArray(rtemp,rtemp.length);

        for (int i = 0; i < c.length; i++) {
            if(i==0){
                for (int j = 0; j < c[0].length-shifting[i]; j++) {
                    c[i][j] = ltemp[j+shifting[i]];
                    d[i][j] = rtemp[j+shifting[i]];
                }
                int k = 0;
                for (int j = c[i].length-shifting[i]; j < c[i].length; j++) {
                    c[i][j]=ltemp[k];
                    d[i][j]=rtemp[k];
                    k++;
                }

            }
            else {
                for (int j = 0; j < c[0].length-shifting[i]; j++) {
                    c[i][j] = c[i-1][j+shifting[i]];
                    d[i][j] = d[i-1][j+shifting[i]];
                }
                int k = 0;
                for (int j = c[i].length-shifting[i]; j < c[i].length; j++) {
                    c[i][j]=c[i-1][k];
                    d[i][j]=d[i-1][k];
                    k++;
                }
            }

        }
    }
    void genKey(){
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                keys[i][j]=c[i][j];
                keys[i][j+c[i].length]=d[i][j];
            }
        }
        for (int i = 0; i < keys.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                key[i][j] = keys[i][pc2[j]-1];

            }

        }

        for (int i = 0; i < key.length; i++) {
            System.out.print("K["+i+"]=");
            for (int j = 0; j < key[i].length; j++) {
                System.out.print(key[i][j]);
            }
            System.out.println("");

        }



    }
    void printing(){
        System.out.println("");
        for (int i = 0; i < c.length; i++) {
            System.out.print("c["+i+"]={");
            for (int j = 0; j < c[i].length; j++) {
                System.out.print(c[i][j]+",");
            }
            System.out.println("}");

        }
        for (int i = 0; i < d.length; i++) {
            System.out.print("d["+i+"]={");
            for (int j = 0; j < d[i].length; j++) {
                System.out.print(d[i][j]+",");
            }
            System.out.println("}");

        }
    }
    private int[] IP(String s) {
        int []stringip = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {

            stringip[i] = Character.getNumericValue(s.charAt(ip[i]-1));
        }
        return stringip;
    }
    int [] ep (int r[]){
        int []er = new int[48];
        for (int i = 0; i <Ebox.length ; i++) {
            er[i]= r[Ebox[i]-1];
            
        }
        return er;

    }
    int [] xor (int [] r , int [] k){
        int [] x = new int[r.length];
        for (int i = 0; i < r.length; i++) {
            x[i]=r[i]^k[i];
        }
        return x;
    }



}
