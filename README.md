# Прототип проекта на java


## Подёргать за "ручки":

Информация по организации:
curl -v http://localhost:8080/organizations/1 | json_pp

Информация по всем организациям:
curl -v http://localhost:8080/organizations | json_pp

Добавить организацию:
curl -v -X POST localhost:8080/organizations -H 'Content-type:application/json' -d '{"shortName": "Шорт073", "fullName": "Фулл 073","inn": "24073", "kpp": "073"}' | json_pp

Обновить поля в организации
curl -v -X PUT localhost:8080/organizations/53 -H 'Content-type:application/json' -d '{"shortName": "СФУ", "fullName": "Во истину"}' | json_pp

Удалить организацию
curl -v -X DELETE localhost:8080/organizations/52


Информация по всем долгам:
curl -v http://localhost:8080/obligations | json_pp

Информация по одному долгу:
curl -v http://localhost:8080/obligations/1 | json_pp

Добавить долг:
curl -v -X POST http://localhost:8080/obligations -H 'Content-type:application/json' -d '{"creditor": {"id":153}, "debtor": {"id":203},"cost": 24073, "paymentDate": "2023-02-27"}' | json_pp

Отменить долг (если он в состоянии CONTRACT_IS_CONCLUDED)
curl -v -X DELETE http://localhost:8080/obligations/1/cancel | json_pp



## TODO

Нарисовать переходы состояний статусов и закодировать их соответственно в RestController и Assembler

Подумать:
- нужно ли при попытке добавить существующую в базе пару ИНН+КПП обрабатывать исключния и ошибки
или оставить как есть? (Клиенту возвращается 500)
- чем определяется эквивалентность долгов?

Добавить статусы компании, например, "банкрот".

Тесты

P.S.
- База данных поднимается отдельно
- логи пока только в rest controller'ах, чисто информативные о вызовах функций
- в таблице organization пара ИНН + КПП уникальная, нужно ли обрабатывать исключния и ошибки или оставить?
- Добавил сущность "Долг" со статусами. Статусы и переходы не все, доделать. Пока без информации о договоре.
- эквивалентность означает эквивалентность всех полей, то есть изменения суммы, статуса, даты и т.д. сделает долг иным (подумать об этом!)
- Долг ссылается на дебитора и кредитора. Компания, явлюящаяся участником долга, не удаляется.
