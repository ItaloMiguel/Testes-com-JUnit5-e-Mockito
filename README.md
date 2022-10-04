# Teste Mockito e boas práticas
Não é um projeto, apenas fiz ele para estudo de mockito.

# Ferramentas usadas
Java 11
Junit 5
Mockito
Spring boot

# Como rodar o projeto?
Primeira forma é abrindo uma idea e rodando normalmente.
Segunda e mais recomendavel é pelo docker.
   - Primeiro comando: sudo docker run -p 8080:8080 -t mockito
   - Segundo comando: docker run mockito
   
# Como Utilizar a api?

Recomendo rodar ela no postman
Não pode ter 2 usuários com o mesmo email, se tiver vai jogar uma exception mas de forma bonita é claro =)
obs: só criei 2 pessoas então so tem até o id 2

URI padrão: localhost:8080/user :: se voce mudar a porta no docker lembre de mudar aqui tambem, obrigado.

GET : localhost:8080/user 
 - Lista de todas pessoas
 
GET : localhost:8080/user/{id}
 - pega a pessoa pelo id dela
 
POST : localhost:8080/user 
 - adicionar nova pessoa.
 
   - exemplo de pessoa:
        {
          "name": "Pedro",
          "email": "pedroca@gmail.com",
          "password": "123"
        }
PUT : localhost:8080/user/{id} -> para editar usuario existe.
   - exemplo de update: 
        {
          "name": "Pedro Silva",
          "email": "pedroca_silva@gmail.com",
          "password": "123456"
        }
        
DELETE : localhost:8080/user/1
