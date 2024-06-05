# Mobile-bank-api


## eurika servsice
```http://localhost:8761```

## api-gateway
взаимодействие с приложением проводится через шлюз с адресом 
```http://localhost:8765```


### взаимодействие с сервисом аутенфикации; для доступа к нему не требуется наличие токена в хедере запроса, к остальным сервисам он требуется
Получение jwt токена (вход в систему по логину и паролю) (Метод POST)

```http://localhost:8765/auth/token```

Пример JSON для login:
```yaml
{
    "username":"maxim",
    "password":123
}
```

Для регистрации в тело POST запроса нужно добавить JSON, описывающий пользователя, на основе этих данных в таблице для аутенфикации появится строка с юзернеймом, зашифрованным паролем и айди, который будет использоваться в таблице user info (содержащей имя и email) в качестве условного foreign key. (Метод POST)


```http://localhost:8765/auth/register```

Пример JSON для регистрации:
```yaml
{
    "username":"irina",
    "password":123,
    "email":"iraim@mail.ru",
    "name":"Irina Chikipiki"
}
```
Валидация токена (Метод Get)

```http://localhost:8765/auth/validate```

Пример запроса для валидации:

```http://localhost:8765/auth/validate?token=(токен)```

### Взамиодействие с user-info-service

Получение пользователя по id (Метод Get)

```http://localhost:8765/userinfo/{userId}```

Обновление информации о пользователе (Метод PUT)
```http://localhost:8765/userinfo```

Пример JSON
```yaml
{
    "email":"igor@mail.com",
    "name":"egor",
    "user_id":10
}
```

### Взамиодействие с wallet-service

Получение кошелька по id (Метод Get)

```http://localhost:8765/wallet/{walletId}```

Получение всех кошельков определенного пользователя по id ползователя (Метод Get)

```http://localhost:8765/wallet/userwallets/{ownerId}```

Добавление кошелька пользователю (метод POST)

```http://localhost:8765/wallet```

Пример JSON
```yaml
{
    "owner_id":2
}
```

Просмотр баланса кошелька по id
```http://localhost:8765/wallet/balance/{walletId}```


### Взамиодействие с transaction-service

Получение транзакции по id (Метод Get)

```http://localhost:8765/transaction/{transactionId}```


Получение списка транзакци по id кошелька (Метод Get)

```http://localhost:8765/transaction/wallettransactions/walId```

Добавление транзакции (метод POST)

```http://localhost:8765/transaction```

Пример JSON
```yaml
{
    "senderWalletId":4,
    "receiverWalletId":5,
    "amount":10
}
```