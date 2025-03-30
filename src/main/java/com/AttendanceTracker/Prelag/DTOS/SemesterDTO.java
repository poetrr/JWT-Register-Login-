package com.AttendanceTracker.Prelag.DTOS;

public class SemesterDTO {
    private long semesterId;     
    private String semesterName;


    public long getSemesterId(){
        return this.semesterId;
    }
    public void setSemesterId(int semesterId){
        this.semesterId=semesterId;
    }
    public String getSemesterName(){
        return this.semesterName;
    }
    public void setSemesterName(String semesterName){
        this.semesterName=semesterName;
    }

}   
