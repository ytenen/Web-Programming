import React, { useState, useEffect } from "react";
import Header from "./components/Header.tsx";
import Button from "./components/Button.tsx";
import StatButton from "./components/StatButton.tsx";
import InfoMenu from "./components/InfoMenu.tsx";
import { users, User } from "./data.js";

import "./App.css";

export default function App() {
  const [user, setUser] = useState<number>(0);
  const [kills, setKills] = useState<number>(0);
  const [saves, setSaves] = useState<number>(0);
  const [time, setTime] = useState<Date>(new Date());

  useEffect(() => {
    const interval = setInterval(() => setTime(new Date()), 1000);
    return () => clearInterval(interval);
  }, []);

  function handleClick() {
    setUser(user === 0 ? 1 : 0);
  }

  function updateStatistics() {
    if (users[user].name === "Satoru Gojo") {
      setSaves((prevState) => prevState + 1);
    } else {
      setKills((prevState) => prevState + 1);
    }
  }

  const statistics = users[user].name === "Satoru Gojo" ? saves : kills;

  return (
    <div className="main">
      <Header {...users[user]} time={time.toLocaleTimeString()} />
      <Button onClick={handleClick} name={users[user].name} isActive={false}>
        Switch user
      </Button>
      <InfoMenu userIndex={user} />
      <div>
        <StatButton
          updateStatistics={updateStatistics}
          statistics={statistics}
          name={users[user].name}
        />
      </div>
    </div>
  );
}
