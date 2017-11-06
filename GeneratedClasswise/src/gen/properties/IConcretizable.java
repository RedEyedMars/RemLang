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

public interface IConcretizable extends Entry {


	public Boolean getIsConcrete();
	public void setIsConcrete(Boolean newIsConcrete);
}