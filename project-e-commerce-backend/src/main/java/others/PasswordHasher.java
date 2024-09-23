package others;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hashPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            // Băm mật khẩu
            byte[] hashBytes = digest.digest(password.getBytes());

            // Chuyển đổi mảng byte sang chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String password = "12345678912345678900";
        String hashedPassword = hashPassword(password);

        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password (SHA-512): " + hashedPassword);
        System.out.println("Hashed Password (SHA-512) length: " + hashedPassword.length());
    }
}
