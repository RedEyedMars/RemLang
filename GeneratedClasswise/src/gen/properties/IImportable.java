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

public interface IImportable extends Entry {


	public ImportListEntry getImportPackage();
	public void setPackage(String newPackage,String newName);
	public void addImports(ImportListEntry newImport);
}