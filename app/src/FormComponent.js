import React from 'react';
import axios from 'axios';

export default function Form({ setWords }) {

    const handleSubmit = async e => {

        e.preventDefault();

        const formData = new FormData();
        formData.append('text', e.target.text.value);
        formData.append('file', e.target.file.files[0]);

        const config = { headers: { 'content-type': 'multipart/form-data' } }

        try {
            const response = await axios.post('/getTopWords', formData, config);
            setWords(response.data);

        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className="form-words">
            <form onSubmit={handleSubmit}>

                <label className="app-label" htmlFor="text">Text/Url:</label>
                <br />
                <textarea className="app-textarea" id="text" />
                <hr />

                <label className="app-label" htmlFor="file">File Upload:</label>
                <br />
                <input type="file" id="file" />
                <hr />

                <button type="submit">Submit</button>
            </form>
        </div>
    );
};