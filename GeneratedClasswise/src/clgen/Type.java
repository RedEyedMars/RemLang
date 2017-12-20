package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.Type;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import com.rem.parser.generation.classwise.ExternalStatement;
import clgen.TypeStatement;
public class  Type   {
	public static class classes {
	}
	public static Type variables = null;
	public static Type methods = null;
	//Externals


	//Internals
protected ExternalStatement.TypeName asPublicStatement = new ExternalStatement.TypeName();
protected List<List<TypeStatement>> parts = new ArrayList<List<TypeStatement>>();
protected List<ExternalStatement> templateTypes = new ArrayList<ExternalStatement>();
protected Boolean isInlineList = false;
protected Boolean isInner = true;
protected int numberOfArraySymbols = 0;
protected Boolean hasChanged = false;
protected final HashSet<Integer> camelizedParts = new HashSet<Integer>();
protected ExternalStatement findMethod = null;

	public ExternalStatement.TypeName getAsPublicStatement()  {
		return asPublicStatement;
	}
	public ExternalStatement.TypeName get_asPublicStatement()  {
		return asPublicStatement;
	}
	public List<List<TypeStatement>> getParts()  {
		return parts;
	}
	public List<List<TypeStatement>> get_parts()  {
		return parts;
	}
	public List<ExternalStatement> getTemplateTypes()  {
		return templateTypes;
	}
	public List<ExternalStatement> get_templateTypes()  {
		return templateTypes;
	}
	public Boolean getIsInlineList()  {
		return isInlineList;
	}
	public Boolean get_isInlineList()  {
		return isInlineList;
	}
	public Boolean getIsInner()  {
		return isInner;
	}
	public Boolean get_isInner()  {
		return isInner;
	}
	public int getNumberOfArraySymbols()  {
		return numberOfArraySymbols;
	}
	public int get_numberOfArraySymbols()  {
		return numberOfArraySymbols;
	}
	public Boolean getHasChanged()  {
		return hasChanged;
	}
	public Boolean get_hasChanged()  {
		return hasChanged;
	}
	public HashSet<Integer> getCamelizedParts()  {
		return camelizedParts;
	}
	public HashSet<Integer> get_camelizedParts()  {
		return camelizedParts;
	}
	public ExternalStatement getFindMethod()  {
		return findMethod;
	}
	public ExternalStatement get_findMethod()  {
		return findMethod;
	}
public ExternalStatement.TypeName getAsStatement()  {
	if (hasChanged) {
		update();
		hasChanged = false;
	}
	return asPublicStatement;
}
public void camelize()  {
	camelizedParts.add(parts.size() - 1);
}
public void inner()  {
	isInner = true;
	hasChanged = true;
}
public void outer()  {
	isInner = false;
	hasChanged = true;
}
public TypeStatement statement(final ExternalStatement statement)  {
	final TypeStatement typeStatement = new TypeStatement();
	typeStatement.set(statement);
	return typeStatement;
}
public TypeStatement string(final String string)  {
	final TypeStatement typeStatement = new TypeStatement();
	typeStatement.set(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(string.toString())))));
	typeStatement.set(string);
	return typeStatement;
}
public void addSubClass(final String subClass)  {
	final List<TypeStatement> part = new ArrayList<TypeStatement>();
	parts.add(part);
	part.add(string(subClass));
	hasChanged = true;
}
public void addSubClass(final ExternalStatement subClass)  {
	final List<TypeStatement> part = new ArrayList<TypeStatement>();
	parts.add(part);
	part.add(statement(subClass));
	hasChanged = true;
}
public void addSubClass(final Type subType)  {
	final int numberOfParts = parts.size();
	parts.addAll(subType.parts);
	templateTypes.addAll(subType.templateTypes);
	if (subType.isInlineList) {
		isInlineList = true;
	}
	numberOfArraySymbols = subType.numberOfArraySymbols;
	for (final Integer camelizedPart :  subType.camelizedParts) {
		camelizedParts.add(numberOfParts + camelizedPart);
	}
	hasChanged = true;
}
public void addTemplateClass(final ExternalStatement templateType)  {
	templateTypes.add(templateType);
	hasChanged = true;
}
public void addTemplateClass(final Type templateType)  {
	templateTypes.add(templateType.getAsStatement());
	hasChanged = true;
}
public void addArraySymbol()  {
	numberOfArraySymbols += 1;
	hasChanged = true;
}
public void setIsInlineList(final Boolean newInlineList)  {
	isInlineList = newInlineList;
	hasChanged = true;
}
public void concatenateWith(final Type otherType)  {
	parts.get(parts.size() - 1).addAll(otherType.parts.get(0));
	int i = 1;
	while (i < otherType.parts.size()) {
		parts.add(otherType.parts.get(i));
		i += 1;
	}
	hasChanged = true;
}
public void addFindMethod(final ExternalStatement newFindMethod)  {
	findMethod = newFindMethod;
}
public void update()  {
	asPublicStatement.clear();
	final ExternalStatement partStatement = new ExternalStatement(".");
	for (Integer i = 0; i <  parts.size(); ++i) {
		final List<TypeStatement> part = parts.get(i);
		final ExternalStatement concatPart = new ExternalStatement("+");
		for (final TypeStatement p :  part) {
			concatPart.add(p.getAsStatement());
		}
		if (isInner) {
			if (i == 0 ) {
				if (camelizedParts.contains(i)) {
					if (part.size() > 1  || part.get(0).getAsString() == null) {
						partStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("ExternalClassEntry"))), /*Enty*/new ExternalStatement(new StringEntry("classMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("camelize")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart)))))))))));
					}
					else  {
						final String zerothPartAsString = part.get(0).getAsString();
						partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry(FlowController.camelize("zerothPartAsString"))))));
					}
				}
				else  {
					if (part.size() > 1  || part.get(0).getAsString() == null) {
						partStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("ExternalClassEntry"))), /*Enty*/new ExternalStatement(new StringEntry("classMap"))), /*Enty*/new ExternalStatement(new StringEntry("get"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart))))))));
					}
					else  {
						partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(part.get(0).getAsString().toString())))));
					}
				}
			}
			else  {
				if (camelizedParts.contains(i)) {
					if (part.size() > 1  || part.get(0).getAsString() == null) {
						partStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("getSubClass")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("camelize")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart))))))))));
					}
					else  {
						final String zerothPartAsString = part.get(0).getAsString();
						partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(FlowController.camelize(zerothPartAsString.toString()).toString())))));
					}
				}
				else  {
					if (part.size() > 1  || part.get(0).getAsString() == null) {
						partStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("getSubClass")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart)))))));
					}
					else  {
						partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(part.get(0).getAsString().toString())))));
					}
				}
			}
		}
		else  {
			if (camelizedParts.contains(i)) {
				if (part.size() > 1  || part.get(0).getAsString() == null) {
					partStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("camelize")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart)))))));
				}
				else  {
					final String zerothPartAsString = part.get(0).getAsString();
					partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry(FlowController.camelize("zerothPartAsString"))))));
				}
			}
			else  {
				if (part.size() > 1  || part.get(0).getAsString() == null) {
					partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatPart))));
				}
				else  {
					partStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(part.get(0).getAsString().toString())))));
				}
			}
		}
	}
	asPublicStatement.setTypeName(partStatement);
	if (templateTypes.isEmpty() == false) {
		final ExternalStatement templateStatement = new ExternalStatement(",");
		for (final ExternalStatement type :  templateTypes) {
			templateStatement.add(type);
		}
		asPublicStatement.setTemplateType(templateStatement);
	}
	for (Integer i = 0; i <  numberOfArraySymbols; ++i) {
		asPublicStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("[]")))));
	}
	if (isInlineList) {
		asPublicStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("...")))));
	}
	if (findMethod != null) {
		asPublicStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("getMethod")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(findMethod)))))));
	}
}

}