# electronic_clock_facial_recognition
A Flask app to register employee's entrances and exits via facial recognition. MongoDB used for storage.

# FERRAMENTAS NECESSARIAS PARA RODAR O PROGRAMA #

- Python 3 (instalado)
	# PACOTES DO PYTHON NECESSARIOS #
	- pymongo
	- cmake
	- pygame
	- flask
	- face_recognition (demora aprox. 10 minutos: recomendado ser 				    a única aplicação rodando)
- MongoDb

# COMO EXECUTAR #

Salvar a sua foto na pasta /src/static/known_people
No terminal, rode o script: app.py
Ao concluir, abra no browser a URL: http://127.0.0.1:4990/

# ARMAZENAMENTO #

O diretório ./src/static/known_people contém a 'base de imagens' de pessoas cadastradas na empresa de ponto. 

Para mudar o funcionário, editar as variáveis base_picture e base_name nas linhas 12 e 13 do arquivo /src/app.py 

Ao tirar a foto, a imagem que foi capturada estará disponível na pasta: ./src/static/unknown_people

Para acessar o a foto disponível no MongoDB pelo terminal:
- mongo
- use ponto
- db.entries.find()    lista todas as entradas
- db.exits.find()      lista todas as saídas





