# coding: utf-8
import json
import os
from Diretorio import Diretorio
from flask import Flask
from flask import request

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST', 'PUT', 'DELETE'])
def api():
    if request.method == 'GET':
        return json.dumps({'mensagem' : Diretorio().retornaArquivos()})
    else:
        return json.dumps({'erro': 'Método inválido'})

@app.route('/get/<recur>', methods=['GET'])
def get(recur):
    return json.dumps({'mensagem': Diretorio().lerArquivo(recur)})



@app.route('/post', methods=['POST'])
def post():
    return json.dumps({'mensagem': 'Requisição POST recebida'})


@app.route('/put/<recurso>', methods=['PUT'])
def put(recurso):
    return json.dumps({'mensagem': Diretorio().atualizar_criar(recurso, request.form["conteudo"], request.form["operacao"])})


@app.route('/delete/<recurso>', methods=['DELETE'])
def delete(recurso):
    return json.dumps({'mensagem': Diretorio().deletarArquivo(recurso)})



if __name__ == "__main__":
    app.run(debug=True)