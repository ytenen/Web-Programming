import React from "react";
import Button from "./Button.tsx";
import Article from "./Article.tsx";
import "../App.css";
import { users, info } from "../data.js";

interface InfoMenuProps {
  userIndex: number;
}

const InfoMenu: React.FC<InfoMenuProps> = ({ userIndex }) => {
  const [infType, setInfType] = React.useState<"common" | "personal" | "duel">(
      "common",
  );
  const [activeButton, setActiveButton] = React.useState(1);

  function handleInfTypeClick(
      type: "common" | "personal" | "duel",
      buttonId: number,
  ): void {
    setInfType(type);
    setActiveButton(buttonId);
  }

  const articleClass =
      users[userIndex].name === "Satoru Gojo" ? "article-gojo" : "article-sukuna";

  return (
      <>
        <div className="button-container">
          <Button
              onClick={() => handleInfTypeClick("common", 1)}
              name={users[userIndex].name}
              isActive={activeButton === 1}
          >
            Common information
          </Button>
          <Button
              onClick={() => handleInfTypeClick("personal", 2)}
              name={users[userIndex].name}
              isActive={activeButton === 2}
          >
            Personal information
          </Button>
          <Button
              onClick={() => handleInfTypeClick("duel", 3)}
              name={users[userIndex].name}
              isActive={activeButton === 3}
          >
            Best duel
          </Button>
        </div>
        <div className={articleClass}>
          <Article text={info[userIndex][infType]} />
        </div>
      </>
  );
};

export default InfoMenu;
