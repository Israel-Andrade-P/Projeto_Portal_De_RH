package util;

import java.security.SecureRandom;

public class FuncionarioUtils {
    public static String generateId() {
        String pool = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            int randIdx = rand.nextInt(pool.length());
            sb.append(pool.charAt(randIdx));
        }
        return sb.toString();
    }
}
