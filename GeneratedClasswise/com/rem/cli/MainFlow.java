package com.rem.cli;
import java.util.*;
import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import com.rem.cli.MainFlow;

public  class MainFlow extends FlowController{
	public static MainFlow self = new MainFlow();
	public static Set<ExternalClassEntry> outputClasses = new HashSet<ExternalClassEntry>();
	public static File __ROOTDIRECTORY__ = new File(".");
	public MainFlow() {
	}
	public static void main(String[] args){
		if (args.length==1 ){
			Parser parser = new Parser();
			Parser.Result result = parser.parse(args[0]);
			self.setupRootDirectory(args[0]);
			self.setupGenerators();
			self.setup(result);
			self.generate(result);
			ExternalImportEntry.solidify();
			self.output();
		}
		else {
			System.err.println("No Filename Provided!");
		}
	}
	public void setupRootDirectory(String fileName){
		String fileName = camelize(fileName);
		int indexOfDot = fileName.indexOf(".");
		if (indexOfDot>=0 ){
			__ROOTDIRECTORY__=new File("../"+fileName.substring(0,indexOfDot)+"/src");
		}
		else {
			__ROOTDIRECTORY__=new File("../"+fileName+"/src");
		}
		__ROOTDIRECTORY__.mkdirs();
	}
	public void setupGenerators(){
		{
			X._.__INIT__();
			MainFlow.outputClasses.add(X._);
		};
	}
	public void output(){
		for (ExternalClassEntry outputClass: outputClasses){
			outputClass.outputToFile(this,__ROOTDIRECTORY__);
		}
	}
	public ExternalClassEntry.classMap.get(String)[] getMyArray(){
	}
	public ExternalClassEntry.classMap.get(void) blah(ExternalClassEntry newClass){
	}
}