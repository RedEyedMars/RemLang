package com.rem.gen;
import com.rem.gen.Cargon3;
public class Cargon3 {
	public static void main(String[] args){
		Cargon3.init();
		com.rem.output.helpers.OutputHelper.parse2(args,new com.rem.gen.parser.Parser(),Cargon3::onCreate,Cargon3::onUpdate);
	}
	public static void init(){
		;
	}
}
