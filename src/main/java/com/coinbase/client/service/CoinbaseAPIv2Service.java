package com.coinbase.client.service;

import com.coinbase.domain.account.request.CbAccountUpdateRequest;
import com.coinbase.domain.account.response.CbAccountListResponse;
import com.coinbase.domain.account.response.CbAccountResponse;
import com.coinbase.domain.address.request.CbCreateAddressRequest;
import com.coinbase.domain.address.response.CbAddressListResponse;
import com.coinbase.domain.address.response.CbAddressResponse;
import com.coinbase.domain.address.response.CbAddressTransactionListResponse;
import com.coinbase.domain.address.response.CbAddressTransactionResponse;
import com.coinbase.domain.order.request.CbOrderRequest;
import com.coinbase.domain.price.response.CbCurrencyCodeListResponse;
import com.coinbase.domain.price.response.CbExchangeRateResponse;
import com.coinbase.domain.price.response.CbPriceResponse;
import com.coinbase.domain.system.response.CbTimeResponse;
import com.coinbase.domain.trade.request.CbCashTransactionRequest;
import com.coinbase.domain.trade.response.CbCashTransactionListResponse;
import com.coinbase.domain.trade.response.CbCashTransactionResponse;
import com.coinbase.domain.trade.response.CbTradeListResponse;
import com.coinbase.domain.trade.response.CbTradeResponse;
import com.coinbase.domain.transaction.request.CbMoneyRequest;
import com.coinbase.domain.transaction.response.CbPaymentMethodListResponse;
import com.coinbase.domain.transaction.response.CbPaymentMethodResponse;
import com.coinbase.domain.user.request.CbUserUpdateRequest;
import com.coinbase.domain.user.response.CbUserResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;


/**
 * The MIT License (MIT)
 *
 *	Copyright (c) 2021 antlen
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 * ------------------------------------------------
 * The main service for the rest api. The implementation will be generated as a proxy.
 *
 * @author antlen
 */
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
@Path("/v2")
public interface CoinbaseAPIv2Service{

    @GET
    @Path("/user")
    CompletionStage<CbUserResponse> getUser();

    @GET
    @Path("/users/{user}")
    CompletionStage<CbUserResponse> getUser(@PathParam("user") String userId);

    @GET
    @Path("/payment-methods")
    CompletionStage<CbPaymentMethodListResponse> getPaymentMethods(@QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);

    @GET
    @Path("/payment-methods/{id}")
    CompletionStage<CbPaymentMethodResponse> getPaymentMethod(@PathParam("id") String id);

    @PUT
    @Path("/user")
    CompletionStage<CbUserResponse> updateUser(CbUserUpdateRequest u);

    @GET
    @Path("/accounts")
    CompletionStage<CbAccountListResponse> getAccounts(@QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);

    @GET
    @Path("/accounts/{id}")
    CompletionStage<CbAccountResponse> getAccount(@PathParam("id") String id);

    @PUT
    @Path("/accounts/{account}")
    CompletionStage<CbAccountResponse> updateAccountName(@PathParam("account") String account, CbAccountUpdateRequest req);

    @DELETE
    @Path("/accounts/{account}")
    CompletionStage<Response> deleteAccount(@PathParam("account") String account);

    @GET
    @Path("/accounts/{account}/addresses")
    CompletionStage<CbAddressListResponse> getAddresses(@PathParam("account") String account, @QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);

    @GET
    @Path("/accounts/{account}/addresses/{address}")
    CompletionStage<CbAddressResponse> getAddress(@PathParam("account") String account, @PathParam("address") String addressId);

    @POST
    @Path("/accounts/{account}/addresses")
    CompletionStage<CbAddressResponse> createAddress(@PathParam("account") String account, CbCreateAddressRequest request);

    @GET
    @Path("/accounts/{account}/addresses/{address}/transactions")
    CompletionStage<CbAddressTransactionListResponse> getTransactions(@PathParam("account") String account, @PathParam("address") String address,
                                                     @QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);
    @GET
    @Path("/time")
    CompletionStage<CbTimeResponse> getServerTime();

    @GET
    @Path("/prices/{ticker}/{type}")
    CompletionStage<CbPriceResponse> getPrice(@PathParam("ticker") String ticker, @PathParam("type") String type);

    @GET
    @Path("/prices/{ticker}/spot")
    CompletionStage<CbPriceResponse> getSpotPrice(@PathParam("ticker") String ticker, @QueryParam("date") String date);

    @GET
    @Path("/currencies")
    CompletionStage<CbCurrencyCodeListResponse> getCurrencyCodes();

    @GET
    @Path("/exchange-rates")
    CompletionStage<CbExchangeRateResponse> getExchangeRate(@QueryParam("currency") String currency);

    @GET
    @Path("/exchange-rates")
    CompletionStage<CbExchangeRateResponse> getExchangeRate();

    @POST
    @Path("/accounts/{account}/transactions")
    CompletionStage<CbAddressTransactionResponse> sendMoneyRequest(@PathParam("account") String account, CbMoneyRequest req);

    @GET
    @Path("/accounts/{account}/{side}")
    CompletionStage<CbTradeListResponse> getTrades(@PathParam("account") String account, @PathParam("side") String side,
                                  @QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);

    @GET
    @Path("/accounts/{account}/{side}/{id}")
    CompletionStage<CbTradeResponse> getTrade(@PathParam("account") String account, @PathParam("side")String side, @PathParam("id") String id);

    @GET
    @Path("/accounts/{account}/{type}")
    CompletionStage<CbCashTransactionListResponse> getCashTransactions(@PathParam("account") String account, @PathParam("type") String type,
                                                      @QueryParam("limit") int pageSize, @QueryParam("starting_after") String after);

    @GET
    @Path("/accounts/{account}/{type}/{id}")
    CompletionStage<CbCashTransactionResponse> getCashTransaction(@PathParam("account") String account,
                                                 @PathParam("type") String type, @PathParam("id") String id);

    @POST
    @Path("/accounts/{account}/{type}")
    CompletionStage<CbCashTransactionResponse> executeCashTransaction(@PathParam("type") String type, CbCashTransactionRequest req);

    @POST
    @Path("/accounts/{account}/{type}/{id}/commit")
    CompletionStage<CbCashTransactionResponse> commitCashTransaction(@PathParam("account") String account,
                                                    @PathParam("type") String type, @PathParam("id") String id);

    @POST
    @Path("/accounts/{account}/{side}")
    CompletionStage<CbTradeResponse> placeOrder(@PathParam("account") String account, @PathParam("type") String side, CbOrderRequest request);

    @POST
    @Path("/accounts/{account}/{side}/{id}/commit")
    CompletionStage<CbTradeResponse> commitOrder(@PathParam("account") String account, @PathParam("side") String side, @PathParam("id") String id);
}
