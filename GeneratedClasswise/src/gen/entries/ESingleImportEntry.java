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

public class ESingleImportEntry implements Entry {
	public ESingleImportEntry getSelf(){
		return this;
	}


	private ListEntry myName = (ListEntry)null;
	private ListEntry myPackage = (ListEntry)null;

	public ESingleImportEntry(ListEntry iPackage,ListEntry iName){
		myPackage = iPackage;
		myName = iName;
	}

	public ListEntry getMyName(){
		return myName;
	}
	public ListEntry getMyPackage(){
		return myPackage;
	}
	public void get(StringBuilder builder){
		if((myPackage != null && !myPackage.isEmpty())){
			new TabEntry(0,new ListEntry(new ElementEntry(ClasswiseGenerator.asExternalImportElement,new ListEntry(myPackage,myName)))).get(builder);
		}
		else {
			new ListEntry().get(builder);
		}
	}
}