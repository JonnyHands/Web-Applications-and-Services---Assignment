package com.webapps2022.thrift;


import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 *
 * @author Jonny
 */
public class TimeClient {

    /**
     *
     * @return
     */
    public static double getTime() {
        double result;
        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            TimeServer.Client client = new TimeServer.Client(protocol);
            result = client.time();
            System.out.println("RPC Call: " + result);
            transport.close();
            return result;
        } catch (TException x) {
            System.err.println(x);
            return 0;
        }       
    }
}
