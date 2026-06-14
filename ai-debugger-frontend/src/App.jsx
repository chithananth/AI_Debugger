import { useEffect, useState } from "react";
import axios from "axios";
import ReactMarkdown from "react-markdown";
import "./App.css";

function App() {

  const [codeSnippet, setCodeSnippet] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [response, setResponse] = useState("");
  const [loading, setLoading] = useState(false);

  const [history, setHistory] = useState([]);

  const [difficulty, setDifficulty] = useState("");
  const [technology, setTechnology] = useState("");

  // LOAD HISTORY
  const loadHistory = async () => {

    try {

      const result =
        await axios.get(
          "http://localhost:8080/debug"
        );

      setHistory(result.data.reverse());

    } catch (error) {

      console.log(error);
    }
  };

  useEffect(() => {
    loadHistory();
  }, []);

  // EXTRACT AI INFO
  const extractInfo = (text) => {

    const difficultyMatch =
      text.match(/Difficulty Level.*?(Easy|Medium|Hard)/is);

    const technologyMatch =
      text.match(/Technology Detected.*?\n-?\s*(.*)/i);

    if (difficultyMatch) {
      setDifficulty(difficultyMatch[1]);
    }

    if (technologyMatch) {
      setTechnology(technologyMatch[1]);
    }
  };

  // EXPLAIN ERROR
  const explainError = async () => {

    if (!codeSnippet || !errorMessage) {

      alert("Please fill all fields");

      return;
    }

    try {

      setLoading(true);

      const result =
        await axios.post(
          "http://localhost:8080/debug/explain",
          {
            codeSnippet,
            errorMessage
          }
        );

      setResponse(result.data.aiResponse);

      extractInfo(result.data.aiResponse);

      loadHistory();

    } catch (error) {

      console.log(error);

      alert("Backend connection failed");

    } finally {

      setLoading(false);
    }
  };

  // COPY
  const copyResponse = () => {

    navigator.clipboard.writeText(response);

    alert("Response copied!");
  };

  // OPEN HISTORY
  const openHistory = (item) => {

    setCodeSnippet(item.codeSnippet);

    setErrorMessage(item.errorMessage);

    setResponse(item.aiResponse);

    extractInfo(item.aiResponse);
  };

  return (

    <div className="dashboard">

      {/* SIDEBAR */}

      <div className="sidebar">

        <div className="logo-section">

          <h2>AI Debugger</h2>

          <p>
            Beginner-Friendly Coding Mentor
          </p>

        </div>

        <div className="history-title">

          Previous Analyses

        </div>

        {
          history.map((item) => (

            <div
              key={item.id}
              className="history-card"
              onClick={() => openHistory(item)}
            >

              <h4>
                {item.errorMessage}
              </h4>

              <p>
                {item.codeSnippet.substring(0, 50)}...
              </p>

            </div>
          ))
        }

      </div>

      {/* MAIN CONTENT */}

      <div className="main-content">

        <div className="glass-card">

          <h1>
            AI Error Explainer & Fix Generator
          </h1>

          <textarea
            placeholder="Paste your code here..."
            value={codeSnippet}
            onChange={(e) =>
              setCodeSnippet(e.target.value)
            }
          />

          <textarea
            placeholder="Paste your error here..."
            value={errorMessage}
            onChange={(e) =>
              setErrorMessage(e.target.value)
            }
          />

          <button onClick={explainError}>

            {
              loading
                ? "Analyzing Error..."
                : "Explain Error"
            }

          </button>

          {
            response && (

              <>

                {/* BADGES */}

                <div className="badge-container">

                  <div
                    className={`difficulty-badge ${difficulty.toLowerCase()}`}
                  >
                    Difficulty: {difficulty}
                  </div>

                  <div className="tech-badge">
                    Technology: {technology}
                  </div>

                </div>

                {/* RESPONSE */}

                <div className="response-box">

                  <div className="response-header">

                    <h2>
                      AI Analysis
                    </h2>

                    <button
                      className="copy-btn"
                      onClick={copyResponse}
                    >
                      Copy
                    </button>

                  </div>

                  <ReactMarkdown>
                    {response}
                  </ReactMarkdown>

                </div>

              </>
            )
          }

        </div>

      </div>

    </div>
  );
}

export default App;

