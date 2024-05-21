// import './App.css';
import ChallengeList from "./Components/ChallengeList";
import { useEffect, useState } from "react";
import axios from 'axios';
import AddChallenge from './Components/AddChallenge';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [challenges, setChallenges] = useState([]);

  const fetchChallenges = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/challenges");
      console.log(response);
      setChallenges(response.data);
      
    } catch (error) {
      console.log(`Error occurred while fetching challenges :: ${error}`);
    }
  };

  useEffect(() => {
    fetchChallenges();

  }, []);

  const handleChallenge = () => {
    fetchChallenges();
  };

  return (
    <div className="container mt-5 mb-4">
      <h1 className="text-center mb-4">Monthly Challenge</h1>
      <AddChallenge onChallengeAdded={handleChallenge}/>
      <ChallengeList challenges={challenges}/>
    </div>
  );
}

export default App;
