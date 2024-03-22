public class Employee {
    private final String name;
    private final String department;
    private final String designation;
    private final String epfNumber;

    public Employee(String name, String department, String designation, String epfNumber) {
        this.name = name;
        this.department = department;
        this.designation = designation;
        this.epfNumber = epfNumber;
    }


    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public String getEpfNumber() {
        return epfNumber;
    }

}
