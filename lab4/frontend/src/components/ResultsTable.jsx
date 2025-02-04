import React from 'react';

const ResultsTable = ({ results }) => {
    return (
        <table className="results-table">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Hit</th>
            </tr>
            </thead>
            <tbody>
            {results.map((result, index) => (
                <tr key={index}>
                    <td>{result.x}</td>
                    <td>{result.y}</td>
                    <td>{result.r}</td>
                    <td>{result.hit ? 'Yes' : 'No'}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default ResultsTable;
