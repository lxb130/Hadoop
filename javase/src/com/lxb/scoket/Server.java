package com.lxb.scoket;

/*
*�������˴���
*
*/
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
public class Server
{
    public static void main(String[] args)
    {
        new Server();
    }
 
    public Server()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8888);
            while (true)
            {
                // ������������Ϣ
                Socket s = ss.accept();
                InputStreamReader isr = new InputStreamReader(s
                        .getInputStream());
                BufferedReader in = new BufferedReader(isr);
                String line = in.readLine();
                System.out.println("Client���͵���Ϣ��:  " + line);
 
                // �ѳɹ���Ϣ�����ؿͻ���
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                out.println("�Ѿ����ܵ���Ϣ��!");
 
                out.close();
                in.close();
                isr.close();
                s.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
    }
}