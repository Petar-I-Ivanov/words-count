# Word Counter - Spring Boot and ReactJS
This project is a simple word counting application that allows users to input a URL, text or a file, and returns the top 10 most common words found in the text.

## Technologies Used
- Java 11
- Spring Boot 2.5.0
- ReactJS 17.0.2
- Axios 0.21.1

## Setup
### Prerequisites
- Java 11
- Node.js and NPM
### Running the Application
Just clone the repository, the "frontend-maven-plugin" will take care to connect front and backend.
### Usage
#### Inputting a URL
To input a URL, simply enter the URL into the input field on the homepage and click the "submit" button. The application will then retrieve the text from the URL and return the top 10 most common words found in the text.

#### Uploading a Text File
To upload a text file, click the "Choose File" button on the homepage and select the text file you wish to upload. Once the file has been selected, click the "submit" button to upload the file and retrieve the top 10 most common words found in the text. (Submited files are stored in {basedir}/resources/static/files/)

#### Inputting Text
To input text directly into the application, simply enter the text into the input field on the homepage and click the "submit" button. The application will then return the top 10 most common words found in the text.
