package com.capgemini;

import com.capgemini.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@SuiteClasses({EmployeeServiceTest.class, OfficeServiceTest.class, CarServiceTest.class, LoanServiceTest.class})
public class AllTests {

}
