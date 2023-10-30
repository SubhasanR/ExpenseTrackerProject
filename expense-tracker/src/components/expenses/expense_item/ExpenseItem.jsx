import React, { useContext } from "react";
import Skeleton, { SkeletonTheme } from "react-loading-skeleton";
import "./ExpenseItem.css";
import ExpenseDate from "../expense_date/ExpenseDate";
import Card from "../../ui/Card";
import { Col, Row } from "react-bootstrap";
import { IsLoadingContext } from "../../../layer/expense_tracker/ExpenseTracker";

const ExpenseItem = (props = {}) => {
  const isLoading = useContext(IsLoadingContext);

  const { id, date, title, amount, onDelete } = props;

  if (isLoading) {
    return <Skeleton width="100%" height="95px" className="loaderSkeleton"/>;
  }

  const handleDelete = () => {
    onDelete(id);
  };

  return (
    <li>
      <Row>
        <Col>
          <Card className="expense-item">
            <div className="row">
              <div className="col-lg-12">
                <ExpenseDate date={date} />
                <div className="expense-item__description">
                  <h2>{title}</h2>
                  <div
                    className="expense-item__price"
                    style={{ width: "8rem" }}
                  >
                    ${amount}
                  </div>
                  <button className="btn btn-danger" onClick={handleDelete}>
                    <i className="bi bi-trash"></i>
                  </button>
                </div>
              </div>
            </div>
          </Card>
        </Col>
      </Row>
    </li>
  );
};

export default ExpenseItem;