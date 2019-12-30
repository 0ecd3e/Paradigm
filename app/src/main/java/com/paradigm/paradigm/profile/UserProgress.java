package com.paradigm.paradigm.profile;

import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import java.util.HashMap;
import java.util.Map;

public class UserProgress {
//    private Map<String, Boolean> courseElements;
//
//    public UserProgress() {
//        courseElements = new HashMap<>();
//    }
//
//    public void addCourse(Course course) {
//        String courseName = course.getName() + ", ";
//
//        for (ContentModule module : course.getModules()) {
//            String moduleName = module.getName() + ", ";
//
//            for (Lesson lesson : module.getLessons()) {
//                String lessonName = lesson.getName();
//                String lessonElement = courseName + moduleName + lessonName;
////                courseElements.put(lessonElement, lesson.isComplete());
//                courseElements.put(lessonElement, false);
//            }
//
//            for (Question question : module.getQuestions()) {
//                String questionName = question.getQuestionName();
//                String questionElement = courseName + moduleName + questionName;
////                courseElements.put(questionElement, question.isAnsweredCorrectly());
//                courseElements.put(questionElement, false);
//            }
//        }
//    }
//
//    public void markQuestionCorrect(Question question) {
//        courseElements.put(getQuestionKey(question), true);
//    }
//
//    public void markQuestionIncorrect(Question question) {
//        courseElements.put(getQuestionKey(question), false);
//    }
//
//    public String getQuestionKey(Question question) {
//        String courseName = question.getParentCourse() + ", ";
//        String moduleName = question.getParentContentModule() + ", ";
//        String questionName = question.getQuestionName();
//        return courseName + moduleName + questionName;
//    }
//
//    public boolean isAnsweredCorrectly(Question question) {
//        return courseElements.get(getQuestionKey(question));
//    }

    private Map<String, CourseProgress> courses;

    public UserProgress() {
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        String courseName = course.getName();
        CourseProgress courseProgress = new CourseProgress(courseName);

        for (ContentModule module : course.getModules()) {
            String moduleName = module.getName();
            String moduleElementName = courseName + ", " + moduleName;
            ModuleProgress moduleProgress = new ModuleProgress(moduleElementName);

            for (Lesson lesson : module.getLessons()) {
                String lessonName = lesson.getName();
                String lessonElementName = moduleElementName + ", " + lessonName;
//                courseElements.put(lessonElement, lesson.isComplete());
                moduleProgress.setLessonProgress(lessonElementName, false);
            }

            for (Question question : module.getQuestions()) {
                String questionName = question.getQuestionName();
                String questionElementName = moduleElementName + ", " + questionName;
//                courseElements.put(questionElement, question.isAnsweredCorrectly());
                moduleProgress.setQuestionProgress(questionElementName, false);
            }

            courseProgress.setModuleProgress(moduleName, moduleProgress);
        }

        courses.put(courseName, courseProgress);
    }

    public void markQuestionCorrect(Question question) {
//        courseElements.put(getQuestionKey(question), true);
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setQuestionProgress(questionName, true);
    }

    public void markQuestionIncorrect(Question question) {
//        courseElements.put(getQuestionKey(question), false);
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setQuestionProgress(questionName, false);
    }

    public boolean isAnsweredCorrectly(Question question) {
//        return courseElements.get(getQuestionKey(question));
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        return currentModuleProgress.getQuestionProgress(questionName);
    }

}
