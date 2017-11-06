package com.rem.parser.generation.classwise;

import java.util.ArrayList;
import java.util.Arrays;

import com.rem.parser.generation.Entry;
import com.rem.parser.generation.StringEntry;

public class ExternalClassHelper {
	public static void setup(){
		ExternalClassEntry.classMap.put("List<T1>", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("List<T1>"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class List<T1> "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("add"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("sort"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("T1"), new StringEntry("get"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("List<T1>").__INIT__();
		supplimentListClass("List","List","Object");
		ExternalClassEntry.classMap.put("ArrayList", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("ArrayList"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class ArrayList "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("add"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("sort"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("ArrayList").__INIT__();
		ExternalClassEntry.classMap.put("HashMap", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("HashMap"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class HashMap "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("Object"), new StringEntry("put"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("HashMap").__INIT__();
		ExternalClassEntry.classMap.put("Map", new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry("Map"),
						" class ",
						null,
						new ArrayList<Entry>(),
						new StringEntry(" class Map "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(Arrays.asList(
								new ExternalMethodEntry(
										0, false, new StringEntry("Object"), new StringEntry("put"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("remove"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Boolean"), new StringEntry("isEmpty"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("void"), new StringEntry("addAll"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body()),
								new ExternalMethodEntry(
										0, false, new StringEntry("Integer"), new StringEntry("size"),
										new ArrayList<ExternalVariableEntry>(),new ExternalStatement.Body())
								)
								),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get("Map").__INIT__();
	}

	public static ExternalContext supplimentListClass(final String fullName,final String listType,final String innerType) {

		ExternalClassEntry.classMap.put(fullName, new ExternalClassEntry(){
			@Override
			public void __INIT__(){
				super.__SETUP__(

						new StringEntry("java.util"), 
						new StringEntry(""),
						new StringEntry(fullName),
						" class ",
						new StringEntry(listType+"<T1>"),
						new ArrayList<Entry>(),
						new StringEntry(" class "+fullName+" "),
						new ArrayList<ExternalVariableEntry>(),
						new ArrayList<ExternalMethodEntry>(),new ArrayList<ExternalClassEntry>()				

						);
			}
		});
		ExternalClassEntry.classMap.get(fullName).__INIT__();
		ExternalClassEntry clazz = ExternalClassEntry.classMap.get(fullName);
		ExternalClassEntry parent = ExternalClassEntry.classMap.get(listType+"<T1>");
		for(String methodName: parent.getMethodNames()){
			if(parent.getMethod(methodName).getTypeName().contains("T1")){
				clazz.addMethod(new ExternalMethodEntry(parent.getMethod(methodName).getTypeName().replace("T1", innerType),parent.getMethod(methodName)));
			}
		}
		return ExternalClassEntry.classMap.get(fullName).getContext();
	}
}
