package com.sandeep.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FindLastWorkingDay {


	public static LocalDate getLastWorkingDay(int month , int year)
	{

		if(month<1 || month>12)
			return null;
		if(year<1000 || year >3000)
			return null;
		LocalDate a;
		a = LocalDate.of(year, month, 28);
		LocalDate end = a.with(TemporalAdjusters.lastDayOfMonth());
		DayOfWeek dayOfWeek = end.getDayOfWeek();
		String day = dayOfWeek.toString();
		if(day.equals("SUNDAY"))
		{
			end = end.minusDays(2);
			
		}
		else if(day.equals("SATURDAY"))
		{
			end = end.minusDays(1);
		}
		dayOfWeek = end.getDayOfWeek();
		day = dayOfWeek.toString();
		return end;
	}
	

}
