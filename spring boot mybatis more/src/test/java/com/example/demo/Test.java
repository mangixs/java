package com.example.demo;

public class Test {
    public static void main(String[] args) {
        String s = "abc oiulak3 laskdjo java string abc oiuladk3 laskdjo java strin33g";
        String[] arr = s.split("\\s+");
        String address1 = "";
        String address2 = "";
        int i = 0;
        boolean done = false;
        for (String val : arr) {
            if (done) {
                address2 += " " + val;
                continue;
            }
            String temp = address1 + " " + val;
            if (i == 0) {
                temp = temp.trim();
            }
            i++;
            int len = temp.length();
            if (len > 32) {
                done = true;
                address2 += val;
                continue;
            }
            address1 = temp;
        }
        System.out.print(address1 + "----" + address2);
    }
}
