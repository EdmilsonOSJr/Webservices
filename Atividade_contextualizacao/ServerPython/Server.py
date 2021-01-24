import socket
from Aluno import Aluno 
from Turma import Turma

serverPort = 12345
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverSocket.bind(('127.0.0.1', serverPort))
serverSocket.listen(1)

print("The server is ready to receive")

turmas = []

while True:
    connectionSocket, addr = serverSocket.accept()
    print("Conexão vinda de {}".format(addr))
    
    while True:
        message = connectionSocket.recv(2048).decode()
        
        if(message == ''):
            break
        
        messageTurma = message.split("!!")


        connectionSocket.send('\n'.encode('utf-8'))# mensagem necessária para que as leituras consecutivas 
                                                    # não se juntem no caminho         
        if(messageTurma[0] == '1'):
            turma = Turma(messageTurma[1],messageTurma[2],messageTurma[3])
            turmas.append(turma)
        elif(messageTurma[0] == '2'):
            aluno = Aluno(messageTurma[1],messageTurma[2],messageTurma[3])
            turmas[-1].setAluno(aluno)        

    for tu in turmas:
        print(tu.resposta())
    turmas.clear()
    
    connectionSocket.close()
