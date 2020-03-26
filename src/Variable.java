public class Variable implements Unifiable
{
	private String printName = null;
	private static int nextId = 1;
	private int id;
	
	public Variable()
	{
		this.id = nextId++;
	}
	
	public Variable(String printName)
	{
		this();
		this.printName = printName;
	}
	
	public Variable(Variable v)
	{
		this.id = nextId++;
		this.printName = v.printName;
	}
	
	public String toString()
	{
		if (printName != null)
		return printName + "_" + id;
		return "V" + id;
	}
	
	public SubstitutionSet unify(Unifiable p, SubstitutionSet s)
	{
		if (this == p) return s;
		
		if(s.isBound(this))
			return s.getBinding(this).unify(p, s);
		
		SubstitutionSet sNew = new SubstitutionSet(s);
		sNew.add(this, p);
		
		return sNew;
	}
	
	public PCExpression replaceVariables(SubstitutionSet s)
	{
		if(s.isBound(this))
			return s.getBinding(this).replaceVariables(s);
		else
			return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((printName == null) ? 0 : printName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (id != other.id)
			return false;
		if (printName == null) {
			if (other.printName != null)
				return false;
		} else if (!printName.equals(other.printName))
			return false;
		return true;
	}
	
	
	//unify and other functions to be defined shortly.
}