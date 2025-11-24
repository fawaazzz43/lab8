package lab7;
import java.time.LocalDate;
import java.util.ArrayList;

public class CertificateGenerator {

    public static void generateCertificateIfPassed(String studentID, String courseID, String courseTitle, ArrayList<Quiz> quizzes, ArrayList<Integer> studentScores) throws Exception {
        boolean allPassed = true;

        for (int i = 0; i < quizzes.size(); i++) {
            Quiz q = quizzes.get(i);
            int score = studentScores.get(i);
            if (!q.isPassed(score)) {
                allPassed = false;
                break;
            }
        }

        if (allPassed) {
            String issueDate = LocalDate.now().toString();
            Certificate cert = new Certificate(studentID, courseID, courseTitle, issueDate);
            CertificateManager.addCertificate(studentID, cert);
        }
    }
}
