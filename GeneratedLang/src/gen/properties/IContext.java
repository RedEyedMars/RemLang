package gen.properties;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.checks.*;
import gen.entries.*;
import lists.*;

public interface IContext {


	public HashMap<String,VariableEntry> getVariables();
	public IContext getParentContext();
	public ClassEntry getContextClass();
	public Boolean getShouldReturn();
	public ClassEntry getType();
	public VariableEntry getVariable(String variableName);
	public void addVariable(String variableName);
	public void setVariable(String variableName,VariableEntry variable);
	public void setShouldReturn(Boolean newShould);
	public ClassEntry descend();
}