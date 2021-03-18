import os

class Diretorio:

    def __init__(self, caminho="arquivos"):
        self._caminho = caminho

    def getCaminho(self):
        return self._caminho
 
    def retornaArquivos(self):
        lista = os.listdir(self._caminho)

        nomes = "Arquivos:\n\n\t"

        if len(lista)>0:
            for file in lista:
                nomes+= f"{file}\n\t"
            return nomes
        else:
            return f"O diretório {self._caminho} está vazio."


    def deletarArquivo(self, nomeArq):
        try:
            
            mensagem = f"O arquivo {nomeArq} não foi encontrado."

            if nomeArq == "deleteAll":
                
                dir = os.listdir(self._caminho)

                if len(dir)>0:
                    for file in dir:
                        os.remove(f"{self._caminho}/{file}")
                    mensagem = f"Todos os arquivos do diretório {self._caminho} foram deletados."
                else:
                    mensagem = f"O diretório {self._caminho} está vazio."

            else:
                os.remove(f"{self._caminho}/{nomeArq}")
                mensagem = f"O arquivo {nomeArq} foi deletado."
            
            return mensagem
        
        except Exception:
        
            return mensagem


    def lerArquivo(self, nomeArq):
        
        try:
            
            with open(f"{self._caminho}/{nomeArq}", 'r', encoding="utf-8") as arq: 
                  conteudo = arq.read()   

            return conteudo

        except Exception:
            
            return f"O arquivo {nomeArq} não foi encontrado."
                

    def atualizar_criar(self, nomeArq, conteudo, operacao):

        try:   

            existe = False

            dir = os.listdir(self._caminho)
            for file in dir:
                if file == nomeArq:
                    existe = True
        
            if operacao=='atualizar':
                if existe == True:
                    with open(f"{self._caminho}/{nomeArq}", 'a', encoding="utf-8") as arq:
                        arq.write(conteudo)

                    mensagem = f"O arquivo {nomeArq} foi atualizado com sucesso." 
                    
                else:
                    mensagem = f"O arquivo {nomeArq} não existe !!!" 


            if operacao=='criar':
                if existe == False:
                    with open(f"{self._caminho}/{nomeArq}", 'a', encoding="utf-8") as arq:
                        arq.write(conteudo)

                    mensagem = f"O arquivo {nomeArq} criado com sucesso." 
                    
                else:
                    mensagem = f"O arquivo {nomeArq} já existe !!!" 
            
            
            
            return mensagem

        except Exception:
            
            return "Erro na abertura do arquivo!!"
        
