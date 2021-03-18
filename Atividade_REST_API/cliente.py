import json
import requests
import os

def recuperaNomeArq():
    
    nomeArq = input("Forneça o nome do arquivo com sua devida extensão: ")
    
    return nomeArq

def recuperaConteudo():

    conteudo = input("Forneça o conteudo que será adiciona do arquivo: ")
    
    return conteudo

def menu():

    while(True):
               
        num = input("\nSELECIONE UMA OPÇÃO:\n"+
                "1. Listar arquivos\n"+
                "2. Exibir arquivo\n"+
                "3. Remover arquivo\n"+
                "4. Remover todos os arquivos\n"+
                "5. Criar arquivo\n"+
                "6. Atualizar arquivo\n"+
                "0. Finalizar\n"+
                "Opção: ")
        
        if num=='1':

            r = requests.get('http://127.0.0.1:5000/')
    
        elif num=='2':

            nomeArq = recuperaNomeArq() 

            r = requests.get('http://127.0.0.1:5000/get/'+nomeArq)
        
        elif num=='3':
            
            nomeArq = recuperaNomeArq() 
            
            r = requests.delete('http://127.0.0.1:5000/delete/'+nomeArq)
        
        elif num=='4':
            
            r = requests.delete('http://127.0.0.1:5000/delete/deleteAll')
            
        elif num=='5':

            nomeArq = recuperaNomeArq() 
            conteudo = recuperaConteudo()
            
            r = requests.put('http://127.0.0.1:5000/put/'+nomeArq, data={'conteudo': conteudo, 'operacao': 'criar'})

        elif num=='6':
            
            nomeArq = recuperaNomeArq() 
            conteudo = recuperaConteudo()
            
            r = requests.put('http://127.0.0.1:5000/put/'+nomeArq, data={'conteudo': conteudo, 'operacao': 'atualizar'})


        elif num=='0' or num=='':
            break
        
        else:
            print("\nComando inválido!!")
            

        r = json.loads(r.text)
        resposta = r["mensagem"]
        print(f"\n{resposta}")

        input('\nPress <ENTER> to continue')
        os.system('clear || cls')


menu()