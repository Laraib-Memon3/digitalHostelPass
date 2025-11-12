package student;

public class Students {
    private String cmsId;
    private String name;
    private String password;
    private String department;
    private String guardianName;
    private String stcontact;

    public Students(String cmsId, String name, String password, String department, String guardianName, String stcontact) {
        this.cmsId = cmsId;
        this.name = name;
        this.password = password;
        this.department = department;
        this.guardianName = guardianName;
        this.stcontact = stcontact;
    }

    // Getters
    public String getCmsId() { return cmsId; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getDepartment() { return department; }
    public String getGuardianName() { return guardianName; }
    public String getContact() { return stcontact; }

    // Convert to line for file saving
    public String toFileString() {
        return cmsId + "," + name + "," + password + "," + department + "," + guardianName + "," + stcontact;
    }
}
