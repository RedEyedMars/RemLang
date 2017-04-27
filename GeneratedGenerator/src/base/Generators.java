package base;

import com.rem.parser.generation.Generator;

public class Generators {

	public static final GeneratorGenerator generator = new GeneratorGenerator();
	public static final PropertyGenerator property = new PropertyGenerator();
	public static final EntryClassGenerator entryClass = new EntryClassGenerator();
	public static final CheckGenerator check = new CheckGenerator();
	public static final Generator[] _ = new Generator[]{Generators.generator,
			Generators.property,Generators.entryClass,Generators.check};
}
