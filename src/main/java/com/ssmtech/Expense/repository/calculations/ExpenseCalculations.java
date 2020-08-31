package com.ssmtech.Expense.repository.calculations;
import java.time.Duration;
import java.time.Instant;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ssmtech.Expense.expense.Expense;

import com.ssmtech.Expense.repository.ExpenseRepository;
import com.ssmtech.Expense.spent.ExpensesSpent;
@Service
public class ExpenseCalculations  {
    @Autowired
	ExpenseRepository repository;
	
@Async	
public CompletableFuture<Double> todayCalc()
{
	List<Expense> exp=new ArrayList<Expense>();
	
     exp=repository.getTodayExp();
     double expense=0.0;
		for(int i=0;i<exp.size();i++)
		{   
			Expense ex=exp.get(i);
			if(ex.getExpense().equalsIgnoreCase("salary"))
			{
			 }
			else
			{
				expense+=ex.getAmount();
			}
		}
		return CompletableFuture.completedFuture(expense);
}
@Async
public CompletableFuture<Double> lastSevenDay()
{
	List<Expense> exp=new ArrayList<Expense>();
	exp=repository.lastSevenDay();
	double expense=0.0;
	for(int i=0;i<exp.size();i++)
	{   
		Expense ex=exp.get(i);
		if(ex.getExpense().equalsIgnoreCase("salary"))
		{
		 }
		else
		{
			expense+=ex.getAmount();
		}
	}
	return CompletableFuture.completedFuture(expense);
	
}
@Async
public CompletableFuture<Double> lastoneMonth()
{
	List<Expense> exp=new ArrayList<Expense>();
  exp=repository.lastOneMonth();
  double expense=0.0;
	for(int i=0;i<exp.size();i++)
	{   
		Expense ex=exp.get(i);
		if(ex.getExpense().equalsIgnoreCase("salary"))
		{
		 }
		else
		{
			expense+=ex.getAmount();
		}
	}
	return CompletableFuture.completedFuture(expense);
}
@Async
public CompletableFuture<Double> lastthreeMonth()
{
	List<Expense> exp=new ArrayList<Expense>();
  exp=repository.lastThreeMonth();
  double expense=0.0;
	for(int i=0;i<exp.size();i++)
	{   
		Expense ex=exp.get(i);
		if(ex.getExpense().equalsIgnoreCase("salary"))
		{
		 }
		else
		{
			expense+=ex.getAmount();
		}
	}
	return CompletableFuture.completedFuture(expense);
}
@Async
public CompletableFuture<Double[]> currentmonth()
{
	Double arr[]=new Double[2];
	arr[0]=0.0;arr[1]=0.0;
	List<Expense> exp=new ArrayList<Expense>();
  exp=repository.currentMonthDebit();
  for(int i=0;i<exp.size();i++)
  {
	  Expense ex=exp.get(i);
	  if(ex.getExpense().equalsIgnoreCase("salary"))
	  {
		  arr[0]+=ex.getAmount();
	  }
	  else
	  {
		  arr[1]+=ex.getAmount();
	  }
   }
  
  return CompletableFuture.completedFuture(arr);
}
@Async
 public CompletableFuture<List<LinkedHashMap<String,Double>>> lastquarter()
{
   List<LinkedHashMap<String,Double>> list=new ArrayList<LinkedHashMap<String,Double>>();
	String nameOfMonth;
	int monthval;Double expense=0.0;
	Month name;
	LinkedHashMap<String,Double> map=new LinkedHashMap<String,Double>();
	int x=4;
	for(int i=5;i>1;i--)
	{   x--;
		int k=i-1;
		List<Expense> exp=repository.lastQuarter(i, k);
		if(exp.size()==0)
		{
		 Date d=new Date();
		 monthval=d.getMonth()-x;
		 name=Month.of(monthval);
		 expense=0.0;
		 nameOfMonth=name.toString();
		 map.put(nameOfMonth, expense);
		}
		else
		{
		Expense o=exp.get(0);
		monthval=o.getDate().getMonthValue();
		name=Month.of(monthval);
		nameOfMonth=name.toString();
		System.out.println(nameOfMonth);
		for(int v=0;v<exp.size();v++)
		{   
			Expense ex=exp.get(v);
			if(ex.getExpense().equalsIgnoreCase("salary"))
			{
			 }
			else
			{
				expense+=ex.getAmount();
			}
		}
		map.put(nameOfMonth, expense);
		}
		
	}	
//	l.add(map);
	list.add(map);
	return CompletableFuture.completedFuture(list);
	
} 

	public ExpensesSpent calculate()
	{   Instant before = Instant.now();
		ExpensesSpent expenses=new ExpensesSpent();Double lastonemonth;
		Double lastthreemonth;Double[] currentMonth;List<LinkedHashMap<String,Double>> lastquart;
		Double today;Double lastsevenday;
		try {
			today = todayCalc().get();
			expenses.setTodaysSpent(today);
			lastsevenday=lastSevenDay().get();
			expenses.setLastSevenDaysSpent(lastsevenday);
			expenses.setLastSevenDaysSpent(lastsevenday);
			lastonemonth=lastoneMonth().get();
			expenses.setLastSevenDaysSpent(lastsevenday);
			lastthreemonth=lastthreeMonth().get();
			currentMonth=currentmonth().get();
			lastquart=lastquarter().get();
			expenses.setLastSevenDaysSpent(lastsevenday);
			expenses.setLastOneMonthSpent(lastonemonth);
			expenses.setLastThreeMonthSpent(lastthreemonth);
			expenses.setCurrentMonthDebit(currentMonth[1]);
			expenses.setCurrentMonthCredit(currentMonth[0]);
			expenses.setLastQuarter(lastquart);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instant after = Instant.now();
		long delta = Duration.between(before, after).toMillis();
		System.out.println(delta);
		return expenses;
	}

}
