import React, { createContext, useEffect, useState } from "react";
import Expenses from "../../components/expenses/Expenses";
import NewExpense from "../../components/new_expense/NewExpense";

import "./ExpenseTracker.css";
import Navbars from "../../components/Navbars";
const DUMMY_DATA = [
  {
    id: "e1",
    title: "Toilet Paper",
    amount: 94.12,
    date: new Date(2020, 7, 14),
  },
  { id: "e2", title: "New TV", amount: 799.49, date: new Date(2021, 2, 12) },
  {
    id: "e3",
    title: "Car Insurance",
    amount: 294.67,
    date: new Date(2019, 2, 28),
  },
  {
    id: "e4",
    title: "New Desk ",
    amount: 450,
    date: new Date(2020, 8, 12),
  },
  {
    id: "e5",
    title: "Toilet Paper",
    amount: 94.12,
    date: new Date(2021, 9, 14),
  },
  { id: "e6", title: "New TV", amount: 799.49, date: new Date(2021, 2, 12) },
  {
    id: "e7",
    title: "Car Insurance",
    amount: 29.67,
    date: new Date(2020, 7, 28),
  },
  {
    id: "e8",
    title: "New Desk",
    amount: 450,
    date: new Date(2021, 5, 12),
  },
  {
    id: "e9",
    title: "Car Insurance",
    amount: 294.67,
    date: new Date(2021, 2, 28),
  },
  {
    id: "e10",
    title: "New Desk",
    amount: 45,
    date: new Date(2021, 5, 12),
  },
  {
    id: "e11",
    title: "Groceries",
    amount: 120.34,
    date: new Date(2021, 8, 5),
  },
  {
    id: "e12",
    title: "Phone Bill",
    amount: 55.99,
    date: new Date(2021, 8, 20),
  },
  {
    id: "e13",
    title: "Restaurant Dinner",
    amount: 75.5,
    date: new Date(2021, 9, 2),
  },
  {
    id: "e14",
    title: "Movie Tickets",
    amount: 30.0,
    date: new Date(2021, 9, 15),
  },
  {
    id: "e15",
    title: "Books",
    amount: 49.99,
    date: new Date(2021, 10, 8),
  },
  {
    id: "e16",
    title: "Home Repairs",
    amount: 300.0,
    date: new Date(2021, 12, 3),
  },
  {
    id: "e17",
    title: "Vacation Expenses",
    amount: 800.0,
    date: new Date(2020, 11, 20),
  },
  {
    id: "e18",
    title: "Fitness Membership",
    amount: 40.0,
    date: new Date(2022, 0, 5),
  },
  {
    id: "e19",
    title: "Electricity Bill",
    amount: 90.5,
    date: new Date(2022, 0, 18),
  },
  {
    id: "e20",
    title: "Clothing Shopping",
    amount: 150.0,
    date: new Date(2022, 1, 8),
  },
];

const IsLoadingContext = createContext(null);
export { IsLoadingContext };
 
const ExpenseTracker = () => {
  const [expenses, setExpenses] = useState(DUMMY_DATA);
  const onAddNewExpense = (new_expense) => {
    setExpenses((previous_expenses) => {
      return [new_expense, ...previous_expenses];
    });
  };
  return (
    <div>
      <IsLoadingContext.Provider value={false}>
        <Navbars />
        <NewExpense onAddNewExpense={onAddNewExpense} />
        <Expenses expenses={expenses} />
      </IsLoadingContext.Provider>
    </div>
  );
};

export default ExpenseTracker;
