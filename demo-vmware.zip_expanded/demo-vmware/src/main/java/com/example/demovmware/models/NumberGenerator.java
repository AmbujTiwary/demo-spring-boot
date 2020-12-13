package com.example.demovmware.models;

import java.sql.Date;
import java.util.UUID;

public class NumberGenerator {
	private UUID id;
	StatusList status;
	
	public StatusList getStatus() {
		return status;
	}
	public void setStatus(int status) {
		if(status == 0)
			this.status = StatusList.IN_PROGRESS;
		else if (status == 1)
			this.status = StatusList.SUCCESS;
		else if (status == 2)
			this.status = StatusList.ERROR;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	private int goal;
	private int step;
	
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
}


