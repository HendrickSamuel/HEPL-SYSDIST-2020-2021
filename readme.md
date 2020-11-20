# Laboratoire systèmes distribués final

#### Service de shopping en micro services



#### Architecture proposée au prof et acceptée

![](./architecture.jpg)

La partie GRPC et SOAP sont **optionnels** selon le temps restant. 



#### micro-service TVA

| fonctionnalité        | requête (url)         | note                                          |
| --------------------- | --------------------- | --------------------------------------------- |
| getListTvaAndCategory | http://service/       |                                               |
| getTvaByCategory      | http://service/{type} | renvoi de catégorie **autre** en cas d'erreur |



#### micro-service STOCK

| fonctionnalité | requête(url)                              | note                                                      |
| -------------- | ----------------------------------------- | --------------------------------------------------------- |
| getAllItems    | http://service/items/                     |                                                           |
| getItemDetails | http://service/items/{item-id}            |                                                           |
| getStockOfItem | http://service/stock/{item-id}/{quantity} | réponse similaire au précédent + boolean de disponibilité |
| ??             | ??                                        | un moyen de retirer du stock ?                            |

 

