import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Department implements Serializable {
    private final String name;
    private final List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void addDesignation(String ignoredDesignation) {
    }
}
