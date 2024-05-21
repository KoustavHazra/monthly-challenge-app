import { useState } from "react";
import axios from "axios";

function AddChallenge({ onChallengeAdded }) {
    const [month, setMonth] = useState("");
    const [description, setDescription] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/challenge", { month, description });
            setMonth("");
            setDescription("");
            console.log(response.data);
            onChallengeAdded();  // call back function, after submitting a challenge it will update the list automatically.

        } catch (error) {
            console.log(`Error occurred while submitting challenge :: ${error}`);
        }
    };

    return (
        <div className="card my-5">
            <div class="card-header">
                Add New Challenge
            </div>
            <div className="card-body">
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="month" className="form-label">Month</label>
                        <input type="text" 
                                id="month" 
                                value={month} 
                                className="form-control"
                                onChange={(e) => setMonth(e.target.value)} 
                                placeholder="month name"
                                required>
                        </input>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="description" className="form-label">Description</label>
                        <textarea id="description" 
                                    value={description} 
                                    className="form-control"
                                    onChange={(e) => setDescription(e.target.value)} 
                                    placeholder="describe the challenge"
                                    required>
                        </textarea>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    );
};

export default AddChallenge;