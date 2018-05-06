/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import java.util.GregorianCalendar;


public class CalDayTest{

  @Test(timeout = 4000)
  public void calDayConstructorTest1()  throws Throwable  {
	  CalDay day = new CalDay();
	  assertEquals("", day.toString());
  }
  
  @Test(timeout = 4000)
  public void calDayConstructorTest2()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  //assertEquals("\t --- 4/9/2018 --- \n --- -------- Appointments ------------ --- \n\n", day.toString());
  }
  
  @Test(timeout = 4000)
  public void addApptTest1()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  day.addAppt(appt0);
	  // assertEquals("\t --- 4/9/2018 --- \n --- -------- Appointments ------------ --- \n\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \n", day.toString());
  }
  
  @Test(timeout = 4000)
  public void addApptTest2()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  Appt appt1 = new Appt(17, 30, 9, 4, 2018, "Birthday Party 2", "This is mom's birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  day.addAppt(appt0);
	  day.addAppt(appt1);
	  // assertEquals("\t --- 4/9/2018 --- \n --- -------- Appointments ------------ --- \n\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \t4/9/2018 at 5:30pm ,Birthday Party 2, This is mom's birthday party\n \n", day.toString());
  }
  
  @Test(timeout = 4000)
  public void addApptTest3()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  Appt appt1 = new Appt(17, 30, 9, 4, 2018, "Birthday Party 2", "This is mom's birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  day.addAppt(appt1);
	  day.addAppt(appt0);
	  // assertEquals("\t --- 4/9/2018 --- \n --- -------- Appointments ------------ --- \n\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \t4/9/2018 at 5:30pm ,Birthday Party 2, This is mom's birthday party\n \n", day.toString());
  }
  
  @Test(timeout = 4000)
  public void addApptTest4()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 2, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  day.addAppt(appt0);
	  // assertEquals("\t --- 4/9/2018 --- \n --- -------- Appointments ------------ --- \n\n", day.toString());
  }
  
  @Test(timeout = 4000)
  public void iteratorTest1()  throws Throwable  {
	  CalDay day = new CalDay();
	  assertEquals(null, day.iterator());
  }
  
  @Test(timeout = 4000)
  public void getSizeApptsTest()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 2, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  day.addAppt(appt0);
	  assertEquals(1, day.getSizeAppts());
  }
  
  @Test(timeout = 4000) 
  public void getFullInfomrationAppTest1()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t3:30PM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000)
  public void getFullInfomrationAppTest2()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\tBirthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000)
  public void getFullInfomrationAppTest3()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(9, 15, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t9:15AM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000)
  public void getFullInfomrationAppTest4()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(0, 15, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t12:15AM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000)
  public void getFullInfomrationAppTest5()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 5, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t3:05PM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000) // Added in Assignment 3
  public void getFullInfomrationAppTest6()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(12, 5, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t0:05AM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000) // Added in Assignment 3
  public void getFullInfomrationAppTest7()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 10, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  day.addAppt(appt0);
	  assertEquals("4-9-2018 \n\t3:10PM Birthday Party This is my birthday party ",day.getFullInfomrationApp(day));
  }
  
  @Test(timeout = 4000) // Added in Assignment 3
  public void addApptTest5()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  Appt appt1 = new Appt(15, 30, 9, 4, 2018, "Birthday Party 2", "This is mom's birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  day.addAppt(appt0);
	  day.addAppt(appt1);
	  assertEquals("\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 3:30pm ,Birthday Party, This is my birthday party\n \t9/2018/2018 at 3:30pm ,Birthday Party 2, This is mom's birthday party\n \n", day.toString());
	  // Test takes into account of bugs from Appt
  }
  
  @Test(timeout = 4000) // Added in Assignment 3
  public void addApptTest6()  throws Throwable  {
	  GregorianCalendar gc = new GregorianCalendar(2018, 3, 9);
	  CalDay day = new CalDay(gc);
	  Appt appt0 = new Appt(17, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  Appt appt1 = new Appt(15, 30, 9, 4, 2018, "Birthday Party 2", "This is mom's birthday party", "xyz@gmail.com");
	  appt1.setValid();
	  day.addAppt(appt0);
	  day.addAppt(appt1);
	  assertEquals("\t --- 5/9/2018 --- \n --- -------- Appointments ------------ --- \n\t9/2018/2018 at 5:30pm ,Birthday Party, This is my birthday party\n \t9/2018/2018 at 3:30pm ,Birthday Party 2, This is mom's birthday party\n \n", day.toString());
	// Test takes into account of bugs from Appt
  }

}
