import React from 'react';

export default function MapComponent(props) {

    return (
        <div className="list-words">
            <h1>Top 10 words (out of {props.count}):</h1>
            
            {props.words.size !== 0 ? (
                <ol>
                    {Object.entries(props.words).map(([key, value]) => (
                        <li key={key}>{key}: {value}</li>
                    ))}
                </ol>
            ) : (
                <p></p>
            )}
        </div>
    );
}
