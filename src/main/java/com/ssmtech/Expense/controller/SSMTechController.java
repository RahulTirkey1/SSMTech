package com.ssmtech.Expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssmtech.Expense.repository.calculations.ExpenseCalculations;
import com.ssmtech.Expense.spent.ExpensesSpent;

@RestController
public class SSMTechController 
{
    @Autowired
	ExpenseCalculations calculations;
    
    @GetMapping(value="/expenses",produces="application/json")
    public ExpensesSpent spent()
    {
    	return calculations.calculate();
    }
}
