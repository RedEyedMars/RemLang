package com.rem.parser.generation.classwise;
import java.util.*;

import com.rem.parser.generation.Entry;

public class ExternalContext {

	private static final Map<String,ExternalContext> types = new HashMap<String, ExternalContext>();
	public static class Minor extends ExternalContext {
		public Minor(ExternalContext initialParentContext) {
			super(initialParentContext);
		}
		public void add(ExternalVariableEntry entry){
			if(parentContext!=null){
				parentContext.add(entry);
			}
			super.add(entry);
		}
		public void add(ExternalMethodEntry entry){
			if(parentContext!=null){
				parentContext.add(entry);
			}
			super.add(entry);
		}
		public void setParent(ExternalContext newParentContext){
			super.setParent(newParentContext);
			for(String key:links.keySet()){
				newParentContext.links.put(key, links.get(key));
			}
		}
	}
	public static ExternalContext getClassContext(String className) {
		if(!types.containsKey(className)){
			types.put(className, new ExternalContext(null));
		}
		return types.get(className);
	}
	
	
	protected ExternalContext parentContext = null;
	protected Map<String, ExternalContext > links = new HashMap<String, ExternalContext>();
	protected ExternalStatement ender;
	
	public ExternalContext(ExternalContext initialParentContext, ExternalVariableEntry... variables){
		parentContext = initialParentContext;
		for(ExternalVariableEntry variable:variables){
			add(variable);
		}
	}
	public ExternalContext(ExternalContext initialParentContext, List<ExternalVariableEntry> variables){
		parentContext = initialParentContext;
		for(ExternalVariableEntry variable:variables){
			add(variable);
		}
	}
	public void add(ExternalVariableEntry entry){
		links.put(entry.getName(), entry.getClassContext());
	}
	public void add(ExternalMethodEntry entry){
		links.put(entry.getName(), entry.getClassContext());
	}
	public void setParent(ExternalContext newParentContext){
		parentContext = newParentContext;
	}
	public void setEnder(ExternalStatement ender){
		this.ender = ender;
	}
	public Boolean hasEnder(){
		return this.ender != null;
	}
	public ExternalStatement getEnder(){
		return ender;
	}
	public Boolean hasLink(String query){
	  if(links.containsKey(query)){
	  	return true;
	  }
	  else if(parentContext != null) {
	    return parentContext.hasLink(query);
	  }
	  else {
	  	return false;
	  }
	}
	public ExternalContext link(String query){
	  if(links.containsKey(query)){
	  	return links.get(query);
	  }
	  else if (parentContext != null) {
	    return parentContext.link(query);
	  }
	  else {
	  	return null;
	  }

 }
}
