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
curl -v -X POST http://localhost:8080/obligations -H 'Content-type:application/json' -d '{"creditorId": 1, "debtorId": 54,"cost": 24073, "paymentDate": "2023-02-27"}' | json_pp

Отменить долг (если он в состоянии CONTRACT_IS_CONCLUDED)
curl -v -X DELETE http://localhost:8080/obligations/1/cancel | json_pp



## TODO

Добавить сущность "Долг" со статусами
Пока:
- без информации о договоре
- эквивалентность означает эквивалентность всех полей, то есть изменения суммы, статуса, даты и т.д. сделает долг иным (подумать об этом!)

Добавить логи 
Нарисовать переходы состояний статусов и закодировать их соответственно в RestController и Assembler

Обеспечить уникальность пары ИНН + КПП

Тесты

P.S. База данных поднимается отдельно
