import os
from flask import Flask, request, jsonify
from flask_cors import CORS
import requests
from dotenv import load_dotenv

# Load the secure API key
load_dotenv()
API_KEY = os.getenv("GROK_API_KEY")

app = Flask(__name__)
# Allow Spring Boot (running on port 8080) to talk to this Python script
CORS(app)

@app.route('/chat', methods=['POST'])
def chat():
    # 1. Get the message from Spring Boot
    data = request.json
    user_message = data.get('message', '')

    if not user_message:
        return jsonify({"error": "No message provided"}), 400

    print(f"Java sent us: {user_message}")

    # 2. Setup the request to Grok
    headers = {
        "Authorization": f"Bearer {API_KEY}",
        "Content-Type": "application/json"
    }
    
    payload = {
        "messages": [
            {"role": "system", "content": "You are GrokPulse, a helpful AI assistant."},
            {"role": "user", "content": user_message}
        ],
<<<<<<< HEAD
        "model": "llama3-8b-8192", # Update this if you use a specific model version
=======
        "model": "llama-3.1-8b-instant", # Update this if you use a specific model version
>>>>>>> 54dedb5 (Secure commit: Removed API keys and added gitignore)
        "stream": False
    }

    # 3. Send it to Grok
    try:
        response = requests.post("https://api.groq.com/openai/v1/chat/completions", json=payload, headers=headers)
        
        if response.status_code == 200:
            # Success! Extract the answer
            ai_reply = response.json()['choices'][0]['message']['content']
            return jsonify({"reply": ai_reply})
        else:
            print(f"Grok API Error: {response.text}")
            return jsonify({"reply": "Error: Grok refused the connection."}), 500

    except Exception as e:
        print(f"Connection Error: {e}")
        return jsonify({"reply": "Error: Could not reach the AI server."}), 500

if __name__ == '__main__':
    print("AI Brain is running on port 5000...")
    app.run(port=5000)