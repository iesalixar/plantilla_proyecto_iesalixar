import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signinService } from '../../service/auht/authService';
import './style.scss';
import { useAuth } from '../../authContext/autContext';

const LoginForm = () => {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [loginInfo, setLoginInfo] = useState({
        identifier: '',
        password: '',
        rememberMe: false, // Nuevo estado para recordar la selección del usuario
    });
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        const newValue = type === 'checkbox' ? checked : value; // Manejo especial para checkbox
        setLoginInfo({ ...loginInfo, [name]: newValue });
        if (type !== 'checkbox') {
            validateField(name, newValue); // Solo validación para campos no checkbox
        }
    };

    const validateField = (name, value) => {
        let fieldError = null;
        switch (name) {
            case 'identifier':
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(value)) {
                    fieldError = 'El email debe ser un email válido';
                }
                break;
            case 'password':
                if (value.length < 3 || value.length > 20) {
                    fieldError = 'La contraseña debe tener entre 3 y 20 caracteres';
                }
                break;
            default:
                break;
        }
        setErrors((prevErrors) => ({ ...prevErrors, [name]: fieldError }));
    };

    const handleSubmit = async () => {
        const formErrors = {};
        Object.keys(loginInfo).forEach((name) => {
            validateField(name, loginInfo[name]);
            if (errors[name]) {
                formErrors[name] = errors[name];
            }
        });

        if (Object.keys(formErrors).length > 0) {
            setErrors(formErrors);
            return;
        }

        try {
            const token = await signinService(loginInfo.identifier, loginInfo.password);
            login({ token });
            navigate('/');
        } catch (error) {
            console.error('Error al iniciar sesión:', error.message);
            setErrors({ generic: 'Error al iniciar sesión' });
        }
    };

    return (
        <div className='form-container'>
            <h1>Welcome back to <span style={{ color: '#00666B' }}>FitConnet</span></h1>
            <div className="login-form">
                <input
                    type="text"
                    name="identifier"
                    value={loginInfo.identifier}
                    onChange={handleChange}
                    className={errors.identifier ? 'error' : 'success'}
                    placeholder="Email"
                />
                {errors.identifier && <div className="error-message">{errors.identifier}</div>}
                <input
                    type="password"
                    name="password"
                    value={loginInfo.password}
                    onChange={handleChange}
                    className={errors.password ? 'error' : 'success'}
                    placeholder="Password"
                />
                {errors.password && <div className="error-message">{errors.password}</div>}
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

            <button type="button" className='submit-btn' onClick={handleSubmit}>Sign In</button>
            {errors.generic && <div className="error-message">{errors.generic}</div>}
            <div className="register-link">
                <Link to="/">Forgot the password?</Link>
                <Link to="/register">Create an account?</Link>
            </div>
        </div >
    );
};

export default LoginForm;
