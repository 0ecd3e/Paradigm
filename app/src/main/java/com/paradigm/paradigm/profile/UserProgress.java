package com.paradigm.paradigm.profile;

import com.paradigm.paradigm.exercises.question.Question;
import com.paradigm.paradigm.profile.progressEntries.CourseProgress;
import com.paradigm.paradigm.profile.progressEntries.LessonProgress;
import com.paradigm.paradigm.profile.progressEntries.ModuleProgress;
import com.paradigm.paradigm.profile.progressEntries.QuestionProgress;
import com.paradigm.paradigm.text.ContentModule;
import com.paradigm.paradigm.text.Course;
import com.paradigm.paradigm.text.Lesson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProgress implements Serializable {

    private List<CourseProgress> courses;

    public UserProgress() {
        courses = new ArrayList<>();
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
                LessonProgress lessonProgress = new LessonProgress(lessonElementName);

                for (Question question : lesson.getQuestions()) {
                    String questionName = question.getQuestionName();
                    String questionElementName = moduleElementName + ", " + questionName;
                    QuestionProgress questionProgress = new QuestionProgress(questionElementName);
                    lessonProgress.setQuestionProgress(questionProgress);
                }
                moduleProgress.setLessonProgress(lessonProgress);
            }
            courseProgress.setModuleProgress(moduleProgress);
        }

        courses.add(courseProgress);
    }

    public void markQuestionCorrect(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();

        CourseProgress currentProgress = new CourseProgress();
        for (CourseProgress courseProgress : courses) {
            if (courseProgress.getComponentName().equals(parentCourse)) {
                currentProgress = courseProgress;
            }
        }

        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        LessonProgress currentLessonProgress = currentModuleProgress.getLessonProgress(parentLesson);
        currentLessonProgress.setQuestionProgress(questionName, true);
    }

    public void markQuestionIncorrect(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        assert currentProgress != null;
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setQuestionProgress(questionName, false);
    }

    public boolean isAnsweredCorrectly(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = question.getParentContentModule();
        String questionName = parentCourse + ", " + parentModule + ", " + question.getQuestionName();
        CourseProgress currentProgress = courses.get(parentCourse);
        assert currentProgress != null;
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        return currentModuleProgress.getQuestionProgress(questionName);
    }

    public void markLessonComplete(Lesson lesson) {
        String parentCourse = lesson.getParentCourse();
        String parentModule = lesson.getParentContentModule();
        String lessonName = parentCourse + ", " + parentModule + ", " + lesson.getName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setLessonProgress(lessonName, true);
    }

    public void markLessonIncomplete(Lesson lesson) {
        String parentCourse = lesson.getParentCourse();
        String parentModule = lesson.getParentContentModule();
        String lessonName = parentCourse + ", " + parentModule + ", " + lesson.getName();
        CourseProgress currentProgress = courses.get(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        currentModuleProgress.setLessonProgress(lessonName, false);
    }

    public boolean isLessonComplete(Lesson lesson) {
        String parentCourse = lesson.getParentCourse();
        String parentModule = lesson.getParentContentModule();
        String lessonName = parentCourse + ", " + parentModule + ", " + lesson.getName();
        CourseProgress currentProgress = courses.get(parentCourse);
        assert currentProgress != null;
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        return currentModuleProgress.getLessonProgress(lessonName);
    }

    public boolean isModuleComplete(ContentModule contentModule) {
        String parentCourse = contentModule.getParentCourse();
        CourseProgress currentProgress = courses.get(parentCourse);
        assert currentProgress != null;
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(contentModule.getName());
        return currentModuleProgress.isComplete();
    }

    public boolean isCourseComplete(Course course) {
        CourseProgress currentProgress = courses.get(course.getName());
        assert currentProgress != null;
        return currentProgress.isComplete();
    }

}
