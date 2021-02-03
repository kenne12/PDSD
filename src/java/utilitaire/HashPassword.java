/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaire;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kenne
 */
public class HashPassword {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();

        for (byte b1 : b) {
            sb.append(Integer.toHexString(b1).toString());
        }
        return sb.toString();
    }

    public static String md5(String c) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(c.getBytes());
        String str = new String(md.digest());
        return str;
    }

    public static void main(String[] args) {
        String password = "admin";
        try {
            hashPassword(password);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("" + ex);
        }
    }

}
