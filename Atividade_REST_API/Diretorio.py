import os

class Diretorio:

    def __init__(self, caminho="arquivos"):
        self._caminho = caminho

    def getCaminho(self):
        return self._caminho
 
    def retornaArquivos(self):
        return os.listdir(self._caminho)
    

    def deletarArquivo(self, arquivo):
        try:
            
            if arquivo == "deleteAll":
                dir = os.listdir(self._caminho)
                for file in dir:
                    os.remove(f"{self._caminho}/{file}")
            else:
                os.remove(f"{self._caminho}/{arquivo}")
            
            return "arquivo(s) deletado(s)."
        
        except Exception:
        
            return "arquivo não existe!!"


    def lerArquivo(self, nomeArq):
        
        try:
            
            with open(f"{self._caminho}/{nomeArq}", 'r', encoding="utf-8") as arq: 
                  conteudo = arq.read()   

            return conteudo

        except Exception:
            
            return "Arquivo informado não existe!!"
                

    def atualizar_criar(self, nomeArq, conteudo, operacao):

        try:   

            existe = False

            dir = os.listdir(self._caminho)
            for file in dir:
                if file == nomeArq:
                    existe = True
        
            if operacao=='atualizar':
                if existe == 1:
                    with open(f"{self._caminho}/{nomeArq}", 'a', encoding="utf-8") as arq:
                        arq.write(conteudo)

                    mensagem = "Arquivo atualizado com sucesso." 
                    
                else:
                    mensagem = "O arquivo não existe !!!" 


            if operacao=='criar':
                if existe == 0:
                    with open(f"{self._caminho}/{nomeArq}", 'a', encoding="utf-8") as arq:
                        arq.write(conteudo)

                    mensagem = "Arquivo criado com sucesso." 
                    
                else:
                    mensagem = "O arquivo já existe !!!" 
            
            
            
            return mensagem

        except Exception:
            
            return "Erro na abertura do arquivo!!"
        
