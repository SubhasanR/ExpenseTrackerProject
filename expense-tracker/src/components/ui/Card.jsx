import React from "react";
import './Card.css';
import {Row,Col,Container} from 'react-bootstrap'
const Card = (props) => {
    const classes = 'card '+ props.className;2
   return(
      <div>
        <Container>
            <Row>
                <Col>
                <div className={classes}>
                    {props.children}
                </div>
                </Col>
            </Row>
        </Container>
      </div>
   ); 
}

export default Card;