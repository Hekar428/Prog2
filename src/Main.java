// Main.java -- the main program

import java.io.*;
import java.util.*;

public class Main {
    // Array of token names used for debugging the scanner
    private static final String TokenName[] = {
	"QUOTE",			// '
	"LPAREN",			// (
	"RPAREN",			// )
	"DOT",				// .
	"TRUE",				// #t
	"FALSE",			// #f
	"INT",				// integer constant
	"STRING",			// string constant
	"IDENT"				// identifier
    };

    public static void main (String argv[]) {
    	
	// create scanner that reads from standard input
	Scanner scanner = new Scanner(System.in);


	if (argv.length > 1) {
	    System.err.println("Usage: java Main [-d]");
	    System.exit(1);
	}
	
	// if commandline option -d is provided, debug the scanner
	if (argv.length == 1 && argv[0].equals("-d")) {
	    // debug scanner
	    Token tok = scanner.getNextToken();
	    while (tok != null) {
		int tt = tok.getType();
		System.out.print(TokenName[tt]);
		if (tt == Token.INT)
		    System.out.println(", intVal = " + tok.getIntVal());
		else if (tt == Token.STRING)
		    System.out.println(", strVal = " + tok.getStrVal());
		else if (tt == Token.IDENT)
		    System.out.println(", name = " + tok.getName());
		else
		    System.out.println();

		tok = scanner.getNextToken();
	    }
	    System.exit(0);
	}
	
	// Create parser
	Parser parser = new Parser(scanner);
	Node root;
	
	Environment env = new Environment();
	Ident id;

	id = new Ident("set-car!");
    env.define(id, new BuiltIn(id));
    id = new Ident("set-cdr!");
    env.define(id, new BuiltIn(id));
    id = new Ident("null?");
    env.define(id, new BuiltIn(id));
    id = new Ident("pair?");
    env.define(id, new BuiltIn(id));
    id = new Ident("eq?");
    env.define(id, new BuiltIn(id));
    id = new Ident("procedure?");
    env.define(id, new BuiltIn(id));
    id = new Ident("read");
    env.define(id, new BuiltIn(id));
    id = new Ident("write");
    env.define(id, new BuiltIn(id));
    id = new Ident("display");
    env.define(id, new BuiltIn(id));
    id = new Ident("newline");
    env.define(id, new BuiltIn(id));
    id = new Ident("eval");
    env.define(id, new BuiltIn(id));
    id = new Ident("apply");
    env.define(id, new BuiltIn(id));
    id = new Ident("interaction-environment");
    env.define(id, new BuiltIn(id));
    id = new Ident("b+");
    env.define(id, new BuiltIn(id));
    id = new Ident("car");
    env.define(id, new BuiltIn(id));
    id = new Ident("symbol?");
    env.define(id, new BuiltIn(id));
    id = new Ident("number?");
    env.define(id, new BuiltIn(id));
    id = new Ident("b/");
    env.define(id, new BuiltIn(id));
    id = new Ident("b-");
    env.define(id, new BuiltIn(id));
    id = new Ident("b*");
    env.define(id, new BuiltIn(id));
    id = new Ident("b=");
    env.define(id, new BuiltIn(id));
    id = new Ident("b<");
    env.define(id, new BuiltIn(id));
    id = new Ident("cdr");
    env.define(id, new BuiltIn(id));
    id = new Ident("cons");
    env.define(id, new BuiltIn(id));

    Environment env_new = new Environment(env);
    BuiltIn foo = new BuiltIn(env_new);

    System.out.print("SchemeCB> ");
    root = parser.parseExp();
    while (root != null) {
        try {
    	foo.apply(root).eval(env_new).print(0);
    	System.out.print("> ");
        }
        catch (NullPointerException e) {
            System.out.print("> ");
        }
    	root = parser.parseExp();
    }
    System.exit(0);
    }
}
