import React from "react";
import "./style.scss";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPersonRunning } from "@fortawesome/free-solid-svg-icons"; 

function LoginNav() {
  return (
    
    <Navbar expand="lg" className="bg-white">
    <Container className="d-flex flex-row nav-containe justify-content-between ">
      <div className="logo-container d-flex flex-row align-items-lg-center ">
      <FontAwesomeIcon icon={faPersonRunning} size="2xl" style={{color: "#01696e",}} />
      <Navbar.Brand href="#home" className="" style={{color:"#04292A"}}><p className="h2 p-0 mt-2 ms-3"><strong>FitConnet</strong></p></Navbar.Brand>
      </div>
      <div>

        <Button className="justify-content-end primary-custom-btn">
          Sign up
        </Button>

      </div>
    </Container>
  </Navbar>

  );
}

export default LoginNav;
