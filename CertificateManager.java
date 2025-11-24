package lab7;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

public class CertificateManager {
    private static final String USERS_FILE = "users.json";

    public static ArrayList<Certificate> getCertificates(String studentID) throws Exception {
        ArrayList<Certificate> certificates = new ArrayList<>();
        String jsonString = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
        JSONObject usersObj = new JSONObject(jsonString);
        JSONArray students = usersObj.getJSONArray("students");

        for (int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            if (student.getString("userId").equals(studentID) && student.has("certificates")) {
                JSONArray certArray = student.getJSONArray("certificates");
                for (int j = 0; j < certArray.length(); j++) {
                    JSONObject c = certArray.getJSONObject(j);
                    certificates.add(new Certificate(
                            c.getString("studentID"),
                            c.getString("courseID"),
                            c.getString("courseTitle"),
                            c.getString("issueDate")
                    ));
                }
            }
        }
        return certificates;
    }

    public static void addCertificate(String studentID, Certificate cert) throws Exception {
        String jsonString = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
        JSONObject usersObj = new JSONObject(jsonString);
        JSONArray students = usersObj.getJSONArray("students");

        for (int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            if (student.getString("userId").equals(studentID)) {
                if (!student.has("certificates")) student.put("certificates", new JSONArray());
                JSONArray certArray = student.getJSONArray("certificates");

                JSONObject c = new JSONObject();
                c.put("certificateID", cert.getCertificateID());
                c.put("studentID", cert.getStudentID());
                c.put("courseID", cert.getCourseID());
                c.put("courseTitle", cert.getCourseTitle());
                c.put("issueDate", cert.getIssueDate());

                certArray.put(c);
                break;
            }
        }
        try (FileWriter file = new FileWriter(USERS_FILE)) {
            file.write(usersObj.toString(4));
        }
    }
}
