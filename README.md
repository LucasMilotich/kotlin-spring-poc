### **Test results:**

With no relation is quicker than one relation, and one relation quicker than oneToMany relation

Each method creates an object in db inside a transaction, closes the transaction, and then look for it object by id. 

_disclaimer: bytes in/out can help to the difference, but each object is just a UUID_

`/ --> with one relation`
![Optional Text](result.jpeg)
