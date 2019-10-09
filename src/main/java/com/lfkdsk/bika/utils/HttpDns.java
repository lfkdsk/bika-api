package com.lfkdsk.bika.utils;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Dns;

public class HttpDns implements Dns {
    private static ArrayList<InetAddress> mResult = null;
    private String[] addresses = null;

    //修正bika登录bug
    private boolean isBika(String host) {
        return host.contains("picacomic.com");
    }

    public List<InetAddress> lookup(@NotNull String hostname) throws UnknownHostException {
        if (!isBika(hostname)) return Dns.SYSTEM.lookup(hostname);
        if (addresses == null)
            addresses = Constants.dns.toArray(new String[]{});
        //fix reload DNS Address,once load,everywhere can load.
        if (mResult != null) {
            return mResult;
        }
        try {
            ArrayList<InetAddress> result = new ArrayList<>();
            if (this.addresses.length <= 0) {
                mResult = new ArrayList<>();
            } else {
                for (String byName : this.addresses) {
                    result.add(InetAddress.getByName(byName));
                }
            }
            mResult = result;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return Dns.SYSTEM.lookup(hostname);
        }
    }
}