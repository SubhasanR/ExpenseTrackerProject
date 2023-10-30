import "./ExpenseDate.css";
import CalendarIcon from "react-calendar-icon";
import { ThemeProvider } from "@emotion/react";

const ExpenseDate = (props) => {
  const { date } = props;
  const month = date.toLocaleString("US-en", { month: "long" });
  const date_ = date.toLocaleDateString("US-en", { day: "numeric" });
  const year = date.getFullYear();

  const theme = {
    calendarIcon: {
      textColor: "white", 
      primaryColor: "black", 
      backgroundColor: "#fafafa",
      width: "24px",
      height:"24px"

    },
  };
  

  return (
    <div className="expense-date">
      <ThemeProvider theme={theme}>
      <CalendarIcon date={date}  />
      </ThemeProvider>
    </div>
  );
};

export default ExpenseDate;
