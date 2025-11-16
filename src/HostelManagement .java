import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class HostelManagement {

    // Data Classes 
    static class Student {
        String studentId, name, department, email, password, status;
        Student(String id, String name, String dept, String email, String pass) {

            studentId = id; this.name = name; department = dept;
            this.email = email; password = pass; status = "Active";
        }

        public void printDetails() {
            System.out.println("---------------------------");
            System.out.println("Student ID: " + studentId);
            System.out.println("Name      : " + name);
            System.out.println("Department: " + department);
            System.out.println("Email     : " + email);
            System.out.println("Status    : " + status);
            System.out.println("---------------------------");
        }
    }

    static class Admin {
        String adminId, name, password;
        Admin(String id, String name, String pass) { adminId = id; this.name = name; password = pass; }
    }

    static class LeaveRequest {
        String studentId, reason;
        LocalDate startDate, endDate;
        LeaveRequest(String id, String r, LocalDate s, LocalDate e) {
            studentId = id; reason = r; startDate = s; endDate = e;
        }

        public void printDetails() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            System.out.println("---------------------------");
            System.out.println("Student ID: " + studentId);
            System.out.println("Reason    : " + reason);
            System.out.println("From      : " + startDate.format(formatter));
            System.out.println("To        : " + endDate.format(formatter));
            System.out.println("---------------------------");
        }
    }

    // ======================= DATA STRUCTURES =======================
    static class MyArrayList {
        Student[] arr = new Student[10]; int size = 0;

        void add(Student s) { if (size == arr.length) expand(); arr[size++] = s; }

        void expand() {
            Student[] newArr = new Student[arr.length * 2];
            for(int i=0;i<size;i++) newArr[i]=arr[i];
            arr=newArr;
        }

        Student search(String id){
            for(int i=0;i<size;i++)
                if(arr[i].studentId.equals(id)) return arr[i];
            return null;
        }

        boolean remove(String id){
            for(int i=0;i<size;i++){
                if(arr[i].studentId.equals(id)){
                    for(int j=i;j<size-1;j++) arr[j]=arr[j+1];
                    arr[--size]=null;
                    return true;
                }
            }
            return false;
        }

        void printAll(){
            if(size==0) System.out.println("No students yet.");
            for(int i=0;i<size;i++) arr[i].printDetails();
        }

        int count(){ return size; }
    }

    static class MyLinkedListQueue {
        static class Node { LeaveRequest data; Node next; Node(LeaveRequest d){data=d;} }
        Node front, rear;

        void enqueue(LeaveRequest data){
            Node n=new Node(data);
            if(rear==null) front=rear=n;
            else {rear.next=n; rear=n;}
        }

        LeaveRequest dequeue(){
            if(front==null) return null;
            LeaveRequest t=front.data;
            front=front.next;
            if(front==null) rear=null;
            return t;
        }

        boolean isEmpty(){ return front==null; }

        void printQueue(){
            Node curr=front;
            if(curr==null){System.out.println(" No pending leave requests."); return;}
            while(curr!=null){ curr.data.printDetails(); curr=curr.next; }
        }

        int count(){
            int c=0; Node curr=front;
            while(curr!=null){c++; curr=curr.next;}
            return c;
        }
    }

    static class MyStack {
        static class Node { LeaveRequest data; Node next; Node(LeaveRequest d){data=d;} }
        Node top;

        void push(LeaveRequest data){ Node n=new Node(data); n.next=top; top=n; }

        LeaveRequest pop(){
            if(top==null) return null;
            LeaveRequest t=top.data;
            top=top.next;
            return t;
        }

        boolean isEmpty(){ return top==null; }
    }

  // Data Handler
    static class DataHandler{
        MyArrayList students = new MyArrayList();
        MyLinkedListQueue leaveQueue = new MyLinkedListQueue();
        MyStack leaveHistory = new MyStack();

        void addStudent(Student s){ students.add(s); System.out.println(" Student added: "+s.name); }

        void removeStudent(String id){
            if(students.remove(id)) System.out.println(" Student removed.");
            else System.out.println(" Not found.");
        }

        Student searchStudent(String id){
            Student s=students.search(id);
            if(s!=null){ System.out.println(" Found: "); s.printDetails(); }
            else System.out.println(" Not found.");
            return s;
        }

        void updateStudentStatus(String id,String st){
            Student s=students.search(id);
            if(s!=null){s.status=st; System.out.println(" Updated: "); s.printDetails();}
            else System.out.println(" Not found.");
        }

        void enqueueRequest(LeaveRequest r){
            leaveQueue.enqueue(r);
            System.out.println(" Leave requested: ");
            r.printDetails();
        }

        void processNextRequest(){
            LeaveRequest r=leaveQueue.dequeue();
            if(r==null) System.out.println(" No pending requests.");
            else{System.out.println(" Processed: "); r.printDetails(); leaveHistory.push(r);}
        }

        void popHistory(){
            LeaveRequest r=leaveHistory.pop();
            if(r==null) System.out.println("No history.");
            else { System.out.println(" Removed from history: "); r.printDetails(); }
        }

        void showPendingRequests(){
            System.out.println(" Pending Leave Requests ("+leaveQueue.count()+")");
            leaveQueue.printQueue();
        }
    }


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        DataHandler handler=new DataHandler();
        Admin admin=new Admin("admin01","System Admin","admin123");

        // default students
        handler.addStudent(new Student("S001","Alice","CS","alice@uni.edu","pass1"));
        handler.addStudent(new Student("S002","Bob","EE","bob@uni.edu","pass2"));

        System.out.println(" Welcome to Hostel Management System");

        while(true){
            System.out.println("\n==============================");
            System.out.println(" Admin Login");
            System.out.println(" Student Login");
            System.out.println("Exit");
            System.out.print("Enter choice: ");
            int ch=sc.nextInt(); sc.nextLine();

            if(ch==1){
                System.out.print(" Admin ID: "); String id=sc.nextLine();
                System.out.print(" Password: "); String pass=sc.nextLine();
                if(id.equals(admin.adminId) && pass.equals(admin.password)){
                    System.out.println("\n Welcome back, "+admin.name+"!");
                    while(true){
                        System.out.println("\n--- Admin Menu ---");
                        System.out.println("Total Students: "+handler.students.count());
                        System.out.println("Pending Leave Requests: "+handler.leaveQueue.count());
                        System.out.println("1. Add Student  2. Remove Student  3. Search Student  4. Update Status");
                        System.out.println("5. Show All Students  6. Process Next Leave  7. Pop History  0. Logout");
                        System.out.print(" Choice: "); int a=sc.nextInt(); sc.nextLine();
                        if(a==0) break;
                        switch(a){
                            case 1->{
                                System.out.print("ID: "); String sid=sc.nextLine();
                                System.out.print("Name: "); String sname=sc.nextLine();
                                System.out.print("Dept: "); String dept=sc.nextLine();
                                System.out.print("Email: "); String email=sc.nextLine();
                                System.out.print("Password: "); String spass=sc.nextLine();
                                handler.addStudent(new Student(sid,sname,dept,email,spass));
                            }
                            case 2->{ System.out.print("ID to remove: "); handler.removeStudent(sc.nextLine()); }
                            case 3->{ System.out.print("ID to search: "); handler.searchStudent(sc.nextLine()); }
                            case 4->{
                                System.out.print("ID to update: "); String uid=sc.nextLine();
                                System.out.print("New Status: "); String st=sc.nextLine();
                                handler.updateStudentStatus(uid,st);
                            }
                            case 5->handler.students.printAll();
                            case 6->handler.processNextRequest();
                            case 7->handler.popHistory();
                            default->System.out.println(" Invalid choice!");
                        }
                    }
                }else System.out.println("Invalid admin credentials!");
            }
            else if(ch==2){
                System.out.print(" Student ID: "); String sid=sc.nextLine();
                System.out.print("Password: "); String spass=sc.nextLine();
                Student s=handler.students.search(sid);
                if(s!=null && s.password.equals(spass)){
                    System.out.println("\n Welcome "+s.name+"!");
                    while(true){
                        System.out.println("\n--- Student Menu ---");
                        handler.showPendingRequests();
                        System.out.println("1. View My Details  2. Request Leave  0. Logout");
                        System.out.print(" Choice: "); int b=sc.nextInt(); sc.nextLine();
                        if(b==0) break;
                        switch(b){
                            case 1->s.printDetails();
                            case 2->{
                                System.out.print("Reason: "); String r=sc.nextLine();
                                System.out.print("Start Date (YYYY-MM-DD): "); LocalDate sd=LocalDate.parse(sc.nextLine());
                                System.out.print("End Date (YYYY-MM-DD): "); LocalDate ed=LocalDate.parse(sc.nextLine());
                                handler.enqueueRequest(new LeaveRequest(s.studentId,r,sd,ed));
                            }
                            default->System.out.println(" Invalid choice!");
                        }
                    }
                }else System.out.println("Invalid student credentials!");
            }
            else if(ch==0){ System.out.println(" Exiting system... Goodbye!"); break;}
            else System.out.println(" Invalid choice!");
        }
        sc.close();
    }
}
