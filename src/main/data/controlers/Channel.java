package data.controlers;

/**
 * Définit les canaux de communication & header
 *
 * @author rem
 */
public enum Channel {
    LIDAR((char) 0x20, (char) 0x21),
    ROBOT_POSITION((char) 0x20, (char) 0x22),
    BUDDY_POSITION((char) 0x20, (char) 0x23),
    EVENT((char) 0x20, (char) 0x24),
    SENSORS((char) 0x20, (char) 0x25)
    ;

    /**
     * Headers
     */
    private String headers;

    /**
     * Consruit un cannal de com
     * @param h1    char 1
     * @param h2    char 2
     */
    Channel(char h1, char h2) {
        char[] h = {h1, h2};
        this.headers = new String(h);
    }

    /**
     * Getter
     */
    public String getHeaders() {
        return headers;
    }
}
