import java.io.Serializable;
public class DefaultHRAssistant implements HRAssistant, Serializable {
    private final String username;
    private final String password;

    public DefaultHRAssistant(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
