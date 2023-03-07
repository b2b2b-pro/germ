## Прототип проекта на java


# Подёргать за "ручки":

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

# TODO

Добавить сущность "Долг" со статусами

Обеспечить уникальность пары ИНН + КПП

P.S. База данных поднимается отдельно
