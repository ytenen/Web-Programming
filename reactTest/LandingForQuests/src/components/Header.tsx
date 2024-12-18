import "./header.css";
import React from "react";

interface HeaderProps {
  name: string;
  variant: number;
  group: string;
  time: string;
}

const Header: React.FC<HeaderProps> = ({ name, variant, group, time }) => {
  const headerClass =
    name === "Satoru Gojo" ? "header-satoru" : "header-sukuna";
  return (
    <header className={headerClass}>
      Name: {name} Variant: {variant} Group: {group}
      <br />
      <span>Time: {time}</span>
    </header>
  );
};

export default Header;
