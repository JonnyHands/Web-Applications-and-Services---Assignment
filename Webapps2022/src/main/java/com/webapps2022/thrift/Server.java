package com.webapps2022.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 *
 * @author Jonny
 */
public class Server {

    /**
     *
     */
    public static TimeServerImpl handler;

    /**
     *
     */
    public static TimeServer.Processor processor;

    /**
     *
     */
    public static TServerTransport serverTransport;

    /**
     *
     * @param handler
     */
    public static void setHandler(TimeServerImpl handler) {
        Server.handler = handler;
    }

    /**
     *
     * @param processor
     */
    public static void setProcessor(TimeServer.Processor processor) {
        Server.processor = processor;
    }

    /**
     *
     * @param serverTransport
     */
    public static void setServerTransport(TServerTransport serverTransport) {
        Server.serverTransport = serverTransport;
    }

    /**
     *
     * @param server
     */
    public static void setServer(TServer server) {
        Server.server = server;
    }

    /**
     *
     * @return
     */
    public static TimeServerImpl getHandler() {
        return handler;
    }

    /**
     *
     * @return
     */
    public static TimeServer.Processor getProcessor() {
        return processor;
    }

    /**
     *
     * @return
     */
    public static TServerTransport getServerTransport() {
        return serverTransport;
    }

    /**
     *
     * @return
     */
    public static TServer getServer() {
        return server;
    }

    /**
     *
     */
    public static TServer server;

    /**
     *
     */
    public static void getTime() {
        try {
            handler = new TimeServerImpl();
            processor = new TimeServer.Processor(handler);

            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
            System.in.read();
            server.stop();
            
        } catch (Exception x) {
            System.err.println(x);
        }
    }

    /**
     *
     * @param processor
     */
    public static void simple(TimeServer.Processor processor) {
        try {
            serverTransport = new TServerSocket(9090);
            server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server in Thread " + Thread.currentThread().getId());
            server.serve();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
