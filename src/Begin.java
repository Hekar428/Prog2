import java.io.*;

class Begin extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Begin(Node t){
		
	}
    void print(Node t, int n, boolean p) {
        Printer.printBegin(t, n, p);

    }
    
    public Node eval(Node t, Environment env) { 
    	Node n = t.getCdr();
    	Node x = n.getCar().eval(env);
    	n = n.getCdr();

        System.out.println("Begin fired.");

    	while (!n.isNull()) {
    		x = n.getCar().eval(env);
    		n = n.getCdr();
    	}

    	return x;
    }
}
