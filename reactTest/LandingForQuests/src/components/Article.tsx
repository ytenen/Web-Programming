import React from "react";

interface ArticleProps {
  text: string;
}

const Article: React.FC<ArticleProps> = ({ text }) => {
  return (
    <div>
      <h2>{text}</h2>
    </div>
  );
};

export default Article;
