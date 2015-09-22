/**
*	UDP Client Program
*	Connects to a UDP Server
*	Receives a line of input from the keyboard and sends it to the server
*	Receives a response from the server and displays it.
*
*	@author: Nicholas Vega
@	version: 2.1
*/

import java.io.*;
import java.net.*;

class Red {
    
    public static void main(String args[]) throws Exception
    {
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        
        DatagramSocket clientSocket = new DatagramSocket();
        
        InetAddress IPAddress = InetAddress.getByName("x");
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        

        String firstContact = "HELLO";
        int lastContact = -1;
        sendData = firstContact.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        
        String firstRawReceive = new String(receivePacket.getData());
        int firstReceive = Integer.parseInt(firstRawReceive);
        while(firstReceive == 100)
        {
            clientSocket.receive(receivePacket);
            firstRawReceive = new String(receivePacket.getData());
            firstReceive = Integer.parseInt(firstRawReceive);
            
        }
        if(firstReceive == 200)
        {
            while(firstReceive != lastContact)
            {
                String sentence = inFromUser.readLine();
                sendData = sentence.getBytes();
                DatagramPacket sendNewPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendNewPacket);
                
                DatagramPacket receiveNewPacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receiveNewPacket);
                firstRawReceive = new String(receiveNewPacket.getData());
                firstReceive = Integer.parseInt(firstRawReceive);
                System.out.println("FROM USER: " + firstRawReceive);
            }
            
            
        }
        
        clientSocket.close();
    }
}