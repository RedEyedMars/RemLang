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

public class ParametersEntry implements Entry {
	public ParametersEntry getSelf(){
		return this;
	}


	private Boolean undefined = false;
	private String contextName = null;
	private String contextSubName = null;
	private ListEntry listEntry = new ListEntry();

	public ParametersEntry(Boolean initialUndefined,String initialContextName,String initialContextSubName){
		undefined = initialUndefined;
		contextName = initialContextName;
		contextSubName = initialContextSubName;
	}

	public Boolean getUndefined(){
		return undefined;
	}
	public String getContextName(){
		return contextName;
	}
	public String getContextSubName(){
		return contextSubName;
	}	public ListEntry getListEntry(){
		return listEntry;
	}
	public Boolean isEmpty(){
		return (listEntry.isEmpty());
	}
	public Boolean add(Entry entry){
		Map<String,Map<String,List<ITypeListener>>> methodParameters = (Map<String,Map<String,List<ITypeListener>>>)Generators.generator.getMethodParameters();
		ITypeListener listener_entry = (ITypeListener)entry;
		if((undefined == true)){
			methodParameters.get(contextName).get(contextSubName).add(listener_entry);
		}
		else {
			Integer listSize = (Integer)listEntry.size();
			if((!methodParameters.get(contextName).get(contextSubName).get(listSize).hasType())){
				methodParameters.get(contextName).get(contextSubName).get(listSize).changeType(listener_entry.getType());
			}
		}
		Boolean result = (Boolean)listEntry.add(entry);
		return result;
	}
	public void get(StringBuilder builder){
		if((listEntry != null)){
			listEntry.get(builder);
		}
	}
}