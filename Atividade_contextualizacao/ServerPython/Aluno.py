class Aluno:
    def __init__(self,id,nome,matriculado):
        self.id = id
        self.nome = nome
        self.matriculado = matriculado
    
    def getId(self):
        return self.id

    def setId(self, id):
        self.id = id
    
    def getNome(self):
        return self.nome

    def setNome(self, nome):
        self.nome = nome
    
    def getMatriculado(self):
        return self.matriculado
    
    def setMatriculado(self, matriculado):
        self.matriculado = matriculado

    def __srt__(self):
        return "{} {} {}".format(self.id, self.nome, self.matriculado)