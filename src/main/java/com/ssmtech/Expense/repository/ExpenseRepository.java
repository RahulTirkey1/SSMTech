package com.ssmtech.Expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.ssmtech.Expense.expense.Expense;
@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
	
	@Query(value="select * from expense where date(date) = curdate()",nativeQuery=true)
	public List<Expense> getTodayExp();
	
	@Query(value="select * from expense where date BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND CURDATE()-1",nativeQuery=true)
	public List<Expense> lastSevenDay();

	@Query(value="select * from expense WHERE date >= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL 2 MONTH)), INTERVAL 1 DAY) AND date <= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL 1 MONTH)), INTERVAL 0 DAY)",nativeQuery=true)
	public List<Expense> lastOneMonth();
	
	@Query(value="select * from expense WHERE date >= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL 4 MONTH)), INTERVAL 1 DAY) AND date <= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL 1 MONTH)), INTERVAL 0 DAY)",nativeQuery=true)
	public List<Expense> lastThreeMonth();
	
	@Query(value="select * from expense where date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 MONTH) AND CURDATE()",nativeQuery=true)
	public List<Expense> currentMonthDebit();
	
	@Query(value="select * from expense WHERE date >= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL ? MONTH)), INTERVAL 1 DAY) AND date <= DATE_ADD(LAST_DAY(DATE_SUB(NOW(), INTERVAL ? MONTH)), INTERVAL 0 DAY)",nativeQuery=true)
	public List<Expense> lastQuarter(int prevmonthvalue,int nextmonthvalue);
}
