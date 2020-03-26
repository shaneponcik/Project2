import java.util.ArrayList;
import java.util.HashMap;

public class ResolutionFunctions {
	public static ArrayList<Clause> getResolvents(Clause clause1, Clause clause2)
	{
		ArrayList<Clause> resolvents = new ArrayList<Clause>();
		
		for(Unifiable p1: clause1.positiveLiterals)
		{
			for(Unifiable n2: clause2.negativeLiterals)
			{
				if(ifUnifiable(p1,n2))
					resolvents.add(getResolvent(clause1, clause2, p1, n2));
			}
		}
		
		for(Unifiable p2: clause2.positiveLiterals)
		{
			for(Unifiable n1: clause1.negativeLiterals)
			{
				if(ResolutionFunctions.ifUnifiable(p2,n1))
					resolvents.add(getResolvent(clause2, clause1, p2, n1));
			}
		}
		
		return resolvents;
	}
	
	public static boolean ifUnifiable(Unifiable positiveLiteral, Unifiable negativeLiteral)
	{
		if(positiveLiteral.unify(negativeLiteral, new SubstitutionSet()) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Clause getResolvent(Clause clause1, Clause clause2, Unifiable positiveLiteral, Unifiable negativeLiteral)
	{	
		Clause clause1Copy = clause1.shallowCopy();
		Clause clause2Copy = clause2.shallowCopy();
		
		//Have sub set to apply
		SubstitutionSet ss = positiveLiteral.unify(negativeLiteral, new SubstitutionSet());
		
		//Remove the two literals from their respective clauses
		//Positive
		for(int i = 0; i < clause1Copy.positiveLiterals.size(); ++i)
		{
			if(clause1Copy.positiveLiterals.get(i) == positiveLiteral)
			{
				clause1Copy.positiveLiterals.remove(i);
			}
		}
		
		//Negative
		for(int i = 0; i < clause2Copy.negativeLiterals.size(); ++i)
		{
			if(clause2Copy.negativeLiterals.get(i) == negativeLiteral)
			{
				clause2Copy.negativeLiterals.remove(i);
			}
		}
		
		//Apply substitutions to all the literals remaining
		
		for(int i = 0; i < clause1Copy.positiveLiterals.size(); ++i)
		{
			clause1Copy.positiveLiterals.set(i, (Unifiable) clause1Copy.positiveLiterals.get(i).replaceVariables(ss));
		}
		
		for(int i = 0; i < clause1Copy.negativeLiterals.size(); ++i)
		{
			clause1Copy.negativeLiterals.set(i, (Unifiable) clause1Copy.negativeLiterals.get(i).replaceVariables(ss));
		}
		
		for(int i = 0; i < clause2Copy.positiveLiterals.size(); ++i)
		{
			clause2Copy.positiveLiterals.set(i, (Unifiable) clause2Copy.positiveLiterals.get(i).replaceVariables(ss));
		}
		
		for(int i = 0; i < clause2Copy.negativeLiterals.size(); ++i)
		{
			clause2Copy.negativeLiterals.set(i, (Unifiable) clause2Copy.negativeLiterals.get(i).replaceVariables(ss));
		}
		
		//Combine the two clauses now
		Clause resolvent = Clause.appendTwoClauses(clause1Copy, clause2Copy);

		resolvent.propogateNewVariables();
		
		return resolvent;
	}
}
