package com.rem.lang.helpers.input;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.rem.lang.helpers.ParseControlPort;
import com.rem.lang.helpers.output.OutputClassStructure;

import java.nio.file.SimpleFileVisitor;
import java.util.HashMap;
import java.util.List;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.WatchEvent;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.LinkOption;
import java.nio.file.WatchKey;
import java.nio.file.FileVisitResult;
import java.io.IOException;
import java.util.Map;
import java.nio.file.WatchService;
public class InputFileWatcher<R> extends Thread {

	private static Map<String,InputFileWatcher<?>> watchers = new HashMap<String,InputFileWatcher<?>>();
	private Supplier<Boolean> isRunning;
	private Consumer<R> printMethod;
	
	private Consumer<Path> onCreateMethod;
	private Consumer<Path> onModifyMethod;
	private Consumer<Path> onDeleteMethod;
	private Predicate<Path> hasExtension;
	
	private WatchService service;
	private Map<WatchKey, Path> keys;
	private Map<Path, InputStructureBuilder> helpers;
	private final boolean recursive = true;

	private InputFileWatcher(){
	}
	public static <R,P extends Supplier<T>,T  extends Function<ID,Supplier<T>>, ID> void start(
			Supplier<Boolean> isRunning,
			Path rootDirectory,
			String extension,
			ParseControlPort.ParserPackage<R,P , T, ID> parsePackage) {
		if(watchers.containsKey(extension)){
			return;
		}
		OutputClassStructure.setup(rootDirectory.toString());
		InputFileWatcher<R> watcher = new InputFileWatcher<R>();
		watchers.put(extension, watcher);
		watcher.isRunning = isRunning;
		if (extension == null){
			watcher.hasExtension = P->true;
		}
		else {
			watcher.hasExtension = P->P.endsWith(extension);
		}
		final ParseControlPort.Parser<R,P,T,ID> parser = parsePackage.parser();
		final List<InputRequest<T>> requestList = parsePackage.requestList();

		watcher.onCreateMethod = Path->{
			R result = parser.parse(Path.toString());
			watcher.printMethod.accept(result);
			P pass = parser.asPass(result);
			if (pass!=null) {
				InputStructureBuilder helper = new InputStructureBuilder();
				watcher.helpers.put(Path,helper);
				helper.iterate(pass.get(),requestList,true);
			}
		};
		watcher.onModifyMethod = Path->{
			R result = parser.parse(Path.toString());
			watcher.printMethod.accept(result);
			P pass = parser.asPass(result);
			if (pass!=null) {
				InputStructureBuilder helper = watcher.helpers.get(Path);
				helper.iterate(pass.get(),requestList,false);
			}
		};
		watcher.onDeleteMethod = Path->{
			InputStructureBuilder helper = watcher.helpers.get(Path);
			System.out.print(Path.toString());
			System.out.println(" Deleted from file system");

			helper.iterate(null,requestList,false);
		};
		
		try {
			watcher.service=FileSystems.getDefault().newWatchService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		watcher.keys=new HashMap<WatchKey, Path>();
		watcher.helpers=new HashMap<Path, InputStructureBuilder>();

		watcher.printMethod = parser.printMethod(PrintMethodType.START);
		watcher.registerAll(rootDirectory);
		watcher.printMethod = parser.printMethod(PrintMethodType.CONTINUE);
	}
	public static void end(){
		watchers.keySet().forEach(K->watchers.get(K).closeService());
	}
	public void run(){
		while(isRunning.get()){
			WatchKey key;
			try{
				key=service.take();
			}
			catch(ClosedWatchServiceException e0){
				return ;
			}
			catch(InterruptedException e0){
				return ;
			}
			Path dir = keys.get(key);
			if(dir==null){
				continue;
			}
			for(WatchEvent<?> event:key.pollEvents()){
				WatchEvent.Kind<?> kind = event.kind();
				if(kind==StandardWatchEventKinds.OVERFLOW){
					continue;
				}
				@SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path name = ev.context();
				Path child = dir.resolve(name);
				if(recursive&&(kind==StandardWatchEventKinds.ENTRY_CREATE)){
					if(Files.isDirectory(child,LinkOption.NOFOLLOW_LINKS)){
						registerAll(child);
					}
					else if(Files.isRegularFile(child) && hasExtension.test(child)) {
						onCreateMethod.accept(child);
					}
				}
				else if(kind==StandardWatchEventKinds.ENTRY_MODIFY){
					if(Files.isRegularFile(child) && hasExtension.test(child) ) {
						onModifyMethod.accept(child);
					}
				}
				else if(kind==StandardWatchEventKinds.ENTRY_DELETE){
					if(Files.isRegularFile(child) && hasExtension.test(child) ) {
						onDeleteMethod.accept(child);
					}
				}
			}
			boolean valid = key.reset();
			if(!valid){
				keys.remove(key);
			}
		}
	}
	public void register(Path dir){
		try{
			WatchKey key = dir.register(service,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
			keys.put(key,dir);
		}
		catch(IOException e0){
		}
	}
	public void registerAll(final Path start){
		try{
			Files.walkFileTree(start,new FileRegisterer());
		}
		catch(IOException e0){
		}
	}
	public void closeService(){
		try{
			service.close();
		}
		catch(ClosedWatchServiceException | IOException e0){
		}
	}
	public class FileRegisterer extends SimpleFileVisitor<Path> {
		public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs){
			register(dir);
			return FileVisitResult.CONTINUE;
		}
	}
}
 
