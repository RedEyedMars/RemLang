package gen;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import com.rem.parser.parser.*;
import gen.entries.*;
import gen.properties.*;
import lists.*;

public class Generators extends Object {

	public static final BaseGenerator base = new BaseGenerator();
	public static final RuleGenerator rule = new RuleGenerator();
	public static final ListGenerator list = new ListGenerator();
}