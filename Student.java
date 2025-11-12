public class Student {
    int id;
    String name;
    private String status; // can be Applied / Approved / Rejected / In Hostel / At Home

    public Student(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Status: " + status);
    }
}
