import React, { useState } from 'react';
import { TextField, Button, Typography } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser, clearError } from '../slices/RegisterSlice';
import '../style/Form.css';
import { useHistory } from 'react-router-dom';
import {MainHeader} from "./MainHeader";

const RegisterForm = ({ onSwitch }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const history = useHistory();

    const dispatch = useDispatch();
    const { loading, error } = useSelector((state) => state.register);

    const handleRegister = () => {
        if (password !== confirmPassword) {
            setPasswordError('Passwords do not match');
            return;
        }
        setPasswordError('');
        dispatch(registerUser({ username, password }))
            .unwrap()
            .then(() => {
                history.push('/main');
            })
            .catch((err) => {
                console.error('Registration failed:', err);
            });
    };

    const handleInputChange = (setter) => (event) => {
        dispatch(clearError());
        setter(event.target.value);
        if (passwordError) setPasswordError('');
    };

    return (
        <div className="form-container">
            <MainHeader/>
            <h1>Register</h1>

            <TextField
                label="Username"
                value={username}
                onChange={handleInputChange(setUsername)}
                fullWidth
                variant="outlined"
            />
            <TextField
                label="Password"
                type="password"
                value={password}
                onChange={handleInputChange(setPassword)}
                fullWidth
                variant="outlined"
            />
            <TextField
                label="Confirm Password"
                type="password"
                value={confirmPassword}
                onChange={handleInputChange(setConfirmPassword)}
                fullWidth
                variant="outlined"
            />
            <Button
                variant="contained"
                color="primary"
                size="large"
                onClick={handleRegister}
                fullWidth
                disabled={loading}
            >
                {loading ? 'Registering...' : 'Register'}
            </Button>
            {passwordError && (
                <Typography color="error" className="error-message">
                    {passwordError}
                </Typography>
            )}
            {error && (
                <Typography color="error" className="error-message">
                    {error}
                </Typography>
            )}
            {!error && (
                <h4>
                    <Button
                        variant="text"
                        color="primary"
                        size="small"
                        onClick={onSwitch}
                    >
                        Login
                    </Button>
                </h4>
            )}
        </div>
    );
};

export default RegisterForm;
