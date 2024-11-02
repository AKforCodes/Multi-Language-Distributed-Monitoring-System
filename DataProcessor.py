# processor.py
from fastapi import FastAPI
import uvicorn
import requests

app = FastAPI()

@app.post("/process")
async def process_data(data: dict):
    # Perform data processing
    result = analyze_data(data)
    # Send result back to Go server
    requests.post("http://localhost:8080/result", json=result)
    return {"status": "processed"}

def analyze_data(data):
    # Placeholder for analysis logic
    return {"analysis": "some result"}

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
