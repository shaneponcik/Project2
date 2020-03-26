import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Clause implements Comparable{
	public int clauseNumber;
	public ArrayList<Unifiable> positiveLiterals;
	public ArrayList<Unifiable> negativeLiterals;
	public Integer parent1;
	public Integer parent2;
	
	public Clause()
	{
		this.clauseNumber = -1;
		this.positiveLiterals = new ArrayList<Unifiable>();
		this.negativeLiterals = new ArrayList<Unifiable>();
		parent1 = 0;
		parent2 = 0;
	}
	
	public Clause shallowCopy()
	{
		Clause nClause = new Clause();
		nClause.clauseNumber = this.clauseNumber;
		nClause.positiveLiterals = (ArrayList<Unifiable>) this.positiveLiterals.clone();
		nClause.negativeLiterals = (ArrayList<Unifiable>) this.negativeLiterals.clone();
		nClause.parent1 = this.parent1.intValue();
		nClause.parent2 = this.parent2.intValue();
		
		return nClause;
	}
	
	public boolean isFalse()
	{
		if(this.negativeLiterals.size() == 0 && this.positiveLiterals.size() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void propogateNewVariables()
	{
		//Need to get all the variables in both literal sets
		//With the set of variables, make a new set to convert them to ->
		HashMap<Unifiable, Unifiable> swapMap = new HashMap<Unifiable, Unifiable>();
		
		for(int i = 0; i < this.positiveLiterals.size(); ++i)
		{
			UtilityFunctions.propogateNewVariablesInUnifiable(this.positiveLiterals.get(i), swapMap);
		}
		
		for(int i = 0; i < this.negativeLiterals.size(); ++i)
		{
			UtilityFunctions.propogateNewVariablesInUnifiable(this.negativeLiterals.get(i), swapMap);
		}
		
		//System.out.println(swapMap);
		
		SubstitutionSet ss = new SubstitutionSet();
		for(Unifiable old: swapMap.keySet())
		{
			ss.add((Variable) old, swapMap.get(old));
		}
		
		for(int i = 0; i < this.positiveLiterals.size(); ++i)
		{
			this.positiveLiterals.set(i, (Unifiable) this.positiveLiterals.get(i).replaceVariables(ss));
		}
		
		for(int i = 0; i < this.negativeLiterals.size(); ++i)
		{
			this.negativeLiterals.set(i, (Unifiable) this.negativeLiterals.get(i).replaceVariables(ss));
		}
		
	}

	@Override
	public String toString() {
		return "Clause [clauseNumber=" + clauseNumber + ", positiveLiterals=" + positiveLiterals + ", negativeLiterals="
				+ negativeLiterals + ", parent1=" + parent1 + ", parent2=" + parent2 + "]";
	}

	
	public static Clause appendTwoClauses(Clause clause1, Clause clause2)
	{
		Clause nClause = new Clause();
		nClause.parent1 = clause1.clauseNumber;
		nClause.parent2 = clause2.clauseNumber;
		
		nClause.positiveLiterals = Clause.appendLiterals(clause1.positiveLiterals, clause2.positiveLiterals);
		nClause.negativeLiterals = Clause.appendLiterals(clause1.negativeLiterals, clause2.negativeLiterals);
		
		return nClause;
	}
	
	public static ArrayList<Unifiable> appendLiterals(ArrayList<Unifiable> al1, ArrayList<Unifiable> al2)
	{
		HashSet<Unifiable> hs = new HashSet<Unifiable>();
		
		for(Unifiable u: al1)
		{
			hs.add(u);
		}
		for(Unifiable u: al2)
		{
			hs.add(u);
		}
		
		ArrayList<Unifiable> result = new ArrayList<Unifiable>();
		for(Unifiable u: hs)
		{
			result.add(u);
		}
		return result;
	}

	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		Clause c = (Clause) other;
		return this.clauseNumber - c.clauseNumber;
	}
	
} 
