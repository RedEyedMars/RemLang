package com.rem.gen.parser;
import com.rem.gen.parser.ParseRule;
import java.util.HashMap;
import java.nio.CharBuffer;
import com.rem.gen.parser.Braces;
import java.util.function.Consumer;
import java.util.List;
import com.rem.gen.parser.ParseError;
import java.util.Stack;
import com.rem.gen.parser.Parser;
import java.util.PrimitiveIterator.OfInt;
import com.rem.gen.parser.ErrorList;
import java.util.Arrays;
import java.util.Map;
public class Braces {
	public static List<Brace> braces = Arrays.asList(new Braces.B11('#','#','\\',true),new Braces.B11('"','"','\\',true),new Braces.B11('(',')','\\',false),new Braces.B11('[',']','\\',false));
	public static Map<Integer, Data> find(CharBuffer chars){
		Stack<Data> data = new Stack<Data>();
		Map<Integer, Data> positions = new HashMap<Integer, Data>();
		OfInt itr = chars.duplicate().chars().iterator();
		if(!itr.hasNext()){
			return positions;
		}
		boolean locked = false;
		boolean ignored = false;
		int position = 0;
		int c1;
		int c2;
		int c3 = itr.next();
		if(!itr.hasNext()){
			return positions;
		}
		c2=c3;
		c3=itr.next();
		int cf1 = c2;
		int cf2 = c3;
		Brace open = braces.stream().filter( B->B.open(cf1,cf2)).findAny().orElse(null);
		if(open!=null){
			data.push(new Data(position,open));
			locked=open.isLock();
		}
		while(itr.hasNext()){
			c1=c2;
			c2=c3;
			c3=itr.next();
			++position;
			if(!ignored&&locked&&data.peek().getBrace().ignore(c2)){
				ignored=true;
			}
			else if(ignored){
				ignored=false;
			}
			else if(!data.isEmpty()&&data.peek().getBrace().close(c2,c3)){
				locked=false;
				data.pop().close(position,positions);
			}
			else if(!locked){
				int cf3 = c1;
				int cf4 = c2;
				int cf5 = c3;
				open=braces.stream().filter( B->!B.ignore(cf3)&&B.open(cf4,cf5)).findAny().orElse(null);
				if(open!=null){
					data.push(new Data(position,open));
					locked=open.isLock();
				}
			}
		}
		return positions;
	}
	public static interface Brace {
		public boolean open(int c1,int c2);
		public boolean close(int c1,int c2);
		public boolean ignore(int c);
		public boolean isLock();
		public int advanceOpen();
		public int advanceClose();
		public String getOpenString();
		public String getCloseString();
	}
	public static class B11 implements Brace {
		protected char ignore = '\\';
		protected char open;
		protected char close;
		protected boolean isLock = false;
		public char getIgnore(){
			return ignore;
		}
		public char getOpen(){
			return open;
		}
		public char getClose(){
			return close;
		}
		public boolean getIsLock(){
			return isLock;
		}
		public B11 (char o,char c,char i,boolean isLock){
			open=o;
			close=c;
			ignore=i;
			this.isLock=isLock;
		}
		public boolean open(int c1,int c2){
			return c1==open;
		}
		public boolean close(int c1,int c2){
			return c1==close;
		}
		public boolean ignore(int c){
			return c==ignore;
		}
		public boolean isLock(){
			return isLock;
		}
		public int advanceOpen(){
			return 1;
		}
		public int advanceClose(){
			return 1;
		}
		public String getOpenString(){
			return new Character(open).toString();
		}
		public String getCloseString(){
			return new Character(close).toString();
		}
	}
	public static class B12 implements Brace {
		protected char ignore = '\\';
		protected char open;
		protected char close1;
		protected char close2;
		protected boolean isLock = false;
		public char getIgnore(){
			return ignore;
		}
		public char getOpen(){
			return open;
		}
		public char getClose1(){
			return close1;
		}
		public char getClose2(){
			return close2;
		}
		public boolean getIsLock(){
			return isLock;
		}
		public B12 (char o,char c1,char c2,char i,boolean isLock){
			open=o;
			close1=c1;
			close2=c2;
			ignore=i;
			this.isLock=isLock;
		}
		public boolean open(int c1,int c2){
			return c1==open;
		}
		public boolean close(int c1,int c2){
			return c1==close1&&c2==close2;
		}
		public boolean ignore(int c){
			return c==ignore;
		}
		public boolean isLock(){
			return isLock;
		}
		public int advanceOpen(){
			return 1;
		}
		public int advanceClose(){
			return 2;
		}
		public String getOpenString(){
			return new Character(open).toString();
		}
		public String getCloseString(){
			return new Character(close1).toString()+new Character(close2).toString();
		}
	}
	public static class B21 implements Brace {
		protected char ignore = '\\';
		protected char open1;
		protected char open2;
		protected char close;
		protected boolean isLock = false;
		public char getIgnore(){
			return ignore;
		}
		public char getOpen1(){
			return open1;
		}
		public char getOpen2(){
			return open2;
		}
		public char getClose(){
			return close;
		}
		public boolean getIsLock(){
			return isLock;
		}
		public B21 (char o1,char o2,char c,char i,boolean isLock){
			open1=o1;
			open2=o2;
			close=c;
			ignore=i;
			this.isLock=isLock;
		}
		public boolean open(int c1,int c2){
			return c1==open1&&c2==open2;
		}
		public boolean close(int c1,int c2){
			return c1==close;
		}
		public boolean ignore(int c){
			return c==ignore;
		}
		public boolean isLock(){
			return isLock;
		}
		public int advanceOpen(){
			return 2;
		}
		public int advanceClose(){
			return 1;
		}
		public String getOpenString(){
			return new Character(open1).toString()+new Character(open2).toString();
		}
		public String getCloseString(){
			return new Character(close).toString();
		}
	}
	public static class B22 implements Brace {
		protected char ignore = '\\';
		protected char open1;
		protected char open2;
		protected char close1;
		protected char close2;
		protected boolean isLock = false;
		public char getIgnore(){
			return ignore;
		}
		public char getOpen1(){
			return open1;
		}
		public char getOpen2(){
			return open2;
		}
		public char getClose1(){
			return close1;
		}
		public char getClose2(){
			return close2;
		}
		public boolean getIsLock(){
			return isLock;
		}
		public B22 (char o1,char o2,char c1,char c2,char i,boolean isLock){
			open1=o1;
			open2=o2;
			close1=c1;
			close2=c2;
			ignore=i;
			this.isLock=isLock;
		}
		public boolean open(int c1,int c2){
			return c1==open1&&c2==open2;
		}
		public boolean close(int c1,int c2){
			return c1==close1&&c2==close2;
		}
		public boolean ignore(int c){
			return c==ignore;
		}
		public boolean isLock(){
			return isLock;
		}
		public int advanceOpen(){
			return 2;
		}
		public int advanceClose(){
			return 2;
		}
		public String getOpenString(){
			return new Character(open1).toString()+new Character(open2).toString();
		}
		public String getCloseString(){
			return new Character(close1).toString()+new Character(close2).toString();
		}
	}
	public static class Rule implements ParseRule {
		protected Brace brace;
		protected ParseRule contender;
		public Brace getBrace(){
			return brace;
		}
		public ParseRule getContender(){
			return contender;
		}
		public Rule (Integer braceIndex,ParseRule contender){
			this.brace=Braces.braces.get(braceIndex);
			this.contender=contender;
		}
		public int parse(Consumer<Token> parent,ErrorList errors,CharBuffer chars,Map<Integer, Braces.Data> braceData){
			Data data = braceData.get(chars.position());
			if(data!=null&&brace==data.getBrace()){
				CharBuffer buffer = chars.duplicate();
				buffer.position(buffer.position()+data.getBrace().advanceOpen());
				Parser.__WHITESPACE__.parse( T->{						;
 },new ErrorList.Dummy(),buffer,braceData);
				buffer.limit(data.getClose());
				int result = contender.parse(parent,errors,buffer,braceData);
				if(result==Parser.PASS||buffer.position()!=data.getClose()){
					CharBuffer dupe = buffer.duplicate();
					dupe.limit(dupe.position()+brace.advanceClose());
					errors.append(new ParseError.WrongString(null,chars.position(),dupe,brace.getCloseString()));
				}
				chars.position(data.getClose()+brace.advanceClose());
				Parser.__WHITESPACE__.parse( T->{						;
 },new ErrorList.Dummy(),buffer,braceData);
				return Parser.ERROR_ON_END;
			}
			else{
				CharBuffer dupe = chars.duplicate();
				dupe.limit(dupe.position()+brace.advanceOpen());
				errors.adjust(new ParseError.WrongString(null,chars.position(),dupe,brace.getOpenString()));
				return Parser.FAIL;
			}
		}
	}
	public static class Data {
		protected Integer open;
		protected Integer close;
		protected Brace brace;
		public Integer getOpen(){
			return open;
		}
		public Integer getClose(){
			return close;
		}
		public Brace getBrace(){
			return brace;
		}
		public Data (Integer start,Brace brace){
			this.open=start;
			this.brace=brace;
		}
		public void close(Integer close,Map<Integer, Data> positions){
			this.close=close;
			positions.put(open,this);
		}
	}
}
