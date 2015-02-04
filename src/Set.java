import java.io.*;

class Set extends Special {
 
    // TODO: Add any fields needed.

 
    // TODO: Add an appropriate constructor.
	public Set(Node t){

	}
	
    void print(Node t, int n, boolean p) {
        Printer.printSet(t, n, p);
    }
    
    public Node eval(Node t, Environment env) {
    	
        
        Node ev = t.getCdr().getCdr().getCar().eval(env);
        
        if(!(env.lookup(t.getCdr().getCar()).isNull())){
            env.assign(t.getCdr().getCar(), ev);
            return new StrLit("#{Unspecific}");
        }
        else {
            return new StrLit("Error in Set Class");
        }
               
                
    }
}
