# DAO (Data Access Object):

O padrão DAO é utilizado para isolar a lógica de acesso a dados do restante da aplicação. Ele fornece uma abstração para o acesso a um banco de dados ou outra fonte de dados. O principal objetivo é encapsular as operações de leitura, gravação, atualização e exclusão de dados, proporcionando uma interface simples para o restante do código.

```java
// Neste exemplo, UserDao é uma interface que define operações comuns de acesso a dados para a entidade User.
public interface UserDao {
  User findById(int userId);
  List<User> findAll();
  void save(User user);
  void update(User user);
  void delete(int userId);
}

```

# DTO (Data Transfer Object):

O padrão DTO é usado para transferir dados entre camadas do sistema. Ele permite encapsular dados em uma classe simples que não possui lógica de negócios, sendo usada apenas para transporte de dados. O DTO ajuda a evitar o acoplamento entre camadas e a reduzir a quantidade de dados transferidos.

```java
// Neste exemplo, UserDto é uma classe simples que carrega dados da entidade User.
// Pode ser usada para transferir dados entre o backend e o frontend,
// por exemplo, sem expor diretamente a estrutura interna da entidade User.
public class UserDto {
  private int userId;
  private String username;
  private String email;

  // Getters e Setters
}

```

# Principais Diferenças:

## Propósito:

- DAO: Lida com a abstração e encapsulamento do acesso a dados.
- DTO: Lida com a transferência eficiente de dados entre camadas da aplicação.

## Conteúdo:

- DAO: Contém métodos para interagir diretamente com a fonte de dados (por exemplo, banco de dados).
- DTO: Contém apenas campos de dados e métodos de acesso (getters e setters), sem lógica de negócios.

## Camada de Aplicação:

- DAO: Pertence à camada de acesso a dados.
- DTO: Pertence à camada de transferência de dados entre camadas.
