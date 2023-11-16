package com.server.socket;

import com.handler.client.ClientHandler;
import com.handler.client.ClientHandlerFactory;
import com.handler.client.PrintLn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    ConcurrentHashMap<InputStream, ClientHandler> inputStreamOutputStreamHashMap = new ConcurrentHashMap<>();
    public Server(int serverPort, ClientHandlerFactory clientHandler){


        Thread socket=new Thread(()->{

            try {
                readFromSocket(serverPort,clientHandler);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        socket.start();
        Thread t=new Thread(()->{
            try {
                readFromClient(inputStreamOutputStreamHashMap);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();

    }

    private void readFromSocket(int serverPort,ClientHandlerFactory clientHandler) throws IOException, InterruptedException {
        ServerSocket serverSocket =new ServerSocket(serverPort);
        while (true){

            Socket s=serverSocket.accept();
            PrintLn printLn = new PrintLn() {
                @Override
                public void write(byte[] buff) throws IOException {
                    s.getOutputStream().write(buff);
                }

                @Override
                public void write(byte[] buff, int start, int len) throws IOException {
                    s.getOutputStream().write(buff,start,len);
                }

                @Override
                public void write(int b) throws IOException {
                    s.getOutputStream().write(b);
                }
            };

            inputStreamOutputStreamHashMap.put(s.getInputStream(),clientHandler.getClientHandler(printLn));
            Thread.sleep(1);
        }
    }

    private void readFromClient(ConcurrentHashMap<InputStream,ClientHandler> inputStreamOutputStreamHashMap) throws IOException, InterruptedException {
        while (true){
            for(InputStream is:inputStreamOutputStreamHashMap.keySet()){
                int k=0;
                while((k=is.available())!=0){
                    byte[] read = new byte[1024];
                    int f = is.read(read);
                    inputStreamOutputStreamHashMap.get(is).processMessage(read,f);                }
            }
            Thread.sleep(1);

        }
    }
}
