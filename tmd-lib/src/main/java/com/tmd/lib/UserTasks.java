package com.tmd.lib;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTasks {
	private static final Logger log = LoggerFactory.getLogger(UserTasks.class);
	public static final String version = "Version 1.0.0 -- 2015-09-29";
	
	String userId;
	List<String> completedTasks;
	
	public UserTasks(){
		
	}	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(List<String> completedTasks) {
		this.completedTasks = completedTasks;
	}

}
