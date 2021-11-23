# coinbase-java-api

## How to use

### Create a client 
``` 
CoinbaseClientBuilder builder = new CoinbaseClientBuilder(apiKey, secret); 
CoinbaseClient client = builder.create(); `
```
### User

Get current user’s public information. To get user’s email or private information,
use permissions wallet:user:email and wallet:user:read. If current request has a wallet:transactions:send scope,
then the response will contain a boolean sends_disabled field that indicates if the user’s send
functionality has been disabled.

```
CbUser user = client.getUser();
```
To update some of the user information you can use a CbUserUpdateBuilder.

```
CbUserUpdateBuilder builder = new CbUserUpdateBuilder(u);
builder.setName("antlen");
client.updateUser(builder.build());
```

### Accounts

Lists current user’s accounts to which the authentication method has access to.

```
List<CbAccount> accounts client.getAccounts();
```
Show current user’s account. To access the primary account for a given currency,
a currency string (BTC or ETH) can be used instead of the account id in the URL.

```
CbAccount account = client.getAccount(id);
```

Modifies user’s account name.

```

CbAccountUpdateRequest req = new CbAccountUpdateRequest(accountId, "antlen's BTC account.")
CbAccount updateAccountName(req);
```

Removes user’s account. 

```
boolean deleted = client.deleteAccount(d);
```

### Prices
Gets the current buy price from the exchange

```
CbPrice buy = client.getPrice(PriceType.BUY, "BTC-USD");
```

Gets the current sell price from the exchange

```
CbPrice sell = client.getPrice(PriceType.SELL, "BTC-USD");
```

Gets the current spot price from the exchange

```
CbPrice spot = client.getPrice(PriceType.SPOT, "BTC-USD");
```

Gets the spot price on the date from the exchange

```
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date = LocalDate.parse("2019-01-01", format);
CbPrice spot = client.getSpotPrice("BTC-USD", date);
```
  
List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).

```
List<CbCurrencyCode> codes = client.getCurrencyCodes();
```

Get the current exchange rates for the currency provided.

```
CbExchangeRate rates = client.getExchangeRate("BTC");
```

Get the USD exchange rates

```
CbExchangeRate rates = client.getExchangeRate();
```

### Money

Send funds to a bitcoin address, bitcoin cash address, litecoin address, ethereum address,
or email address. No transaction fees are required for off blockchain bitcoin transactions.
It’s recommended to always supply a unique idem field for each transaction.
This prevents you from sending the same transaction twice if there has been an unexpected network outage
or other issue.

```
 CbSendMoneyRequestBuilder b = new CbSendMoneyRequestBuilder()
              .setToAddress(to).setDescription(note)setFrom(from)
              setAmount(amount).setCurrency(currency).setIdem(idem);
 
 CbAddressTransaction transaction = client.sendMoney(b.build());`
```

Requests money from an email address.

```
 CbRequestMoneyRequestBuilder b = new CbRequestMoneyRequestBuilder()
              .setFrom(from).setToEmail(email).setDescription(note).
              setAmount(amount).setCurrency(currency);
 
 CbAddressTransaction transaction = client.reqestdMoney(b.build());`
```

Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
...THE COINBASE API DOES NOT WORK AT THE MOMENT FOR TRANSFER REQUESTS, ITS A COINBASE ISSUE...

```
 CbTransferMoneyRequestBuilder b = new CbTransferMoneyRequestBuilder()
               .setToAccount(to).setDescription(note).setFrom(from)
               .setAmount(amount).setCurrency(currency);
 
 CbAddressTransaction transaction = client.transferMoney(b.build());`
```

### Trading



Places an order to the exchange but do not commit
     
```
 CbOrderRequestBuilder b = CbOrderRequestBuilder.newBuy()
                   .setPaymentMethod(pm).setCommit(commit)
                   .setFrom(from).setAmount(amount).setCurrency(currency)
                   .setCommit(false);
CbTrade trade client.placeOrder(CbOrderRequest request);
```
And then to commit:

```
CbTrade committed = client.commitOrder(trade);
```
Or just set commit to true to commit straight away

```
 CbOrderRequestBuilder b = CbOrderRequestBuilder.newBuy()
                   .setPaymentMethod(pm).setCommit(commit)
                   .setFrom(from).setAmount(amount).setCurrency(currency)
                   .setCommit(true);
CbTrade trade client.placeOrder(CbOrderRequest request);
```

## Related

[coinbase-java-cmdln-app](https://github.com/antlen/coinbase-java-cmdln-app) :A command line app for runinng api calls.
