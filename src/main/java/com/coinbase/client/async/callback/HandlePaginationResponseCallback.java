package com.coinbase.client.async.callback;

import com.coinbase.callback.PaginatedCollectionCallback;
import com.coinbase.callback.PaginatedResponseCallback;
import com.coinbase.client.api.request.PaginatedRequest;
import com.coinbase.domain.general.response.CbResponse;
import com.coinbase.domain.pagination.response.CbPaginatedResponse;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
 * internal base callback for handling pagination.
 *
 * @author antlen
 *
 * @param <RESPONSE>
 */
public abstract class HandlePaginationResponseCallback<RESPONSE extends CbResponse> implements PaginatedResponseCallback<RESPONSE> {

    @Override
    public final void pagedResults(RESPONSE response, PaginatedRequest<RESPONSE> next) {
        handlerResults(response, next !=  null);
        if(next != null){
            handleNext(next);
        }
    }

    protected abstract void handlerResults(RESPONSE response, boolean more);

    protected abstract void handleNext(PaginatedRequest<RESPONSE> next);
}
