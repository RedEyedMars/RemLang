package com.rem.parser.generation.classwise;
import java.util.*;


public class ExternalContext {

	private static final Map<String,ExternalContext> types = new HashMap<String, ExternalContext>();
	public static ExternalContext getClassContext(String className) {
		
		
		if(!types.containsKey(className)){
			int indexOfAngle = className.indexOf('<');
			if (indexOfAngle>0){
			  int indexOfClose = className.lastIndexOf('>');
			  String templateClassName = className.substring(indexOfAngle+1,indexOfClose);
			  String parentClassName = className.substring(0,indexOfAngle);
			  if(parentClassName.equals("List")||parentClassName.equals("ArrayList")){
				  types.put(className, new ExternalContext(true,null));
				  types.put(className, ExternalClassHelper.supplimentListClass(className,parentClassName,templateClassName));
			  }
			  else {
				  types.put(className, new ExternalContext(true,null));
			  }
			}
			else {
			  types.put(className, new ExternalContext(true,null));
			}
		}
		return types.get(className);
	}

	public static void freeClass(String className) {
		types.remove(className);
	}

	protected ExternalContext   parentContext = null;
	protected boolean isBody = false;
	protected List<ExternalVariableEntry> variables = new ArrayList<ExternalVariableEntry>();
	protected Map<String, ExternalContext > links = new HashMap<String, ExternalContext>();
	protected Map<String, String > linkedTypes = new HashMap<String, String>();
	protected ExternalStatement ender;

	public ExternalContext(boolean isBody){
		this.isBody = isBody;
	}
	public ExternalContext(boolean isBody, ExternalContext initialParentContext, ExternalVariableEntry... variables){
		this.isBody = isBody;
		parentContext = initialParentContext;
		for(ExternalVariableEntry variable:variables){
			add(variable);
		}
	}
	public ExternalContext(boolean isBody, ExternalContext initialParentContext, List<ExternalVariableEntry> variables){
		this.isBody = isBody;
		parentContext = initialParentContext;
		for(ExternalVariableEntry variable:variables){
			add(variable);
		}
	}
	public void setIsBody(boolean newIsBody){
		this.isBody = newIsBody;
	}
	public void add(String name, String type, ExternalContext context){
		if(isBody){
			links.put(name, context);
			linkedTypes.put(name, type);
		}
		else {
			if(parentContext!=null){
				parentContext.add(name, type, context);
			}
			else {
				links.put(name, context);
				linkedTypes.put(name, type);
			}
		}
	}
	public void add(ExternalVariableEntry entry){
		add(entry.getName(),entry.getTypeName(), entry.getClassContext());
		variables.add(entry);
	}
	public void add(ExternalMethodEntry entry){
		entry.getHeaderContext().setParent(this);
		add(entry.getName(), entry.getTypeName(), entry.getClassContext());
		add(entry.getSimpleName(), entry.getTypeName(), entry.getClassContext());
	}
	public void setParent(ExternalContext newParentContext){
		parentContext = newParentContext;
		if(!isBody){
			for(String entryName:links.keySet()){
				parentContext.add(entryName, linkedTypes.get(entryName), links.get(entryName));
			}
			links.clear();
			linkedTypes.clear();
		}
	}
	public void setEnder(ExternalStatement ender){
		this.ender = ender;
	}
	public Boolean hasEnder(){
		return this.ender != null;
	}
	public List<ExternalVariableEntry> getVariables(){
		return variables;
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
	public String type(String query){
		return type(query,new HashSet<ExternalContext>());
	}
	private String type(String query, Set<ExternalContext> set){
		if(set.add(this)){
			if(linkedTypes.containsKey(query)){
				return linkedTypes.get(query);
			}
			else if (parentContext != null) {
				return parentContext.type(query,set);
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
			else {
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
				System.out.println(this.toString()+"P:>");
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
