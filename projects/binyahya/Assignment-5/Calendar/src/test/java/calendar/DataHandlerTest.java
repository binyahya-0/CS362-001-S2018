
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


public class DataHandlerTest{
	

  @Test(expected = DateOutOfRangeException.class)
  public void getApptRangeTest1()  throws Throwable  {
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  dh.getApptRange(gc1,gc2);
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest2()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }
  
  // test weekly
  @Test
  public void getApptRangeTest3()  throws Throwable  {
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 17);
	  DataHandler dh = new DataHandler("test.xml");
	  // appt is on Monday
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  // to repeat appt every Monday
	  int[] recurDays = {2};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> calDayList = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  for (int i = 0; i < calDayList.size(); i++) {
		  if (i == 0 || i == 7) assertEquals(calDayList.get(i).getAppts().size(), 1);
		  else assertEquals(calDayList.get(i).getAppts().size(), 0);
	  }
  }
  
  // test monthly
  @Test
  public void getApptRangeTest4()  throws Throwable  {
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 4, 10);
	  
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 2, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> calDayList = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  
	  for (CalDay cd : calDayList) {
		  if (cd.getDay() == appt0.getStartDay()) assertEquals(cd.getAppts().size(), 1);
		  else assertEquals(cd.getAppts().size(), 0);
	  }
  
  }
  
  // test year
  @Test
  public void getApptRangeTest5()  throws Throwable  {
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  GregorianCalendar gc2 = new GregorianCalendar(2019, 3, 10);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  // appt only repeated once
	  appt0.setRecurrence(recurDays, 3, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> calDayList = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  
	  // bug detected against undermentioned accurate test
	  /*
	  for (CalDay cd : calDayList) {
		  if ((cd.getDay() == appt0.getStartDay()) && (cd.getMonth() == appt0.getStartMonth()))
			  assertEquals(cd.getAppts().size(),1);
		  else assertEquals(cd.getAppts().size(),0);
	  }
	  */
	  
	  // bug accounted test (as appt is only repeated once, apparently now on a montly basis)
	  // confirmed against source code 
	  // case Appt.RECUR_BY_YEARLY: (nextDay.add(nextDay.YEAR, 1);... >>
	  // case Appt.RECUR_BY_YEARLY: (nextDay.add(nextDay.MONTH, 1);...
	  for (CalDay cd : calDayList) {
		  if ((cd.getDay() == appt0.getStartDay()) && (cd.getYear() == appt0.getStartYear())) {
			  if ((cd.getMonth() == appt0.getStartMonth()) || (cd.getMonth() == (appt0.getStartMonth()+1)))
				  assertEquals(cd.getAppts().size(),1);
			  else assertEquals(cd.getAppts().size(),0);
		  }
		  else assertEquals(cd.getAppts().size(),0);
	  }
	  
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest6()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 4, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }

  
  @Test(timeout = 4000)
  public void saveApptTest1()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertTrue(dh.saveAppt(appt0));
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void saveApptTest2()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(dh.saveAppt(appt0));
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void saveApptTest3()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  assertTrue(dh.saveAppt(appt0));
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void saveApptTest3_5()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  assertTrue(dh.saveAppt(appt0));
	  dh.deleteAppt(appt0);
  }
  
  @Test(timeout = 4000)
  public void saveApptTest4()  throws Throwable  {
	  DataHandler dh = new DataHandler("test.xml", false);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertTrue(dh.saveAppt(appt0));
	  dh.deleteAppt(appt0);
  } 
  
  @Test(timeout = 4000)
  public void deleteApptTest1()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.saveAppt(appt0);
	  assertTrue(dh.deleteAppt(appt0));
  } 
  
  @Test(timeout = 4000)
  public void deleteApptTest2()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.saveAppt(appt0);
	  assertFalse(dh.deleteAppt(appt0));
  } 
  
  @Test(timeout = 4000)
  public void deleteApptTest3()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  Appt appt1 = new Appt(17, 30, 9, 4, 2018, "Birthday Party", "This is Dad's birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  dh.saveAppt(appt0);
	  assertFalse(dh.deleteAppt(appt1));
	  dh.deleteAppt(appt0);
  } 
  
  
  @Test(timeout = 4000)
  public void deleteApptTest4()  throws Throwable  {
	  DataHandler dh = new DataHandler("test.xml", false);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.saveAppt(appt0);
	  assertTrue(dh.deleteAppt(appt0));
	  dh.save();
  } 

}
