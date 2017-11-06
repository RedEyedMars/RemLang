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

public interface IGlobalizable extends Entry {


	public Boolean getIsGlobal();
	public void setIsGlobal(Boolean newIsGlobal);
}