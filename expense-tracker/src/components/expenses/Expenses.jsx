import { useState } from "react";
import "./Expenses.css";
import ExpenseFilter from "./expense_filter/ExpenseFilter";
import ExpensesList from "./expenses_list/ExpensesList";

const Expenses = (props) => {
  const { expenses = [] } = props;
  const [filterYear, setFilterYear] = useState("all");

  const onChangeFilterHandler = (selected_year) => {
    setFilterYear(selected_year);
   
  };

  const filter_expenses = expenses.filter((each_expense) => {
    const expenseYear = each_expense.date.getFullYear().toString();
    const yearFilter = filterYear === "all" || expenseYear === filterYear;
    return yearFilter;
  });

  return (
    <div>
      <ExpenseFilter
        selectedValue={filterYear}
        onChangeFilterHandler={onChangeFilterHandler}
      />
      <div>
        <ExpensesList items={filter_expenses} />
      </div>
    </div>
  );
};

export default Expenses;
