import React from "react";

interface StatButtonProps {
  updateStatistics: () => void;
  statistics: number;
  name: string;
}

const StatButton: React.FC<StatButtonProps> = ({
  updateStatistics,
  statistics,
  name,
}) => {
  if (name === "Satoru Gojo") {
    return (
      <button className="bn13-gojo" onClick={updateStatistics}>
        Saves: {statistics}
      </button>
    );
  }
  return (
    <button className="bn13-sukuna" onClick={updateStatistics}>
      Kills: {statistics}
    </button>
  );
};

export default StatButton;
