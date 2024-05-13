import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signinService } from '../../service/auht/authService';
import './style.scss';
import { useAuth } from '../../authContext/autContext';

const LoginForm = () => {
    const navigate = useNavigate();
    const { login } = useAuth();
    const [loginInfo, setLoginInfo] = useState({
        email: '',
        password: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLoginInfo({ ...loginInfo, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = await signinService(loginInfo.email, loginInfo.password);
            login({ token });
            navigate('/home');
        } catch (error) {
            console.error('Error al iniciar sesión:', error.message);
            // Aquí podrías mostrar un mensaje de error al usuario
        }
    };
    return (
        <div>
            <h2>Iniciar Sesión</h2>
            <form onSubmit={handleSubmit}>
                <br />
                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        value={loginInfo.email}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Contraseña:
                    <input
                        type="password"
                        name="password"
                        value={loginInfo.password}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <button type="submit">Iniciar Sesión</button>
            </form>
            <Link to="/register" >
                <div>Create an account</div>
            </Link>

        </div>
    );
};

export default LoginForm;
