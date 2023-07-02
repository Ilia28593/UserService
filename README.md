# Auth_Service

*Микросервис отвечающий а прием и обработку данных в репозиторий

* позволяет добавлять
* позволяет обновлять
* позволяет удалять 
* получить список 
* получить по id
* сменить роль пользователя
* получить пользователя по email

    * /**
    * Контроллер отвечающий за получения списка всех пользователей.
    * @return возвращает псписок всех пользователей.
     * */
     * @GetMapping("/all")
     * public ResponseEntity<List<Person>> getPersons() {
     *   return new ResponseEntity<>(service.getAllPersons(), HttpStatus.OK);
     * }

* для отладки подключен Swagger http://localhost:8081/swagger-ui/index.html

![](../../Desktop/2023-07-02_22-55-22.png)