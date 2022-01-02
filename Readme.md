CUSTOMER ACCOUNT TRACKER REST SERVICES

End points:

Create Customer:
POST: http://localhost:8086/customer

{
	"customerId":1,
	"customerName":"Sandesh",
	"contactNo":8762970953,
	"email":"sujithha30@gmail.com"

}

Create Account:
 POST: http://localhost:8086/account
 {"accountNo":4568,"balance":2000.0,"currency":"INR","accountType":"SAVINGS","customerId":30}

Get customers:
   Get: http://localhost:8086/customers


Get Acounts:
Get: http://localhost:8086/accounts

Delete customer:
Delete: http://localhost:8086/customer/29

Delete Account:
Delete: http://localhost:8086/account/13

Transfer Money:
PUT: http://localhost:8086/transfer/fromAc/31/toAc/32/amount/10000


Update account: 
PUT: http://localhost:8086/account
 {
        "accountNo": 31,
        "balance": 1000,
        "currency": "INR",
        "accountType": "SAVINGS",
        "customerId": 30
}
    
   
   Update Customer:
   PUT: http://localhost:8086/customer
    {
        "customerId": 34,
        "customerName": "Mahesh",
        "contactNo": 9901051131,
        "email": "sujithha30@gmail.com"
    } 






