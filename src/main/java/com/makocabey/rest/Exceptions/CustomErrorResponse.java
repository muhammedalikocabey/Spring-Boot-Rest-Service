package com.makocabey.rest.Exceptions;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime; 



public class CustomErrorResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
	
	/**/
    private int status;
    
    private String error;
    
    private String detail;
    
    private String path;
    
  
    
    public LocalDateTime getTimestamp() {
    	return timestamp;
    }
    
    public int getStatus() { 
    	return status;
    }
    
    public String getError() { 
    	return error;
    }
    
    public String getDetail() {
    	return detail;
    }
    
    public String getPath() {
    	return path;
    }
    
    
    public void setTimestamp(LocalDateTime timestamp) {
    	this.timestamp = timestamp;
    }
    
    public void setStatus(int status) { 
    	this.status = status;
    }
 
    public void setError(String error) { 
    	this.error = error;
    }
    
    public void setDetail(String detail) {
    	this.detail = detail;
    }
    
    public void setPath(String path) {
    	this.path = path;
    }
}
