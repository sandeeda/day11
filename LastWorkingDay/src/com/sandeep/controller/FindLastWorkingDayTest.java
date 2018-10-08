package com.sandeep.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FindLastWorkingDayTest {

	@Test
	void testGetLastWorkingDay() {

		//regular case
		assertEquals(LocalDate.of(2018, 9, 28), FindLastWorkingDay.getLastWorkingDay(9, 2018));
		//leap year February
		assertEquals(LocalDate.of(2012, 2, 29), FindLastWorkingDay.getLastWorkingDay(2, 2012));
		//end date is Friday
		assertEquals(LocalDate.of(2012, 11, 30), FindLastWorkingDay.getLastWorkingDay(11, 2012));
		//future date
		assertEquals(LocalDate.of(2019, 11, 29), FindLastWorkingDay.getLastWorkingDay(11, 2019));
		

	}

	@Test
	void testCasesWithInvalidInput() {
		//with invalid month
		assertEquals(null, FindLastWorkingDay.getLastWorkingDay(13, 2018));
		//with invalid year
		assertEquals(null, FindLastWorkingDay.getLastWorkingDay(4, -9999));
	}
}

