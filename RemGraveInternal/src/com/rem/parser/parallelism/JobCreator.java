package com.rem.parser.parallelism;

import java.util.ArrayList;
import java.util.List;


public class JobCreator extends Thread{

	private static final int UNSTARTED = -1;
	private static final int WAITING_FOR_JOBS = 0;
	private static final int WAITING_FOR_PROCESSORS = 1;
	private static final int PROCESSING_JOBS = 2;
	private List<JobProcessor> allProcessors = new ArrayList<JobProcessor>();
	private List<JobProcessor> readyProcessors = new ArrayList<JobProcessor>();
	private List<ParallelJob> readyJobs = new ArrayList<ParallelJob>();
	private Object jobsMonitor = new Object();
	private int jobsFinished = 0;
	private int jobsStarted = 0;
	private int cores;
	private int status = UNSTARTED;
	private static JobCreator creator;
	public static void add(ParallelJob job){
		if(creator==null){
			creator = new JobCreator();
			creator.cores = Runtime.getRuntime().availableProcessors();
			creator.readyJobs.add(job);
			JobProcessor processor = new JobProcessor(creator);
			creator.allProcessors.add(processor);
			creator.start();
			return;
		}
		if(creator.allProcessors.size()<creator.cores&&creator.readyProcessors.isEmpty()){
			JobProcessor processor = new JobProcessor(creator);
			creator.allProcessors.add(processor);
			processor.start();
		}
		synchronized(creator.readyJobs){
			creator.readyJobs.add(job);
			if(creator.status == WAITING_FOR_JOBS){
				creator.readyJobs.notifyAll();
			}
		}
	}

	public static void waitUntilDoneProcessing(){
		try {
			if(creator==null){
				return;
			}

			synchronized(creator.jobsMonitor){
				while((creator.jobsStarted==0&&creator!=null)||((creator.jobsFinished<creator.jobsStarted||!creator.readyJobs.isEmpty())&&creator!=null)){
					creator.jobsMonitor.wait();
				}
			}
			creator.jobsStarted = 0;
			creator.jobsFinished = 0;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void end(){
		if(creator!=null){
			JobCreator dying = creator;
			creator = null;
			synchronized(dying.readyJobs){
				dying.readyJobs.clear();
				dying.readyJobs.notifyAll();
			}
			for(int i=0;i<dying.allProcessors.size();++i){
				dying.allProcessors.get(i).end();
			}
			synchronized(dying.readyProcessors){
				dying.readyProcessors.notifyAll();
			}
			synchronized(dying.jobsMonitor){
				dying.jobsFinished = dying.jobsStarted;
				dying.jobsMonitor.notifyAll();
			}
		}
	}

	static boolean isRunning(){
		return creator != null;
	}


	@Override
	public void run(){
		try {

			allProcessors.get(0).start();
			while(creator!=null){
				status = WAITING_FOR_JOBS;
				synchronized(readyJobs){
					while(readyJobs.isEmpty()&&creator!=null){
						readyJobs.wait();
					}				
				}
				status = WAITING_FOR_PROCESSORS;
				synchronized(readyProcessors){
					while(readyProcessors.isEmpty()&&creator!=null){
						readyProcessors.wait();
					}
				}
				status = PROCESSING_JOBS;
				synchronized(readyProcessors){
					synchronized(readyJobs){
						while(!readyJobs.isEmpty()&&!readyProcessors.isEmpty()&&creator!=null){
							++jobsStarted;
							readyProcessors.remove(0).process(readyJobs.remove(0));
						}		
					}
				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("CREATOR END");
	}



	public void ready(JobProcessor processor) {
		synchronized(readyProcessors){
			readyProcessors.add(processor);
			if(status == WAITING_FOR_PROCESSORS){
				readyProcessors.notifyAll();
			}
		}
	}
	public void complete() {
		synchronized(jobsMonitor){
			++jobsFinished;
			jobsMonitor.notifyAll();
		}
	}
}
