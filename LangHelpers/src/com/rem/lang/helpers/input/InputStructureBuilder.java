package com.rem.lang.helpers.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rem.lang.helpers.output.OutputClass;
import com.rem.lang.helpers.output.OutputClassStructure;
import com.rem.lang.helpers.output.OutputFile;


public class InputStructureBuilder {
	private Map<String,OutputFile> previousOutputFiles = null;
	private Map<String,OutputFile> outputFiles;


	public <T extends Function<ID,Supplier<T>>,ID> void iterate(T rootToken, List<InputRequest<T>> requestList, boolean isFirst) {
		if(rootToken != null){
			if(!isFirst){
				previousOutputFiles.keySet().stream().collect(Collectors.toList()).forEach(S->{
					previousOutputFiles.get(S).getOutputClass().restore();});
			}
			outputFiles = new HashMap<String,OutputFile>();

			List<InputUnresolvedRequest> unresolved = new ArrayList<InputUnresolvedRequest>();
			Supplier<T> iterator = rootToken.apply(null);
			T subToken = iterator.get();
			while(subToken!=null) {
				final T inputToken = subToken;
				requestList.forEach(R->unresolved.addAll(R.apply(inputToken)));
				subToken = iterator.get();
			}
			Stream<InputUnresolvedRequest> unresolvedStream = unresolved.parallelStream();
			StreamLeech leecher = new StreamLeech();
			while(leecher.end()){
				unresolvedStream = unresolvedStream.flatMap(R->{leecher.inc();return R.apply(InputStructureBuilder.this);});
			}

		}
		if(!isFirst){
			previousOutputFiles.keySet().stream().filter(K->!outputFiles.containsKey(K)).collect(Collectors.toList()).forEach(S->{try{Files.deleteIfExists(Paths.get(S));}catch(IOException e){}});
		}
		outputFiles.keySet().parallelStream().forEach(F->outputFiles.get(F).outputToFile(OutputClassStructure.__ROOTDIRECTORY__));
		previousOutputFiles = outputFiles;
	}
	private static class StreamLeech {
		private int previousItr = -1;
		private int currentItr = 0;
		public void inc(){
			++currentItr;
		}
		public boolean end(){
			boolean result = previousItr == currentItr || currentItr == 0 || currentItr >= Short.MAX_VALUE;
			previousItr = currentItr;
			currentItr = 0;
			return result;
		}
	}
	public void addOutputClass(OutputClass newClass){
		outputFiles.put(
				newClass.getPackageName().evaluate()+"."+newClass.getFullName().evaluate(),
				new OutputFile(newClass));
		synchronized(OutputClassStructure.packages){
			newClass.solidifyImports(newClass.getPackageName(), OutputClassStructure.packages);
		}
	}
}
