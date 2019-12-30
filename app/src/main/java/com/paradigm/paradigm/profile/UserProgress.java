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

    private Map<String, CourseProgress> courses;

    public UserProgress() {
        courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        String courseName = course.getName();
        CourseProgress courseProgress = new CourseProgress(courseName);

        for (ContentModule module : course.getModuleList()) {
            String moduleName = module.getName();
            String moduleElementName = courseName + ", " + moduleName;
            ModuleProgress moduleProgress = new ModuleProgress(moduleElementName);

            for (Lesson lesson : module.provideLessonValues()) {
                String lessonName = lesson.getName();
                String lessonElementName = moduleElementName + ", " + lessonName;
                moduleProgress.setLessonProgress(lessonElementName, false);
            }

            for (Question question : module.provideQuestionValues()) {
                String questionName = question.getQuestionName();
                String questionElementName = moduleElementName + ", " + questionName;
                moduleProgress.setQuestionProgress(questionElementName, false);
            }

            courseProgress.setModuleProgress(moduleName, moduleProgress);
        }

        courses.put(courseName, courseProgress);
    }

    public void markQuestionCorrect(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setQuestionProgress(questionName, true);
    }

    public void markQuestionIncorrect(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setQuestionProgress(questionName, false);
    }

    public boolean isAnsweredCorrectly(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        return currentModuleProgress.getQuestionProgress(questionName);
    }

}
