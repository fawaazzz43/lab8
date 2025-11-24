package lab7;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mahmoud-elzankalony
 */
public class Instructor{
    private int userId ;
    private String role ;
    private String username ;
    private String email ;
    private String passwordHash ;

    ArrayList<Course> createdCourses = new ArrayList<>();
    Courses courses = new Courses("courses.json");
      User user=new User("users.json");

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Course> getCreatedCourses() {
        return createdCourses;
    }

    public Courses getCourses() {
        return courses;
    }

    public User getUser() {
        return user;
    }
    public Instructor(int userId, String role, String username, String email, String passwordHash) throws IOException
    {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        courses.load();
        user.load();
    }

    public void Createcourse(int courseId, String title, String description) throws IOException
    {
        Course course = new Course(courseId,title,description,this.userId,"PENDING");
        Courses courses = new Courses("courses.json");
        User user=new User("users.json");
                user.load();
                courses.load();
        courses.addCourse(course);
        this.createdCourses.add(course);
        courses.SaveToJsonCourses(); 
    user.save();           }

    public void Editcourse(Course course, int courseId, String title, String description, int instructorId) throws IOException
    {
        Courses courses = new Courses("courses.json");
        Course cu = null;
        User user=new User("users.json");
                user.load();
                courses.load();
          for(Course c: this.createdCourses)
              if(c.getCourseId()==(course.getCourseId()))
                   cu=c;
        if(!title.equals("")){
            course.setTitle(title);
            cu.setTitle(title);}
        if(!description.equals("")){
            course.setDescription(description);
            cu.setDescription(description);}
        
        courses.SaveToJsonCourses();
        user.save();
        courses.load();
        user.load();
    }
    public void deleteCourse(int courseId) throws IOException
    {Courses courses = new Courses("courses.json");
    User user=new User("users.json");
courses.load();
user.load();
        for(Course course : courses.getCourses()){
            if(course.getCourseId()==(courseId)){
                courses.deleteCourse(course);
                createdCourses.remove(course);
                break;}
        }
        for(Course course : this.createdCourses){
            if(course.getCourseId()==(courseId))
        this.createdCourses.remove(course);
        for(Student student:user.getStudents())
            for(Course cours : student.getEnrolledCourses())
                if(cours.getCourseId()==(courseId))
                    student.getEnrolledCourses().remove(cours);
         user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
        
    }}

    public void Createlesson(Course course,int lessonId, String title, String content,Quiz quiz) throws IOException
    {
        Courses courses = new Courses("courses.json");
    User user=new User("users.json");
        Lesson lesson = new Lesson(lessonId,title,content,quiz);
        course.getLessons().add(lesson);
        courses.SaveToJsonCourses();
        for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId())
                c.getLessons().add(lesson);
         user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }


    public void Editlesson(Course course,Lesson lesson,int lessonId, String title, String content) throws IOException
    {
        Courses courses = new Courses("courses.json");
    User user=new User("users.json");
    for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId()){
               for(Lesson l :c.getLessons())
                   if(l.getLessonId()==lessonId){
                       l=lesson;
                       break;}
               break;}
        if(!title.equals(""))
            lesson.setTitle(title);
        if(!content.equals(""))
            lesson.setContent(content);
        user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }


    public void Deletelesson(Course course,int lessonId) throws IOException
    {
         Courses courses = new Courses("courses.json");
    User user=new User("users.json");
        for(Lesson lesson : course.getLessons())
        {
            if (lesson.getLessonId()==(lessonId)){
                lesson.setQuiz(null);
                course.getLessons().remove(lesson);
            break;}
        }
        for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId()){
               for(Lesson l :c.getLessons())
                   if(l.getLessonId()==lessonId){
                       l.setQuiz(null);
                       c.getLessons().remove(l);
                       break;}
               break;}
        user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }
    public void EditQuiz(Course course,Lesson lesson,Quiz quiz,int quizID,String title,int numberOfQuestions) throws IOException
    {
        Courses courses = new Courses("courses.json");
    User user=new User("users.json");
    for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId()){
               for(Lesson l :c.getLessons())
                   if(l.getLessonId()==lesson.getLessonId()){
                       l.setQuiz(lesson.getQuiz());
                       break;}
               break;}
        if(!title.equals(""))
            quiz.setTitle(title);
        if(numberOfQuestions != 0)
            quiz.setNumberOfQuestions(numberOfQuestions);
        user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }
    public void EditQuestions(Course course,Lesson lesson,Quiz quiz,Questions questions,String questionText, String correctAnswer, String[] options) throws IOException
    {
        Courses courses = new Courses("courses.json");
    User user=new User("users.json");
    for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId()){
               for(Lesson l :c.getLessons())
                   if(l.getLessonId()==lesson.getLessonId()){
                      for(Questions q :l.getQuiz().getQuestions())
                          if(q.getQuestionsId()==questions.getQuestionsId()){
                              q=questions;
                              break;}
                       break;}
               break;}
        if(!questionText.equals(""))
            questions.setQuestionText(questionText);
        if(!correctAnswer.equals(""))
            questions.setCorrectAnswer(correctAnswer);
        if(options!=null)
            for(String option:options){
                int i=0;
                if(!option.equals(""))
                    questions.getOptions()[i]=option;}
        user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }
    public void DeleteQuestions(Course course,Lesson lesson,Quiz quiz,Questions questions) throws IOException
    {
    Courses courses = new Courses("courses.json");
    User user=new User("users.json");
    for(Course c : createdCourses)
            if(c.getCourseId()==course.getCourseId()){
               for(Lesson l :c.getLessons())
                   if(l.getLessonId()==lesson.getLessonId()){
                      for(Questions q :l.getQuiz().getQuestions())
                          if(q.getQuestionsId()==questions.getQuestionsId()){
                              l.getQuiz().getQuestions().remove(q);
                              break;}
                       break;}
               break;}
    quiz.getQuestions().remove(questions);
    user.save();
        courses.SaveToJsonCourses();
        courses.load();
        user.load();
    }
    public int getUserId()
    {
        return userId;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }
}
