package com.github.zs.rikit;

import java.util.ArrayList;
import java.util.List;

public class Rikit implements RikitActivity, ReceiverHandler {
	
	private List<Rikit> predecessors = new ArrayList<Rikit>();
	
	private List<Rikit> successors = new ArrayList<Rikit>();

	public List<Rikit> getPredecessors() {
		return predecessors;
	}

	public void setPredecessors(List<Rikit> predecessors) {
		this.predecessors = predecessors;
	}

	public List<Rikit> getSuccessors() {
		return successors;
	}

	public void setSuccessors(List<Rikit> successors) {
		this.successors = successors;
	}

	@Override
	public void create() {
		
		
	}

	@Override
	public void join() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stabilize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inform() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixFingers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPredecessor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceived(String received) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findSuccessor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closestPrecedingNode() {
		// TODO Auto-generated method stub
		
	}
	
	
}
