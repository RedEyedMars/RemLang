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

public class BaseGenerator extends Generator {

	private File directory = (File)null;
	private String seedName = null;


	public BaseGenerator(){
	}
	public void setup(ParseContext data){
		this.addPage();
		String fileName = data.getFileName();
		Integer indexOfDot = fileName.lastIndexOf(".");
		if((indexOfDot > -1)){
			fileName = fileName.substring(0,indexOfDot);
		}
		seedName = fileName;
		directory = new File(Generators.base.buildString("../Generated",Generators.base.camelize(fileName),"/src/"));
		directory.mkdirs();
	}

	public File getDirectory(){
		return directory;
	}

	public String getSeedName(){
		return seedName;
	}

	public String getName(){
		return "Base";
	}

	public void generateRoot(IToken root){
	}

	public void generate(ParseContext data){
	}

	public IParser getLazyNameParser(){
		return null;
	}
}