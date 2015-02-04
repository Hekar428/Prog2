import java.io.*;

class If extends Special {
 
    // TODO: Add any fields needed.
	
 
    // TODO: Add an appropriate constructor.
	public If(Node t){	}
    void print(Node t, int n, boolean p) {
        Printer.printIf(t, n, p);
    }
    
    public Node eval(Node t, Environment env) { 
        if(t.getCdr().getCar().eval(env).getBoolean()==true){
            return t.getCdr().getCdr().getCar().eval(env);
        }
        else{
            return t.getCdr().getCdr().getCdr().getCar().eval(env);
        }
    }
}
