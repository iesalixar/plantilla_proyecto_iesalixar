import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
//REDUX
import { useDispatch, useSelector } from 'react-redux';
import { signin } from '../../slices/userSlice';
//CONTEXTS
import { ThemeContext } from '../../contexts/themeContexts';
//STYLE
import './style.scss';

const LoginForm = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { theme } = useContext(ThemeContext);

    const { loading, error } = useSelector((state) => state.user);

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

    const validateEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

    const validatePassword = (password) => password.length > 3;

    const handleChange = (e) => {
        const { name, value, checked, type } = e.target;
        const updatedValue = type === 'checkbox' ? checked : value;

        setLoginInfo({
            ...loginInfo,
            [name]: updatedValue
        });

        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: ''
        }));

        if (name === 'identifier' && !validateEmail(value)) {
            setErrors((prevErrors) => ({
                ...prevErrors,
                identifier: 'Please enter a valid email address.'
            }));
        }

        if (name === 'password' && !validatePassword(value)) {
            setErrors((prevErrors) => ({
                ...prevErrors,
                password: 'Password must be more than 3 characters.'
            }));
        }
    };

    const handleFocus = (e) => {
        const { name } = e.target;
        setErrors((prevErrors) => ({
            ...prevErrors,
            [name]: ''
        }));
    };

    const handleBlur = () => { };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newErrors = { identifier: '', password: '', generic: '' };

        if (!validateEmail(loginInfo.identifier)) {
            newErrors.identifier = 'Please enter a valid email address.';
        }

        if (!validatePassword(loginInfo.password)) {
            newErrors.password = 'Password must be more than 3 characters.';
        }

        if (newErrors.identifier || newErrors.password) {
            setErrors(newErrors);
        } else {
            try {
                await dispatch(signin({
                    identifier: loginInfo.identifier,
                    password: loginInfo.password
                })).unwrap();
                navigate('/'); // Navegar a /home después de iniciar sesión correctamente
            } catch (err) {
                setErrors({ generic: err });
            }
        }
    };

    return (
        <div className='main-container'>
            <h1 style={{ color: theme.grayA12 }}>Welcome back to <span style={{ color: theme.tealA12 }}>FitConnet</span></h1>
            <form onSubmit={handleSubmit} className="login-container" style={{ borderColor: theme.gray7, borderWidth: '1px', borderStyle: 'solid' }}>
                <div className="input-container">
                    <input
                        type="text"
                        name="identifier"
                        value={loginInfo.identifier}
                        onChange={handleChange}
                        onFocus={handleFocus}
                        onBlur={handleBlur}
                        placeholder="Email"
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}
                        required
                    />
                    <input
                        type="password"
                        name="password"
                        value={loginInfo.password}
                        onChange={handleChange}
                        onFocus={handleFocus}
                        onBlur={handleBlur}
                        placeholder="Password"
                        style={{ color: theme.gray12, borderColor: theme.gray8 }}
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
                        <span style={{ color: theme.gray11 }} className='small-text'>Save this information for next time.</span>
                    </label>
                </div>
                <button type="submit" className='submit-btn' style={{ background: theme.teal11, color: theme.gray7, borderColor: theme.gray6 }} disabled={loading}>
                    {loading ? 'Signing In...' : 'Sign In'}
                </button>
            </form>

            <div className="register-link">
                <Link to="/" style={{ color: theme.teal11 }}>Forgot the password?</Link>
                <Link to="/register" style={{ color: theme.teal11 }}>Create an account?</Link>
            </div>

            <section>
                {errors.identifier && <div className="error-message">{errors.identifier}</div>}
                {errors.password && <div className="error-message">{errors.password}</div>}
                {errors.generic && <div className="error-message">{errors.generic}</div>}
                {error && <div className="error-message">{error}</div>}
            </section>
        </div>
    );
};

export default LoginForm;
