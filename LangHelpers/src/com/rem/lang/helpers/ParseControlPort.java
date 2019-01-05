package com.rem.lang.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.rem.lang.helpers.input.InputFileWatcher;
import com.rem.lang.helpers.input.InputRequest;
import com.rem.lang.helpers.input.PrintMethodType;
import com.rem.lang.helpers.output.OutputClassStructure;

public class ParseControlPort {
	public static interface Parser <R,P extends Supplier<T>,T extends Function<ID,Supplier<T>>,ID>{
		public R parse(String fileName);
		public P asPass(R result);
		public Consumer<R> printMethod(PrintMethodType type);
	}
	public static class ParserPackage <R, P extends Supplier<T>, T extends Function<ID,Supplier<T>>, ID> {
		private Parser<R, P, T, ID> parser;
		private String extension;
		private List<InputRequest<T>> requestList;
		public ParserPackage(
				String extension,
				Parser<R,P,T,ID> parser,
				List<InputRequest<T>> requestList){
			this.parser = parser;
			this.extension = extension;
			this.requestList = requestList;
		}
		public Parser<R,P,T,ID> parser(){return parser;}
		public String extension(){return extension; }
		public List<InputRequest<T>> requestList(){ return requestList; }
	}
	@SafeVarargs
	public static <R,P extends Supplier<T>,T  extends Function<ID,Supplier<T>>, ID>
		void start(
			Supplier<Boolean> isRunning,
			String[] args,
			ParserPackage<R,P,T,ID>... packages)  {
		if (args.length==1 ) {
			Path rootDirectory = Paths.get(args[0]);
			if(!Files.exists(rootDirectory)){
				try {
					Files.createDirectories(rootDirectory);
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
			for(ParserPackage<R,P,T,ID> _package:packages){
				InputFileWatcher.start(isRunning,rootDirectory,_package.extension(),_package);
			}

		}
		else  {
			System.err.println("No Directory Provided!");
		}
	}
	private static boolean defaultStopperUsed = false;
	public static void startDefaultStopper(Supplier<Boolean> isRunning, Supplier<Boolean> onStop){
		new Thread(new Runnable(){
			@Override
			public void run() {
				try (Scanner scanner = new Scanner(System.in)){
					while(isRunning.get()){
						if("exit".equals(scanner.nextLine())){
							onStop.get();
						}
					}
					scanner.close();
				}
				catch(Exception e){}
			}}).start();
		defaultStopperUsed = true;
	}
	public static void end(){
		InputFileWatcher.end();
		if(defaultStopperUsed){
			try {
				System.in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static enum State {
		SUCCESS,
		FAIL
	}
	public static String getStatus(State state){
		switch(state){
		case SUCCESS: return "Passed";
		case FAIL: return "Failed";
		}
		return null;
	}
	
	
	//TEMP
	@SafeVarargs
	public static <R,P extends Supplier<T>,T  extends Function<ID,Supplier<T>>, ID>
		void ruminate(
			Supplier<Boolean> isRunning,
			String[] args,
			final Parser<R,P,T,ID> parser,
			final Consumer<P>... onSuccessMethods)  {
		if (args.length == 2&&args[0].equals("-once")){
			R result = parser.parse(args[1]);
			P pass = parser.asPass(result);
			System.out.println(result.toString());
			
			if(pass!=null){
				OutputClassStructure.setup(args[1]);
				Arrays.asList(onSuccessMethods).forEach(C->C.accept(pass));
				OutputClassStructure.output();
			}
			
		}
		else if (args.length==1 &&(args.length == 2&&args[0].equals("-continue"))) {
			String fileName = args[0];
			
			new Thread(new Runnable(){
				@Override
				public void run() {
					Scanner scanner = new Scanner(System.in);
					while(true){
						String command = scanner.nextLine();
						if("quit".equals(command)||"exit".equals(command)){
							scanner.close();
							System.exit(1);
						}
						else if("full".equals(command)||"push".equals(command)){
							R result = parser.parse(fileName);
							P pass = parser.asPass(result);

							System.out.println(result.toString());
							if(pass!=null){
								OutputClassStructure.setup(fileName);
								Arrays.asList(onSuccessMethods).forEach(C->C.accept(pass));
								OutputClassStructure.output();
								OutputClassStructure.clear();
							}
						}
					}
				}}).start();
		}
		else  {
			System.err.println("No Root File Provided!");
		}
	}
}
