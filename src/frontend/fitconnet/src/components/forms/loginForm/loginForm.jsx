"use client"; 

import React, { useState } from 'react';
import './loginForm.scss';
import {signIn} from '@/services/adminAuth/authAdminService';

function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const token = await signIn(email, password); // Utilizar la función signIn
      localStorage.setItem('token', data.token);
      console.log('Token:', token);
      // Manejar el token devuelto según tus necesidades (por ejemplo, almacenarlo en el estado o en localStorage)
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div className="container">
      <p className='h3'><strong>Welcome to Fitconnet</strong></p>
      {error && <div className="error">{error}</div>}
      <div className='continue-with'>
        <div className='btn-group'>
          <button type="submit" className='facebook-btn'>Continue with Facebook</button>
          <button type="submit" className='google-btn'>Continue with Google</button>
        </div>
        <span>Or</span>
      </div>
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={handleEmailChange}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
          <button type="submit">Log in</button>
        </form>
      </div>
      <div className="container-options">
        <span>
          <a href="#">Forgot password?</a>
        </span>
        <span>
          <a href="#">Don&apos;t have an account?</a>
        </span>
      </div>
    </div>
  );
}

export default LoginForm;