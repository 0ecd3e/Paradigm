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
            String moduleElementName = courseName + "," + moduleName;
            ModuleProgress moduleProgress = new ModuleProgress(moduleElementName);

            for (Lesson lesson : module.getLessons()) {
                String lessonName = lesson.getName();
                String lessonElementName = moduleElementName + "," + lessonName;
                LessonProgress lessonProgress = new LessonProgress(lessonElementName);

                for (Question question : lesson.getQuestions()) {
                    String questionName = question.getQuestionName();
                    String questionElementName = lessonElementName + "," + questionName;
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
        QuestionProgress currentQuestionProgress = getQuestionProgress(question);
        currentQuestionProgress.setComplete();
    }

    private QuestionProgress getQuestionProgress(Question question) {
        String parentCourse = question.getParentCourse();
        String parentModule = parentCourse + "," + question.getParentContentModule();
        String parentLesson = parentModule + "," + question.getParentLesson();
        String questionName = parentLesson + "," + question.getQuestionName();

        CourseProgress currentProgress = getCourseProgress(parentCourse);

        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        LessonProgress currentLessonProgress = currentModuleProgress.getLessonProgress(parentLesson);
        return currentLessonProgress.getQuestionProgress(questionName);
    }

    private CourseProgress getCourseProgress(String parentCourse) {
        CourseProgress currentProgress = new CourseProgress();
        for (CourseProgress courseProgress : courses) {
            if (courseProgress.getComponentName().equals(parentCourse)) {
                currentProgress = courseProgress;
            }
        }
        return currentProgress;
    }

    public void markQuestionIncorrect(Question question) {
        QuestionProgress currentQuestionProgress = getQuestionProgress(question);
        currentQuestionProgress.clearProgress();

    }

    public boolean isAnsweredCorrectly(Question question) {
        QuestionProgress currentQuestionProgress = getQuestionProgress(question);
        return currentQuestionProgress.isComplete();
    }

    public boolean isLessonComplete(Lesson lesson) {
        String parentCourse = lesson.getParentCourse();
        String parentModule = lesson.getParentContentModule();
        CourseProgress currentProgress = getCourseProgress(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        LessonProgress currentLessonProgress = currentModuleProgress.getLessonProgress(lesson.getName());
        return (currentLessonProgress.completePercentage() == 100);
    }

    public boolean isModuleComplete(ContentModule contentModule) {
        String parentCourse = contentModule.getParentCourse();
        CourseProgress courseProgress = getCourseProgress(parentCourse);
        ModuleProgress currentModuleProgress = courseProgress.getModuleProgress(contentModule.getName());
        return (currentModuleProgress.completePercentage() == 100);
    }

    public boolean isCourseComplete(Course course) {
        for (CourseProgress courseProgress : courses) {
            if (courseProgress.getComponentName().equals(course.getName())) {
                return (courseProgress.completePercentage() == 100);
            }
        }
        return false;
    }
}
