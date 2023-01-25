import React, { useState } from 'react';
import Form from './FormComponent';
import MapComponent from './ShowWordsComponent';
import './App.css';

const App = () => {

  const [words, setWords] = useState(new Map());
  const [wordsCount, setWordsCount] = useState(0);

  return (
    <div className="App">

      <main className="App-main">
        <Form setWords={setWords} setWordsCount={setWordsCount} />
        <MapComponent words={words} count={wordsCount} />
      </main>
    </div>
  );
}

export default App;
