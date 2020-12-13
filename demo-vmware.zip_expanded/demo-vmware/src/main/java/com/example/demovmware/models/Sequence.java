package com.example.demovmware.models;

import java.util.List;
import java.util.UUID;

public class Sequence {

	private int goal;
	private int step;
	private UUID taskId;
	public List<Integer> list;
	private int status = 0; 
	public UUID getTaskId() {
		return taskId;
	}
	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
	}
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<Integer> getList()
	{
		return list;
	}
}
