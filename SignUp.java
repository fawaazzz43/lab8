package lab7;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;


public class SignUp {

    private ArrayList<Student> allStudents;
    private ArrayList<Instructor> allInstructors;
    private ArrayList<admin> allAdmins ;
    private User user;
    
    


    public SignUp() {
        this.user = new User("users.json");
        user.load();
        this.allStudents = user.getStudents() != null ? user.getStudents() : new ArrayList<>();
        this.allInstructors = user.getInstructors() != null ? user.getInstructors() : new ArrayList<>();
        this.allAdmins = user.getAdmins() != null ? user.getAdmins() : new ArrayList<>();
        user.save();
    }

    

    

    public void AddStudent(Student student) {
        
        allStudents.add(student);
        user.save();
       
    }
    public void AddInstructor(Instructor instructor) {
        
        allInstructors.add(instructor);
        user.save();
        
    }
    public void AddAdmin(admin admin)
    {
        allAdmins.add(admin);
        user.save();
    }

    

    public boolean validateuserId(int userId) {
        for ( Student student : allStudents) {
            if (student.getUserId()==(userId)) {
                return true;
            }
        }

        for ( Instructor instructor : allInstructors) {
            if (instructor.getUserId()==(userId)) {
                return true;
            }
        }

        for  ( admin ad : allAdmins)
        {
            if (ad.getUserId()==(userId)) {
                return true;
            }
        }
        return false;
    }
    public int validateuserEmail(String email) {

        if (!email.contains("@") || !email.contains(".")) {
            return 1;
        }
        for (Student student : allStudents) {
            if (student.getEmail().equals(email)) {
                return 2;
            }
        }
        for (Instructor instructor : allInstructors) {
            if (instructor.getEmail().equals(email)) {
                return 2;
            }
        }
        return 0;
    }

     public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            
        
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
