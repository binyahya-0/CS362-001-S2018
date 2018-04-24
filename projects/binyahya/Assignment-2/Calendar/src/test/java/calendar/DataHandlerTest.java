
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
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest3()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n]", lCD.toString());
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest3_51()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest4()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 2, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }
  
  @Test(timeout = 4000)
  public void getApptRangeTest5()  throws Throwable  {
	  GregorianCalendar gc2 = new GregorianCalendar(2018, 3, 11);
	  GregorianCalendar gc1 = new GregorianCalendar(2018, 3, 9);
	  DataHandler dh = new DataHandler("test.xml");
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 3, 1, 1);
	  dh.saveAppt(appt0);
	  List<CalDay> lCD = dh.getApptRange(gc1,gc2);
	  dh.deleteAppt(appt0);
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
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
	  assertEquals("[\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n, \t --- 5/10/2018 --- \n --- -------- Appointments ------------ --- \n\n]", lCD.toString());
  }

  
  @Test(timeout = 4000)
  public void saveApptTest1()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertTrue(dh.saveAppt(appt0));
  }
  
  @Test(timeout = 4000)
  public void saveApptTest2()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(dh.saveAppt(appt0));
  }
  
  @Test(timeout = 4000)
  public void saveApptTest3()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  assertTrue(dh.saveAppt(appt0));
  }
  
  @Test(timeout = 4000)
  public void saveApptTest3_5()  throws Throwable  {
	  DataHandler dh = new DataHandler();
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  int[] recurDays = {};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  assertTrue(dh.saveAppt(appt0));
  }
  
  @Test(timeout = 4000)
  public void saveApptTest4()  throws Throwable  {
	  DataHandler dh = new DataHandler("test.xml", false);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertTrue(dh.saveAppt(appt0));
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
  } 
  
  @Test(timeout = 4000)
  public void deleteApptTest4()  throws Throwable  {
	  DataHandler dh = new DataHandler("text.xml", false);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  dh.saveAppt(appt0);
	  dh.save();
	  assertTrue(dh.deleteAppt(appt0));
  } 
  

}
