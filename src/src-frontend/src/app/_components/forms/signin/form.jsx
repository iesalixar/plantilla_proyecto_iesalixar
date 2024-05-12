"use client";

import React, { useState } from 'react';
import './style.scss';
import { signIn } from '@/app/_services/auth/signin/authService';
import Link from 'next/link'

function SigninForm() {
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
      const token = await signIn(email, password);
      localStorage.setItem("token", token);
      console.log("Token:", token);
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
          <Link href="/singup">Don&apos;t have an account?</Link>
        </span>
      </div>
    </div>
  );
}

export default SigninForm;