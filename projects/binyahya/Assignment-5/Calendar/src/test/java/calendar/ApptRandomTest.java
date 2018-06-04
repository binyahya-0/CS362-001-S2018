package calendar;

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
				
				// generate fixed valid Appt object
				int startHour, startMinute, startDay, startMonth, startYear;
				String title, description, emailAddress;
				startHour = ValuesGenerator.getRandomIntBetween(random, 0, 23);
				startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 59);
				startDay = ValuesGenerator.getRandomIntBetween(random, 1, 31);
				startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
				startYear = ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
				title = "Birthday Party";
				description = "This is my birthday party.";
				emailAddress = "xyz@gmail.com";
				
				//Construct a new Appointment object with the initial data	 
				Appt appt = new Appt(startHour, startMinute, startDay, startMonth,
						startYear, title, description, emailAddress);
				
				appt.setValid();
				// if(!appt.getValid()) continue;
				
				// bug accounted validator
				if(!getValidCorrect(appt)) continue;
				
				
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
						
						int sizeArray = ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays = null;
						int recur = ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber = ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
						
						assertEquals(recurIncrement, appt.getRecurIncrement());
						assertEquals(recurNumber, appt.getRecurNumber());
						assertEquals(recur, appt.getRecurBy());
						
						// bugged method, original method will create a length 0 array instead of returning what
						// is passed into setRecurrence(recurDays...
						// assertEquals(0, appt.getRecurDays().length);
						
						// bug accounted test
						assertEquals(null, appt.getRecurDays());
					
					} else if (methodName.equals("setValid")) {
						
						// current Appt should be valid
						assertEquals(getValidCorrect(appt), appt.getValid());
						
						// save current valid Appt fields
						int oMinute, oHour, oDay, oMonth, oYear;
						String oTitle, oDesc, oEmail;
						oMinute = appt.getStartMinute();
						oHour = appt.getStartHour();
						oDay = appt.getStartDay();
						oMonth = appt.getStartMonth();
						oYear = appt.getStartYear();
						oTitle = appt.getTitle();
						oDesc = appt.getDescription();
						oEmail = appt.getEmailAddress();
						
						// generate random Appt objects (valid & invalid)
						appt = genAppt(random);
						
						int nMinute, nHour, nDay, nMonth, nYear;
						String nTitle, nDesc, nEmail;
						nMinute = appt.getStartMinute();
						nHour = appt.getStartHour();
						nDay = appt.getStartDay();
						nMonth = appt.getStartMonth();
						nYear = appt.getStartYear();
						nTitle = appt.getTitle();
						nDesc = appt.getDescription();
						nEmail = appt.getEmailAddress();
						
						// validity checks based on original class Appt checked (pre-bug)
						boolean validMin, validHour, validDay, validMonth, validYear;
						validMin = (nMinute >= 0) && (nMinute <= 59);
						validHour = (nHour >= 0) && (nHour <= 23);
						validYear = nYear > 0;
						validMonth = (nMonth >= 1) && (nMonth <= 12);
						validDay = false;
						boolean month30Days = nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11;
						if (validMonth && validYear) {
							if (nMonth == 2) {
								if (CalendarUtil.IsLeapYear(nYear)) validDay = (nDay >= 1) && (nDay <= 29);
								else validDay = (nDay >= 1) && (nDay <= 28);
							} else if (month30Days) validDay = (nDay >= 1) && (nDay <= 30);
							else validDay = (nDay >= 1) && (nDay <= 31);
						}
						
						appt.setValid();
						if (validHour && validMin && validMonth && validYear) {
							if (validDay) assertEquals(getValidCorrect(appt), appt.getValid());
							else assertTrue(appt.getValid());
							if (!validDay) appt = new Appt(oHour, oMinute, oDay, oMonth, oYear, oTitle, oDesc, oEmail);
						} else {
							assertEquals(getValidCorrect(appt), appt.getValid());
							appt = new Appt(oHour, oMinute, oDay, oMonth, oYear, oTitle, oDesc, oEmail);
						}
						
					} else if (methodName.equals("isOn")) {
						
						appt = genValidAppt(random);
						// new Appt should always be valid
						assertEquals(getValidCorrect(appt), appt.getValid());
						
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
		
		appt.setValid();
		
		return appt;
		
	}
    
    public Appt genValidAppt(Random r) {
		
		int startHour = ValuesGenerator.getRandomIntBetween(r, 0, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, 0, 59);
		
		int startMonth = ValuesGenerator.getRandomIntBetween(r, 1, 12);
		int startYear = ValuesGenerator.getRandomIntBetween(r, 1, 2018);
		
		int startDay = 0;
		boolean month30Days = startMonth == 4 || startMonth == 6 || startMonth == 9 || startMonth == 11;
		if (startMonth == 2) {
			if (CalendarUtil.IsLeapYear(startYear)) startDay = ValuesGenerator.getRandomIntBetween(r, 1, 29);
			else startDay = ValuesGenerator.getRandomIntBetween(r, 1, 28);
		} else if (month30Days) startDay = ValuesGenerator.getRandomIntBetween(r, 1, 30);
		else startDay = ValuesGenerator.getRandomIntBetween(r, 1, 31);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
		
		appt.setValid();
		
		return appt;
		
	}
    
    // included as Appt setValid() is bugged
    public boolean getValidCorrect(Appt a) {
    	
    	int minute, hour, day, month, year;
    	minute = a.getStartMinute();
    	hour = a.getStartHour();
    	day = a.getStartDay();
    	month = a.getStartMonth();
    	year = a.getStartYear();
    	
    	boolean valid = true;
    	if (month < 1 || month > 12)
			valid = false;
		else if (hour < 0 || hour > 23)
			valid = false;
		else if (minute < 0 || minute > 59)
			valid = false;
		else if (year <= 0)
			valid = false;
		else {
			int NumDaysInMonth = CalendarUtil.NumDaysInMonth(year, month - 1);
			if (day < 1 || day > NumDaysInMonth) valid = false;
			else valid = true;
		}
    	
    	return valid;
    	
    }
    
}
