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

public interface IInterfaceable extends Entry {


	public Boolean getIsInterface();
	public void setIsInterface(Boolean newIsInterface);
}