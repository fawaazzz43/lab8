package lab7;
import java.util.ArrayList;

public class Login {
      private ArrayList<Student> allStudents;
    private ArrayList<Instructor> allInstructors;
    private ArrayList<admin> allAdmins;
    private User user;
    
    


    public Login() {
        this.user = new User("users.json");
        user.load();
        this.allStudents = user.getStudents() ;
        this.allInstructors = user.getInstructors() ;
        this.allAdmins = user.getAdmins() ;
        user.save();
       
    }

    public int search(String email, String password) {
        for (Student student : allStudents) {
            if (student.getEmail().equals(email) && student.getPasswordHash().equals(password)) {
                return 1;
            }
        }

        for (Instructor instructor : allInstructors) {
            if (instructor.getEmail().equals(email) && instructor.getPasswordHash().equals(password)) {
                return 2;
            }
        }

        for (admin ad : allAdmins) {
            if (ad.getEmail().equals(email) && ad.getPasswordHash().equals(password)) {
                return 3;
            }
        }

        return 0;
    }




}
