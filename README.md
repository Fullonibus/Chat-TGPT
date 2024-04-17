## О проекте:
Базовая реализация телеграм-бота с подключенным API Gemini на Spring. Реализованы поддержка диалога, подключение к множеству пользователей, список команд бота с легким добавлением.


## Начало работы:
### Перед началом: 
- необходимо [создать телеграм-бота](https://core.telegram.org/bots/tutorial) если его еще нет
- создать апи ключ для подключения к Gemini
- подготовить к использованию базы данных MySQL, Redis, MongoDB
###  Установка конфиг-файла:
- spring.datasource.url - url вашей бд MySQL
- spring.datasource.username 
- spring.datasource.password - данные для подключения к юзеру БД
  
- spring.data.mongodb.uri 
- spring.data.mongodb.username=mongodb db 
- spring.data.mongodb.password=mongodb db - аналогично, только для MongoDB
  
- spring.data.redis.host
- spring.data.redis.port
- spring.data.redis.username
- spring.data.redis.password - данные для Redis
  
- token.value= - токен телеграм-бота
- bot.name.value - имя телеграм-бота

- gemini.api.key - API ключ для gemini

### После первого запуска:
- spring.jpa.hibernate.ddl-auto=create меняем на auto
