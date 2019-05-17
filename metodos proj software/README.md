# Job.Ufpb
Projeto Metodos de projeto de software,  UFPB, junho 2018


Job.Ufpb
Em desenvolvimento pelos alunos do Centro de Informatica da Ufpb: Sabrina Alicrim e Vinicius Guedes, o "Job.Ufpb" é um aplicativo de compartilhamento de vagas de estagio e trabalho, na comunidade academica da Universidade  Federal da Paraiba.


Padrões Aplicados


# ** DAO

O padrão DAO foi aplicado no package infra para servir como um intermediário entre as classes de gerênciae os dados salvos, que nesse caso utiliza um sistema de arquivo.

Classes Utilizadas:
CadastroDAO
PublicacoesDAO
RelatorioDAO


# ** Template Method

O padrão template method foi utilizado para tratar os dois diferentes tipos de relatórios gerados pelos administradores, um relatório em XML e outro em PDF. O programa simula a chamada dos dois métodos dependendo da opção escolhida.


# **Singleton

O padrão singleton foi aplicado nas classes de Gerencia afim de que apenas uma instância dessas classes possam ser criadas durante a execução do projeto.

# **Facade
O padrão Facade foi aplicado no package view para servir como uma interface que agrupa os diferentes métodos de outras classes,



# **Adapter
O padrão adapter foi utilizado como um intermediário entre a aplicação e a API para validação de logins do Facebook, de forma que 


# **Memento

O padrão memento foi utilizado na classe ControleDeUsuario, foi criada uma classe ControledeusuarioMemento que mantém o estado anterior da lista de usuários, de forma que quando o programa insere ou remove um usuário, o memento pode ser utilizado para recuperar o estado anterior da lista onde aquele usuário ainda não foi inserido/removido.


# **Command

O padrão command foi utilizado para implementar a funcionalidade de notificações ao usuario na aplicação.



# Padrões Extras

# **Iterator

O padrão iterator foi aplicado em classes de gerência de forma que uma interface Iterador é criada para cada uma das gerências, com isso as formas de acesso a lista de entidades da gerencia foi substituida para realizar essa atividade com o uso do iterador.

# **Proxy

O padrão proxy foi implementado sobre a classe Administrador,na filha da classe administrador chamada UsuaerioAdministradorProxy, seu objetivo é chamar as funções da classe pai desde que os seus atributos, nesse caso o login e a senha passadas, sejam as desejadas para que o usuário tenha acesso aos comandos de administrador. O padrão proxy garante que os métodos chamados apenas sejam executados caso o usuário tenha permissão para utilizar esses métodos.
