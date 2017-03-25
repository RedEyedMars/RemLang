package gen.properties;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import lists.*;

public interface ITypeListener {

	public final static String TYPE_UNKNOWN = "$UNKNOWN";

	public String getType();
	public String getDefaultType();
	public List<ITypeListener> getListeners();
	public void setDefaultType(String newDefaultType);
	public void addListener(ITypeListener listener);
	public void changeType(String newType);
	public Boolean hasType();
}