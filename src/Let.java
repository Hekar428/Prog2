import java.io.*;

class Let extends Special {
 
    // TODO: Add any fields needed.
	
 
    // TODO: Add an appropriate constructor.
	public Let(Node t){}
    void print(Node t, int n, boolean p) {
    	  Printer.printLet(t, n, p);
    }

    public Node createEnv(Node node, Environment env) {
        Environment tmpEnv = new Environment(env);
        Node nodeCar = node.getCar();
        
        if (node.isNull())
            return tmpEnv;
        else
        {
            Node firstElm = nodeCar.getCar();
            Node nextVal = nodeCar.getCdr().getCar();

            nextVal = nextVal.eval(env);
            tmpEnv.define(firstElm, nextVal);
            node = node.getCdr();
            nextVal = node.getCar();
        }

        return tmpEnv;

    }

    public Node eval(Node node, Environment env) {
    	Node foo = node.getCar().getCdr();
        Node oof = node.getCdr().getCdr().getCar();
        Environment thisEnv = new Environment(env);

        foo = createEnv(foo, thisEnv);
        return oof.eval(thisEnv);
    }
}
