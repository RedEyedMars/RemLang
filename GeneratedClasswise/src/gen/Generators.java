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

	public static final ClasswiseGenerator classwise = new ClasswiseGenerator();
	public static final ExternalGenerator external = new ExternalGenerator();
	public static final InternalGenerator internal = new InternalGenerator();
	public static final ClazzGenerator clazz = new ClazzGenerator();
	public static final BodyGenerator body = new BodyGenerator();
	public static final MethodGenerator method = new MethodGenerator();
	public static final VariableGenerator variable = new VariableGenerator();
	public static final Generator[] _ = new Generator[]{classwise,external,internal,clazz,body,method,variable};
}