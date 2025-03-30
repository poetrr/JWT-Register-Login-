package com.AttendanceTracker.Prelag.DTOS;

public class AlterSubjectsRequest {
    private int userId;
    private Long semesterId;
    private Long subjectId;
    private int presentCount;
    private int absentCount;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Long getSemesterId() {
        return semesterId;
    }
    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }
    public Long getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
    public int getPresentCount() {
        return presentCount;
    }
    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }
    public int getAbsentCount() {
        return absentCount;
    }
    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

}
