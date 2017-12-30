package clgen;
import java.util.*;
import java.io.*;
import lists.*;
import com.rem.parser.generation.classwise.*;
import com.rem.parser.generation.*;
import com.rem.parser.parser.*;
import com.rem.parser.token.*;
import com.rem.parser.*;
import clgen.NameVar;
import com.rem.parser.generation.*;
import com.rem.parser.generation.classwise.*;
import clent.*;
import java.util.*;
import java.io.*;
import java.nio.*;
import com.rem.parser.generation.classwise.ExternalStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.StringBuilder;
public class  NameVar   {
	public static class classes {
	}
	public static NameVar variables = null;
	public static NameVar methods = null;
	//Externals


	//Internals
protected ExternalStatement asPublicStatement = new ExternalStatement();
protected String asPublicString = null;
protected List<List<ExternalStatement>> parts = new ArrayList<List<ExternalStatement>>();
protected List<String> strings = new ArrayList<String>();
protected Boolean hasChanged = false;
protected Boolean isInner = true;
protected Boolean isCamelized = false;
protected final HashSet<Integer> camelizedParts = new HashSet<Integer>();
protected final HashSet<Integer> asStatementParts = new HashSet<Integer>();

	public ExternalStatement getAsPublicStatement()  {
		return asPublicStatement;
	}
	public ExternalStatement get_asPublicStatement()  {
		return asPublicStatement;
	}
	public String getAsPublicString()  {
		return asPublicString;
	}
	public String get_asPublicString()  {
		return asPublicString;
	}
	public List<List<ExternalStatement>> getParts()  {
		return parts;
	}
	public List<List<ExternalStatement>> get_parts()  {
		return parts;
	}
	public List<String> getStrings()  {
		return strings;
	}
	public List<String> get_strings()  {
		return strings;
	}
	public Boolean getHasChanged()  {
		return hasChanged;
	}
	public Boolean get_hasChanged()  {
		return hasChanged;
	}
	public Boolean getIsInner()  {
		return isInner;
	}
	public Boolean get_isInner()  {
		return isInner;
	}
	public Boolean getIsCamelized()  {
		return isCamelized;
	}
	public Boolean get_isCamelized()  {
		return isCamelized;
	}
	public HashSet<Integer> getCamelizedParts()  {
		return camelizedParts;
	}
	public HashSet<Integer> get_camelizedParts()  {
		return camelizedParts;
	}
	public HashSet<Integer> getAsStatementParts()  {
		return asStatementParts;
	}
	public HashSet<Integer> get_asStatementParts()  {
		return asStatementParts;
	}
public ExternalStatement getAsStatement()  {
	if (hasChanged) {
		update();
		hasChanged = false;
	}
	return asPublicStatement;
}
public String getAsString()  {
	if (hasChanged) {
		update();
		hasChanged = false;
	}
	return asPublicString;
}
public void camelize()  {
	camelizedParts.add(parts.size() - 1);
	isCamelized = true;
}
public void inner()  {
	isInner = true;
	hasChanged = true;
}
public void outer()  {
	isInner = false;
	hasChanged = true;
}
public void add(final ExternalStatement subStatement)  {
	final StringBuilder builder = new StringBuilder();
	subStatement.get(builder);
	strings.add(builder.toString());
	final List<ExternalStatement> part = new ArrayList<ExternalStatement>();
	part.add(subStatement);
	parts.add(part);
	hasChanged = true;
}
public void add(final String subStatement)  {
	final List<ExternalStatement> part = new ArrayList<ExternalStatement>();
	part.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Enty*/new ExternalStatement(new StringEntry(subStatement.toString())))));
	parts.add(part);
	strings.add(subStatement);
	hasChanged = true;
}
public void add(final NameVar otherNameVar)  {
	parts.addAll(otherNameVar.parts);
	strings.addAll(otherNameVar.strings);
	if (otherNameVar.isCamelized) {
		isCamelized = true;
	}
	hasChanged = true;
}
public void concatenateWith(final NameVar otherNameVar)  {
	parts.get(parts.size() - 1).addAll(otherNameVar.parts.get(0));
	int i = 1;
	while (i < otherNameVar.parts.size()) {
		parts.add(otherNameVar.parts.get(i));
		i += 1;
	}
	strings.addAll(otherNameVar.strings);
	if (otherNameVar.isCamelized) {
		isCamelized = true;
	}
	hasChanged = true;
}
public void update()  {
	asPublicStatement.clear();
	final ExternalStatement newStatement = new ExternalStatement(".");
	int i = 0;
	for (final List<ExternalStatement> partList :  parts) {
		final ExternalStatement concatStatement;
		if (partList.size() > 1 ) {
			if (isInner) {
				concatStatement = new ExternalStatement("+");
			}
			else  {
				concatStatement = new ExternalStatement();
			}
		}
		else  {
			concatStatement = newStatement;
		}
		for (final ExternalStatement part :  partList) {
			if (isInner) {
				final String string = MainFlow.camelize(strings.get(i));
				if (i == 0  || partList.size() > 1 ) {
					concatStatement.add(part);
				}
				else  {
					concatStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("get")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(part)))))));
				}
			}
			else  {
				concatStatement.add(part);
			}
		}
		if (partList.size() > 1 ) {
			if (i == 0  || isInner == false) {
				newStatement.add(concatStatement);
			}
			else  {
				newStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("get")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(concatStatement)))))));
			}
		}
		i += 1;
	}
	if (isCamelized) {
		asPublicStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement("",
			 	new ExternalStatement(".", /*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry("MainFlow"))), /*Enty*/new ExternalStatement(new StringEntry("camelize"))),
			 	new ExternalStatement(new StringEntry("("),new StringEntry(")"),"",
			 		new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*BrOp*/new ExternalStatement("",new ExternalStatement(new StringEntry("("),new StringEntry(")"),"", /*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(newStatement)))), new ExternalStatement("."), /*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("toString")),new ExternalStatement.Parameters())))))))));
	}
	else  {
		asPublicStatement.add(newStatement);
	}
	final StringBuilder stringBuilder = new StringBuilder();
	i = 0;
	for (final String string :  strings) {
		if (camelizedParts.contains(i)) {
			stringBuilder.append(MainFlow.camelize(string));
		}
		else  {
			stringBuilder.append(string);
		}
		i += 1;
	}
	asPublicString = stringBuilder.toString();
}

}