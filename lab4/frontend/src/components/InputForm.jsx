import React, { useState } from "react";
import '../style/ErrorMessage.css';

const InputForm = ({ x, setX, y, setY, r, setR, onSubmit }) => {
    const [errorMessage, setErrorMessage] = useState("");

    const handleYChange = (value) => {
        setY(value);
        if (value === "") {
            setErrorMessage("Y coordinate is required.");
        } else if (isNaN(value) || value < -5 || value > 3) {
            setErrorMessage("Y must be a number between -5 and 3.");
        } else {
            setErrorMessage("");
        }
    };

    const handleSubmit = () => {
        if (y === "" || isNaN(y) || y < -5 || y > 3) {
            setErrorMessage("Please provide a valid Y coordinate.");
            return;
        }
        setErrorMessage("");
        onSubmit();
    };

    return (
        <div className="input-form">
            <label>
                X:
                <select value={x} onChange={(e) => setX(e.target.value)}>
                    {["-4", "-3", "-2", "-1", "0", "1", "2", "3", "4"].map((value) => (
                        <option key={value} value={value}>
                            {value}
                        </option>
                    ))}
                </select>
            </label>
            <label>
                Y:
                <input
                    type="text"
                    value={y}
                    onChange={(e) => handleYChange(e.target.value)}
                    placeholder="(-5 ... 3)"
                />
                {errorMessage && <p className="error-message">{errorMessage}</p>}
            </label>
            <label>
                R:
                <select value={r} onChange={(e) => setR(e.target.value)}>
                    {["-4", "-3", "-2", "-1", "0", "1", "2", "3", "4"].map((value) => (
                        <option key={value} value={value}>
                            {value}
                        </option>
                    ))}
                </select>
            </label>
            <button
                className="submit-button"
                onClick={handleSubmit}
            >
                go
            </button>
        </div>
    );
};

export default InputForm;
