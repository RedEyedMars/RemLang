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

public interface ITypeListener extends Entry {

	public static final String TYPE_UNKNOWN = "$UNKNOWN";

	public String getType();
	public String getDefaultType();
	public List<ITypeListener> getListeners();
	public Boolean getIsCast();
	public Boolean getIsEntry();
	public void setCast(Boolean newCast);
	public void setDefaultType(String newDefaultType);
	public void addListener(ITypeListener listener);
	public void changeType(String newType);
	public void setType(String newType);
	public Boolean hasType();
}