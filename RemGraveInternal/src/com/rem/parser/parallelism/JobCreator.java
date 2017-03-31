package com.rem.parser.parallelism;

import java.util.ArrayList;
import java.util.List;

import com.rem.parser.ParseUtil;

public class JobCreator extends Thread{

	private boolean started = false;

	private List<JobProcessor> allProcessors = new ArrayList<JobProcessor>();
	private List<JobProcessor> processors = new ArrayList<JobProcessor>();
	private List<ParallelJob> jobs = new ArrayList<ParallelJob>();
	private int cores;
	private boolean isWaiting;
	private static JobCreator creator;
	public static void add(ParallelJob job){
		if(creator==null){
			creator = new JobCreator();
			creator.cores = Runtime.getRuntime().availableProcessors();			
			creator.start();
		}
		if(creator.allProcessors.size()<creator.cores&&creator.processors.isEmpty()){
			JobProcessor processor = new JobProcessor(creator);
			creator.allProcessors.add(processor);
			processor.start();
			creator.addProcessor(processor);
		}
		synchronized(creator.jobs){
			while(!creator.started){
				try {
					creator.isWaiting = true;
					creator.jobs.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			boolean isEmpty = creator.jobs.isEmpty();
			creator.jobs.add(job);
			if(isEmpty){
				creator.jobs.notifyAll();
			}
		}
	}

	public static boolean isProcessing(){
		if(creator==null){
			return false;
		}
		return !creator.jobs.isEmpty()||creator.processors.size()!=creator.allProcessors.size();
	}

	public static void waitUntilDoneProcessing(){

		if(creator!=null){
			try {
				creator.isWaiting = true;
				while(isProcessing()){
					synchronized(creator){
						creator.wait();
					}
				}
				if(creator!=null){
					creator.isWaiting = false;
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void end(){
		if(creator!=null){
			JobCreator dying = creator;
			creator = null;
			synchronized(dying){
				dying.notifyAll();
			}
			synchronized(dying.jobs){
				dying.jobs.notifyAll();
			}
			synchronized(dying.processors){
				dying.processors.notifyAll();
			}
			for(int i=0;i<dying.allProcessors.size();++i){
				dying.allProcessors.get(i).end();
			}
		}
	}

	static boolean isRunning(){

		return creator != null;
	}


	@Override
	public void run(){
		try {
			started=true;
			if(isWaiting){
				synchronized(jobs){
					jobs.notifyAll();
				}
				isWaiting = false;
			}
			while(creator!=null){
				synchronized(jobs){
					while(jobs.isEmpty()&&creator!=null){
						jobs.wait();									
					}				
				}
				synchronized(processors){
					while(processors.isEmpty()&&creator!=null){
						processors.wait();									
					}				
				}
				while(!jobs.isEmpty()&&!processors.isEmpty()&&creator!=null){
					synchronized(processors){
						processors.remove(0).process(jobs.remove(0));
					}

				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("CREATOR END");
	}



	void addProcessor(JobProcessor processor) {
		synchronized(processors){
			boolean isEmpty = processors.isEmpty();
			processors.add(processor);
			if(isEmpty){
				processors.notifyAll();
			}
			if(isWaiting){
				synchronized(this){
					this.notifyAll();
				}
			}
		}
	}	
}
