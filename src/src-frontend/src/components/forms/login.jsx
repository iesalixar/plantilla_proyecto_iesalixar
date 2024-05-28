import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signinService } from '../../service/auht/authService';
import './style.scss';
import { useAuth } from '../../authContext/autContext';

const LoginForm = () => {
    const navigate = useNavigate();
    const { login } = useAuth();

    //#region  SET STATES
    const [loginInfo, setLoginInfo] = useState({
        identifier: '',
        password: '',
        rememberMe: false,
    });

    const [errors, setErrors] = useState({
        identifier: '',
        password: '',
        generic: ''
    });
    //#endregion

    //#region  VALIDATIONS
    const validateEmail = (email) => {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    };

    const validatePassword = (password) => {
        return password.length > 3;
    };
    //#endregion

    //#region HANDLECHANGE
    const handleChange = (e) => {

        const { name, value, checked, type } = e.target;
        const updatedValue = type === 'checkbox' ? checked : value;
        // Update loginInfo state
        setLoginInfo({
            ...loginInfo,
            [name]: updatedValue
        });
        // Reset the specific error for the changed input
        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: ''
        }));

        // Perform validation
        if (name === 'identifier') {
            if (!value) {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    identifier: 'Email is required.'
                }));
            } else if (!validateEmail(value)) {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    identifier: 'Please enter a valid email address.'
                }));
            }
        }

        if (name === 'password') {
            if (!value) {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    password: 'Password is required.'
                }));
            } else if (!validatePassword(value)) {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    password: 'Password must be more than 3 characters.'
                }));
            }
        }
    };
    //#endregion

    //#region HANDLE SUBMIT
    const handleSubmit = async (e) => {
        e.preventDefault();
        const newErrors = { identifier: '', password: '', generic: '' };

        // Email validation
        if (!validateEmail(loginInfo.identifier)) {
            newErrors.identifier = 'Please enter a valid email address.';
        }

        // Password validation
        if (!validatePassword(loginInfo.password)) {
            newErrors.password = 'Password must be more than 3 characters.';
        }

        if (newErrors.identifier || newErrors.password) {
            setErrors(newErrors);
        } else {
            try {
                const result = await signinService(loginInfo.identifier, loginInfo.password);
                if (result.success) {
                    login({ token: result.token });
                    navigate('/');
                }
            } catch (error) {
                setErrors({ generic: error.message });
            }
        }
    };

    //#endregion

    //#region  HTML
    return (
        <div className='main-container'>
            <h1>Welcome back to <span style={{ color: '#00666B' }}>FitConnet</span></h1>
            <form onSubmit={handleSubmit} id='login-container'>
                <div className="input-container">
                    <input
                        type="text"
                        name="identifier"
                        value={loginInfo.identifier}
                        onChange={handleChange}
                        className={errors.identifier ? 'error' : 'success'}
                        placeholder="Email"
                        required
                    />
                    <input
                        type="password"
                        name="password"
                        value={loginInfo.password}
                        onChange={handleChange}
                        className={errors.password ? 'error' : 'success'}
                        placeholder="Password"
                        required
                    />

                    <label>
                        <input
                            type="checkbox"
                            name="rememberMe"
                            checked={loginInfo.rememberMe}
                            onChange={handleChange}
                            className='check'
                        />
                        <span style={{ color: '#5E8A8C' }}>Save this information for next time.</span>
                    </label>
                </div>
                <button type="submit" className='submit-btn'>Sign In</button>
            </form>

            <div className="register-link">
                <Link to="/">Forgot the password?</Link>
                <Link to="/register">Create an account?</Link>
            </div>

            <section>
                {errors.identifier && <div className="error-message">{errors.identifier}</div>}
                {errors.password && <div className="error-message">{errors.password}</div>}
                {errors.generic && <div className="error-message">{errors.generic}</div>}
            </section>
        </div>

    );
    //#endregion
};


export default LoginForm;
