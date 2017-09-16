package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.checks.*;
import gen.properties.*;
import lists.*;

public class ImportListEntry implements Entry,IInnerable {
	public ImportListEntry getSelf(){
		return this;
	}

	public static final Integer EXTERNAL = 0;
	public static final Integer INTERNAL = 1;

	private Boolean isInner = false;
	private ListEntry myPackages = new ListEntry();
	private ListEntry myNames = new ListEntry();
	private ListEntry myInternal = new SetEntry();
	private ListEntry myExternal = new SetEntry();
	private Integer mode = 0;
	private Boolean isAnonymous = false;

	public ImportListEntry(){
		String x = "x";
	}
	public ImportListEntry(String myPackage,String myName){
		myPackages.add(new StringEntry(myPackage));
		myNames.add(new StringEntry(myName));
	}

	public Boolean getIsInner(){
		return isInner;
	}
	public void setIsInner(Boolean newInner) {
		isInner = newInner;
	}	public ListEntry getMyPackages(){
		return myPackages;
	}	public ListEntry getMyNames(){
		return myNames;
	}	public ListEntry getMyInternal(){
		return myInternal;
	}	public ListEntry getMyExternal(){
		return myExternal;
	}
	public Integer getMode(){
		return mode;
	}
	public Boolean getIsAnonymous(){
		return isAnonymous;
	}
	public void setIsAnon(Boolean newAnon){
		isAnonymous = newAnon;
	}
	public void clear(){
		myInternal.clear();
		myExternal.clear();
	}
	public void addImports(ImportListEntry newImports){
		ListEntry newPackages = (ListEntry)newImports.getMyPackages();
		ListEntry newNames = (ListEntry)newImports.getMyNames();
		if((!newImports.getIsAnonymous())){
			if((newImports.getIsInner())){
				myInternal.add(new ISingleImportEntry(newPackages,newNames));
			}
			if((!newImports.getIsInner())){
				myExternal.add(new ESingleImportEntry(newPackages,newNames));
			}
		}
		else {
			myExternal.add(new ESingleImportEntry(newPackages,newNames));
		}
		myInternal.add(newImports.getMyInternal());
		myExternal.add(newImports.getMyExternal());
	}
	public void addPackage(ISingleImportEntry newPackage){
		myInternal.add(newPackage);
	}
	public void addPackage(ESingleImportEntry newPackage){
		myExternal.add(newPackage);
	}
	public void addPackage(String packageName,String nameName){
		myPackages.clear();
		myNames.clear();
		myPackages.add(new StringEntry(packageName));
		myNames.add(new StringEntry(nameName));
	}
	public void addPackage(Entry packageName,Entry nameName){
		myPackages.clear();
		myNames.clear();
		myPackages.add(packageName);
		myNames.add(nameName);
	}
	public void setMode(Integer newMode){
		mode = newMode;
	}
	public ImportListEntry get(String option){
		ImportListEntry newSelf = new ImportListEntry();
		newSelf.addImports(this.getSelf());
		if((option.equals("EXTERNAL"))){
			newSelf.setMode(EXTERNAL);
		}
		else {
			newSelf.setMode(INTERNAL);
		}
		return newSelf;
	}
	public void get(StringBuilder builder){
		if((mode == INTERNAL)){
			myInternal.get(builder);
		}
		else if((mode == EXTERNAL)){
			new ListEntry().get(builder);
		}
	}
}