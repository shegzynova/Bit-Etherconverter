package com.example.segun.bit_etherconverter;

public class Currencies  {

    private String mCurr;

    private final double mBtc;

    private final double mEth;

    public Currencies(String curr, double btc, double eth) {
        mCurr = curr;
        mBtc = btc;
        mEth = eth;
    }

    public double getmBtc() { return mBtc; }

    public double getmEth() { return mEth; }

    public String getmCurr() { return mCurr; }

}

