import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class HRManager implements Serializable {
    private List<Department> departments;

    public HRManager() {
        this.departments = new ArrayList<>();
    }

    public HRManager(String ignoredPassword) {
    }

    public void addDepartment(String departmentName) {
        Department department = new Department(departmentName);
        departments.add(department);
    }

    public void addDesignation(String departmentName, String designation) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                department.addDesignation(designation);
                break;
            }
        }
    }

    public Employee searchEmployee(String criteria, String value) {
        for (Department department : departments) {
            for (Employee employee : department.getEmployees()) {
                switch (criteria.toLowerCase()) {
                    case "name" -> {
                        if (employee.getName().equalsIgnoreCase(value)) {
                            return employee;
                        }
                    }
                    case "department" -> {
                        if (employee.getDepartment().equalsIgnoreCase(value)) {
                            return employee;
                        }
                    }
                    case "designation" -> {
                        if (employee.getDesignation().equalsIgnoreCase(value)) {
                            return employee;
                        }
                    }
                    case "epfnumber" -> {
                        if (employee.getEpfNumber().equalsIgnoreCase(value)) {
                            return employee;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void addEmployee() {
    }

    public Object getDepartments() {
        return null;
    }

    public void setDepartments(Object ignoredDepartments) {
    }
}
