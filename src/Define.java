import java.io.*;

class Define extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Define(Node t){

	}
    void print(Node t, int n, boolean p) {
    	  Printer.printDefine(t, n, p);
   	}

   	@Override
    public Node eval(Node node, Environment env) {
    	Node id = node.getCdr().getCar();
      Node val = node.getCdr().getCdr().getCar();

      if (id.isSymbol()) {
        env.define(id, val);
      } 
      else {
        Closure func = new Closure(new Cons(node.getCdr().getCar().getCdr(), node.getCdr().getCdr()), env);
        env.define(id.getCar(), func);
      }

      return new StrLit("No values");
    }
}
