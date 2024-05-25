import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../authContext/autContext';
import { signupService } from '../../service/auht/authService';

const SignupForm = () => {

    const navigate = useNavigate();
    const { login } = useAuth();

    //#region  SET STATES
    const [registrationInfo, setRegistrationInfo] = useState({
        firstName: '',
        lastName: '',
        username: '',
        age: '',
        email: '',
        password: '',
        privacyPolicy: false,
        rememberMe: false,
    });
    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        username: '',
        age: '',
        email: '',
        password: '',
        privacyPolicy: '',
        generic: ''
    });
    //#endregion

    //#region VALIDATIONS
    const validateFirstName = (firstName) => {
        return firstName.length > 3;
    };

    const validateLastName = (lastName) => {
        return lastName.length > 3;
    };

    const validateUsername = (username) => {
        return username.length > 3;
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
            case 'firstName':
                if (!validateFirstName(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        firstName: 'Please enter a valid name.'
                    }));
                }
                break;
            case 'lastName':
                if (!validateLastName(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        lastName: 'Please enter a valid surname.'
                    }));
                }
                break;
            case 'username':
                if (!validateUsername(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        username: 'Please enter a valid username.'
                    }));
                }
                break;
            case 'age':
                if (!validateAge(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        age: 'Please enter a valid age, 18 or older.'
                    }));
                }
                break;
            case 'email':
                if (!validateEmail(updatedValue)) {
                    setErrors((prevErrors) => ({
                        ...prevErrors,
                        email: 'Please enter a valid email address.'
                    }));
                }
                break;
            case 'password':
                if (!validatePassword(updatedValue)) {
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
    const handleSubmit = async () => {
        const newErrors = {
            firstName: '',
            lastName: '',
            username: '',
            age: '',
            email: '',
            password: '',
            privacyPolicy: '',
            generic: ''
        };

        if (!validateFirstName(registrationInfo.firstName)) {
            newErrors.firstName = 'Please enter a valid name.';
        }
        if (!validateLastName(registrationInfo.lastName)) {
            newErrors.lastName = 'Please enter a valid surname.';
        }
        if (!validateUsername(registrationInfo.username)) {
            newErrors.username = 'Please enter a valid username.';
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
        const registrationInfoWithNumberAge = {
            ...registrationInfo,
            age: parseInt(registrationInfo.age)
        };
        if (Object.values(newErrors).some(error => error)) {
            setErrors(newErrors);
        } else {
            try {
                console.log(registrationInfoWithNumberAge);
                const token = await signupService(registrationInfo);
                login({ token });
                console.log({ token });
                navigate('/');
            } catch (error) {
                console.error('Error al iniciar sesión:', error.message);
                setErrors({ ...errors, generic: 'Error al iniciar sesión' });
            }
        }
    };
    //#endregion

    //#region HTML
    return (
        <div className='form-container'>
            <h1>Welcome to <span style={{ color: '#00666B' }}>FitConnet</span></h1>
            <div className="login-form">
                <input
                    type="text"
                    name="firstName"
                    value={registrationInfo.firstName}
                    onChange={handleChange}
                    placeholder='Firstname'
                    className={errors.firstName ? 'error' : 'success'}
                    required
                />
                {errors.firstName && <div className="error-message">{errors.firstName}</div>}

                <input
                    type="text"
                    name="lastName"
                    value={registrationInfo.lastName}
                    onChange={handleChange}
                    placeholder='Lastname'
                    className={errors.lastName ? 'error' : 'success'}
                />
                {errors.lastName && <div className="error-message">{errors.lastName}</div>}

                <input
                    type="text"
                    name="username"
                    value={registrationInfo.username}
                    onChange={handleChange}
                    placeholder='Username'
                    className={errors.username ? 'error' : 'success'}
                    required
                />
                {errors.username && <div className="error-message">{errors.username}</div>}

                <input
                    type="number"
                    name="age"
                    value={registrationInfo.age}
                    onChange={handleChange}
                    placeholder='Age'
                    className={errors.age ? 'error' : 'success'}
                    required
                />
                {errors.age && <div className="error-message">{errors.age}</div>}

                <input
                    type="email"
                    name="email"
                    value={registrationInfo.email}
                    onChange={handleChange}
                    placeholder='Email'
                    className={errors.email ? 'error' : 'success'}
                    required
                />
                {errors.email && <div className="error-message">{errors.email}</div>}

                <input
                    type="password"
                    name="password"
                    value={registrationInfo.password}
                    onChange={handleChange}
                    placeholder='Password'
                    className={errors.password ? 'error' : 'success'}
                    required
                />
                {errors.password && <div className="error-message">{errors.password}</div>}

                <section>
                    <label>
                        <input
                            type="checkbox"
                            name="privacyPolicy"
                            checked={registrationInfo.privacyPolicy}
                            onChange={handleChange}
                            className='check'
                        />
                        <span style={{ color: '#5E8A8C' }}>Accept the terms and conditions.</span>
                    </label>
                    <label>
                        <input
                            type="checkbox"
                            name="rememberMe"
                            checked={registrationInfo.rememberMe}
                            onChange={handleChange}
                            className='check'
                        />
                        <span style={{ color: '#5E8A8C' }}>Save this information for next time.</span>
                    </label>
                </section>
            </div>
            <button type="button" className='submit-btn' onClick={handleSubmit}>Sign Up</button>
            <div className="register-link">
                <Link to="/login">Already registered?</Link>
            </div>
            <section>
                {errors.privacyPolicy && <div className="error-message">{errors.privacyPolicy}</div>}
                {errors.generic && <div className="error-message">{errors.generic}</div>}

            </section>
        </div>
    );
    //#endregion
};

export default SignupForm;
