import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { signinService } from '../../service/auht/authService';
import './style.scss';

const LoginForm = () => {
    const [loginInfo, setLoginInfo] = useState({ email: '', password: '' });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLoginInfo({ ...loginInfo, [name]: value });
    };

    const handleSubmit = async (e) => { // Haz la función asincrónica para poder esperar la respuesta del servicio
        e.preventDefault();
        try {
            const token = await signinService(loginInfo.email, loginInfo.password); // Llama al servicio con las credenciales
            // Aquí puedes guardar el token en el estado o en el almacenamiento local
            console.log('Token:', token);
            // Redirige al usuario a la página /home
            navigate('/home');
        } catch (error) {
            console.error('Error al iniciar sesión:', error.message);
        }
    };

    return (
        <div>
            <h2>Iniciar Sesión</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Usuario:
                    <input
                        type="text"
                        name="username"
                        value={loginInfo.username}
                        onChange={handleChange}
                    />
                </label>
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
