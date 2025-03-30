package com.AttendanceTracker.Prelag.DTOS;

public class CreateSubjectsRequest{

    private long semesterId;
    
    private String subjectName;
   
    private int presentCount;
    private int absentCount;
    public long getSemesterId() {
        return semesterId;
    }
    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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