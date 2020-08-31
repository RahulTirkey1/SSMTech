package com.ssmtech.Expense.spent;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpensesSpent 
{
private Double todaysSpent;
private Double lastSevenDaysSpent;
private Double lastOneMonthSpent;
private Double lastThreeMonthSpent;
private Double currentMonthCredit;
private Double currentMonthDebit;
private List<LinkedHashMap<String,Double>> lastQuarter;
public Double getTodaysSpent() {
	return todaysSpent;
}
public void setTodaysSpent(Double todaysSpent) {
	this.todaysSpent = todaysSpent;
}
public Double getLastSevenDaysSpent() {
	return lastSevenDaysSpent;
}
public void setLastSevenDaysSpent(Double lastSevenDaysSpent) {
	this.lastSevenDaysSpent = lastSevenDaysSpent;
}
public Double getLastOneMonthSpent() {
	return lastOneMonthSpent;
}
public void setLastOneMonthSpent(Double lastOneMonthSpent) {
	this.lastOneMonthSpent = lastOneMonthSpent;
}
public Double getLastThreeMonthSpent() {
	return lastThreeMonthSpent;
}
public void setLastThreeMonthSpent(Double lastThreeMonthSpent) {
	this.lastThreeMonthSpent = lastThreeMonthSpent;
}
public Double getCurrentMonthCredit() {
	return currentMonthCredit;
}
public void setCurrentMonthCredit(Double currentMonthCredit) {
	this.currentMonthCredit = currentMonthCredit;
}
public Double getCurrentMonthDebit() {
	return currentMonthDebit;
}
public void setCurrentMonthDebit(Double currentMonthDebit) {
	this.currentMonthDebit = currentMonthDebit;
}
public List<LinkedHashMap<String,Double>> getLastQuarter() {
	return lastQuarter;
}
public void setLastQuarter(List<LinkedHashMap<String,Double>> lastQuarter) {
	this.lastQuarter =lastQuarter;
}


}
