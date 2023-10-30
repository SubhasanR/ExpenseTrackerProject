import React, { useEffect, useState } from "react";
import ExpenseItem from "../expense_item/ExpenseItem";
import "./ExpensesList.css";

const ExpensesList = (props) => {
  const { items } = props;
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    setExpenses(items);
  }, [items]);

  const handleDeleteExpense = (id) => {
    console.log(expenses);
    setExpenses((prev) => prev.filter((expense) => expense.id != id));
  };

  if (expenses.length === 0 || !items) {
    return <h2 className="expenses-list__fallback">No expenses found.</h2>;
  }

  return (
    <div>
      <div>
        <ul
          className="expenses-list"
          style={{ display: "grid", gridTemplateColumns: "repeat(5,1fr)" }}
        >
          {expenses.map((each_expense, index) => (
            <ExpenseItem
              id={each_expense.id}
              key={index}
              date={each_expense.date}
              title={each_expense.title}
              amount={each_expense.amount}
              onDelete={handleDeleteExpense}
            />
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ExpensesList;
