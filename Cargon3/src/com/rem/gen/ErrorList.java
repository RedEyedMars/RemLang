package com.rem.gen;
public class ErrorList {
	public static com.rem.output.helpers.OutputClass OUTPUT = new com.rem.output.helpers.OutputClass()._package(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("com"),null).add(new com.rem.output.helpers.OutputExact().set("rem"),null).add(new com.rem.output.helpers.OutputExact().set("gen"),null).add(new com.rem.output.helpers.OutputExact().set("parser"),null)).name(new com.rem.output.helpers.OutputExact().set("ErrorList")).extendType(null).variable(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("List")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")))),new com.rem.output.helpers.OutputExact().set("children")).assign(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ArrayList")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")))),new com.rem.output.helpers.OutputArguments()))).variable(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")),new com.rem.output.helpers.OutputExact().set("error")).assign(new com.rem.output.helpers.OutputExact().set("null"))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList")),new com.rem.output.helpers.OutputExact().set("")).parameters(new com.rem.output.helpers.OutputParameters()).body(new com.rem.output.helpers.OutputBody())).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList")),new com.rem.output.helpers.OutputExact().set("")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")),new com.rem.output.helpers.OutputExact().set("error")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("this"),null).add(new com.rem.output.helpers.OutputExact().set("error"),null)).operator("=").right(new com.rem.output.helpers.OutputExact().set("error")))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("adjust")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")),new com.rem.output.helpers.OutputExact().set("newError")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputConditional().init("if").body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputExact().set("error")).operator("=").right(new com.rem.output.helpers.OutputExact().set("newError"))))).header(new com.rem.output.helpers.OutputConditionalHeader().declare(null).call(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputExact().set("error")).operator("==").right(new com.rem.output.helpers.OutputExact().set("null"))).operator("||").right(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("newError"),null).add(new com.rem.output.helpers.OutputExact().set("getPosition"),new com.rem.output.helpers.OutputArguments()))).operator(">").right(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("error"),null).add(new com.rem.output.helpers.OutputExact().set("getPosition"),new com.rem.output.helpers.OutputArguments()))))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("merge")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList")),new com.rem.output.helpers.OutputExact().set("errors")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("children"),null).add(new com.rem.output.helpers.OutputExact().set("addAll"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("errors"),null).add(new com.rem.output.helpers.OutputExact().set("children"),null))))).add(new com.rem.output.helpers.OutputConditional().init("if").body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("adjust"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("errors"),null).add(new com.rem.output.helpers.OutputExact().set("error"),null)))))).header(new com.rem.output.helpers.OutputConditionalHeader().declare(null).call(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("errors"),null).add(new com.rem.output.helpers.OutputExact().set("error"),null)).operator("!=").right(new com.rem.output.helpers.OutputExact().set("null"))))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("append")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")),new com.rem.output.helpers.OutputExact().set("errors")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("children"),null).add(new com.rem.output.helpers.OutputExact().set("add"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("errors"))))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("boolean")),new com.rem.output.helpers.OutputExact().set("isEmpty")).parameters(new com.rem.output.helpers.OutputParameters()).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("return ").set(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputExact().set("error")).operator("==").right(new com.rem.output.helpers.OutputExact().set("null"))).operator("&&").right(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("children"),null).add(new com.rem.output.helpers.OutputExact().set("isEmpty"),new com.rem.output.helpers.OutputArguments())))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("String")),new com.rem.output.helpers.OutputExact().set("toString")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("CharBuffer")),new com.rem.output.helpers.OutputExact().set("chars")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("StringBuilder")),new com.rem.output.helpers.OutputExact().set("builder")).assign(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("StringBuilder")),new com.rem.output.helpers.OutputArguments())))).add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("List")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Integer")))),new com.rem.output.helpers.OutputExact().set("lines")).assign(new com.rem.output.helpers.OutputNewObject().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ArrayList")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Integer")))),new com.rem.output.helpers.OutputArguments())))).add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("CharBuffer")),new com.rem.output.helpers.OutputExact().set("liner")).assign(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("chars"),null).add(new com.rem.output.helpers.OutputExact().set("duplicate"),new com.rem.output.helpers.OutputArguments())))).add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputStaticCall().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("IntStream")),new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("range"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("0")).add(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("chars"),null).add(new com.rem.output.helpers.OutputExact().set("limit"),new com.rem.output.helpers.OutputArguments()))).add(new com.rem.output.helpers.OutputExact().set("forEach"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputLambda().declare(new com.rem.output.helpers.OutputVariable().set(com.rem.output.helpers.OutputType.Any,new com.rem.output.helpers.OutputExact().set("I"))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputConditional().init("if").body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("lines"),null).add(new com.rem.output.helpers.OutputExact().set("add"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("I")))))).header(new com.rem.output.helpers.OutputConditionalHeader().declare(null).call(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("liner"),null).add(new com.rem.output.helpers.OutputExact().set("get"),new com.rem.output.helpers.OutputArguments())).operator("==").right(new com.rem.output.helpers.OutputExact(("\'\\n\'").toString()))))))))))).add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("toString"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact(("builder::append").toString())).add(new com.rem.output.helpers.OutputExact().set("lines")).add(new com.rem.output.helpers.OutputExact().set("chars"))))).add(new com.rem.output.helpers.OutputStatement().prefix("return ").set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("builder"),null).add(new com.rem.output.helpers.OutputExact().set("toString"),new com.rem.output.helpers.OutputArguments()))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("toString")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Consumer")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("String")))),new com.rem.output.helpers.OutputExact().set("builder"))).add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("List")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Integer")))),new com.rem.output.helpers.OutputExact().set("lines"))).add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("CharBuffer")),new com.rem.output.helpers.OutputExact().set("chars")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("children"),null).add(new com.rem.output.helpers.OutputExact().set("forEach"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputLambda().declare(new com.rem.output.helpers.OutputVariable().set(com.rem.output.helpers.OutputType.Any,new com.rem.output.helpers.OutputExact().set("C"))).call(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("C"),null).add(new com.rem.output.helpers.OutputExact().set("toString"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("builder")).add(new com.rem.output.helpers.OutputExact().set("lines")).add(new com.rem.output.helpers.OutputExact().set("chars")))))))).add(new com.rem.output.helpers.OutputConditional().init("if").body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().set(new com.rem.output.helpers.OutputCall().add(new com.rem.output.helpers.OutputExact().set("error"),null).add(new com.rem.output.helpers.OutputExact().set("toString"),new com.rem.output.helpers.OutputArguments().add(new com.rem.output.helpers.OutputExact().set("builder")).add(new com.rem.output.helpers.OutputExact().set("lines")).add(new com.rem.output.helpers.OutputExact().set("chars")))))).header(new com.rem.output.helpers.OutputConditionalHeader().declare(null).call(new com.rem.output.helpers.OutputOperator().left(new com.rem.output.helpers.OutputExact().set("error")).operator("!=").right(new com.rem.output.helpers.OutputExact().set("null"))))))).encloseClass(ErrorList.Dummy.OUTPUT);
	public static class Dummy {
		public static com.rem.output.helpers.OutputClass OUTPUT = new com.rem.output.helpers.OutputClass()._package(new com.rem.output.helpers.OutputCall()).name(new com.rem.output.helpers.OutputExact().set("Dummy")).extendType(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList"))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Dummy")),new com.rem.output.helpers.OutputExact().set("")).parameters(new com.rem.output.helpers.OutputParameters()).body(new com.rem.output.helpers.OutputBody())).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("adjust")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ParseError")),new com.rem.output.helpers.OutputExact().set("newError")))).body(new com.rem.output.helpers.OutputBody())).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("merge")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList")),new com.rem.output.helpers.OutputExact().set("errors")))).body(new com.rem.output.helpers.OutputBody())).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("append")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("ErrorList")),new com.rem.output.helpers.OutputExact().set("errors")))).body(new com.rem.output.helpers.OutputBody())).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("boolean")),new com.rem.output.helpers.OutputExact().set("isEmpty")).parameters(new com.rem.output.helpers.OutputParameters()).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("return ").set(new com.rem.output.helpers.OutputExact().set("true"))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("String")),new com.rem.output.helpers.OutputExact().set("toString")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("CharBuffer")),new com.rem.output.helpers.OutputExact().set("chars")))).body(new com.rem.output.helpers.OutputBody().add(new com.rem.output.helpers.OutputStatement().prefix("return ").set(new com.rem.output.helpers.OutputQuote().set(""))))).method(new com.rem.output.helpers.OutputMethod().access("public ").set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("void")),new com.rem.output.helpers.OutputExact().set("toString")).parameters(new com.rem.output.helpers.OutputParameters().add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Consumer")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("String")))),new com.rem.output.helpers.OutputExact().set("builder"))).add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("List")).template(new com.rem.output.helpers.OutputType.Template().add(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("Integer")))),new com.rem.output.helpers.OutputExact().set("lines"))).add(new com.rem.output.helpers.OutputVariable().set(new com.rem.output.helpers.OutputType().add(new com.rem.output.helpers.OutputExact().set("CharBuffer")),new com.rem.output.helpers.OutputExact().set("chars")))).body(new com.rem.output.helpers.OutputBody()));
	}
}