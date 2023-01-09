import React, { useState } from 'react';
import Form from './FormComponent';
import MapComponent from './ShowWordsComponent';
import logo from './logo.svg';
import './App.css';

const App = () => {

  const [words, setWords] = useState(new Map());

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>

      <main className="App-main">
        <Form setWords={setWords} />
        <MapComponent words={words} />
      </main>
    </div>
  );
}

export default App;
