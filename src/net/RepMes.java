package src.net;

public class RepMes implements Mess {

	private String message= "This is the default message! ";
	private int times = 3;
	
	public RepMes()
	{
	
	}
	
	public RepMes(String m, int t)
	{
		message = m;
		times = t;
	}
	
	public String state()
	{
		String m = "";
		for(int i = 0; i < times; i++)
			m += message;
		return m;
	}
	
	public void newMessage(String r)
	{
		message = r;
	}
}
