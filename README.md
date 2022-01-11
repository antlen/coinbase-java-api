# coinbase-java-api

### Maven
```
        <dependency>
            <groupId>org.estonlabs</groupId>
            <artifactId>coinbase-java-api</artifactId>
            <version>1.3</version>
        </dependency>
```

## How to use

### Create a synchronous client 
``` 
CoinbaseClientBuilder builder = new CoinbaseClientBuilder(apiKey, secret); 
CoinbaseRestClient client = builder.buildRestClient(); 
```
### Create an asynchronous client 
``` 
CoinbaseClientBuilder builder = new CoinbaseClientBuilder(apiKey, secret); 
CoinbaseRestClient asyncClient = builder.buildAsyncRestClient(Executors.newSingleThreadScheduledExecutor());
``` 
or
``` 
CoinbaseClientBuilder builder = new CoinbaseClientBuilder(apiKey, secret); 
CoinbaseClient aSyncClient = builder.buildAsyncRestClient();
``` 

### User

Get current user’s public information. To get user’s email or private information,
use permissions wallet:user:email and wallet:user:read. If current request has a wallet:transactions:send scope,
then the response will contain a boolean sends_disabled field that indicates if the user’s send
functionality has been disabled.

```
CbUserResponse user = syncClient.getUser();
```
or
```
aSyncClient.fetchUser(new CoinbaseCallback<CbUserResponse>() {
     @Override
     public void onResponse(CbUserResponse cbUser, boolean more) {
           //here is the user         
     }

     @Override
     public void failed(Throwable throwable) {
          //called when there was an api failure.
     }
});

```
To update some of the user information you can use a CbUserUpdateBuilder.

```
CbUserUpdateBuilder builder = new CbUserUpdateBuilder(u);
builder.setName("antlen");
syncClient.updateUser(builder.build());
```
or
```
CbUserUpdateBuilder builder = new CbUserUpdateBuilder(u);
builder.setName("antlen");

aSyncClient.updateUser(builder.build(), new CoinbaseCallback<CbUserResponse>() {
  @Override
  public void onResponse(CbUserResponse cbUser, boolean more) {

  }
  @Override
   public void failed(Throwable throwable) {

   }
  });

```
## Other API calls

All API calls below have an async equivilent but for simplicity have been omitted from the documentation.

### Accounts

Lists current user’s accounts to which the authentication method has access to.

```
List<CbAccount> accounts syncClient.getAccounts().getData();
```
Show current user’s account. To access the primary account for a given currency,
a currency string (BTC or ETH) can be used instead of the account id in the URL.

```
CbAccount account = syncClient.getAccount(id).getData();
```

Modifies user’s account name.

```

CbAccountUpdateRequest req = new CbAccountUpdateRequest(accountId, "antlen's BTC account.")
CbAccount syncClient.updateAccountName(req).getData();
```

Removes user’s account. 

```
boolean deleted = syncClient.deleteAccount(d);
```

### Prices
Gets the current buy price from the exchange

```
CbPrice buy = syncClient.getPrice(PriceType.BUY, "BTC-USD").getData();
```

Gets the current sell price from the exchange

```
CbPrice sell = syncClient.getPrice(PriceType.SELL, "BTC-USD").getData();
```

Gets the current spot price from the exchange

```
CbPrice spot = syncClient.getPrice(PriceType.SPOT, "BTC-USD").getData();
```

Gets the spot price on the date from the exchange

```
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
LocalDate date = LocalDate.parse("2019-01-01", format);
CbPrice spot = syncClient.getSpotPrice("BTC-USD", date).getData();
```
  
List known currencies. Currency codes will conform to the ISO 4217 standard where possible.
Currencies which have or had no representation in ISO 4217 may use a custom code (e.g. BTC).

```
List<CbCurrencyCode> codes = syncClient.getCurrencyCodes().getData();
```

Get the current exchange rates for the currency provided.

```
CbExchangeRate rates = syncClient.getExchangeRate("BTC").getData();
```

Get the USD exchange rates

```
CbExchangeRate rates = syncClient.getExchangeRate().getData();
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
 
 CbAddressTransaction transaction = syncClient.sendMoney(b.build()).getData();`
```

Requests money from an email address.

```
 CbRequestMoneyRequestBuilder b = new CbRequestMoneyRequestBuilder()
              .setFrom(from).setToEmail(email).setDescription(note).
              setAmount(amount).setCurrency(currency);
 
 CbAddressTransaction transaction = syncClient.reqestdMoney(b.build()).getData();`
```

Transfer bitcoin, bitcoin cash, litecoin or ethereum between two of a user’s accounts. Following transfers are allowed:
...THE COINBASE API DOES NOT WORK AT THE MOMENT FOR TRANSFER REQUESTS, ITS A COINBASE ISSUE...

```
 CbTransferMoneyRequestBuilder b = new CbTransferMoneyRequestBuilder()
               .setToAccount(to).setDescription(note).setFrom(from)
               .setAmount(amount).setCurrency(currency);
 
 CbAddressTransaction transaction = syncClient.transferMoney(b.build()).getData();`
```

### Trading



Places an order to the exchange but do not commit
     
```
 CbOrderRequestBuilder b = CbOrderRequestBuilder.newBuy()
                   .setPaymentMethod(pm).setCommit(commit)
                   .setFrom(from).setAmount(amount).setCurrency(currency)
                   .setCommit(false);
CbTrade trade syncClient.placeOrder(CbOrderRequest request).getData();
```
And then to commit:

```
CbTrade committed = syncClient.commitOrder(trade).getData();
```
Or just set commit to true to commit straight away

```
 CbOrderRequestBuilder b = CbOrderRequestBuilder.newBuy()
                   .setPaymentMethod(pm).setCommit(commit)
                   .setFrom(from).setAmount(amount).setCurrency(currency)
                   .setCommit(true);
CbTrade trade syncClient.placeOrder(CbOrderRequest request).getData();
```

## Related

[coinbase-java-cmdln-app](https://github.com/antlen/coinbase-java-cmdln-app) :A command line app for runinng api calls.
