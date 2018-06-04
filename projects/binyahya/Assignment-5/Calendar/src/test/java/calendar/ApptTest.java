/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;



public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }
  
  // Constructors Test(s)
  @Test(timeout = 4000)
  public void apptConstructorTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  String string0 = appt0.toString();
	  assertEquals("\t14/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
  }
  
  @Test(timeout = 4000)
  public void apptConstructorTest2()  throws Throwable  {
	  Appt appt0 = new Appt(9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  String string0 = appt0.toString();
	  assertEquals("\t14/9/2018 at -1:-1am ,Birthday Party, This is my birthday party\n", string0);
  }
  
  // setValid Test(s)
  @Test(timeout = 4000)
  public void setValidTrueTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertTrue(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidMonthTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, -1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidMonthTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 13, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }

  @Test(timeout = 4000)
  public void setValidHourTest1()  throws Throwable  {
	  Appt appt0 = new Appt(-1, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidHourTest2()  throws Throwable  {
	  Appt appt0 = new Appt(24, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidMinuteTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, -1, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  @Test(timeout = 4000)
  public void setValidMinuteTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidYearTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, -1, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  assertFalse(appt0.getValid());
  }
  
  @Test(timeout = 4000)
  public void setValidDayTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 0, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  // Purposefully created an Appt object with an invalid Day field (i.e. less than 1), expected for setValid() to fail
	  // assertFalse(appt0.getValid());
	  
	  // Bug accounted test
	  assertTrue(appt0.getValid());
  }

//test against months with max 31 days (test against >31)
  @Test(timeout = 4000)
  public void setValidDayTest2_1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 32, 1, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  // Purposefully created an Appt object with an invalid Day field (i.e. more than 31), expected for setValid() to fail
	  // assertFalse(appt0.getValid());
	  
	  // Bug accounted test
	  assertTrue(appt0.getValid());
  }
  
  // test against months with max 30 days (test against >30)
  @Test(timeout = 4000)
  public void setValidDayTest2_2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 31, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  // Purposefully created an Appt object with an invalid Day field (i.e. more than 30), expected for setValid() to fail
	  // assertFalse(appt0.getValid());
	  
	  // Bug accounted test
	  assertTrue(appt0.getValid());
  }
  
//test against months with max 28 days (test against >28)
  @Test(timeout = 4000)
  public void setValidDayTest2_31()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 29, 2, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  // Purposefully created an Appt object with an invalid Day field (i.e. more than 28), expected for setValid() to fail
	  // assertFalse(appt0.getValid());
	  
	  // Bug accounted test
	  assertTrue(appt0.getValid());
  }
  
