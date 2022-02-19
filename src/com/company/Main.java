package com.company;

public class Main {

    public static void main(String[] args) {
	    String plainText = "\u0001#Eg\u0089«Íï";
        String K = "\u00134Wy\u009B¼ßñ";
        DES des = new DES(plainText,K);


    }

}
