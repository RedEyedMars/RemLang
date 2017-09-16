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

public class ContextEntry implements Entry {
	public ContextEntry getSelf(){
		return this;
	}


	private ContextEntry parentContext = (ContextEntry)null;

	public ContextEntry(ContextEntry iParent){
		parentContext = iParent;
	}
	public ContextEntry(){
		parentContext = null;
	}

	public ContextEntry getParentContext(){
		return parentContext;
	}
	public Integer getTab(){
		if((parentContext != null)){
			Integer tab = parentContext.getTab();
			return tab + 1;
		}
		else {
			return 0;
		}
	}
	public void setParentContext(ContextEntry newParent){
		parentContext = newParent;
	}
	public void get(StringBuilder builder){
	}
}