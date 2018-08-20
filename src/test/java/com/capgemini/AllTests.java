package com.capgemini;

import com.capgemini.service.CarServiceTest;
import com.capgemini.service.EmployeeServiceTest;
import com.capgemini.service.OfficeService;
import com.capgemini.service.OfficeServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({EmployeeServiceTest.class, OfficeServiceTest.class, CarServiceTest.class})
public class AllTests {

}
