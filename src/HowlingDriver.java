import java.util.ArrayList;

public class HowlingDriver {

	public static void main(String[] args) {
//			(defvar *howling*   '((1 ((howl z)) ((hound z)))
//        (2 nil ((have x y) (cat y) (have x z) (mouse z)))
//        (3 nil ((ls w) (have w v) (howl v)))
//        (4 ((have (john) (a))) nil)
//        (5 ((cat (a)) (hound (a))) nil)
//        (6 ((mouse (b))) nil)    ;; goal clause begins here
//        (7 ((ls (john))) nil)
//        (8 ((have (john) (b))) nil)))
		

		
		ClauseContainer clauseContainer = HowlingDriver.getHowlingClauseContainer();
		//System.out.println(ResolutionFunctions.getResolvents(clauseContainer.get(1), clauseContainer.get(3)));
		
		boolean isSolvable = TwoPointer.TwoPointer(clauseContainer, 6);
		
		System.out.println(isSolvable);
		//clauseContainer.printClauses();
		
		if(isSolvable)
		{
			clauseContainer.getReducedContainer().printClauses();
		}
		
		//clauseContainer.printClauses();
	}
	
	public static ClauseContainer getHowlingClauseContainer()
	{
		//Functions
		Constant howl = new Constant("howl");
		Constant hound = new Constant("hound");
		Constant have = new Constant("have");
		Constant cat = new Constant("cat");
		Constant mouse = new Constant("mouse");
		Constant ls = new Constant("ls");
		
		//Constants
		Constant john = new Constant("john");
		Constant a = new Constant("a");
		Constant b = new Constant("b");
		
		//Variables
		Variable z = new Variable("z");
		Variable x = new Variable("x");
		Variable y = new Variable("y");
		Variable w = new Variable("w");
		Variable v = new Variable("v");
		
		
		ClauseContainer clauseContainer = new ClauseContainer();
		
		//1
		z = new Variable("z");
		Clause clause1 = new Clause();
		clause1.positiveLiterals.add(new SimpleSentence(howl, z));
		clause1.negativeLiterals.add(new SimpleSentence(hound, z));
		clauseContainer.add(clause1);
		
		//2 (2 nil ((have x y) (cat y) (have x z) (mouse z)))	
		x = new Variable("x");
		y = new Variable("y");
		z = new Variable("z");
		Clause clause2 = new Clause();
		clause2.negativeLiterals.add(new SimpleSentence(have, x, y));
		clause2.negativeLiterals.add(new SimpleSentence(cat, y));
		clause2.negativeLiterals.add(new SimpleSentence(have, x, z));
		clause2.negativeLiterals.add(new SimpleSentence(mouse, z));
		clauseContainer.add(clause2);
		
		//(3 nil ((ls w) (have w v) (howl v)))
		w = new Variable("w");
		v = new Variable("v");
		Clause clause3 = new Clause();
		clause3.negativeLiterals.add(new SimpleSentence(ls, w));
		clause3.negativeLiterals.add(new SimpleSentence(have, w, v));
		clause3.negativeLiterals.add(new SimpleSentence(howl, v));
		clauseContainer.add(clause3);
		
		////        (4 ((have (john) (a))) nil)
		Clause clause4 = new Clause();
		clause4.positiveLiterals.add(new SimpleSentence(have, john, a));
		clauseContainer.add(clause4);
		
		////        (5 ((cat (a)) (hound (a))) nil)
		Clause clause5 = new Clause();
		clause5.positiveLiterals.add(new SimpleSentence(cat, a));
		clause5.positiveLiterals.add(new SimpleSentence(hound, a));
		clauseContainer.add(clause5);
		
		////        (6 ((mouse (b))) nil)    ;; goal clause begins here
		Clause clause6 = new Clause();
		clause6.positiveLiterals.add(new SimpleSentence(mouse, b));
		clauseContainer.add(clause6);
		
//      (7 ((ls (john))) nil)
		Clause clause7 = new Clause();
		clause7.positiveLiterals.add(new SimpleSentence(ls, john));
		clauseContainer.add(clause7);
		
//      (8 ((have (john) (b))) nil)))
		Clause clause8 = new Clause();
		clause8.positiveLiterals.add(new SimpleSentence(have, john, b));
		clauseContainer.add(clause8);
		
		return clauseContainer;
	}
}
