import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import { useAuthContext } from '../../../../contexts/AuthProvider';
import { ThemeContext } from '../../../../contexts/ThemeProvider';

import { signupService } from '../../../../service/authService';

import './style.scss';

const SignupForm = () => {

    const navigate = useNavigate();
    const { login } = useAuthContext();
    const { theme } = useContext(ThemeContext);

    //#region  SET STATES
    const [registrationInfo, setRegistrationInfo] = useState({
        name: '',
        age: '',
        email: '',
        password: '',
        privacyPolicy: false,
        rememberMe: false,
    });
    const [errors, setErrors] = useState({
        name: '',
        age: '',
        email: '',
        password: '',
        privacyPolicy: '',
        generic: ''
    });
    //#endregion

    //#region VALIDATIONS
    const validateName = (name) => {
        return name.length > 3;
    };

    const validateAge = (age) => {
        return age >= 18;
    };

    const validateEmail = (email) => {
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    };

    const validatePassword = (password) => {
        return password.length > 3;
    };

    const validatePrivacyPolicy = (privacyPolicy) => {
        return privacyPolicy;
    };
    //#endregion

    //#region HANDLECHANGE
    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        const updatedValue = type === 'checkbox' ? checked : value;

        setRegistrationInfo({ ...registrationInfo, [name]: updatedValue });

        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: ''
        }));

        switch (name) {
            case 'name':
                if (!value) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        identifier: 'Name is required.'
                    }));
                } else if (!isNaN(value) || !validateName(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        name: 'Please enter a valid name.'
                    }));
                }
                break;
            case 'age':
                if (!value) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        identifier: 'Age is required.'
                    }));
                } else if (isNaN(updatedValue) || !validateAge(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        age: 'Please enter a valid age, 18 or older.'
                    }));
                }
                break;
            case 'email':
                if (!value) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        identifier: 'Email is required.'
                    }));
                } else if (!validateEmail(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        email: 'Please enter a valid email address.'
                    }));
                }
                break;
            case 'password':
                if (!value) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        identifier: 'Password is required.'
                    }));
                } if (!validatePassword(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        password: 'Please enter a valid password.'
                    }));
                }
                break;
            case 'privacyPolicy':
                if (!validatePrivacyPolicy(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        privacyPolicy: 'Please accept our privacy policy to sign up.'
                    }));
                }
                break;
            default:
                break;
        }
    };
    //#endregion

    //#region HANDLE SUBMIT
    const handleSubmit = async (e) => {
        e.preventDefault();
        const newErrors = {
            name: '',
            age: '',
            email: '',
            password: '',
            privacyPolicy: '',
            generic: ''
        };

        if (!validateName(registrationInfo.name)) {
            newErrors.name = 'Please enter a valid name.';
        }
        if (!validateAge(registrationInfo.age)) {
            newErrors.age = 'Please enter a valid age.';
        }
        if (!validateEmail(registrationInfo.email)) {
            newErrors.email = 'Please enter a valid email address.';
        }
        if (!validatePassword(registrationInfo.password)) {
            newErrors.password = 'Please enter a valid password.';
        }
        if (!validatePrivacyPolicy(registrationInfo.privacyPolicy)) {
            newErrors.privacyPolicy = 'Please accept our privacy policy.';
        }
        if (Object.values(newErrors).some(error => error)) {
            setErrors(newErrors);
        } else {
            try {
                const result = await signupService(registrationInfo);
                if (result.success) {

                    login({ token: result.token, user: result.userDTO });

                    window.sessionStorage.setItem("token", result.token);
                    window.sessionStorage.setItem("userRol", result.userDTO.roles);
                    window.sessionStorage.setItem("isLogged", true);

                    window.location.href = "/home";

                }
            } catch (error) {
                setErrors({ generic: error.message });
            }
        }
    };
    //#endregion

    //#region HTML
    return (
        <div className='main-container'>
            <form onSubmit={handleSubmit} className="signup-container" style={{ borderColor: theme.gray7, borderWidth: '1px', borderStyle: 'solid' }}>
                <h1 style={{ color: theme.grayA12 }}>Welcome to <span style={{ color: theme.tealA11 }}>FitConnet</span></h1>
                <section className='error-container'>
                    {errors.name && <div className="error-message">{errors.name}</div>}
                    {errors.age && <div className="error-message">{errors.age}</div>}
                    {errors.email && <div className="error-message">{errors.email}</div>}
                    {errors.password && <div className="error-message">{errors.password}</div>}
                    {errors.privacyPolicy && <div className="error-message">{errors.privacyPolicy}</div>}

                </section>
                <div className="input-container">
                    <input
                        type="text"
                        name="name"
                        value={registrationInfo.name}
                        onChange={handleChange}
                        placeholder='Name'
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}
                        required
                    />

                    <input
                        type="number"
                        name="age"
                        value={registrationInfo.age}
                        onChange={handleChange}
                        placeholder='Age'
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}
                        required
                    />

                    <input
                        type="email"
                        name="email"
                        value={registrationInfo.email}
                        onChange={handleChange}
                        placeholder='Email'
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}
                        required
                    />

                    <input
                        type="password"
                        name="password"
                        value={registrationInfo.password}
                        onChange={handleChange}
                        placeholder='Password'
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}

                        required
                    />
                    <div>
                        <label>
                            <input
                                type="checkbox"
                                name="privacyPolicy"
                                checked={registrationInfo.privacyPolicy}
                                onChange={handleChange}
                                className='check'
                            />
                            <span style={{ color: theme.gray11 }} className='small-text'>Agree the <span style={{ color: '#0071F6' }} className='term-link small-text'>Terms and conditions.</span></span>
                        </label>
                        <label>
                            <input
                                type="checkbox"
                                name="rememberMe"
                                checked={registrationInfo.rememberMe}
                                onChange={handleChange}
                                className='check'
                            />
                            <span style={{ color: theme.gray11 }} className='small-text'>Save this information for next time.</span>
                        </label>
                    </div>
                </div>
                <button type="submit" className='submit-btn' style={{ background: theme.teal11, color: theme.gray7, borderColor: theme.gray6 }}>Sign Up</button>
            </form>

            <div className="register-link">
                <Link to="/login" style={{ color: theme.teal11 }}>Already registered?</Link>
            </div>
            <section>
                {errors.generic && <div className="error-message">{errors.generic}</div>}

            </section>
        </div>
    );
    //#endregion
};

export default SignupForm;
