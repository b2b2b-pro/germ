# Прототип проекта на java

curl -v http://localhost:8080/organizations

curl -v http://localhost:8080/organizations/1 | json_pp

curl -X POST localhost:8080/organizations -H 'Content-type:application/json' -d '{"shortName": "фирмА3", "fullName": "Тестовая компания3","inn": "247778883", "kpp": "241113"}'

curl -X PUT localhost:8080/organizations/53 -H 'Content-type:application/json' -d '{"shortName": "СФУ", "fullName": "Во истину"}'

curl -X DELETE localhost:8080/organizations/52
