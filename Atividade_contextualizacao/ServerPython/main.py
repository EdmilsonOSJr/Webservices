from Turma import Turma 
from Aluno import Aluno

alunos = []

a1 = Aluno(10,"ed",1)
a2 = Aluno(5,"ala",2)

print(a1.__srt__())

alunos.append(a1)
alunos.append(a2)

t1 = Turma(12,"tsi",100,alunos)

print(t1.__srt__())