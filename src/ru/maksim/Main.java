package ru.maksim;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Main {

    public static void main(String[] args) throws Exception {
        useMessageDigest();
    }

    // 5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5 <--- 12345
    private static void useMessageDigest() throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        try (DigestOutputStream outputStream = new DigestOutputStream(new FileOutputStream("test.txt"), sha)) {
            outputStream.write("12345".getBytes());
            StringBuffer sb = new StringBuffer();
            byte[] byteData = outputStream.getMessageDigest().digest();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Hex format : " + sb.toString() + "\ndigest length: " + sb.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

