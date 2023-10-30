import React from "react";
import "./ExpenseFilter.css";

const ExpenseFilter = (props) => {
  const onDropdownSelectHandler = (event) => {
    props.onChangeFilterHandler(event.target.value);
  };

  return (
    <div className="expenses-filter">
      <div className="expenses-filter__control">
        <div className="contents">
          <div className="content1">
            <label>Filter by year</label>
          </div>
          <div className="contente1">
            <select
              value={props.selectedValue}
              onChange={onDropdownSelectHandler}
            >
              <option value="all">All</option>
              <option value="2019">2019</option>
              <option value="2020">2020</option>
              <option value="2021">2021</option>
              <option value="2022">2022</option>
              <option value="2023">2023</option>
              <option value="2024">2024</option>
              <option value="2025">2025</option>
            </select>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExpenseFilter;
