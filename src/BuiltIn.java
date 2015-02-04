// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

class BuiltIn extends Node {
    private Node symbol;

    public BuiltIn(Node s)		{ symbol = s; }
    
    public Node getSymbol()		{ return symbol; }

    // TODO: The method isProcedure() should be defined in
    // class Node to return false.
    public boolean isProcedure()	{ return true; }

    public void print(int n) {
	// there got to be a more efficient way to print n spaces
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println("#{Built-in Procedure");
	symbol.print(n+3);
	for (int i = 0; i < n; i++)
	    System.out.print(' ');
	System.out.println('}');
    }

    // TODO: The method apply() should be defined in class Node
    // to report an error.  It should be overwritten only in classes
    // BuiltIn and Closure.
    public Node apply (Node args) {
	   String var = args.getCar().getName();
       Node ar1 = args.getCdr().getCar();
       if (ar1 == null || ar1.isNull()) {
            ar1 = new Nil();
       }

        Node ar2 = args.getCdr().getCdr();
        if (ar2 == null || ar2.isNull()) {
            ar2 = new Nil();
        }
        else
            ar2 = ar2.getCar();
        
        if(var.equals("car")){
            if(ar1.isNull()){
                return ar1;
            }
            return ar1.getCar();
        }
        
        else if (var.equals("cdr")){
            if(ar1 == null){
                return ar1;
            }
            return ar1.getCdr();
        }
        
        else if(var.equals("cons")){
            return new Cons(ar1,ar2);
        }
        
        else if(var.equals("set-car!")){
            ar1.setCar(ar2);
            return ar1;
        }
        
        else if(var.equals("set-cdr!")){
            ar1.setCdr(ar2);
            return ar1;
        }
        
        else if(var.equals("number?")){
            return new BooleanLit(ar1.isNumber());
        }
        
        else if(var.equals("symbol?")){
            return new BooleanLit(ar1.isSymbol());
        }
        
        
        else if(var.equals("null?")){
            return new BooleanLit(ar1.isNull());
        }
        
        else if(var.equals("pair?")){
            return new BooleanLit(ar1.isPair());
        } 
        
        else if (var.equals("eq?")) {
            
            return new BooleanLit(ar1 == ar2);
        }
        else if(var.equals("procedure?")){
            return new BooleanLit(ar1.isProcedure());
        }
        
        else if(var.equals("display")){
            return ar1;
        }
        
        else if (var.equals("eval")){
            return ar1;
        }
        
        else if (var.equals("apply")){
            return ar1.apply(ar2);
        }
        
        else if (var.equals("b+")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new IntLit(foo + oof);
            }
            else {
                System.out.println("Error in the b+ method");
                return new StrLit("");
            }
        }
        
        else if (var.equals("b-")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new IntLit(foo - oof);
            }
            else {
                System.out.println("Error in the b- method");
                return new StrLit("");
            }
        }
        
        else if (var.equals("b*")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new IntLit(foo * oof);
            }
            else {
                System.out.println("Error in the b* method");
                return new StrLit("");
            }
        }
        
        else if (var.equals("b/")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new IntLit(foo / oof);
            }
            else {
                System.out.println("Error in the b/ method");
                return new StrLit("");
            }
        }
        
        else if (var.equals("b=")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new BooleanLit(foo == oof);
            }
            else {
                System.out.println("Error in the b= method");
            }
        }
        
        else if (var.equals("b<")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new BooleanLit(foo < oof);
            }
            else {
                System.out.println("Error in the b< method");
            }
        }
        
        else if (var.equals("b>")){
            if (ar1.isNumber() && ar2.isNumber()) {
                int foo = ((IntLit)ar1).getValue();
                int oof = ((IntLit)ar2).getValue();
                return new BooleanLit(foo > oof);
            }
            else {
                System.out.println("Error in the b> method");
            }
        }
        
        else if (var.equals("apply")) {
            return ar1.apply(ar2);
        }

        else if (var.equals("exit")) {
            System.exit(0);
        }

        else {
            System.out.println("Error in BuiltIn");
        }

        return new StrLit("");
    } 

    public Node eval(Node node, Environment env) {
        return this;
    }
}

