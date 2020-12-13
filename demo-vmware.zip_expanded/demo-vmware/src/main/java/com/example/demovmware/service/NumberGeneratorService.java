package com.example.demovmware.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.demovmware.models.NumberGenerator;
import com.example.demovmware.models.StatusList;

@Service
public class NumberGeneratorService {

	Map<UUID, NumberGenerator> listGenerator = new HashMap<UUID, NumberGenerator>();
	Map<UUID, List<List<Integer>>> bunchNumMap = new HashMap<>();
	List<List<Integer>> bunchList;
	
	public void numberGeneratorService(NumberGenerator gen, UUID uid) {
		bunchList = new ArrayList<>();
		numberGenerator(gen, uid);
	}
	
	public void numberGeneratorBunchService(List<NumberGenerator> genList , UUID uid) {
		bunchList = new ArrayList<>();
		for(NumberGenerator gen : genList) {
			numberGenerator(gen, uid);
		}
	}

	/*
	 * Utility function to handle number generator. ThreadPool help to execute the incoming request in parallel
	 * It does not block the response, however it executes in the backend 
	 */
	public void numberGenerator(NumberGenerator gen, UUID id) {
		
		UUID uid = id;
		gen.setId(uid);

		int goal = Integer.valueOf(gen.getGoal());
		int step = Integer.valueOf(gen.getStep());
		listGenerator.put(uid, gen);

		NumberGenerator num = new NumberGenerator();
		num.setStatus(0);
		Executors.newScheduledThreadPool(10).schedule(() -> getGeneratedNumber(goal, step, gen.getId()), 10,
				TimeUnit.SECONDS);

		listGenerator.put(gen.getId(), num);
	}
	
	//Function to generate the random number
	private int getRandomInteger(int maximum, int minimum)
	{ 
		return ((int) (Math.random()*(maximum - minimum))) + minimum; 
	}

	/*
	 * Generate the list of number within the duration of 10sec to 30 sec. 
	 */
	private CompletableFuture<List<List<Integer>>> getGeneratedNumber(int goal, int step, UUID id) {
		int i =goal;
		List<Integer> list = new ArrayList<Integer>();
		int minimum = 10;
		int maximum = 30;
		int randomSecondToSleep = getRandomInteger(maximum, minimum);
		int sleepPerStep = randomSecondToSleep / (step+1);
		while(i >=  0)
		{
			try {
				System.out.println("sleeping for " + sleepPerStep);
				TimeUnit.SECONDS.sleep(sleepPerStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.add(i);
			i -= step;
		}
		NumberGenerator num = new NumberGenerator();
		num.setStatus(1);
		
		listGenerator.put(id, num);
		bunchList.add(list);
		bunchNumMap.put(id, bunchList);

		return CompletableFuture.completedFuture(bunchList);
	}
	
	/*
	 * Return the task status
	 */
	public String fetchTaskStatus (UUID id) {
		if(listGenerator.containsKey(id)) {
			  NumberGenerator obj = listGenerator.get(id);
			  StatusList s = obj.getStatus();
			  return s.toString();
	        
	    }
		return null;
	}
	
	/*
	 * Return the list of generated number
	 */
	public String listOfNumbers(UUID id) {
		
		 if(listGenerator.containsKey(id)) {
			  NumberGenerator obj = listGenerator.get(id);
			  StatusList s = obj.getStatus();
			  if(s == StatusList.IN_PROGRESS)
				  return "invalidStatus";
			  
			  List<List<Integer>> list = bunchNumMap.get(id);	

			  return list.toString();      
	    }
		return null;
	}
}
