
import re
import pandas as pd

def readCsv(csvFile):
    file = pd.read_csv(csvFile)
    return file

def definePattern(patterns):
    pattern = re.compile(patterns)
    return pattern

def substitute(file,pattern,answer):
    for j in range( len(file.columns)):
            for i in range( len(file)):
                new = file.iloc[i,j] = re.sub(pattern,answer,file.iloc[i,j])    
                return new
     
file = readCsv('corpus-q1.csv')






pattern = definePattern(r'(S|P)(123)_')
arquivo = substitute(file,pattern,'\2\1_')
pattern = definePattern(r'_(S|P)(123)')
arquivo = substitute(file,pattern,'_\2\1')





pattern = definePattern(r'\++')

arquivo = substitute(file,pattern,'+')



pattern = definePattern(r'\s+')

arquivo = substitute(file,pattern,' ')




pattern = definePattern(r'(\d+)(\-+)(\d+)')

arquivo = substitute(file,pattern,'\1\2') 



pattern = definePattern(r'\s+(?=_CIDADE|_ESTADO|_PAÍS)')

arquivo = substitute(file,pattern,'') 



pattern = definePattern(r'\s+(?=\(+|\(-)')

arquivo = substitute(file,pattern,'') 



pattern = definePattern(r'(?i)(?<=não|nao) ')

arquivo = substitute(file,pattern,'_') 




pattern = definePattern(r'(?i)_(?=famoso|famosa)')

arquivo = substitute(file,pattern,'&') 



pattern = definePattern(r'\-+')

arquivo = substitute(file,pattern,'-')


pattern = definePattern(r'\s+')

arquivo = substitute(file,pattern,' ')




