package Model;

/**
 * Created by iWin_64bit on 4/28/2018.
 */

public class AnyTermInformation {
    private String courseName;
    private int numOfUnits;
    private double score;
    private String scoreResult;
    private String scoreStatus;
    private String lectureStatus;
    private String courseType;
    private String registrationType;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(String scoreResult) {
        this.scoreResult = scoreResult;
    }

    public String getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(String scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public String getLectureStatus() {
        return lectureStatus;
    }

    public void setLectureStatus(String lectureStatus) {
        this.lectureStatus = lectureStatus;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }
}
