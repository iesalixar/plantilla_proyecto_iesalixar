import React, {useState} from 'react'

import './login.css'

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    alert(`Email: ${email} \nContraseña: ${password}`);
  };

  return (
    <div className="container">
      <h2>Welcome to Fitconnet</h2>
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
    <div class="container-options">
  <span>
    <a href="#">Forgot password?</a>
  </span>
  <span>
    <a href="#">Don't have an account?</a>
  </span>
</div>
  </div>
);
}

export default Login;
