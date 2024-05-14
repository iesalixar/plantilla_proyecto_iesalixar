import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signinService } from '../../service/auht/authService';
import './style.scss';
import { useAuth } from '../../authContext/autContext';

const LoginForm = () => {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [loginInfo, setLoginInfo] = useState({
        identifier: '', // Cambiado de 'email' a 'identifier'
        password: '',
    });
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLoginInfo({ ...loginInfo, [name]: value });
        // Limpiar el error cuando el usuario modifica el campo
        setErrors({ ...errors, [name]: undefined });
        // Validar campo a medida que el usuario escribe
        validateField(name, value);
    };

    const validateField = (name, value) => {
        const fieldErrors = {};
        switch (name) {
            case 'identifier': // Cambiado de 'email' a 'identifier'
                if (value.length < 3 || value.length > 20) {
                    fieldErrors.identifier = 'El email o nombre de usuario debe tener entre 3 y 20 caracteres';
                }
                break;
            case 'password':
                if (value.length < 3 || value.length > 20) {
                    fieldErrors.password = 'La contraseña debe tener entre 3 y 20 caracteres';
                }
                break;
            default:
                break;
        }
        setErrors((prevErrors) => ({ ...prevErrors, ...fieldErrors }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validar campos
        const errors = validateFields();
        if (Object.keys(errors).length > 0) {
            // Mostrar mensajes de error y detener el inicio de sesión
            setErrors(errors);
            return;
        }

        try {
            const token = await signinService(loginInfo.identifier, loginInfo.password); // Cambiado de 'email' a 'identifier'
            login({ token });
            navigate('/home');
        } catch (error) {
            console.error('Error al iniciar sesión:', error.message);
            // Mostrar mensaje de error genérico
            setErrors({ generic: 'Error al iniciar sesión' });
        }
    };

    const validateFields = () => {
        const { identifier, password } = loginInfo;
        const errors = {};

        // Validar email o nombre de usuario
        if (identifier.length < 3 || identifier.length > 20) {
            errors.identifier = 'El email o nombre de usuario debe tener entre 3 y 20 caracteres';
        }

        // Validar contraseña
        if (password.length < 3 || password.length > 20) {
            errors.password = 'La contraseña debe tener entre 3 y 20 caracteres';
        }

        return errors;
    };

    return (
        <div className="login-form">
            <h2>Iniciar Sesión</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text" // Cambiado de 'email' a 'text'
                    name="identifier" // Cambiado de 'email' a 'identifier'
                    value={loginInfo.identifier}
                    onChange={handleChange}
                    className={errors.identifier ? 'error' : 'success'}
                    placeholder="Email o Nombre de Usuario" // Cambiado de 'Email' a 'Email o Nombre de Usuario'
                />
                {errors.identifier && <span className="error-message">{errors.identifier}</span>}
                <input
                    type="password"
                    name="password"
                    value={loginInfo.password}
                    onChange={handleChange}
                    className={`${errors.password ? 'error' : 'success'} ${errors.password ? '' : 'success'}`}
                    placeholder="Password"
                />
                {errors.password && <span className="error-message">{errors.password}</span>}
                <button type="submit">Iniciar Sesión</button>
            </form>
            {errors.generic && <div className="error-message">{errors.generic}</div>}
            <div className="register-link">
                <Link to="/register">Crear una cuenta</Link> {/* Cambiado de 'Create an account' a 'Crear una cuenta' */}
            </div>
        </div>
    );
};

export default LoginForm;
