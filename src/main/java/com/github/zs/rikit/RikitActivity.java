package com.github.zs.rikit;


public interface RikitActivity {

	public void create() throws RikitException;
	public void join() throws RikitException;
	public void stabilize();
	public void inform();
	public void fixFingers();
	public void checkPredecessor();
	public void findSuccessor();
	public void closestPrecedingNode();
}
