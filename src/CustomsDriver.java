
public class CustomsDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClauseContainer cc = CustomsDriver.getCustomsContainer();
		cc.printClauses();
		
		boolean isSolvable = TwoPointer.TwoPointer(cc, 7);
		
		System.out.println(isSolvable);
		//clauseContainer.printClauses();
		
		if(isSolvable)
		{
			cc.getReducedContainer().printClauses();
		}
	}
	
	public static ClauseContainer getCustomsContainer()
	{
//		(defvar *customs* '( (1 ( (v x) (s x (f x)) )
//			      ( (e x) ) )
//			   (2 ( (v y) (c (f y)) )
//			      ( (e y) ) )
//			   (3 ( (e (a)) ) nil )
//			   (4 ( (d (a)) ) nil )
//			   (5 ( (d z) )   ( (s (a) z) ) )
//			   (6 nil   ( (d w) (v w) ) )
//			   (7 nil  ( (d r) (c r) ) )   )    ) ;; goal clause begins here
		
		ClauseContainer cc = new ClauseContainer();
		
		//Variables
		Variable x;
		Variable y;
		Variable z;
		Variable w;
		Variable r;
		
		//Constants
		Constant a = new Constant("a");
		
		//Functions
		Constant v = new Constant("v");
		Constant s = new Constant("s");
		Constant f = new Constant("f");
		Constant e = new Constant("e");
		Constant d = new Constant("d");
		Constant c = new Constant("c");
		
//		(defvar *customs* '( (1 ( (v x) (s x (f x)) ) ( (e x) ) )
		Clause c1 = new Clause();
		x = new Variable("x");
		c1.positiveLiterals.add(new SimpleSentence(v, x));
		c1.positiveLiterals.add(new SimpleSentence(s, x, new SimpleSentence(f,x)));
		
		c1.negativeLiterals.add(new SimpleSentence(e,x));
		
		cc.add(c1);
		
//	   (2 ( (v y) (c (f y)) ) ( (e y) ) )
		Clause c2 = new Clause();
		
		y = new Variable("y");
		c2.positiveLiterals.add(new SimpleSentence(v, y));
		c2.positiveLiterals.add(new SimpleSentence(c, new SimpleSentence(f, y)));
		
		c2.negativeLiterals.add(new SimpleSentence(e, y));
		
		cc.add(c2);
//	   (3 ( (e (a)) ) nil )
		
		Clause c3 = new Clause();
		c3.positiveLiterals.add(new SimpleSentence(e, a));
		
		cc.add(c3);
//	   (4 ( (d (a)) ) nil )
		Clause c4 = new Clause();
		c4.positiveLiterals.add(new SimpleSentence(d, a));
		
		
		cc.add(c4);
//	   (5 ( (d z) )   ( (s (a) z) ) )
		Clause c5 = new Clause();
		z = new Variable("z");
		c5.positiveLiterals.add(new SimpleSentence(d, z));
		
		c5.negativeLiterals.add(new SimpleSentence(s, a, z));
		
		cc.add(c5);
//	   (6 nil   ( (d w) (v w) ) )
		Clause c6 = new Clause();
		w = new Variable("w");
		
		c6.negativeLiterals.add(new SimpleSentence(d, w));
		c6.negativeLiterals.add(new SimpleSentence(v, w));
		
		cc.add(c6);
//	   (7 nil  ( (d r) (c r) ) )   )    ) ;; goal clause begins here
		Clause c7 = new Clause();
		r = new Variable("r");
		
		c7.negativeLiterals.add(new SimpleSentence(d, r));
		c7.negativeLiterals.add(new SimpleSentence(c, r));
		
		cc.add(c7);
		
		return cc;
	}

}
