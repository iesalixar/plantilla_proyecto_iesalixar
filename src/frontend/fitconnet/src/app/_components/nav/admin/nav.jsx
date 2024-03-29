import React from "react";
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

function CustomNavbar() {
return (
    <Navbar expand="lg" bg="light" variant="light">
    <Container>
        <Navbar.Brand href="#home">Navbar</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar-nav" className="border-0">
        <FontAwesomeIcon icon={faBars} />
        </Navbar.Toggle>
        <Navbar.Collapse id="navbar-nav">
        <Nav className="me-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>
            <Nav.Link href="#disabled" disabled>Disabled</Nav.Link>
        </Nav>
        <Nav>
            <Nav.Link href="#search">
            <Button variant="outline-success">Search</Button>
            </Nav.Link>
        </Nav>
        </Navbar.Collapse>
    </Container>
    </Navbar>
);
}

export default CustomNavbar;
