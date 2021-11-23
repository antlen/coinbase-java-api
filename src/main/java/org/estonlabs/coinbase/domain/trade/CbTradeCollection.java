package org.estonlabs.coinbase.domain.trade;

import java.util.*;

/**
 * Takes N CbTrade or List<CbTrade> objects and returns as a unified collection
 */
public class CbTradeCollection {
    private final List<CbTrade> trades = new ArrayList<>();
    private final List<Collection<CbTrade>> groups = new ArrayList<>();

    public CbTradeCollection add(CbTrade t){
        trades.add(t);
        return this;
    }

    public CbTradeCollection add(Collection<CbTrade> t){
        groups.add(t);
        return this;
    }

    public Collection<CbTrade> toCollection(){
        TreeSet<CbTrade> res = new TreeSet<>(Comparator.comparing(CbTrade::getCreationDateTime));

        for(Collection<CbTrade> t : groups){
            res.addAll(t);
        }

        for(CbTrade t : trades){
            res.add(t);
        }
        return res;
    }
}
