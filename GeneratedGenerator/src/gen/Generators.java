package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.checks.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class Generators extends Object {

	public static final GeneratorGenerator generator = new GeneratorGenerator();
	public static final CheckGenerator check = new CheckGenerator();
	public static final PropertyGenerator property = new PropertyGenerator();
	public static final EntryClassGenerator entryClass = new EntryClassGenerator();
	public static final Generator[] _ = new Generator[]{generator,check,property,entryClass};
}