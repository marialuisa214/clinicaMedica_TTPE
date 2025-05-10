from flask import Flask

app = Flask(__name__)

app.config["SECRET_KEY"] = "chave_de_acesso"
app.config["SQLALCHEMY_DATABASE_URI"] = "postgresql://admin:admin123@127.0.0.1:5432/ttpeClinic"

@app.route("/helloWorld", methods=["GET"])
def hello_world():
    return "Hello, World!"

@app.route("/", methods=["GET"])
def root():
    return "o servido esta rodando"


if __name__ == "__main__": 
    app.run(debug=True, host="0.0.0.0", port=5000) 