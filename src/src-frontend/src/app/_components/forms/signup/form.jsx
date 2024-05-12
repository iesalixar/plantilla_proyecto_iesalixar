"use client";

import React, { useState } from 'react';
import './style.scss';
import { signIn } from '@/app/_services/auth/signup/authService';
import Link from 'next/link'

function SignupForm() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const token = await signIn(email, password);
            localStorage.setItem("token", token);
            console.log("Token:", token);
        } catch (error) {
            setError(error.message);
        }
    };
    return (
        <div className="container">
            <p className='h3'><strong>Welcome to Fitconnect</strong></p>
            {error && <div className="error">{error}</div>}
            <div className='continue-with'>
                <div className='btn-group'>
                    <button type="submit" className='facebook-btn'>Continue with Facebook</button>
                    <button type="submit" className='google-btn'>Continue with Google</button>
                </div>
                <span>Or</span>
            </div>
            <div className="form-container">
                <form className="needs-validation" noValidate onSubmit={handleSubmit}>
                    <div className="row g-3">
                        <div className="col-12">
                            <input type="text" className="form-control" id="firstName" placeholder="Name" value="" required="" />
                            <div className="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>

                        {/* <div class="col-sm-6">
                            <label for="lastName" class="form-label">Last name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required="" />
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div> */}

                        <div className="col-12">
                            <div className="input-group has-validation">
                                <span className="input-group-text">@</span>
                                <input type="text" className="form-control" id="username" placeholder="Username" required="" />
                                <div className="invalid-feedback">
                                    Your username is required.
                                </div>
                            </div>
                        </div>
                        <div className="col-12">
                            <input type="email" className="form-control" id="email" placeholder="you@example.com" />
                            <div className="invalid-feedback">
                                Please enter a valid email address for shipping updates.
                            </div>
                        </div>
                        <div className="col-12">
                            <div className="input-group has-validation">
                                <input type="text" className="form-control" id="username" placeholder="Password" required="" />
                                <div className="invalid-feedback">
                                    Your Password is required.
                                </div>
                            </div>
                        </div>
                        <div className="col-12 mb-4">
                            <label for="age" className="form-label">Age</label>
                            <div className="input-group has-validation">
                                <input type="number" className="form-control" id="age" placeholder="Age" required="" />
                                <div className="invalid-feedback">
                                    Your Age is required.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" id="terms-and-conditions" />
                        <label className="form-check-label" htmlFor="terms-and-conditions">I agree to the <a href="/terms" target="_blank">Terms and Conditions</a></label>
                    </div>

                    <div className="form-check">
                        <input type="checkbox" className="form-check-input" id="save-info" />
                        <label className="form-check-label" htmlFor="save-info">Save this information for next time</label>
                    </div>

                    <hr className="my-4" />

                    <button className="w-100 btn btn-primary btn-lg" type="submit">Continue to signup</button>
                </form>
            </div>
            <div className="container-options">
                <span>
                    <a href="#">Forgot password?</a>
                </span>
                <span>
                    <Link href="/login">Already have an account?</Link> {/* Asegúrate de definir el prop 'to' correctamente */}
                </span>
            </div>
        </div>
    );
}

export default SignupForm;