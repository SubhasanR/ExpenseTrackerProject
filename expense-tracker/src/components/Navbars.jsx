import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Offcanvas from "react-bootstrap/Offcanvas";
import "./Navbars.css";
import { Link } from "react-router-dom";

function Navbars() {
  const home = () => {
    window.location.href = "/";
  };

  return (
    <>
      {[false].map((expand) => (
        <Navbar
          key={expand}
          expand={expand}
          className="bg-dark nav-change navbar-dark mb-3 nav"
        >
          <Container fluid className="cd">
            <Link to={"/"}>
              <Navbar.Brand href="#pa" className="he">
                Home
              </Navbar.Brand>
            </Link>
            <Navbar.Brand href="#h-1" className="fea">
              Features
            </Navbar.Brand>
            <Navbar.Brand href="#h-2" className="abt">
              About
            </Navbar.Brand>
            <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-${expand}`} />
            <Navbar.Offcanvas
              id={`offcanvasNavbar-expand-${expand}`}
              aria-labelledby={`offcanvasNavbarLabel-expand-${expand}`}
              placement="end"
            >
              <Offcanvas.Header closeButton>
                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${expand}`}>
                  Expense Tracker
                </Offcanvas.Title>
              </Offcanvas.Header>
              <Offcanvas.Body>
                <Nav className="justify-content-end flex-grow-1 pe-3">
                  <Nav.Link href="#pa">Home</Nav.Link>
                  <Nav.Link href="#h-1">Feature</Nav.Link>
                  <Nav.Link href="#h-2">About</Nav.Link>
                </Nav>
                <Form className="d-flex">
                  <Form.Control
                    type="search"
                    placeholder="Search"
                    className="me-2"
                    aria-label="Search"
                  />
                  <Button variant="outline-success">Search</Button>
                </Form>
              </Offcanvas.Body>
            </Navbar.Offcanvas>
          </Container>
        </Navbar>
      ))}
    </>
  );
}

export default Navbars;
