// script.js
const questions = [
    { question: "Do you enjoy social gatherings?", dimension: "E", weight: 1 },
    { question: "Do you focus more on facts than concepts?", dimension: "S", weight: 1 },
    { question: "Do you make decisions based on logic?", dimension: "T", weight: 1 },
    { question: "Do you prefer structured plans over spontaneity?", dimension: "J", weight: 1 },
];

let currentQuestionIndex = 0;
let scores = { E: 0, I: 0, S: 0, N: 0, T: 0, F: 0, J: 0, P: 0 };

const questionElement = document.getElementById("question");
const questionContainer = document.getElementById("question-container");
const resultContainer = document.getElementById("result-container");
const resultElement = document.getElementById("result");

function displayQuestion() {
    const questionData = questions[currentQuestionIndex];
    questionElement.textContent = questionData.question;
}

function chooseAnswer(answer) {
    const dimension = questions[currentQuestionIndex].dimension;
    const weight = questions[currentQuestionIndex].weight;

    if (answer === "A") {
        scores[dimension] += weight;
    } else {
        scores[getOppositeDimension(dimension)] += weight;
    }

    currentQuestionIndex++;

    if (currentQuestionIndex < questions.length) {
        displayQuestion();
    } else {
        calculateResult();
    }
}

function getOppositeDimension(dimension) {
    const opposites = { E: "I", S: "N", T: "F", J: "P" };
    return opposites[dimension];
}

function calculateResult() {
    const result =
        (scores.E >= scores.I ? "E" : "I") +
        (scores.S >= scores.N ? "S" : "N") +
        (scores.T >= scores.F ? "T" : "F") +
        (scores.J >= scores.P ? "J" : "P");

    resultElement.textContent = result;
    questionContainer.classList.add("hidden");
    resultContainer.classList.remove("hidden");
}

function restart() {
    currentQuestionIndex = 0;
    scores = { E: 0, I: 0, S: 0, N: 0, T: 0, F: 0, J: 0, P: 0 };
    questionContainer.classList.remove("hidden");
    resultContainer.classList.add("hidden");
    displayQuestion();
}

// Initialize the first question
displayQuestion();
