from Aluno import Aluno

class Turma:

    def __init__(self,id,curso,ano):
        self.id = id
        self.ano = ano
        self.curso = curso
        self.alunos = []
        
    
    def getId(self):
        return self.id
    
    def setId(self,id):
        self.id = id
    
    def getAno(self):
        return self.ano

    def setAno(self,ano):
        self.ano = ano

    def getCurso(self):
        return self.curso

    def setCurso(self,curso):
        self.curso = curso

    def  setAluno(self,aluno):
        self.alunos.append(aluno)
        
    def printaAlunos(self):
        message = ""

        for aluno in self.alunos:
            message = message + aluno.__srt__() + " "
            

        return message

    def __srt__(self):
        return "{} {} {} {}".format(self.id, self.ano, self.curso, self.printaAlunos())
    
    def alunosMatriculados(self):
        contador = 0

        for aluno in self.alunos:
            if (aluno.getMatriculado() == "1"):
                contador+=1

        return contador
    
    def resposta(self):
        return "A turma de código {} de {} do curso {} possui {} alunos, dos quais {} estão devidamente matriculados".format(self.id, self.ano ,self.curso, len(self.alunos), self.alunosMatriculados())