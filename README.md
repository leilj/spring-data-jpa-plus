## 启用
```
@EnableQuery
@SpringBootApplication
public class DemoApplication {
   ...
}
```

## 通过http
```java
@RequestMapping(value = "/api/accounts", method = RequestMethod.GET)
public Page<Account> query(@RequestBody AccountQuery query) {
    return accountRepository.findAll(query);
}
```

## 服务内查询
```java
public void query() {
    {
        AccountQuery query = new AccountQuery();
        query.setVersion(">10&<=10");
        qurey.setUsername("*demo-%");
        //from account where version > 10 and version <= 10 and username like "demo-%"; 
        accountRepository.findAll(query);
    }
    
    {
        AccountQuery query = new AccountQuery();
        query.setVersion("0|>10");
        //from account where version = 0 or version > 10; 
        accountRepository.findAll(query);
    }
}
```