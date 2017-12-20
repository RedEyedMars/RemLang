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
protected final HashSet<Integer> camelizedParts = new HashSet<Integer>();

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
	public HashSet<Integer> getCamelizedParts()  {
		return camelizedParts;
	}
	public HashSet<Integer> get_camelizedParts()  {
		return camelizedParts;
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
	part.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Quot*/new ExternalStatement(new QuoteEntry(subStatement.toString().toString())))));
	parts.add(part);
	strings.add(subStatement);
	hasChanged = true;
}
public void add(final NameVar otherNameVar)  {
	parts.addAll(otherNameVar.parts);
	strings.addAll(otherNameVar.strings);
	hasChanged = true;
}
public void concatenateWith(final NameVar otherNameVar)  {
	parts.get(parts.size() - 1).addAll(otherNameVar.parts.get(0));
	int i = 0;
	while (i < otherNameVar.parts.size()) {
		parts.add(otherNameVar.parts.get(i));
		i += 1;
	}
	strings.addAll(otherNameVar.strings);
	hasChanged = true;
}
public void update()  {
	asPublicStatement.clear();
	final ExternalStatement newStatement = new ExternalStatement();
	int i = 0;
	for (final List<ExternalStatement> partList :  parts) {
		final ExternalStatement concatStatement = new ExternalStatement("+");
		for (final ExternalStatement part :  partList) {
			if (isInner) {
				if (camelizedParts.contains(i)) {
					final String string = strings.get(i);
					concatStatement.add(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*Name*/new ExternalStatement(new StringEntry(FlowController.camelize("string"))))));
				}
				else  {
					concatStatement.add(part);
				}
			}
			else  {
				if (camelizedParts.contains(i)) {
					concatStatement.add(/*Name*/new ExternalStatement(/*Call*/new ExternalStatement(null,new StringEntry(")"),"(",/*Name*/new ExternalStatement(new StringEntry("camelize")),new ExternalStatement.Parameters(/*Name*/new ExternalStatement(/*Acss*/new ExternalStatement(/*InCl*/new ExternalStatement(part)))))));
				}
				else  {
					concatStatement.add(part);
				}
			}
		}
		newStatement.add(concatStatement);
		i += 1;
	}
	asPublicStatement.add(newStatement);
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