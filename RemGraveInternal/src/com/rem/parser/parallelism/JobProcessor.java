package com.rem.parser.parallelism;

public class JobProcessor extends Thread {

	private ParallelJob job;
	private JobCreator creator;
	public JobProcessor(JobCreator creator){
		this.creator = creator;
		this.job = null;
	}

	public void process(ParallelJob job) {
		this.job = job;
		synchronized(this){
			this.notifyAll();
		}
	}

	@Override
	public void run(){
		try {
			creator.ready(this);
			while(JobCreator.isRunning()){
				synchronized(this){
					while(job==null&&JobCreator.isRunning()){
						this.wait();
					}
					if(job!=null&&JobCreator.isRunning()){
						try {
							job.act();
						}
						catch(Exception e){
							e.printStackTrace();
							JobCreator.end();
						}
						job = null;
						creator.ready(this);
						creator.complete();
					}
				}
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		//System.out.println("PROCESSOR END");

	}

	public void end() {
		job = null;
		synchronized(this){
			this.notifyAll();
		}
	}

}
