import java.util.Scanner;

public class Main{
  public static void main(String[] args)
  {
	  Main m=new Main();
	  Scanner sc=new Scanner(System.in);
      long x=sc.nextLong();
	  String r=m.getResult(x);
	  System.out.println(r);
  }
public String getResult(long ulDataInput)
    {
    String r="";
    long n=ulDataInput;
    for(long i=2;i<=n;i++)
        {
        if(isZhi(i))
            {
            while(n%i==0)
                {
                n=n/i;
                r=r+String.valueOf(i)+" ";
            }
        }
        
    }
    return r;
}

public boolean isZhi(long x)
    {
    boolean flag=true;
    for(long i=2;i<x;i++)
        {
        if(x%2==0)
            flag=false;
    }
    return flag;
}
}
