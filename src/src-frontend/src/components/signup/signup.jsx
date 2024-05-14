import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const SignupForm = () => {
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

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        const newValue = type === 'checkbox' ? checked : value;
        setRegistrationInfo({ ...registrationInfo, [name]: newValue });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // Aquí puedes agregar la lógica para enviar los datos de registro al servidor
        console.log('Datos de registro:', registrationInfo);
        // También puedes restablecer el formulario después de enviar los datos
        setRegistrationInfo({
            firstName: '',
            lastName: '',
            username: '',
            age: '',
            email: '',
            password: '',
            privacyPolicy: false,
            rememberMe: false,
        });
    };

    return (
        <div>
            <h2>Registro</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Nombre:
                    <input
                        type="text"
                        name="firstName"
                        value={registrationInfo.firstName}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Apellido:
                    <input
                        type="text"
                        name="lastName"
                        value={registrationInfo.lastName}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Usuario:
                    <input
                        type="text"
                        name="username"
                        value={registrationInfo.username}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Edad:
                    <input
                        type="number"
                        name="age"
                        value={registrationInfo.age}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Email:
                    <input
                        type="email"
                        name="email"
                        value={registrationInfo.email}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    Contraseña:
                    <input
                        type="password"
                        name="password"
                        value={registrationInfo.password}
                        onChange={handleChange}
                    />
                </label>
                <br />
                <label>
                    <input
                        type="checkbox"
                        name="privacyPolicy"
                        checked={registrationInfo.privacyPolicy}
                        onChange={handleChange}
                    />
                    Acepto la política de privacidad
                </label>
                <br />
                <label>
                    <input
                        type="checkbox"
                        name="rememberMe"
                        checked={registrationInfo.rememberMe}
                        onChange={handleChange}
                    />
                    Recordar datos de inicio de sesión
                </label>
                <br />
                <button type="submit">Registrarse</button>
            </form>
            <Link to="/login" >
                <div>Login</div>
            </Link>

        </div>
    );
};

export default SignupForm;
