import java.util.Scanner;


public class Reservation {
	
	int size;
	int startDate;
	int endDate;
	boolean [][] reservations;
	
	public int checkReservation(int start, int end)
	{
		int flag = -1;
		for (int i = 0; i < size; i++)
		{
			flag = i;
			for (int j = start; j <=end ; j++)
			{
				if (reservations[i][j] == true)
					flag = -1;
			}
			if (flag != -1)
				return flag;
		}
		return flag;
	}
	public void makeReservation(int room, int start, int end)
	{
		for (int j = start; j <=end ; j++)
		{
			reservations[room][j] = true;
		}
	}
	public void printRooms()
	{
		for (int i = 0; i < size; i++)
		{
			for (int j =0;j<365;j++ )
			{
				System.out.print(reservations[i][j]+" ");
			}	
			System.out.println();
		}
	}
	
	public void inputSize(Scanner sc)
	{
        while (true){
        	sc = new Scanner(System.in);
        	System.out.println("\n ***********************");
	        System.out.print("Enter the size of the hotel: ");
	        try {
	            size = sc.nextInt();
	            if (size>1000 || size <1)
	            {
	            	System.out.println("Size has to be between 1 and 1000.");	        
	            }
	            else{
	            	reservations = new boolean [size][365];
	            	break;
	            }
	        }
	        catch (Exception e)
	        {
	        	System.out.println("Size has to be a positive integer.");
	        	sc = new Scanner(System.in);
	        }
        }
	}
	
	public void inputDates(Scanner sc)
	{
		while (true){
			sc = new Scanner(System.in);
        	System.out.println("\n ***********************");
        	System.out.println("Enter startDate, EndDate as a tuple i.e 5,9: ");
        	String str = sc.nextLine();
        	String []dates = str.split(",");
        	try {
        		startDate = Integer.parseInt(dates[0].trim());
        		endDate = Integer.parseInt(dates[1].trim());
        		if (startDate < 0 || startDate > 365)
        		{
        			System.out.println("startDate has to be between 0 to 365 days.");
        		}
        		else if (startDate > endDate)
        		{
        			System.out.println("startDate can't be greater than endDate.");
        		}
        		else if (endDate < 0 || endDate > 365)
        		{
        			System.out.println("endDate has to be between 0 to 365 days.");       			
        		}
        		else
        			break;
        	}
        	catch (Exception e)
        	{
        		System.out.println("The startDate and endDate has to be an integer.");
        		sc = new Scanner(System.in);
        	}
        }
	}

	public static void main(String[] args) {
		String option;
		Scanner sc = new Scanner(System.in);
		Reservation rs = new Reservation();	
		rs.inputSize(sc);
		//rs.printRooms();
		
		do{
			rs.inputDates(sc);
			int room  = rs.checkReservation(rs.startDate, rs.endDate);
			if (room == -1)
			{
				System.out.println("No free room on the provided dates.");
			}
			else
		    {
				rs.makeReservation(room, rs.startDate, rs.endDate);
				System.out.println("Room reserved for ("+rs.startDate+","+rs.endDate+").");
			}
			System.out.println("\n ***********************");
        	System.out.println("Further reservations? Enter y to continue: ");
        	option = sc.nextLine();
		}while (option.equalsIgnoreCase("y"));
	}
}
