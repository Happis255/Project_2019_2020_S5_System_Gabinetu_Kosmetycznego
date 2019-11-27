package myPage.others;

public class Encrypter {
    public String encrypt(String pass) {
        /*
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update(pass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte raw[] = md.digest();
        String hash = (new BASE64Encoder()).encode(raw);
        */
        return pass;
    }
}
