package slfdemo.sidecar.springboot;


import lombok.Getter;

@Getter
public class Constants {

    private static final String TOKEN_KEY = "slfdemoalmar";
    private static final long TOKEN_VALIDITY = 60 * 60;

    public static long getTokenValidity() {
        return TOKEN_VALIDITY;
    }

    public static String getTokenKey() {
        return TOKEN_KEY;
    }
}
