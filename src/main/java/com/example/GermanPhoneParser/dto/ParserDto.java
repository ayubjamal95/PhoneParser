package com.example.GermanPhoneParser.dto;

import java.util.Date;

public class ParserDto {

    private Date uploadedDate;
    private Long taskId;
    private String phoneNumber;

    public ParserDto(){
        //Default Constructor
    }

    public ParserDto(Date uploadedDate, Long taskId, String phoneNumber){
        this.uploadedDate = uploadedDate;
        this.phoneNumber = phoneNumber;
        this.taskId = taskId;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Date uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
