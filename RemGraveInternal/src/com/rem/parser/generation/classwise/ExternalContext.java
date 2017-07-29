package com.rem.parser.generation.classwise;
import java.util.*;


public class ExternalContext {

	private static final Map<String,ExternalContext> types = new HashMap<String, ExternalContext>();
	public static ExternalContext getClassContext(String className) {
		if(!types.containsKey(className)){
			types.put(className, new ExternalContext(null));
		}
		return types.get(className);
	}

	public static void freeClass(String className) {
		types.remove(className);
	}

	protected ExternalContext   parentContext = null;
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
		//System.out.println("\t"+this.toString()+"++"+entry.getName());
		links.put(entry.getName(), entry.getClassContext());
	}
	public void add(ExternalMethodEntry entry){
		entry.getHeaderContext().setParent(this);
		links.put(entry.getName(), entry.getClassContext());
		links.put(entry.getSimpleName(), entry.getClassContext());
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
		return link(query,new HashSet<ExternalContext>());

	}
	private ExternalContext link(String query, Set<ExternalContext> set){
		if(set.add(this)){
			if(links.containsKey(query)){
				return links.get(query);
			}
			else if (parentContext != null) {
				return parentContext.link(query,set);
			}
		}
		return null;
	}
	public void print(){
		print(0,new HashSet<ExternalContext>());
	}
	private void print(int tab, Set<ExternalContext> set){
		if(set.add(this)){
			if(parentContext!=null){
				parentContext.print(tab,set);
			}
			if(links.isEmpty()){
				for(int i=0;i<tab;++i){
					System.out.print("\t");
				}
				System.out.println(this.toString()+" empty");
			}
			else {
				for(int i=0;i<tab;++i){
					System.out.print("\t");
				}
				System.out.println(this.toString()+":>");
				for(String key:this.links.keySet()){
					for(int i=0;i<tab;++i){
						System.out.print("\t");
					}
					System.out.println(key+":");
					links.get(key).print(tab+1,set);
				}
			}
		}
	}
	public void printClasses(){
		for(String className:types.keySet()){
			System.out.println(className+"::"+types.get(className));
		}
	}
	public void printParents(){
		printParents(0,new HashSet<ExternalContext>());
	}
	private void printParents(int tabs, Set<ExternalContext> set){
		if(set.add(this)){
			if(this.parentContext!=null){
				this.parentContext.printParents(tabs+1, set);
			}
			for(int i=0;i<tabs;++i){
				System.out.print("\t");
			}
			System.out.println(this);
		}
	}
}
