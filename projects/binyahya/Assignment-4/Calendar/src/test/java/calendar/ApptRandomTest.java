package calendar;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for Appt class.
 */

public class ApptRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

	/**
	 * Return a randomly selected method to be tests !.
	 */
    public static String RandomSelectMethod(Random random){
    	// The list of the of methods to be tested in the Appt class
    	String[] methodArray = new String[] {"setTitle","setRecurrence","setRecurrenceNull","setValid", "isOn"};
        
    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
        }
	/**
	 * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
	 */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur 
        }	
	/**
	 * Return a randomly selected appointments to recur forever or Never recur  !.
	 */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER

    	int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur 
        }	
   /**
     * Generate Random Tests that tests Appt Class.
     */
    

    @Test
    public void radnomtest()  throws Throwable  {
    	long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start testing...");
		
		try { 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed = System.currentTimeMillis(); //10
				// System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				
				int startHour = ValuesGenerator.getRandomIntBetween(random, 0, 23);
				int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 59);
				int startDay = ValuesGenerator.getRandomIntBetween(random, 1, 31);
				int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				int startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
				String title = "Birthday Party";
				String description = "This is my birthday party.";
				String emailAddress = "xyz@gmail.com";
				
				//Construct a new Appointment object with the initial data	 
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
						startYear, title, description, emailAddress);
				 
				if(!appt.getValid()) continue;
				
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = ApptRandomTest.RandomSelectMethod(random);
					if (methodName.equals("setTitle")) {
						String newTitle=(String) ValuesGenerator.getString(random);
						appt.setTitle(newTitle);
					} else if (methodName.equals("setRecurrence")) {
						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						int recur=ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						int size = recurDays.length;
						assertEquals(size, appt.getRecurDays().length);
						assertEquals(recurIncrement, appt.getRecurIncrement());
						assertEquals(recurNumber, appt.getRecurNumber());
						assertEquals(recur, appt.getRecurBy());
					} else if (methodName.equals("setRecurrenceNull")) { 
						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays= null;
						int recur=ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						assertEquals(0, appt.getRecurDays().length);
						assertEquals(recurIncrement, appt.getRecurIncrement());
						assertEquals(recurNumber, appt.getRecurNumber());
						assertEquals(recur, appt.getRecurBy());
					} else if (methodName.equals("setValid")) {
						// Save Original Appt Values
						int oriMinute = appt.getStartMinute();
						int oriHour = appt.getStartHour();
						int oriDay = appt.getStartDay();
						int oriMonth = appt.getStartMonth();
						int oriYear = appt.getStartYear();
						String oriTitle = appt.getTitle();
						String oriDescription = appt.getDescription();
						String oriEmail = appt.getEmailAddress();
						
						// setValid Test
						appt = genAppt(random);
						
						try {
							appt.setValid();
						} catch (ArrayIndexOutOfBoundsException e) {
							assertFalse(false); // Clearly invalid Appt
							appt = new Appt(oriHour, oriMinute, oriDay, oriMonth, 
									oriYear, oriTitle, oriDescription, oriEmail);
							continue;
						}
						boolean validHrs, validMin, validDay, validMonth, validYear;
						validHrs = (appt.getStartHour() <= 23) && (appt.getStartHour() >= 0);
						validMin = (appt.getStartMinute() <= 59) && (appt.getStartMinute() >= 0);
						validDay = (appt.getStartDay() >= 1) && (appt.getStartDay() <= 31);
						validMonth = (appt.getStartMonth() >= 1) && (appt.getStartMonth() <= 11);
						validYear = appt.getStartYear() > 0;
			
						/*
						System.out.println("Valid Hours: " + validHrs + " Hrs: " + appt.getStartHour());
						System.out.println("Valid Min: " + validMin + " Min: " + appt.getStartMinute());
						System.out.println("Valid Day: " + validDay + " Day: " + appt.getStartDay());
						System.out.println("Valid Month: " + validMonth + " Month: " + appt.getStartMonth());
						System.out.println("Valid Year: " + validYear + " Year: " + appt.getStartYear());
						System.out.println("Valid Appt: " + appt.getValid());
						System.out.println("");
						*/

						boolean validShortMonth30 = appt.getStartMonth() == 3 ||
								appt.getStartMonth() == 5 ||
								appt.getStartMonth() == 8 ||
								appt.getStartMonth() == 10;
						
						if ( (validShortMonth30 && (appt.getStartDay() > 30)) ||
								(appt.getStartMonth() == 1 && appt.getStartDay() > 28 && !CalendarUtil.IsLeapYear(appt.getStartYear())) ||
								(appt.getStartMonth() == 1 && appt.getStartDay() > 29 && CalendarUtil.IsLeapYear(appt.getStartYear()))) {
							assertFalse(appt.getValid());
							appt = new Appt(oriHour, oriMinute, oriDay, oriMonth, 
									oriYear, oriTitle, oriDescription, oriEmail);
							continue;
						}
			
						if (validHrs && validMin && validDay && validMonth && validYear) {
							assertTrue(appt.getValid());
						} else {
							assertFalse(appt.getValid());
						}
						
					} else if (methodName.equals("isOn")) {
						
						appt = genValidAppt(random);
						int apptDay = appt.getStartDay();
						int apptMonth = appt.getStartMonth();
						int apptYear = appt.getStartYear();
						assertTrue(appt.isOn(apptDay++, apptMonth++, apptYear++));
						assertFalse(appt.isOn(apptDay--, apptMonth--, apptYear--));
						assertFalse(appt.isOn(++apptDay, apptMonth, apptYear));
						--apptDay;
						assertFalse(appt.isOn(apptDay, ++apptMonth, apptYear));
						--apptMonth;
						assertFalse(appt.isOn(apptDay, apptMonth, ++apptYear));
					}
					
				}
			
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0)
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			}
		
		} catch(NullPointerException e) {
			
		}
		 
		System.out.println("Done testing...");
		
    }
    
    public Appt genAppt(Random r) {
		
		int startHour = ValuesGenerator.getRandomIntBetween(r, -20, 43);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, -25, 84);
		int startDay = ValuesGenerator.getRandomIntBetween(r, -35, 65);
		int startMonth = ValuesGenerator.getRandomIntBetween(r, -10, 21);
		int startYear = ValuesGenerator.getRandomIntBetween(r, -1000, 2018);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
		
		return appt;
		
	}
    
    public Appt genValidAppt(Random r) {
		
		int startHour = ValuesGenerator.getRandomIntBetween(r, 0, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, 0, 59);
		int startDay = ValuesGenerator.getRandomIntBetween(r, 1, 28);
		int startMonth = ValuesGenerator.getRandomIntBetween(r, 1, 11);
		int startYear = ValuesGenerator.getRandomIntBetween(r, 1, 2018);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
		
		appt.setValid();
		
		return appt;
		
	}
    
}
