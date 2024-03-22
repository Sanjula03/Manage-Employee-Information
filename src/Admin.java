import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Admin implements Serializable {
    private final List<HRManager> hrManagers;
    private final List<HRAssistant> hrAssistants;

    public Admin() {
        this.hrManagers = new ArrayList<>();
        this.hrAssistants = new ArrayList<>();
    }

    public void createHRManagerAccount() {
        HRManager hrManager = new HRManager();
        this.hrManagers.add(hrManager);
    }

    public void createHRAssistantAccount(String username, String password) {
        DefaultHRAssistant hrAssistant = new DefaultHRAssistant(username, password);
        this.hrAssistants.add(hrAssistant);
    }

    public Object getHrAssistants() {
        return null;
    }

    public Object getHrManagers() {
        return null;
    }

    public void setHrAssistants(Object ignoredHrAssistants) {
    }

    public void setHrManagers(Object ignoredHrManagers) {
    }

}
