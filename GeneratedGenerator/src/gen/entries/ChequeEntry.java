package gen.entries;

import java.io.*;
import java.util.*;
import com.rem.parser.*;
import com.rem.parser.generation.*;
import com.rem.parser.token.*;
import gen.*;
import gen.checks.*;
import gen.properties.*;
import lists.*;

public class ChequeEntry implements Entry {
	public ChequeEntry getSelf(){
		return this;
	}


	private VariableEntry left = (VariableEntry)null;
	private VariableEntry right = (VariableEntry)null;
	private StringEntry continuationOperator = null;
	private String leftName = null;
	private String rightName = null;
	private String eid = null;
	private String rightId = null;
	private String leftId = null;
	private Boolean negative = false;
	private Entry methodOrOperator = null;

	public ChequeEntry(Integer initialIndex,VariableEntry initialLeft,String initialOperator,VariableEntry initialRight){
		left = initialLeft;
		right = initialRight;
		leftName = left.getName();
		rightName = right.getName();
		leftId = Generators.check.buildString(leftName,initialIndex.toString());
		rightId = Generators.check.buildString(rightName,initialIndex.toString());
		eid = "operator";
		methodOrOperator = new StringEntry(initialOperator);
	}
	public ChequeEntry(Integer initialIndex,VariableEntry initialLeft,Entry initialMethod,VariableEntry initialRight){
		left = initialLeft;
		right = initialRight;
		leftName = left.getName();
		rightName = right.getName();
		leftId = Generators.check.buildString(leftName,initialIndex.toString());
		rightId = Generators.check.buildString(rightName,initialIndex.toString());
		eid = "method";
		methodOrOperator = initialMethod;
	}

	public VariableEntry getLeft(){
		return left;
	}
	public VariableEntry getRight(){
		return right;
	}	public StringEntry getContinuationOperator(){
		return continuationOperator;
	}
	public String getLeftName(){
		return leftName;
	}
	public String getRightName(){
		return rightName;
	}
	public String getEid(){
		return eid;
	}
	public String getRightId(){
		return rightId;
	}
	public String getLeftId(){
		return leftId;
	}
	public Boolean getNegative(){
		return negative;
	}	public Entry getMethodOrOperator(){
		return methodOrOperator;
	}
	public void negify(){
		negative = true;
	}
	public void setContination(String op){
		if((op.equals("and "))){
			continuationOperator = new StringEntry("&&");
		}
		else {
			continuationOperator = new StringEntry("||");
		}
	}
	public Entry asIfPart(){
		Entry ret = (Entry)null;
		if((continuationOperator != null)){
			ChequeEntry self = (ChequeEntry)this.getSelf();
			ListEntry part = new ListEntry(continuationOperator,self);
			part.setDelimiter("");
			ret = part;
		}
		else {
			ret = this.getSelf();
		}
		return ret;
	}
	public ListEntry asParameters(){
		String leftType = null;
		String rightType = null;
		if((left.hasType())){
			leftType = left.getType();
		}
		else {
			leftType = left.getDefaultType();
		}
		if((right.hasType())){
			rightType = right.getType();
		}
		else {
			rightType = right.getDefaultType();
		}
		return new ListEntry(new VariableEntry(leftId,leftType),new VariableEntry(rightId,rightType));
	}
	public ListEntry asMethodArguments(){
		return new ListEntry(new StringEntry(leftName),new StringEntry(rightName));
	}
	public ListEntry asAssignment(){
		ListEntry part = new ListEntry();
		part.setDelimiter("");
		part.add(new TabEntry(2,new ListEntry(new ElementEntry(CheckGenerator.constructorAssignmentElement,new ListEntry(new StringEntry(leftId),new StringEntry(leftId))))));
		part.add(new TabEntry(2,new ListEntry(new ElementEntry(CheckGenerator.constructorAssignmentElement,new ListEntry(new StringEntry(rightId),new StringEntry(rightId))))));
		return part;
	}
	public ListEntry asMembers(){
		String leftType = null;
		String rightType = null;
		if((left.hasType())){
			leftType = left.getType();
		}
		else {
			leftType = left.getDefaultType();
		}
		if((right.hasType())){
			rightType = right.getType();
		}
		else {
			rightType = right.getDefaultType();
		}
		ListEntry part = new ListEntry();
		part.setDelimiter("");
		part.add(new TabEntry(1,new ListEntry(new ElementEntry(CheckGenerator.memberElement,new ListEntry(new VariableEntry(leftId,leftType))))));
		part.add(new TabEntry(1,new ListEntry(new ElementEntry(CheckGenerator.memberElement,new ListEntry(new VariableEntry(rightId,rightType))))));
		return part;
	}
	public void get(StringBuilder builder){
		if((negative == false && eid.equals("operator"))){
			new ElementEntry(CheckGenerator.operatorElement,new ListEntry(new StringEntry(leftId),methodOrOperator,new StringEntry(rightId))).get(builder);
		}
		else if((negative == true && eid.equals("operator"))){
			new ElementEntry(CheckGenerator.NEGoperatorElement,new ListEntry(new StringEntry(leftId),methodOrOperator,new StringEntry(rightId))).get(builder);
		}
		else if((negative == false && eid.equals("method"))){
			new ElementEntry(CheckGenerator.methodElement,new ListEntry(new StringEntry(leftId),methodOrOperator,new StringEntry(rightId))).get(builder);
		}
		else if((negative == true && eid.equals("method"))){
			new ElementEntry(CheckGenerator.NEGmethodElement,new ListEntry(new StringEntry(leftId),methodOrOperator,new StringEntry(rightId))).get(builder);
		}
	}
}