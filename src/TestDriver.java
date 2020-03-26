
public class TestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Test these two examples
//		* (unify ’(p x (f x)) ’(p (g x) (f (a))))
//		NIL
//		* (unify ’(p x (f x)) ’(p (g y) (f (g (a)))))
//		((Y A) (X G (A)))
		
		//Problem 1
		Constant function1 = new Constant("p");
		Constant function2 = new Constant("f");
		Constant function3 = new Constant("g");
		Constant constA = new Constant("a");
		Variable x1 = new Variable("x");
		SimpleSentence literal1 = new SimpleSentence(function1, x1, new SimpleSentence(function2, x1));
		Variable x2 = new Variable("x");
		SimpleSentence literal2 = new SimpleSentence(function1, new SimpleSentence(function3, x2), new SimpleSentence(function2, constA));
		
		System.out.println(literal1);
		System.out.println(literal2);
		
		System.out.println(literal1.unify(literal2, new SubstitutionSet()));
		
		//Problem 2
		Variable x3 = new Variable("x");
		SimpleSentence literal3 = new SimpleSentence(function1, x3, new SimpleSentence(function2, x3));
		SimpleSentence literal4 = new SimpleSentence(function1, new SimpleSentence(function3, new Variable("y")), new SimpleSentence(function2, new SimpleSentence(function3, constA)));
		System.out.println(literal3);
		System.out.println(literal4);
		SubstitutionSet ss = new SubstitutionSet();
		System.out.println(ss = literal4.unify(literal3, new SubstitutionSet()));
		System.out.println("Unified expression: " + literal4.replaceVariables(ss));
		
		Variable x4 = new Variable("x");
		SimpleSentence literal5 = new SimpleSentence(function1, x4, new SimpleSentence(function2, x4));
		Variable x5 = new Variable("x");
		SimpleSentence literal6 = new SimpleSentence(function1, x5, new SimpleSentence(function2, x5));
		System.out.println(literal5);
		System.out.println(literal6);
		SubstitutionSet ss2 = new SubstitutionSet();
		System.out.println(ss = literal5.unify(literal6, new SubstitutionSet()));
		System.out.println("Unified expression: " + literal5.replaceVariables(ss));
	}

}
