import java.io.*;

class Cond extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Cond(Node t) {

	}
    void print(Node t, int n, boolean p) { 
    	Printer.printCond(t, n, p);
    }

    @Override
    public Node eval(Node node, Environment env) {
    	Node foo = node.getCdr();

        while ((!(foo.getCar().getCar().eval(env).getBoolean()) && (!foo.isNull()))) {
            foo = foo.getCdr();
        }

        if (foo.isNull())
            return null;
        else
            return foo.getCar().getCdr().getCar().eval(env);
    }
}
