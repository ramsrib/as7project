import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {
    
    public static void main(String args[]) {
        //String password = args[0];
        String password = "password";
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        System.out.println("Password hash : " + hash);
        String passwordHash = org.jboss.security.Base64Utils.tob64(hash);
        System.out.println("Password hash : " + passwordHash);
    }

}
