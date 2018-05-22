package calendar;


import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
	// Variables
	private static final long timeOut = 60 * 500 * 1;
	private static final int NUM_TESTS = 100;
	
	// Generate random Appt objects, given a date
	public Appt genAppt(Random r, GregorianCalendar gc) {
				
		int startHour = ValuesGenerator.getRandomIntBetween(r, 0, 23);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, 0, 59);
		int startDay = gc.get(gc.DAY_OF_MONTH);
		int startMonth = gc.get(gc.MONTH);
		int startYear = gc.get(gc.YEAR);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		Appt appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
	
		appt.setValid();
		
		return appt;
		
	}
	
	// Generate random invalid Appt objects, given a date
	public Appt genInvalidAppt(Random r, GregorianCalendar gc) {
		
		Appt appt = null;
			
		int startHour = ValuesGenerator.getRandomIntBetween(r, -20, -1);
		int startMinute = ValuesGenerator.getRandomIntBetween(r, 24, 50);
		int startDay = gc.get(gc.DAY_OF_MONTH);
		int startMonth = gc.get(gc.MONTH);
		int startYear = gc.get(gc.YEAR);
		
		String title = ValuesGenerator.getString(r);
		String description = ValuesGenerator.getString(r);
		String emailAddress = "xyz@gmail.com";
		
		appt = new Appt(startHour, startMinute, startDay, startMonth, 
				startYear, title, description, emailAddress);
	
		appt.setValid();
		
		return appt;
		
	}
	
	// Generate Random Valid Dates
	public GregorianCalendar genDate(Random r) {
	
		LocalDate d = null;
		
		int year = 0, month = 0, day = 0;
		
		while (d == null) {
			try {
				year = ValuesGenerator.getRandomIntBetween(r, 2018, 2018);
				month = ValuesGenerator.getRandomIntBetween(r, 0, 11);
				day = ValuesGenerator.getRandomIntBetween(r, 1, 31);
				
				d = LocalDate.of(year, month, day);
				
			} catch (DateTimeException e) {
				
			}
		}
		
		return new GregorianCalendar(year,month,day);
			
	}
	
	// Utility
	class ApptComp implements Comparator<Appt> {
		
		@Override
		public int compare(Appt a1, Appt a2) {
			
			Integer c1 = Integer.compare(a2.getStartHour(), a1.getStartHour());
			return c1;
			
		}
		
	}
	
	// Generate Random Tests that tests addAppt(...)
	@Test
	public void randomValidTest()  throws Throwable  {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		
		System.out.println("Testing Underway...");
		
		try { 
			for (int iteration = 0; elapsed < timeOut; iteration++) {
				
				long seed = System.currentTimeMillis();
				Random r = new Random(seed);
				
				for (int i = 0; i < NUM_TESTS; i++) {
					
					ArrayList<Appt> listAppt = null;
					CalDay day = null;
					
					GregorianCalendar gc = genDate(r);
					day = new CalDay(gc);
				
					listAppt = new ArrayList<Appt>();
					
					int numAppt = ValuesGenerator.getRandomIntBetween(r, 1, 20);
					
					for (int j = 0; j < numAppt; j++) {
						Appt appt = genAppt(r,gc);
						listAppt.add(appt);
						day.addAppt(appt);
					}
					
					listAppt.sort(new ApptComp());
					
					for (int j = 0; j < listAppt.size(); j++) {
						assertEquals(listAppt.get(j), day.getAppts().get(j));
					}
					
				}
				
				for (int i = 0; i < NUM_TESTS; i++) {
					
					CalDay day = null;
					
					GregorianCalendar gc = genDate(r);
					day = new CalDay(gc);
				
					int numAppt = ValuesGenerator.getRandomIntBetween(r, 1, 20);
					
					for (int j = 0; j < numAppt; j++) {
						Appt appt = genInvalidAppt(r,gc);
						day.addAppt(appt);
					}
					
					assertEquals(0, day.getAppts().size());
					
				}
				
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				if((iteration%10000)==0 && iteration!=0)
					System.out.println("elapsed time: "+ elapsed + " of "+ timeOut);
			}
				
		} catch(NullPointerException e) {
			
		}
		System.out.println("Testing Complete");
		
	}
	
}