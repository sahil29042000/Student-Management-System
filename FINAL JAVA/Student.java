class Student
{
	private int rno;
	private String name;
	private int marks;
	Student()
	{
		
		
	}
	Student(int rno,String name,int marks)
	{
		this.rno=rno;
		this.name=name;
		this.marks=marks;
	}
	
	public void setRno(int rno)
	{
		this.rno=rno;
		
		
	}
	public int getRno()
	{
		return rno;
		
		
	}
	
	public void setName(String name)
	{
		
		this.name=name;
		
	}
	public String getName()
	{
		return name;
	}
	public void setMarks(int marks)
	{
		this.marks=marks;
	}
	public int getMarks()
	{
		return marks;
	}
	
}