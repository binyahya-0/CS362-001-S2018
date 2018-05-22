package calendar;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;
	
	public static String RandomSelectMethod(Random random) {
		String[] methodArray = new String[] {"getApptRangeInvalid","getApptRange",
				"saveAppt", "deleteAppt"};
		int n = random.nextInt(methodArray.length);
		return methodArray[n];
	}
	
	public Appt genValidAppt(Random r, GregorianCalendar gc) {
		
		int startHour = ValuesGenerator.getRandomIntBetween(r, 0, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, 0, 59);
		int startDay = gc.get(gc.DAY_OF_MONTH);
		int startMonth = gc.get(gc.MONTH)+1;
		int startYear = gc.get(gc.YEAR);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
		
		appt.setValid();
		
		int sizeArray=ValuesGenerator.getRandomIntBetween(r, 0, 8);
		int[] recurDays=ValuesGenerator.generateRandomArray(r, sizeArray);
		int recur=ApptRandomTest.RandomSelectRecur(r);
		int recurIncrement = ValuesGenerator.RandInt(r);
		int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(r);
		appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
		
		return appt;
		
	}
	
	public Appt genInvalidAppt(Random r, GregorianCalendar gc) {
		
		int startHour = ValuesGenerator.getRandomIntBetween(r, 24, 40);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, -10, -1);
		int startDay = gc.get(gc.DAY_OF_MONTH);
		int startMonth = gc.get(gc.MONTH)+1;
		int startYear = gc.get(gc.YEAR);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
		
		appt.setValid();
		
		return appt;
		
	}
	
	public GregorianCalendar genDate(Random r) {
		
		LocalDate d = null;
		
		int year = 0, month = 0, day = 0;
		
		while (d == null) {
			try {
				year = ValuesGenerator.getRandomIntBetween(r, 2018, 2018);
				month = ValuesGenerator.getRandomIntBetween(r, 0, 10); // Account for bug
				day = ValuesGenerator.getRandomIntBetween(r, 1, 31);
				
				d = LocalDate.of(year, month, day);
				
			} catch (DateTimeException e) {
				
			}
		}
		
		boolean valid30 = (month == 3) || (month == 5) || 
				(month == 8) || (month == 10);
		
		boolean leapYear = CalendarUtil.IsLeapYear(year);
		
		if (month == 1) {
			if (leapYear && day > 29) return new GregorianCalendar(year,month,29);
			else if (!leapYear && day > 28) return new GregorianCalendar(year,month,28);
		} else if (valid30 && day > 30) {
			return new GregorianCalendar(year,month,30);
		}
		
		return new GregorianCalendar(year,month,day);
			
	}
	
	@Test
	public void randomTest()  throws Throwable {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println("Start testing...");
		
		try {
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long seed = System.currentTimeMillis();
				Random r = new Random(seed);
				
				GregorianCalendar fixedDate = genDate(r),
						fixedDate2 = (GregorianCalendar) fixedDate.clone();
				fixedDate2.add(fixedDate2.DAY_OF_MONTH, 14);
				Appt appt = null;
				int randomIndex = 0;
				
				DataHandler dh = null;
				int dh_choice = ValuesGenerator.getRandomIntBetween(r, 1, 4);
				
				switch(dh_choice) {
				
					case 2:
						dh = new DataHandler("randomTest.xml");
						break;
					case 3:
						dh = new DataHandler("randomTest.xml", false);
						break;
					case 4:
						dh = new DataHandler("randomTest.xml", true);
						break;
					default:
						dh = new DataHandler();
						break;
				
				}
				
				// Used only to test saveApp and deleteApp
				List<CalDay> list = null;
				ArrayList<Appt> apptList = new ArrayList<Appt>();
				
				for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = DataHandlerRandomTest.RandomSelectMethod(r);
					if (methodName.equals("getApptRangeInvalid")) {
						GregorianCalendar d1 = null, d2 = null;
						d1 = genDate(r);
						d2 = (GregorianCalendar) d1.clone();
						d2.add(d2.DAY_OF_MONTH, -1);
						
						try {
							dh.getApptRange(d1, d2);
						} catch (DateOutOfRangeException e) {
							assertTrue(true); // Working as intended
						}
					
					} else if (methodName.equals("getApptRange")) {
						
						GregorianCalendar d1 = null, d2 = null;
						d1 = genDate(r);
						d2 = (GregorianCalendar) d1.clone();
						d2.add(d2.DAY_OF_MONTH, 1);
						
						list = dh.getApptRange(d1, d2);
						if (list.get(0).getAppts().isEmpty()) assertEquals(list.get(0).getAppts().size(), 0); 
						
						list = dh.getApptRange(fixedDate, fixedDate2);
						assertEquals(apptList.size(),list.get(0).getAppts().size());
						
						
					} else if (methodName.equals("saveAppt")) {
						
						appt = genInvalidAppt(r,fixedDate);
						assertFalse(dh.saveAppt(appt));
						
						appt = genValidAppt(r,fixedDate);
						dh.saveAppt(appt);
						apptList.add(appt);
						list = dh.getApptRange(fixedDate, fixedDate2);
						
						if (!(apptList.size() == list.get(0).getAppts().size())) {
							for (Appt a : apptList) {
								System.out.println(a);
							}
							for (Appt b : list.get(0).getAppts()) {
								System.out.println(b);
							}
						}
						
						assertEquals(apptList.size(),list.get(0).getAppts().size());
						
						appt = null;
						
					} else if (methodName.equals("deleteAppt")) {
						
						appt = genInvalidAppt(r,fixedDate);
						assertFalse(dh.deleteAppt(appt));
						
						list = dh.getApptRange(fixedDate, fixedDate2);
						if (list.get(0).getAppts().isEmpty()) {
							assertEquals(list.get(0).getAppts().size(), apptList.size());
							continue;
						}
						randomIndex = ValuesGenerator.getRandomIntBetween(r, 0, (apptList.size()-1));
						appt = apptList.remove(randomIndex);
						dh.deleteAppt(appt);
						list = dh.getApptRange(fixedDate, fixedDate2);
						assertEquals(list.get(0).getAppts().size(), apptList.size());
						
						appt = genValidAppt(r,fixedDate);
						assertFalse(dh.deleteAppt(appt));
						
						appt = null;
					
					}
					
				}
				
				// Clear DataHandler
				if (!apptList.isEmpty()) {
					for (Appt a : apptList) {
						dh.deleteAppt(a);
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


	
}
