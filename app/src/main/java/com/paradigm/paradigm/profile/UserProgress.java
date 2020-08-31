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
    private static ContentModule currentModule;
    private static Lesson currentLesson;
    private static ContentModule checkpointModule;
    private static Question currentQuestion;

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

    public QuestionProgress getQuestionProgress(Question question) {
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

    public CourseProgress findCourseProgress(String parentCourse) {
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
        String parentModule = parentCourse + "," + lesson.getParentContentModule();
        String lessonName = parentModule + "," + lesson.getName();
        CourseProgress currentProgress = getCourseProgress(parentCourse);
        ModuleProgress currentModuleProgress = currentProgress.getModuleProgress(parentModule);
        LessonProgress currentLessonProgress = currentModuleProgress.getLessonProgress(lessonName);
        if (currentLessonProgress.completePercentage() == 100) {
            currentLessonProgress.setComplete();
        }
        return currentLessonProgress.isComplete();
    }

    public int getModuleCompleteness(ContentModule contentModule) {
        String parentCourse = contentModule.getParentCourse();
        CourseProgress courseProgress = getCourseProgress(parentCourse);
        ModuleProgress currentModuleProgress = courseProgress.getModuleProgress(contentModule.getName());
        return currentModuleProgress.completePercentage();
    }

    public int getCourseCompleteness(Course course) {
        for (CourseProgress courseProgress : courses) {
            if (courseProgress.getComponentName().equals(course.getName())) {
                return courseProgress.completePercentage();
            }
        }
        return 0;
    }

    public static ContentModule getCheckpointModule() {
        return checkpointModule;
    }

    public static void setCheckpointModule(ContentModule cm) {
        checkpointModule = cm;
    }

    public static ContentModule getCurrentModule() {
        return currentModule;
    }

    public static void setCurrentModule(ContentModule cm) {
        currentModule = cm;
    }

    public static Question getCurrentQuestion() {
        return currentQuestion;
    }

    public static void setCurrentQuestion(Question question) {
        currentQuestion = question;
    }

    public static Lesson getCurrentLesson() {
        return currentLesson;
    }

    public static void setCurrentLesson(Lesson cl) {
        currentLesson = cl;
    }
}
