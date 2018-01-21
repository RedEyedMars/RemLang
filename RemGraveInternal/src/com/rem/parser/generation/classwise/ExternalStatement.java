package com.rem.parser.generation.classwise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.rem.parser.generation.*;
public class ExternalStatement extends ExternalImportEntry implements List<ExternalStatement>{
	protected int tabs = 0;
	protected ExternalContext context = new ExternalContext(false);

	public void setParentContext(ExternalContext newContext){
		context.setParent(newContext);
	}
	public ExternalContext getContext() {
		return context;
	}
	public void setTabs(int newTabs){
		tabs = newTabs;
	}
	protected List<ExternalStatement> subStatements = new ArrayList<ExternalStatement>();
	protected List<String> delimiters = new ArrayList<String>();
	protected String delimiter = "";
	protected String onEmptyDelimiter = null;
	protected Entry prefix = null;
	protected Entry suffix = null;
	private boolean negated = false;
	private boolean braced = false;
	public ExternalStatement(){
	}
	public ExternalStatement(String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		onEmptyDelimiter = firstDelimiter;
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			if(initialPrefix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialPrefix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			prefix = initialPrefix;
		}
		if(initialSuffix instanceof ExternalStatement){
			add((ExternalStatement) initialSuffix);
		}
		else {
			if(initialSuffix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialSuffix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			suffix = initialSuffix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, String initialDelimiter, List<String> initialDelimiters, ExternalStatement...statements){
		delimiter = initialDelimiter;
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			if(initialPrefix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialPrefix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			prefix = initialPrefix;
		}
		if(initialSuffix instanceof ExternalStatement){
			add((ExternalStatement) initialSuffix);
		}
		else {
			if(initialSuffix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialSuffix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			suffix = initialSuffix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				if(statement!=null){
					add(statement);
				}
			}
		}
		delimiters = initialDelimiters;
	}
	public ExternalStatement(Entry initialPrefix, Entry initialSuffix, ExternalStatement...statements){
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			if(initialPrefix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialPrefix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			prefix = initialPrefix;
		}
		if(initialSuffix instanceof ExternalStatement){
			add((ExternalStatement) initialSuffix);
		}
		else {
			if(initialSuffix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialSuffix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			suffix = initialSuffix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, String firstDelimiter, ExternalStatement...statements){
		delimiter = firstDelimiter;
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			if(initialPrefix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialPrefix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			prefix = initialPrefix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public ExternalStatement(Entry initialPrefix, ExternalStatement...statements){
		if(initialPrefix instanceof ExternalStatement){
			add((ExternalStatement) initialPrefix);
		}
		else {
			if(initialPrefix instanceof VariableNameEntry){
				VariableNameEntry asVariable = ((VariableNameEntry)initialPrefix);
				if(asVariable.getStatement()!=null){
					addSubImport(asVariable.getStatement());
				}
			}
			prefix = initialPrefix;
		}
		if(statements!=null){
			for(ExternalStatement statement:statements){
				add(statement);
			}
		}
	}
	public void negate(){
		negated = !negated;
	}
	public void brace(){
		braced = !braced;
	}
	public String getStatementType(){
		return null;
	}
	public boolean add(ExternalStatement statement){
		this.addSubImport(statement);
		if(subStatements.size()>0){
			delimiters.add(delimiter);
		}
		else {
			delimiters.add("");
		}
		//System.out.println("#"+statement+"#"+bottomContext+"<"+statement.topContext+">"+statement.getContext());
		statement.setParentContext(context);

		if(statement instanceof ExternalVariableEntry){
			context.add(((ExternalVariableEntry)statement));
		}
		return subStatements.add(statement);
	}
	public void set(String newDelimiter){
		delimiter = newDelimiter;
	}
	public void debug(){
		StringBuilder builder = new StringBuilder();
		builder.append("[\n\tnegated =");
		builder.append(negated);
		builder.append("\n\tprefix ");
		if(prefix!=null){
			builder.append("{");
			prefix.get(builder);
			builder.append("}");
		}
		else {
			builder.append("null.");
		}
		for(int i=0;i<subStatements.size();++i){
			builder.append("\n\t");
			builder.append(i);
			builder.append(":");
			builder.append(delimiters.get(i));
			builder.append(" {");
			subStatements.get(i).get(builder);
			builder.append(" }");
		}
		builder.append("\n\tsuffix ");
		if(suffix!=null){
			builder.append("{");
			suffix.get(builder);
			builder.append("}");
		}
		else {
			builder.append("null.");
		}
		System.out.println(builder.toString());
	}
	public void addImport(String typeName){
		this.addImport(new ImportEntry(new StringEntry(typeName)));
	}

	@Override
	public void get(StringBuilder builder) {
		if(negated){
			builder.append("!");
		}
		if(braced){
			builder.append("(");
		}
		if(prefix!=null){
			if(prefix instanceof TabEntry){
				((TabEntry)prefix).setTab(tabs);
			}
			else if(prefix instanceof ExternalStatement){
				((ExternalStatement)prefix).setTabs(tabs);
			}
			else if(prefix instanceof VariableNameEntry){
				((VariableNameEntry)prefix).setTabs(tabs);
			}
			prefix.get(builder);
		}
		if(subStatements.isEmpty()){
			if(onEmptyDelimiter!=null){
				builder.append(onEmptyDelimiter);
			}
		}
		else {
			for(int i=0;i<subStatements.size();++i){
				builder.append(delimiters.get(i));
				subStatements.get(i).setTabs(tabs);
				subStatements.get(i).get(builder);
			}
		}
		if(suffix!=null){
			if(suffix instanceof TabEntry){
				((TabEntry)suffix).setTab(tabs);
			}
			else if(suffix instanceof ExternalStatement){
				((ExternalStatement)suffix).setTabs(tabs);
			}
			else if(suffix instanceof VariableNameEntry){
				((VariableNameEntry)suffix).setTabs(tabs);
			}
			suffix.get(builder);
		}
		if(braced){
			builder.append(")");
		}
	}
	





	@Override
	public int size() {
		return subStatements.size();
	}
	public Integer getSize(){
		return subStatements.size();
	}
	@Override
	public boolean isEmpty() {
		return subStatements.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		return subStatements.contains(o);
	}
	@Override
	public Iterator<ExternalStatement> iterator() {
		return subStatements.iterator();
	}
	@Override
	public Object[] toArray() {
		return subStatements.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return subStatements.toArray(a);
	}
	@Override
	public boolean remove(Object o) {
		return subStatements.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return subStatements.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends ExternalStatement> c) {
		for(ExternalStatement b:c){
			if(!add(b)){
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean addAll(int index, Collection<? extends ExternalStatement> c) {
		for(ExternalStatement b:c){
			add(index, b);
		}
		return true;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return subStatements.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return subStatements.retainAll(c);
	}
	@Override
	public void clear() {
		subStatements.clear();
	}
	@Override
	public ExternalStatement get(int index) {
		return subStatements.get(index);
	}
	@Override
	public ExternalStatement set(int index, ExternalStatement element) {
		super.addSubImport(element);
		element.setParentContext(context);
		return subStatements.set(index, element);
	}
	@Override
	public void add(int index, ExternalStatement element) {
		super.addSubImport(element);
		element.setParentContext(context);
		subStatements.add(index, element);
	}
	@Override
	public ExternalStatement remove(int index) {
		return subStatements.remove(index);
	}
	@Override
	public int indexOf(Object o) {
		return subStatements.indexOf(o);
	}
	@Override
	public int lastIndexOf(Object o) {
		return subStatements.lastIndexOf(o);
	}
	@Override
	public ListIterator<ExternalStatement> listIterator() {
		return subStatements.listIterator();
	}
	@Override
	public ListIterator<ExternalStatement> listIterator(int index) {
		return subStatements.listIterator(index);
	}
	@Override
	public List<ExternalStatement> subList(int fromIndex, int toIndex) {
		return subStatements.subList(fromIndex, toIndex);
	}

	public static class Body extends ExternalStatement {
		public Body(ExternalStatement... entries) {
			super(new StringEntry(""),"");
			context.setIsBody(true);
			for(ExternalStatement statement: entries){
				this.add(statement);
			}

		}
		@Override
		public boolean add(ExternalStatement otherBody){
			if(otherBody instanceof ExternalStatement.Body){
				for(String name:otherBody.context.links.keySet()){
					this.context.links.put(name,otherBody.context.links.get(name));
				}
				for(String name:otherBody.context.linkedTypes.keySet()){
					this.context.linkedTypes.put(name,otherBody.context.linkedTypes.get(name));
				}
				return super.add(otherBody);
			}
			else {
				return super.add(otherBody);
			}
		}
		public String getStatementType(){
			return "ExternalStatement.Body";
		}
		@Override
		public ExternalStatement getAsStatement(){
			Parameters subStatementsAsStatement = new Parameters ();
			for(ExternalStatement subStatement: subStatements){
				ExternalStatement statement = subStatement.getAsStatement();
				subStatementsAsStatement.add(new ExternalStatement(statement));
			}
			return new ExternalStatement(
					new ExternalStatement.NewObject(
							new TypeName("ExternalStatement.Body"), subStatementsAsStatement));
		}
	}
	public static class Parameters extends ExternalStatement {
		public Parameters(String delimiter, ExternalStatement...statements){
			super(delimiter,statements);
			onEmptyDelimiter = null;
		}
		public Parameters( ExternalStatement...statements){
			super(",",statements);
			onEmptyDelimiter = null;
		}
		@Override
		public ExternalStatement getAsStatement(){
			Parameters statementParameters = new Parameters();
			if(delimiter!=","){
				statementParameters.add(ExternalClassHelper.getAsStatementFromEntry(delimiter));
			}
			for(ExternalStatement subStatement:subStatements){
				ExternalStatement statement = subStatement.getAsStatement();
				statementParameters.add(new ExternalStatement(statement));
			}
			return new NewObject(new TypeName("ExternalStatement.Parameters"),statementParameters);
		}

	}
	public static class ArrayParameters extends ExternalStatement {

		public ArrayParameters( ExternalStatement.Parameters parameters){
			super("][",parameters.subStatements.toArray(new ExternalStatement[0]));
			onEmptyDelimiter = null;
			prefix = new StringEntry("[");
			suffix = new StringEntry("]");
		}
		public ArrayParameters( ExternalStatement...statements){
			super("][",statements);
			onEmptyDelimiter = null;
			prefix = new StringEntry("[");
			suffix = new StringEntry("]");
		}
		@Override
		public ExternalStatement getAsStatement(){
			Parameters statementParameters = new Parameters();
			for(ExternalStatement statement:subStatements){
				statementParameters.add(statement.getAsStatement());
			}
			return new NewObject(new TypeName("ExternalStatement.ArrayParameters"),statementParameters);
		}
	}
	public static class NewObject extends ExternalStatement {
		private ExternalStatement name = null;
		private ExternalStatement parameters = null;
		private ExternalStatement array_parameters = null;
		public NewObject(ExternalStatement initialName){
			super(new StringEntry("new "),new StringEntry(")"),"(",initialName,new Parameters());
			this.addImport(new ImportEntry(initialName));
			StringBuilder nameBuilder = new StringBuilder();
			initialName.get(nameBuilder);
			name = initialName;
		}
		public NewObject(ExternalStatement initialName, ExternalStatement.Parameters initialParameters){
			super(new StringEntry("new "),new StringEntry(")"),"(",initialName,initialParameters);
			this.addImport(new ImportEntry(initialName));
			StringBuilder nameBuilder = new StringBuilder();
			initialName.get(nameBuilder);
			name = initialName;
			parameters = initialParameters;
		}
		public NewObject(ExternalStatement initialName, ExternalStatement.ArrayParameters initialArrayParameters){
			super(new StringEntry("new "),"",initialName,initialArrayParameters);
			this.addImport(new ImportEntry(initialName));
			StringBuilder nameBuilder = new StringBuilder();
			initialName.get(nameBuilder);
			name = initialName;
			array_parameters = initialArrayParameters;
		}
		public NewObject(ExternalStatement initialName, ExternalStatement.Parameters initialParameters, ExternalStatement.ArrayParameters initialArrayParameters){
			super(new StringEntry("new "),"",initialName,initialArrayParameters,new ExternalStatement(new StringEntry("{"),new StringEntry("}"),initialParameters));
			this.addImport(new ImportEntry(initialName));
			StringBuilder nameBuilder = new StringBuilder();
			initialName.get(nameBuilder);
			name = initialName;
			parameters = initialParameters;
			array_parameters = initialArrayParameters;
		}
		@Override
		public ExternalStatement getAsStatement(){
			Parameters statementParameters = new Parameters(ExternalClassHelper.getAsStatementFromEntry(name));
			if(parameters!=null){
				statementParameters.add(ExternalClassHelper.getAsStatementFromEntry(parameters));
			}
			if(array_parameters!=null){
				statementParameters.add(ExternalClassHelper.getAsStatementFromEntry(array_parameters));
			}
			return new NewObject(new TypeName("ExternalStatement.NewObject"),statementParameters);
		}
	}
	public static class TypeName extends ExternalStatement {
		private ExternalStatement cleanTypeName = null;
		private Entry myName = null;
		private Parameters myTemplateTypes = null;
		private int numberOfArraySymbols = 0;
		private boolean isInlineList = false;

		public TypeName(){
			super();
		}
		public TypeName(final String string) {
			this(new Entry(){public void get(StringBuilder builder){builder.append(string);}});
		}
		public TypeName(Entry name){
			super(name);
			myName = name;
			this.addImport(new ImportEntry(name));
		}
		public TypeName(Entry name, Entry templateParameters){
			super(name,templateParameters);
			myName = name;
			this.addImport(new ImportEntry(name));
		}
		public TypeName(Entry name, String angleBrace, Parameters templateParameters, String angleClose){
			super(name,new ExternalStatement(new StringEntry("<"),new StringEntry(">"),templateParameters));
			myName = name ;
			myTemplateTypes = templateParameters;
			addTemplateTypes(this);
		}
		public TypeName(final String string, int n, boolean l) {
			this(new Entry(){public void get(StringBuilder builder){builder.append(string);}});
			this.numberOfArraySymbols = n;
			this.isInlineList = l;
		}
		public TypeName(Entry name, int n, boolean l){
			super(name);
			myName = name;
			this.addImport(new ImportEntry(name));
			this.numberOfArraySymbols = n;
			this.isInlineList = l;
		}
		public TypeName(Entry name, Entry templateParameters, int n, boolean l){
			super(name,templateParameters);
			myName = name;
			this.addImport(new ImportEntry(name));
			this.numberOfArraySymbols = n;
			this.isInlineList = l;
		}
		public TypeName(Entry name, String angleBrace, Parameters templateParameters, String angleClose, int n, boolean l){
			super(name,new ExternalStatement(new StringEntry("<"),new StringEntry(">"),templateParameters));
			myName = name ;
			myTemplateTypes = templateParameters;
			addTemplateTypes(this);
			this.numberOfArraySymbols = n;
			this.isInlineList = l;
		}
		public void addTemplateTypes(TypeName parent){
			parent.addImport(new ImportEntry(myName));
			if(myTemplateTypes==null){
				return;
			}
			for(ExternalStatement type:myTemplateTypes){
				if(type instanceof TypeName){
					((TypeName)type).addTemplateTypes(parent);
				}
				else {
					parent.addImport(new ImportEntry(type));
				}
			}
		}
		public void setTypeName(ExternalStatement typeName){
			cleanTypeName = typeName;
			myName = typeName;
			add(typeName);
			addImport(new ImportEntry(typeName));
		}
		public void setTemplateType(ExternalStatement.Parameters templates){
			add(new ExternalStatement(new StringEntry("<"), new StringEntry(">"),templates));
			myTemplateTypes = templates;
			addTemplateTypes(this);
		}
		@Override
		public void clear(){
			subStatements.clear();
			delimiters.clear();
			imports.clear();
		}
		public ExternalStatement getCleanType() {
			return cleanTypeName;
		}
		public String getStatementType(){
			return "ExternalStatement.TypeName";
		}
		public void setNumberOfArraySymbols(int newNoAS){
			this.numberOfArraySymbols = newNoAS;
		}
		public void setIsInlineList(boolean newIIL){
			this.isInlineList = newIIL;
		}
		@Override
		public void get(StringBuilder builder){
			super.get(builder);
			for(int i=0;i<this.numberOfArraySymbols;++i){
				builder.append("[]");
			}
			if(isInlineList){
				builder.append("...");
			}
		}
		@Override
		public ExternalStatement getAsStatement(){
			Parameters parameters = new Parameters ();
			if(myName!=null&&myTemplateTypes!=null){
				parameters.add(ExternalClassHelper.getAsStatementFromEntry(myName));
				parameters.add(ExternalClassHelper.getAsStatementFromEntry("<"));
				parameters.add(ExternalClassHelper.getAsStatementFromEntry(myTemplateTypes));
				parameters.add(ExternalClassHelper.getAsStatementFromEntry(">"));
			}
			else {
				if(prefix!=null){
					parameters.add(ExternalClassHelper.getAsStatementFromEntry(prefix));
				}
				if(subStatements.size()==1){
					parameters.add(ExternalClassHelper.getAsStatementFromEntry(subStatements.get(0)));
				}
				if(suffix!=null){
					parameters.add(ExternalClassHelper.getAsStatementFromEntry(suffix));
				}
				if(subStatements.size()==2){
					parameters.add(ExternalClassHelper.getAsStatementFromEntry(subStatements.get(0)));
					parameters.add(ExternalClassHelper.getAsStatementFromEntry(subStatements.get(1)));
				}
			}
			if(numberOfArraySymbols>0||isInlineList){
				parameters.add(new ExternalStatement(new StringEntry(""+numberOfArraySymbols)));
				parameters.add(new ExternalStatement(new StringEntry(""+isInlineList)));
			}
			return new ExternalStatement(
					new ExternalStatement.NewObject(
							new TypeName("ExternalStatement.TypeName"), parameters));
		}
	}
	public static class Conditional extends ExternalStatement {
		private ExternalStatement.Body __BODY__;
		private StringEntry leftBrace = new StringEntry("(");
		private StringEntry rightBrace = new StringEntry(")");
		private String name = null;
		private ExternalStatement header = null;
		public Conditional(String name, ExternalStatement header, ExternalStatement body){
			super(new TabEntry(new StringEntry(name)));
			if(header != null){
				super.add(new ExternalStatement(leftBrace,rightBrace,"",header));
			}
			if(__BODY__ instanceof ExternalStatement.Body){
				super.add(new ExternalStatement(new StringEntry(" {")));
				__BODY__ = ((Body)body);
			}
			else {
				__BODY__ = new Body(body);
				super.add(new ExternalStatement(new StringEntry(" {")));
			}
			this.__BODY__.setTabs(tabs+1);
			if(header != null){
				for(ExternalVariableEntry variable:header.getContext().getVariables()){
					body.getContext().add(variable);
				}
			}
			this.addSubImport(__BODY__);
			this.name = name;
			this.header = header;
		}
		public Conditional(String name, String initialLeftBrace, ExternalStatement header, String initialRightBrace, ExternalStatement body){
			this(name,header,body);
			leftBrace.set(initialLeftBrace);
			rightBrace.set(initialRightBrace);
		}
		@Override
		public boolean add(ExternalStatement element){
			boolean result = __BODY__.add(element);
			element.setParentContext(context);
			return result;
		}
		@Override
		public void setTabs(int newTabs){
			this.tabs = newTabs;
			this.__BODY__.setTabs(newTabs+1);
			
		}
		public ExternalStatement setBraces(String left, String right){
			leftBrace.set(left);
			rightBrace.set(right);
			return this;
		}
		@Override
		public void get(StringBuilder builder){
			setTabs(tabs);
			super.get(builder);
			__BODY__.get(builder);
			new TabEntry(tabs,new StringEntry("}")).get(builder);
		}
		public ExternalStatement getAsStatement() {
			return new ExternalStatement.NewObject(
					new TypeName("ExternalStatement.Conditional"),
					new Parameters(
							new ExternalStatement(new QuoteEntry(name)),
							new ExternalStatement(new QuoteEntry(leftBrace.getString())),
							header!=null?header.getAsStatement():new ExternalStatement(new StringEntry("null")),
									new ExternalStatement(new QuoteEntry(rightBrace.getString())),
									__BODY__!=null?__BODY__.getAsStatement():new ExternalStatement(new StringEntry("null")))
					);

		}
	}
	public ExternalStatement getAsStatement() {

		Parameters delimitersAsStatement = new Parameters ();
		boolean hasAboration = false;
		int i = 0;
		for(String delimit: delimiters){
			delimitersAsStatement.add(ExternalClassHelper.getAsStatementFromEntry(delimit));
			if(i>0&&!delimit.equals(delimiter)){
				hasAboration = true;
			}
			++i;
		}
		Parameters subStatementsAsStatement = new Parameters ();
		for(ExternalStatement subStatement: subStatements){
			subStatementsAsStatement.add(subStatement.getAsStatement());
		}
		Parameters completeParameters = new Parameters ();
		if(suffix!=null||hasAboration){
			if(prefix==null){
				completeParameters.add(new ExternalStatement(new StringEntry("null")));
			}
			else {
				completeParameters.add(ExternalClassHelper.getAsStatementFromEntry(prefix));
			}
			if(suffix==null){
				completeParameters.add(new ExternalStatement(new StringEntry("null")));
			}
			else {
				completeParameters.add(ExternalClassHelper.getAsStatementFromEntry(suffix));
			}
		}
		else if(prefix!=null){
			completeParameters.add(ExternalClassHelper.getAsStatementFromEntry(prefix));
		}
		if(!delimiter.equals("")||completeParameters.isEmpty()==false){
			completeParameters.add(new ExternalStatement(new StringEntry("\""+delimiter+"\"")));
		}
		if(hasAboration){
			completeParameters.add(
					new ExternalStatement(new StringEntry("Arrays.asList(new String[]{"), new StringEntry("})"), delimitersAsStatement));
		}
		if(!subStatementsAsStatement.isEmpty()){
			completeParameters.add(subStatementsAsStatement);
		}

		if(getStatementType()==null&&completeParameters.size() == 1 && subStatements.size() == 1) {
			return new ExternalStatement(completeParameters.get(0));
		}
		else {
			return new ExternalStatement(
					new ExternalStatement.NewObject(
							new TypeName(getStatementType()!=null?getStatementType():"ExternalStatement"), completeParameters));
		}
	}
}