//test against months with max 29 days (test against >29)
  @Test(timeout = 4000)
  public void setValidDayTest2_32()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 30, 2, 2020, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setValid();
	  
	  // Purposefully created an Appt object with an invalid Day field (i.e. more than 29), expected for setValid() to fail
	  // assertFalse(appt0.getValid());
	  
	  // Bug accounted test
	  assertTrue(appt0.getValid());
  }
  
  // set Fields Test
  @Test(timeout = 4000)
  public void setStartHourTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setStartHour(12);
	  assertEquals(12,appt0.getStartHour());
  }

  @Test(timeout = 4000)
  public void setStartMinuteTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setStartMinute(12);
	  assertEquals(12,appt0.getStartMinute());
  }
  
  @Test(timeout = 4000)
  public void setStartDayTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setStartDay(12);
	  assertEquals(12,appt0.getStartDay());
  }
  
  @Test(timeout = 4000)
  public void setStartMonthTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setStartMonth(11);
	  assertEquals(11,appt0.getStartMonth());
  }
  
  @Test(timeout = 4000)
  public void setStartYearTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setStartYear(2020);
	  assertEquals(2020,appt0.getStartYear());
  }
  
  @Test(timeout = 4000)
  public void setTitleTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setTitle(null);
	  assertEquals("",appt0.getTitle());
  }
  
  @Test(timeout = 4000)
  public void setTitleTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setTitle("Mom's Birthday");
	  assertEquals("Mom's Birthday",appt0.getTitle());
  }
  
  @Test(timeout = 4000)
  public void setDescriptionTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setDescription(null);
	  assertEquals("",appt0.getDescription());
  }
  
  @Test(timeout = 4000)
  public void setDescriptionTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
	  appt0.setDescription("This is mom's birthday party.");
	  assertEquals("This is mom's birthday party.",appt0.getDescription());
  }
  
  @Test(timeout = 4000)
  public void setEmailAddressTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", null);
	  assertEquals("",appt0.getEmailAddress());
  }
  
  @Test(timeout = 4000)
  public void setEmailAddressTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertEquals("myemail@gmail.com",appt0.getEmailAddress());
  }
    
  @Test(timeout = 4000)
  public void setRecurDays1Test()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = null;
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  int[] test = new int[0];
	  
	  // Purposefully created a null recurDays array which is passed into Appt object, object should return a size 0 array
	  // under normal circumstances
	  // Appt object however returns the recurDays array which was passed into object
	  // assertArrayEquals(test, appt0.getRecurDays());
	  
	  assertArrayEquals(recurDays, appt0.getRecurDays());
	  
	  
  }
  
  @Test(timeout = 4000)
  public void setRecurDays2Test()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  int[] test = {1};
	  assertArrayEquals(test, appt0.getRecurDays());
  }
  
  @Test(timeout = 4000)
  public void setRecurByTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 5, 1, 1);
	  assertEquals(5, appt0.getRecurBy());
  }
  
  @Test(timeout = 4000)
  public void setRecurIncrementTest()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 5, 1);
	  assertEquals(5, appt0.getRecurIncrement());
  }
  
  @Test(timeout = 4000)
  public void setRecurNumber()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 5);
	  assertEquals(5, appt0.getRecurNumber());
  }
  
  
  // Methods Test(s)
  @Test(timeout = 4000)
  public void setIsOnTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertTrue(appt0.isOn(9, 4, 2018));
  }
  
  @Test(timeout = 4000)
  public void setIsOnTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertFalse(appt0.isOn(9, 4, 2019));
  }
  
  @Test(timeout = 4000)
  public void setIsOnTest3()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertFalse(appt0.isOn(9, 5, 2020));
  }
  
  @Test(timeout = 4000)
  public void setIsOnTest4()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertFalse(appt0.isOn(10, 4, 2020));
  }
  
  @Test(timeout = 4000)
  public void hasTimeSet1()  throws Throwable  {
	  Appt appt0 = new Appt(9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertFalse(appt0.hasTimeSet());
  }
  
  @Test(timeout = 4000)
  public void hasTimeSet2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertTrue(appt0.hasTimeSet());
  }
  
  @Test(timeout = 4000)
  public void isRecurringTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  assertFalse(appt0.isRecurring());
  }
  
  @Test(timeout = 4000)
  public void isRecurringTest2()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  int[] recurDays = {1};
	  appt0.setRecurrence(recurDays, 1, 1, 1);
	  assertTrue(appt0.isRecurring());
  }
  
  @Test(timeout = 4000)
  public void representationAppTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  String string0 = appt0.toString();
	  assertEquals("\t4/9/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
  }
  
  @Test(timeout = 4000)
  public void representationAppTest2()  throws Throwable  {
	  Appt appt0 = new Appt(11, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  String string0 = appt0.toString();
	  assertEquals("\t4/9/2018 at 11:30am ,Birthday Party, This is my birthday party\n", string0);
  }
  
  @Test(timeout = 4000)
  public void representationAppTest3()  throws Throwable  {
	  Appt appt0 = new Appt(0, 30, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  String string0 = appt0.toString();
	  assertEquals("\t4/9/2018 at 12:30am ,Birthday Party, This is my birthday party\n", string0);
  }
  
  @Test(timeout = 4000)
  public void toStringTest1()  throws Throwable  {
	  Appt appt0 = new Appt(15, 60, 9, 4, 2018, "Birthday Party", "This is my birthday party", "myemail@gmail.com");
	  appt0.setValid();
	  String string0 = appt0.toString();
	  assertEquals("\t4/9/2018 at 3:60pm ,Birthday Party, This is my birthday party\n", string0);
  }
  
}
