import React from "react";
import classes from "./button.module.css";

interface ButtonProps {
  onClick: () => void;
  name: string;
  children: string;
  isActive: boolean;
}

const Button: React.FC<ButtonProps> = ({
  onClick,
  name,
  children,
  isActive,
}) => {
  if (name === "Satoru Gojo") {
    return (
      <button
        onClick={onClick}
        className={`${classes.buttonGojo} ${isActive ? classes.cracked : ""}`}
      >
        <span>{children}</span>
      </button>
    );
  }

  return (
    <button
      onClick={onClick}
      className={`${classes.buttonSukuna} ${isActive ? classes.cracked : ""}`}
    >
      <span>{children}</span>
    </button>
  );
};

export default Button;
