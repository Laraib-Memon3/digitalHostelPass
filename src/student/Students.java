package student;

public class Students {
    private String cmsId;
    private String name;
    private String password;
    private String department;
    private String guardianName;
    private String contact;

    public Students(String cmsId, String name, String password, String department, String guardianName, String contact) {
        this.cmsId = cmsId;
        this.name = name;
        this.password = password;
        this.department = department;
        this.guardianName = guardianName;
        this.contact = contact;
    }

    // Getters
    public String getCmsId() { return cmsId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getDepartment() { return department; }
    public String getGuardianName() { return guardianName; }
    public String getContact() { return contact; }

    // Convert to line for file saving
    public String toFileString() {
        return cmsId + "," + name + "," + password + "," + department + "," + guardianName + "," + contact;
    }
}
